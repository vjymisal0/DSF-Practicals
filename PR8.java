import java.util.*;

class Student {
    String name;
    int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }
}

class ChainingWithoutReplacement {
    private LinkedList<Student>[] hashTable;
    private int size;

    public ChainingWithoutReplacement(int size) {
        this.size = size;
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    public int hashFunction(int key) {
        return key % size;
    }

    public void insert(Student student) {
        int index = hashFunction(student.getRollNumber());
        hashTable[index].add(student);
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]");
            for (Student student : hashTable[i]) {
                System.out.print(" -> " + student.getName());
            }
            System.out.println();
        }
    }
}

class ChainingWithReplacement {
    private Student[] hashTable;
    private int size;

    public ChainingWithReplacement(int size) {
        this.size = size;
        hashTable = new Student[size];
    }

    public int hashFunction(int key) {
        return key % size;
    }

    public void insert(Student student) {
        int index = hashFunction(student.getRollNumber());
        if (hashTable[index] == null) {
            hashTable[index] = student;
        } else {
            for (int i = (index + 1) % size; i != index; i = (i + 1) % size) {
                if (hashTable[i] == null) {
                    hashTable[i] = student;
                    return;
                }
            }
            System.out.println("HashTable is full. Unable to insert.");
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            if (hashTable[i] != null) {
                System.out.println("[" + i + "] " + hashTable[i].getName());
            }
        }
    }
}

public class PR8 {
    public static void main(String[] args) {
        ChainingWithoutReplacement chainingWithoutReplacement = new ChainingWithoutReplacement(10);
        chainingWithoutReplacement.insert(new Student("Alice", 101));
        chainingWithoutReplacement.insert(new Student("Bob", 102));
        chainingWithoutReplacement.insert(new Student("Charlie", 111));
        chainingWithoutReplacement.display();

        ChainingWithReplacement chainingWithReplacement = new ChainingWithReplacement(10);
        chainingWithReplacement.insert(new Student("Dave", 101));
        chainingWithReplacement.insert(new Student("Eva", 102));
        chainingWithReplacement.insert(new Student("Frank", 111));
        chainingWithReplacement.insert(new Student("Grace", 110));
        chainingWithReplacement.insert(new Student("Hannah", 115));
        chainingWithReplacement.display();
    }
}
