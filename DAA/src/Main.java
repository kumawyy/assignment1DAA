import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000};
        Random rand = new Random();

        System.out.printf("%-12s %-10s %-15s %-15s %-15s\n",
                "Алгоритм", "Размер n", "Время (нс)", "Сравнения", "Глубина");

        for (int n : sizes) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000);

            MergeSort ms = new MergeSort();
            int[] arr1 = arr.clone();
            long start = System.nanoTime();
            ms.sort(arr1);
            long end = System.nanoTime();
            System.out.printf("%-12s %-10d %-15d %-15d %-15d\n",
                    "MergeSort", n, (end - start), ms.comparisons, ms.maxDepth);

            QuickSort qs = new QuickSort();
            int[] arr2 = arr.clone();
            start = System.nanoTime();
            qs.sort(arr2);
            end = System.nanoTime();
            System.out.printf("%-12s %-10d %-15d %-15d %-15d\n",
                    "QuickSort", n, (end - start), qs.comparisons, qs.maxDepth);

            DeterministicSelect dsel = new DeterministicSelect();
            int[] arr3 = arr.clone();
            int k = n / 2;
            start = System.nanoTime();
            int kth = dsel.select(arr3, 0, arr3.length - 1, k, 1);
            end = System.nanoTime();
            System.out.printf("%-12s %-10d %-15d %-15s %-15d\n",
                    "Select", n, (end - start),
                    "- (" + k + "-й=" + kth + ")", dsel.maxDepth);

            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(rand.nextInt(1000), rand.nextInt(1000));
            }
            ClosestPair cp = new ClosestPair();
            start = System.nanoTime();
            double minDist = cp.closest(points, 1);
            end = System.nanoTime();
            System.out.printf("%-12s %-10d %-15d %-15s %-15d\n",
                    "ClosestPair", n, (end - start),
                    "- (dist=" + String.format("%.2f", minDist) + ")", cp.maxDepth);
        }
    }
}
