class MergeSort {
    long comparisons = 0;
    int[] buffer;
    int cutoff = 16;
    int maxDepth = 0;

    void sort(int[] a, int l, int r, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        if (r - l + 1 <= cutoff) {
            insertionSort(a, l, r);
            return;
        }

        if (l < r) {
            int m = (l + r) / 2;
            sort(a, l, m, depth + 1);
            sort(a, m + 1, r, depth + 1);
            merge(a, l, m, r);
        }
    }

    void merge(int[] a, int l, int m, int r) {
        int i = l, j = m + 1, k = l;

        while (i <= m && j <= r) {
            comparisons++;
            if (a[i] <= a[j]) buffer[k++] = a[i++];
            else buffer[k++] = a[j++];
        }

        while (i <= m) buffer[k++] = a[i++];
        while (j <= r) buffer[k++] = a[j++];

        for (i = l; i <= r; i++) a[i] = buffer[i];
    }

    void insertionSort(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = a[i], j = i - 1;
            while (j >= l && a[j] > key) {
                comparisons++;
                a[j + 1] = a[j];
                j--;
            }
            if (j >= l) comparisons++;
            a[j + 1] = key;
        }
    }

    void sort(int[] a) {
        buffer = new int[a.length];
        sort(a, 0, a.length - 1, 1);
    }
}
