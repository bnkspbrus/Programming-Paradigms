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
        int index = binSearch(array, x);
        if (index == array.length || array[index] < x) {
            index = -index - 1;
        }

        System.out.println(index);
    }

    static int binSearch(final int[] array, final int x) {
        // Pred: array.length >= 1
        // Post: res == min{i∣a[i]⩽x} || insertion point
        assert array.length >= 1;
        double left = 0, right = array.length - 1;
        // left == 0 && right >= 0
        if (array[(int) right] > x) {
            // for any i : array[i] > x
            return array.length;
        }
        int res = (int) Math.floor((left + right) / 2);
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
            res = (int) Math.floor((left + right) / 2);
            // res decreases, when Cond
        }
        // Inv && !Cond
        // left == right == res -- min{i∣a[i]⩽x}
        return res;
    }

    static int binSearch(final int[] array, double left, double right, final int x) {
        // Pred: array.length >= 1 && left <= right
        // Post: res == min{i∣a[i]⩽x} || insertion point
        assert array.length >= 1 && left <= right;
        if (array[(int) right] > x) {
            // for any i : array[i] > x
            return array.length;
        }
        int res = (int) left;
        if (left != right) {
            int mid = (int) Math.floor((left + right) / 2);
            if (array[mid] <= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
            res = binSearch(array, left, right, x);
        }
        // Inv && !Cond
        // left == right == res
        return res;
    }
}

