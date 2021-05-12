;(ns expression)

(defn variable [name]
  (fn [vals]
    (vals name)))

(defn constant [val]
  (constantly val))

(defn operation [f]
  (fn [& ops]
    (fn [vals]
      (apply f (map (fn [op] (op vals)) ops)))))

(defn double-div [a b] (/ a (double b)))

(def add (operation +))

(def subtract (operation -))

(def multiply (operation *))

(def divide (operation double-div))

(def negate (operation (fn [a] (- a))))

(def sin (operation (fn [a] (Math/sin a))))

(def cos (operation (fn [a] (Math/cos a))))

(def fun-arg #{+ - *})

(defn parseFunction [expr]
  (let [tokens (read-string expr)]
    (letfn [(filter [token]
              (cond
                (list? token) (letfn [(action [f] (apply f (map filter (rest token))))]
                                (binding [*ns* (find-ns 'user)]
                                  (let [fun (eval (first token))]
                                    (cond
                                      (= fun /) (action divide)
                                      (contains? fun-arg fun) (action (operation fun))
                                      :else (action fun)))))
                (symbol? token) (variable (str token))
                :else (constant token)))]
      (filter tokens))))

(defn proto-get [obj key]                                   ;second set of arguments
  (if (contains? obj key) (obj key)
    (recur (obj :prototype) key)))

(defn proto-call [this key & args]
  (apply (proto-get this key) this args))

(defn field [key]
  (fn [this]
    (proto-get this key)))

(def _name (field :name))

(defn method [key]
  (fn [this & args]
    (apply proto-call this key args)))

(def evaluate (method :evaluate))

(def toString (method :toString))

(defn VCtor [this name]
  (assoc this
    :name name))

(def Constant)

(def VProto
  {:evaluate       (fn [this vars]
                     (let [id (str (Character/toLowerCase (first (_name this))))]
                       (vars id)))
   :toString       _name
   :diff           (fn [this var]
                     (if (= var (_name this)) (Constant 1)
                       (Constant 0)))
   :toStringSuffix toString
   })

(defn constructor [ctor prototype]
  (fn [& args]
    (apply ctor {:prototype prototype} args)))

(def Variable (constructor VCtor VProto))

(defn CCtor [this name]
  (assoc this
    :value name))

(def value (field :value))

(def CProto
  {:evaluate       (fn [this _] (value this))
   :toString       (fn [this] (format "%.1f" (value this))) ;(format "%.1f" (value this)))
   :diff           (fn [_ _] (Constant 0))
   :toStringSuffix toString
   })

(def Constant (constructor CCtor CProto))

(def fun (field :fun))

(def operands (field :operands))

(def sign (field :sign))

(def diff-fun (field :diff-fun))

(def diff (method :diff))

(def toStringSuffix (method :toStringSuffix))

(defn OPCtor [this fun sign diff & operands]
  (assoc this
    :operands operands
    :diff-fun diff
    :fun fun
    :sign sign))

(def OPProto
  {:evaluate       (fn [this vars]
                     (apply (fun this) (mapv #(evaluate % vars) (operands this))))
   :toString       (fn [this]
                     (str "(" (sign this) " " (clojure.string/join " " (mapv #(toString %) (operands this))) ")"))
   :diff           (fn [this var]
                     ((diff-fun this) (operands this) var))
   :toStringSuffix (fn [this]
                     (str "(" (clojure.string/join " " (mapv #(toStringSuffix %) (operands this))) " " (sign this) ")"))
   })

(def Operation (constructor OPCtor OPProto))

(defn create-op [fun sign diff-fun]
  (partial Operation fun sign diff-fun))

(def Negate)

(defn diff-neg [op var]
  (Negate (diff (first op) var)))

(def Negate (create-op - "negate" diff-neg))

(def Add)

(defn diff-add [ops var]
  (apply Add (mapv #(diff % var) ops)))

(def Add (create-op + "+" diff-add))

(def Subtract)

(defn sub-diff [ops var]
  (apply Subtract (mapv #(diff % var) ops)))

(def Subtract (create-op - "-" sub-diff))

(def Multiply)

(defn mul-diff [ops var]
  (letfn [(app-fun [a b] (Add (Multiply (diff a var) b) (Multiply a (diff b var))))]
    (apply app-fun ops)))

(def Multiply (create-op * "*" mul-diff))

(def Divide)

(defn div-diff [ops var]
  (letfn [(app-fun [a b] (Divide (Subtract (Multiply (diff a var) b) (Multiply a (diff b var))) (Multiply b b)))]
    (apply app-fun ops)))

(def Divide (create-op double-div "/" div-diff))

(def Cos)
(def Sin)

(defn sin-diff [op var]
  (Multiply (Cos (first op)) (diff (first op) var)))

(def Sin (create-op #(Math/sin %) "sin" sin-diff))

(defn cos-diff [op var]
  (Multiply (Negate (Multiply (Sin (first op)) (diff (first op) var)))))

(def Cos (create-op #(Math/cos %) "cos" cos-diff))

(def get-fun
  {'+      Add
   '-      Subtract
   'negate Negate
   '*      Multiply
   '/      Divide
   'sin    Sin
   'cos    Cos
   })


(defn parseObject [expr]
  (let [tokens (read-string expr)]
    (letfn [(filter [token]
              (cond
                (list? token) (letfn [(action [obj] (apply obj (map filter (rest token))))]
                                (action (get-fun (first token))))
                (symbol? token) (Variable (str token))
                :else (Constant token)))]
      (filter tokens))))

(defn -return [value tail] {:value value :tail tail})

(def -valid? boolean)

(def -value :value)

(def -tail :tail)

(defn -show [result]
  (if (-valid? result) (str "-> " (pr-str (-value result)) " | " (pr-str (apply str (-tail result))))
    "!"))

(defn tabulate [parser inputs]
  (run! (fn [input] (printf "    %-10s %s\n" (pr-str input) (-show (parser input)))) inputs))

(defn _empty [value] (partial -return value))

(defn _char [p]
  (fn [[c & cs]]
    (if (and c (p c))
      (-return c cs))))

(defn _map [f result]
  (if (-valid? result)
    (-return (f (-value result)) (-tail result))))

(defn _combine [f a b]
  (fn [input]
    (let [ar ((force a) input)]
      (if (-valid? ar)
        (_map (partial f (-value ar))
              ((force b) (-tail ar)))))))

(defn _either [a b]
  (fn [input]
    (let [ar ((force a) input)]
      (if (-valid? ar)
        ar
        ((force b) input)))))

(defn _parser [p]
  (let [pp (_combine (fn [v _] v) p (_char #{\u0000}))]
    (fn [input] (-value (pp (str input \u0000))))))

(defn +char [chars]
  (_char (set chars)))

(defn +char-not [chars]
  (_char (comp not (set chars))))

(defn +map [f parser]                                       ;expected inputs
  (comp (partial _map f) parser))

(def +parser _parser)

(def +ignore                                                ; [parser]
  (partial +map (constantly 'ignore)))

(defn iconj [coll value]
  (if (= value 'ignore)
    coll
    (conj coll value)))

(defn +seq [& ps]                                           ; put in vector
  (reduce (partial _combine iconj) (_empty []) ps))         ; return vector

(defn +seqf [f & ps]
  (+map (partial apply f) (apply +seq ps)))                 ; return

(defn +seqn [n & ps]
  (apply +seqf #(nth %& n) ps))

(defn +or [p & ps]
  (reduce _either p ps))

(defn +opt [p]
  (+or p (_empty nil)))

(defn +star [p]
  (letfn [(rec [] (+or (+seqf cons p (delay (rec))) (_empty ())))] ; return list
    (rec)))

(defn +plus [p]                                             ; p -- parser
  (+seqf cons p (+star p)))                                 ; return list

(defn +str [p] (+map (partial apply str) p))

(def *all-chars (mapv char (range 0 128)))
;(println (str *all-chars))
(def *letter (+char (apply str (filter #(Character/isLetter %) *all-chars))))
(def *digit (+char (apply str (filter #(Character/isDigit %) *all-chars))))
(def *space (+char (apply str (filter #(Character/isWhitespace %) *all-chars))))
(def *ws (+ignore (+star *space)))

(def *identifier (+str (+seqf cons *letter (+star (+or *letter *digit)))))

;=======================================================================================================================

(def *number (+map read-string (+str (+seqf concat
                                            (+map list (+opt (+char "-")))
                                            (+plus *digit)
                                            (+opt (+seqf cons (+char ".") (+plus *digit)))))))
(def *const (+map Constant *number))

(def *variable (+map Variable (+str (+plus (+char "xyzXYZ")))))

(def *fun (+map (comp get-fun symbol) (+str (+plus (+or (+char "+-*/") *identifier)))))

(def *value)

(def *operation (+seqf (fn [_ ops fun _] (apply fun ops)) (+char "(") *ws
                       (+plus (+seqn 0 (delay *value) *ws)) *fun *ws (+char ")")))

(def *value (+or *const *variable *operation))

(def parseObjectSuffix (+parser (+seqn 0 *ws *value *ws)))
































