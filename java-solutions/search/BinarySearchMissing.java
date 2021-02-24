package search;

public class BinarySearchMissing {

    public static void main(final String[] args) {
        // Pred: args.length > 0 && элементы args[] целые числа, заданные строками
        // Post: index of the search key, if it is contained in the array || (-(insertion point) - 1)
        // Integer.parseInt() Pred: integer in string Post: integer
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
        int index = binSearchRecursive(array, 0, array.length - 1, x);

        System.out.println(index);
    }

    static int binSearchIterative(final int[] array, final int x) {
        // Pred: array.length >= 1
        // Post: res == min{i∣a[i]⩽x} || insertion point
        assert array.length >= 1;
        int left = 0, right = array.length - 1;
        // left == 0 && right >= 0
        if (array[(int) right] > x) {
            // for any i : array[i] > x
            return -array.length - 1;
        }
        int res = (left + right) / 2;
        // Inv: array[left] <= res <= array[right]
        while (left != right /* left < right */) {
            // Cond: left < right
            if (array[res] <= x) {
                // array[res] <= x
                right = res; // right decreases
                // left <= res <= right
                // array[left] <= x
            } else {
                // array[left] > x
                left = res + 1; // left increases
                // left <= res <= right

            }
            //res = (int) Math.floor((left + right) / 2);
            // res decreases, when Cond
            res = (left + right) / 2;
        }
        // Inv && !Cond
        // left == right == res -- min{i∣a[i]⩽x}
        if (array[res] < x) {
            res = -res - 1;
        }
        return res;
    }

    static int binSearchRecursive(final int[] array, int left, int right, final int x) {
        // Pred: array.length >= 1 && left <= right
        // Post: res == min{i∣a[i]⩽x} || insertion point
        assert array.length >= 1 && left <= right;
        if (array[(int) right] > x) {
            // for any i : array[i] > x
            return -array.length - 1;
        }
        int res = left;
        if (left != right) {
            int mid = (left + right) / 2;
            if (array[mid] <= x) {
                right = mid;
            } else {
                left = mid + 1;
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

