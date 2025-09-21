import java.util.Random;

class QuickSort {
    long comparisons = 0;
    int maxDepth = 0;
    Random rand = new Random();

    void sort(int[] a, int low, int high, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        while (low < high) {
            int pi = randomizedPartition(a, low, high);

            if (pi - low < high - pi) {
                sort(a, low, pi - 1, depth + 1);
                low = pi + 1; // хвостовая рекурсия
            } else {
                sort(a, pi + 1, high, depth + 1);
                high = pi - 1;
            }
        }
    }

    int randomizedPartition(int[] a, int low, int high) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(a, pivotIndex, high);
        return partition(a, low, high);
    }

    int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons++;
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    void sort(int[] a) {
        sort(a, 0, a.length - 1, 1);
    }
}
