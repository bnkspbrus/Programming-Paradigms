package search;

public class BinarySearchMissing {

    public static void main(final String[] args) {
        assert args.length >= 1;
        final int x = Integer.parseInt(args[0]);
        final int[] array = new int[args.length - 1];
        // args.length >= 1 && array.length >=0
        if (array.length == 0) {
            // args.lenght >= 1 && array.length == 0 - array is empty => insertion point == 0
            System.out.println(-1);
            // "-1"
            return;
        }
        // args.length >= 2 || array.length >= 1
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(args[i + 1]);
        }
//        int index = binSearchRecursive(array, 0, array.length - 1, x);
        int index = binSearchIterative(array,  x);

        System.out.println(index);
    }

    static int binSearchIterative(final int[] array, final int x) {
        // Pred: array.length >= 1 && array contains integers && array sorted descending
        // Post: res == min{i∣a[i]⩽x} || insertion point
        assert array.length >= 1;
        int left = 0, right = array.length - 1;
        // left == 0 && right >= 0
        if (array[right] > x) {
            // for any i : array[i] > x
            return -array.length - 1;
        }
        int res = (left + right) / 2;
        // Inv: left <= res <= right
        while (left < right /* left < right */) {
            // Cond: left < right
            if (array[res] <= x) {
                // array[res] <= x
                right = res;
                // left <= res = res -- Inv
                // right decrease
            } else {
                // array[res] > x
                left = res + 1; // Inv, because left + 1 <= right (Cond) --> res < right, because round flore for divide integers
                // left increase
            }
            res = (left + right) / 2;
            // left <= res <= right --> Inv
        }
        // Inv && !Cond
        // left == right == res -- min{i∣a[i]⩽x}
        if (array[res] < x) {
            res = -res - 1;
        }
        return res;
    }

    static int binSearchRecursive(final int[] array, int left, int right, final int x) {
        // Pred: array.length >= 1 && left <= right && array contains integers && array sorted descending
        // Post: res == min{i∣a[i]⩽x} || insertion point
        assert array.length >= 1 && left <= right;
        if (array[right] > x) {
            // for any i : array[i] > x
            return -array.length - 1;
        }
        int res = left;
        // Inv: left <= res <=right --> array[left] >= array[res] >= array[left]
        if (left < right) { // Cond: left < right
            res = (left + right) / 2;
            // left <= res <= right --> Inv
            if (array[res] <= x) {
                right = res;
                // left <= res = res -- Inv
                // right decrease
            } else {
                // array[res] > x
                left = res + 1; // Inv, because left + 1 <= right (Cond) --> res < right, because round flore for divide integers
                // left increase
            }
            res = binSearchRecursive(array, left, right, x);
        }
        // Inv && !Cond
        // left == right == res
        if (res >= 0 && array[res] < x) {
            res = -res - 1;
        }
        return res;
    }
}

