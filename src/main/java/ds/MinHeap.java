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

    // O(log2 n)
    private void reorder() {
        int i = 1;
        while (i < lastElementIndex) {
            int curr = storage[i];
            // Has right child
            if ((i * 2 + 1) <= lastElementIndex) {
                int left = storage[i * 2];
                int right = storage[i * 2 + 1];
                if (left < curr && left < right) {
                    // Min at left
                    storage[i] = left;
                    storage[i * 2] = curr;
                    i = i * 2;
                } else if (right < curr && right < left) {
                    // Min at right
                    storage[i] = right;
                    storage[i * 2 + 1] = curr;
                    i = i * 2 + 1;
                } else break; // No swapping - done
            } else if (i * 2 <= lastElementIndex && storage[i * 2] < curr) {
                // Min at left - last element
                storage[i] = storage[i * 2];
                storage[i * 2] = curr;
                break; // No next - done
            } else break; // No swapping - done
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
