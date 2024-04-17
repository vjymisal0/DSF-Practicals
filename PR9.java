import java.util.*;

// Define a Student class to hold student data
class Student {
    String name;
    int rank;
    double grade;

    // Constructor
    public Student(String name, int rank, double grade) {
        this.name = name;
        this.rank = rank;
        this.grade = grade;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public double getGrade() {
        return grade;
    }
}

// MaxHeap class to store students based on their ranks
class MaxHeap {
    private List<Student> heap;

    // Constructor
    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Add a student to the heap
    public void add(Student student) {
        heap.add(student);
        heapifyUp(heap.size() - 1);
    }

    // Perform heapify up operation
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index).getRank() > heap.get(parentIndex).getRank()) {
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // Remove and return the top student (topper)
    public Student removeTop() {
        if (heap.isEmpty())
            return null;
        Student top = heap.get(0);
        Collections.swap(heap, 0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return top;
    }

    // Perform heapify down operation
    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largest = index;

        if (leftChild < heap.size() && heap.get(leftChild).getRank() > heap.get(largest).getRank()) {
            largest = leftChild;
        }

        if (rightChild < heap.size() && heap.get(rightChild).getRank() > heap.get(largest).getRank()) {
            largest = rightChild;
        }

        if (largest != index) {
            Collections.swap(heap, index, largest);
            heapifyDown(largest);
        }
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}

public class PR9 {
    public static void main(String[] args) {
        // Create a sample list of students
        List<Student> students = Arrays.asList(
                new Student("Alice", 1, 88.5),
                new Student("Bob", 3, 75.2),
                new Student("Charlie", 2, 92.0),
                new Student("David", 4, 80.8));

        // Task a) Create max heap to find next topper
        MaxHeap maxHeap = new MaxHeap();
        for (Student student : students) {
            maxHeap.add(student);
        }
        System.out.println("Next topper for internship: " + maxHeap.removeTop().getName());

        // Task b) Sort student data in ascending order of grades
        Collections.sort(students, Comparator.comparingDouble(Student::getGrade));
        System.out.println("\nStudents sorted in ascending order of grades:");
        for (Student student : students) {
            System.out.println(student.getName() + " - Grade: " + student.getGrade());
        }
    }
}
