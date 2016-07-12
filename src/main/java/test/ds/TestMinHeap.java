package test.ds;

import ds.MinHeap;

public class TestMinHeap {
    public static void main(String[] a) {
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
    }
}
