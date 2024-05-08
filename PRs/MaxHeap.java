import java.util.*;

class Student {
    String name;
    int rank;

    public Student(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public String toString() {
        return name + " (Rank: " + rank + ")";
    }
}

public class MaxHeap {
    private List<Student> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    private void swap(int i, int j) {
        Student temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap.get(index).rank > heap.get(parent).rank) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    public void insert(Student student) {
        heap.add(student);
        heapifyUp(heap.size() - 1);
    }

    private void heapifyDown(int index) {
        int leftChild, rightChild, maxIndex;
        while (true) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            maxIndex = index;

            if (leftChild < heap.size() && heap.get(leftChild).rank > heap.get(maxIndex).rank)
                maxIndex = leftChild;

            if (rightChild < heap.size() && heap.get(rightChild).rank > heap.get(maxIndex).rank)
                maxIndex = rightChild;

            if (maxIndex == index)
                break;

            swap(index, maxIndex);
            index = maxIndex;
        }
    }

    public Student extractMax() {
        if (heap.isEmpty())
            return null;

        Student max = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);

        return max;
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        // Example usage
        maxHeap.insert(new Student("Alice", 90));
        maxHeap.insert(new Student("Bob", 85));
        maxHeap.insert(new Student("Charlie", 95));
        maxHeap.insert(new Student("David", 88));

        System.out.println("Students in max heap order:");
        while (!maxHeap.heap.isEmpty()) {
            System.out.println(maxHeap.extractMax());
        }
    }
}
