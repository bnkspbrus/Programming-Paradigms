package search;

public class BinarySearchMissing {
    public static void main(final String[] args) {
        // Pred: args.length > 0 && элементы args[] целые числа, заданные строками
        // Post: минимальное значение индекса i, при котором a[i] <= x || "0", если массив пуст
        // Integer.parseInt() Pred: integer in string Post: integer
        assert args.length >= 1;
        final int x = Integer.parseInt(args[0]);
        final int[] array = new int[args.length - 1];
        // args.length >= 1 && array.length >=0
        if (array.length == 0) {
            // args.lenght >= 1 && array.length == 0 - array is empty
            System.out.println(0);
            // "0"
            return;
        }
        // args.length >= 2 || array.length >= 1
        for (int i = 1; i < args.length; i++) {
            array[args.length - i - 1] = Integer.parseInt(args[i]);
        }
        System.out.println(array.length - 1 - binSearch(array, 0, array.length - 1, x));
    }

    static int binSearch(final int[] array, final int x) {
        // Pred: array.length >= 1
        // Post: res : array[res] == x || индекс вставки
        assert array.length >= 1;
        double left = 0, right = array.length - 1;
        // left == 0 && right >= 0
        if (array[(int) left] > x) {
            // for any i : array[i] > x
            return -1;
        }
        int res = (int) Math.ceil((left + right) / 2);
        // Inv: array[left] <= res <= array[right]
        while (left != right /* left < right */) {
            // Cond: left < right
            if (array[res] <= x) {
                // array[res] <= x
                left = res; // res increases --> left increases
                // left <= res <= right
                // array[left] <= x
            } else {
                // array[left] > x
                right = res - 1; // res decreases
                // left <= res <= right

            }
            res = (int) Math.ceil((left + right) / 2);
            // res increases, when Cond
        }
        // Inv
        // left == right == res -- max{i∣a[i]⩽x}
        if (array[res] != x) {
            res++;
        }
        return res;
    }

    static int binSearch(final int[] array, double left, double right, final int x) {
        // Pred: array.length >= 1 && left <= right
        // Post: res == max{i∣a[i]⩽x} || -1 if for any i : array[i] > x
        assert array.length >= 1 && left <= right;
        if (array[(int) left] > x) {
            // for any i : array[i] > x
            return -1;
        }
        int res = (int) left;
        if (left != right) {
            int mid = (int) Math.ceil((left + right) / 2);
            if (array[mid] <= x) {
                left = mid;
            } else {
                right = mid - 1;
            }
            res = binSearch(array, left, right, x);
        }
        // Inv && !Cond
        // left == right == res
        if (array[res] != x) {
            res++;
        }
        return res;
    }
}
