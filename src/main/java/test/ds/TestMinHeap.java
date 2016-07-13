package test.ds;

import ds.MinHeap;

public class TestMinHeap {
    public static void main(String[] a) {

        // 1. Test with Heap structure
        System.out.println("Test 1 - Heap structure - increasing");
        MinHeap minHeap = new MinHeap(20);

        minHeap.add(32);
        minHeap.add(41);
        minHeap.add(87);
        minHeap.add(43);
        minHeap.add(90);
        minHeap.add(14);
        minHeap.add(53);
        minHeap.add(23);
        minHeap.add(64);
        minHeap.add(5);
        minHeap.add(50);

        while (minHeap.hasNext()) {
            System.out.println(minHeap.getMin());
        }

        // 2. Test with build-min-heap method
        System.out.println("Test 2 - Heapsort - decreasing");
        int[] l = new int[]{0, 32, 41, 87, 43, 90, 14, 53, 23, 64, 5, 50}; // First item wasted
        buildMinHeap(l);
        for (int len = l.length; len > 2; len--) {
            // Swapping - min to the bottom
            int aux = l[len - 1];
            l[len - 1] = l[1];
            l[1] = aux;
            minHeapify(l, 1, len - 1);
        }
        for (int i = 1; i < l.length; i++) System.out.println(l[i]);

    }

    private static void buildMinHeap(int[] list) {
        for (int i = list.length / 2; i > 0; i--) {
            minHeapify(list, i, list.length);
        }
    }

    // Precondition: tree at i * 2 and tree at i * 2 + 1 are MinHeaps
    private static void minHeapify(int[] list, int i, int len) {
        int left_i  = i * 2;
        int right_i = i * 2 + 1;
        int min_i;

        if (left_i < len && list[left_i] < list[i]) {
            min_i = left_i;
        } else {
            min_i = i;
        }

        if (right_i < len && list[right_i] < list[min_i]) {
            min_i = right_i;
        }

        if (min_i != i) {
            // Swap
            int aux = list[min_i];
            list[min_i] = list[i];
            list[i] = aux;
            minHeapify(list, min_i, len);
        }
    }
}
