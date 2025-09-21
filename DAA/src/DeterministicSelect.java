class DeterministicSelect {
    long comparisons = 0;
    int maxDepth = 0;

    int select(int[] arr, int left, int right, int k, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        int length = pivotIndex - left + 1;
        if (k == length) return arr[pivotIndex];
        else if (k < length) return select(arr, left, pivotIndex - 1, k, depth + 1);
        else return select(arr, pivotIndex + 1, right, k - length, depth + 1);
    }

    int medianOfMedians(int[] arr, int left, int right) {
        if (right - left < 5) {
            insertionSort(arr, left, right);
            return arr[(left + right) / 2];
        }

        int subRight = left - 1;
        for (int i = left; i <= right; i += 5) {
            int subEnd = Math.min(i + 4, right);
            insertionSort(arr, i, subEnd);
            subRight++;
            swap(arr, subRight, i + (subEnd - i) / 2);
        }
        return medianOfMedians(arr, left, subRight);
    }

    int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) {
                comparisons++;
                left++;
            }
            comparisons++;
            while (arr[right] > pivot) {
                comparisons++;
                right--;
            }
            comparisons++;
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left - 1;
    }

    void insertionSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= l && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
