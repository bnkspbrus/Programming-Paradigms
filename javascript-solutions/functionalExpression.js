let binary = func => (left, right) => (x, y, z) => {
    return func(left(x, y, z), right(x, y, z));
}

let unary = func => left => (x, y, z) => {
    return func(left(x, y, z));
}

let cnst = value => (x, y, z) => value;

let variable = name => (x, y, z) => {
    switch (name) {
        case "x":
            return x;
        case "y":
            return y;
        default:
            return z;
    }
}

let negate = unary(a => -a);

let divide = binary((a, b) => a / b);

let multiply = binary((a, b) => a * b);

let subtract = binary((a, b) => a - b);

let add = binary((a, b) => a + b);

