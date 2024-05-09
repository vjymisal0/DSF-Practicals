import java.util.Scanner;

class InternHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of students: ");
        int n = sc.nextInt();
        MaxHeap heap = new MaxHeap(n);
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the name of student " + (i + 1) + ": ");
            String name = sc.next();
            System.out.println("Enter the rank of student " + (i + 1) + ": ");
            int rank = sc.nextInt();
            heap.insert(name, rank);
        }
        System.out.println("The next topper student for internship is: ");
        heap.peek();
        System.out.println("The students sorted in ascending order of grades are: ");
        heap.sort();
        sc.close();
    }
}

class MaxHeap {
    private Student[] heap;
    private int size;
    private int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        heap = new Student[this.maxSize + 1];
        heap[0] = new Student("", Integer.MAX_VALUE);
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

    private void swap(int fpos, int spos) {
        Student tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (heap[pos].rank < heap[leftChild(pos)].rank ||
                    heap[pos].rank < heap[rightChild(pos)].rank) {
                if (heap[leftChild(pos)].rank > heap[rightChild(pos)].rank) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(String name, int rank) {
        heap[++size] = new Student(name, rank);
        int current = size;
        while (heap[current].rank > heap[parent(current)].rank) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void peek() {
        System.out.println(heap[1].name);
    }

    public void sort() {
        for (int i = size / 2; i >= 1; i--) {
            maxHeapify(i);
            // System.out.println("first for loop: "+heap[i].name + " " + heap[i].rank);
        }
        for (int i = size; i > 1; i--) {
            swap(1, i);
            maxHeapify(1);
            // System.out.println("second for loop: "+heap[i].name + " " + heap[i].rank);

        }
        for (int i = 1; i <= size; i++) {
            System.out.println(heap[i].name + " " + heap[i].rank);

        }

    }

}

class Student {
    String name;
    int rank;

    public Student(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }
}