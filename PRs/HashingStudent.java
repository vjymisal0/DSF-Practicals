import java.util.*;

import other.Student1;

// Class to represent student data
class Student1 {
    int rollNumber;
    String name;

    public Student1(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }
}

// Class to represent HashTable using chaining with linear probing
class HashTable {
    private static final int TABLE_SIZE = 10; // Size of the hash table
    private List<Student1>[] table; // Array of LinkedLists to store students

    // Constructor
    public HashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Hash function to map roll number to index in the hash table
    private int hash(int rollNumber) {
        return rollNumber % TABLE_SIZE;
    }

    // Method to insert a student into the hash table
    public void insert(Student1 student1) {
        int index = hash(student1.getRollNumber());
        table[index].add(student1);
    }

    // Method to retrieve a student by roll number
    public Student1 get(int rollNumber) {
        int index = hash(rollNumber);
        for (Student1 student1 : table[index]) {
            if (student1.getRollNumber() == rollNumber) {
                return student1;
            }
        }
        return null;
    }

    // Method to display the contents of the hash table
    public void display() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.print("Index " + i + ": ");
            for (Student1 student1 : table[i]) {
                System.out.print("(" + student1.getRollNumber() + ", " + student1.getName() + ") -> ");
            }
            System.out.println("null");
        }
    }
}

public class HashingStudent {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        // Inserting students into the hash table
        hashTable.insert(new Student1(101, "Alice"));
        hashTable.insert(new Student1(102, "Bob"));
        hashTable.insert(new Student1(103, "Charlie"));
        hashTable.insert(new Student1(104, "David"));

        // Displaying the contents of the hash table
        hashTable.display();

        // Retrieving a student by roll number
        System.out.println("Student with roll number 103: " + hashTable.get(103).getName());
    }
}
