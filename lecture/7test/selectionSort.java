public class selectionSort {
    /* Selection Sort */
    public static void sort(String[] x){
        sortHelper(x, 0);
    }

    private static void sortHelper(String[] x, int start){
        if (start == x.length) return;
        int smallestIndex = findSmallest(x, start);
        swap(x, start, smallestIndex);
        sortHelper(x, start + 1);
    }

    public static void swap(String[] x, int a, int b){
        String tmp = x[a];
        x[a] = x[b];
        x[b] = tmp;
    }

    public static int findSmallest(String[] x, int start){
        int tmp = start;
        for (int i = start; i < x.length; i++) {
            if (x[tmp].compareTo(x[i]) > 0) tmp = i;
        }
        return tmp;
    }
}
