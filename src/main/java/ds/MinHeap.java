package ds;

public class MinHeap {
    private int[] storage;
    private int lastElementIndex;

    public MinHeap(int capacity) {
        storage = new int[capacity + 1];
        lastElementIndex = 0;
    }

    public void add(int value) {
        lastElementIndex++;
        storage[lastElementIndex] = value;
        heapify();
    }

    public boolean hasNext() {
        return lastElementIndex > 0;
    }

    public int getMin() {
        int i = lastElementIndex;
        if (i < 1) return 0; /* fail */
        int min = storage[1];

        // Last element to new root - needs reorder
        storage[1] = storage[lastElementIndex];
        lastElementIndex--;

        reorder();

        return min;
    }

    // O(log2 n) - because i --> i / 2 --> i / 4 --> ..
    // Precondition: left and right trees are MinHeaps
    private void reorder() {
        int i = 1;
        while (i < lastElementIndex) {
            int left_i = i << 1;
            int right_i = i << 1 + 1;
            int min_i;

            if (left_i <= lastElementIndex && storage[left_i] < storage[i]) {
                min_i = left_i;
            } else {
                min_i = i;
            }

            if (right_i <= lastElementIndex && storage[right_i] < storage[min_i]) {
                min_i = right_i;
            }

            // If it's unordered
            if (min_i != i) {
                // swap!
                int aux = storage[i];
                storage[i] = storage[min_i];
                storage[min_i] = aux;

                i = min_i;
            } else {
                break;
            }
        }
    }

    // O(log2 n)
    private void heapify() {
        int i = lastElementIndex;

        if (i < 1) return;

        int parent = storage[lastElementIndex / 2];
        int curr = storage[lastElementIndex];
        while (curr < parent && i > 1) {
            // Swapping
            int aux = storage[i];
            storage[i] = storage[i / 2];
            storage[i / 2] = aux;

            // Moving up
            i = i / 2;
            curr = storage[i];
            parent = storage[i / 2];
        }
    }
}
