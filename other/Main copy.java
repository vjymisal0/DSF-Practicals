package other;
import java.util.Scanner;
import java.io.*;

class Student {
    private String Name;
    private int Roll_No;

    public Student() {
        Name = "";
        Roll_No = -1;
    }

    public void get_Data() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter Student Data");
        System.out.print("Name: ");
        Name = scanner.next();
        System.out.print("Roll Number: ");
        Roll_No = scanner.nextInt();
    }

    public void put_Data() {
        System.out.println("\n" + Roll_No + "\t" + Name);
    }

    public int return_Roll() {
        return Roll_No;
    }
}

class Seq_File {
    private String File_Name;

    public Seq_File() {
        File_Name = "Student.txt";
        try {
            PrintWriter file = new PrintWriter(new FileWriter(File_Name));
            System.out.println("\nDefault Constructor");
            System.out.println("File opened Successfully");
            file.close();
        } catch (IOException e) {
            System.out.println("\nFile creation Error");
        }
    }

    public Seq_File(String F) {
        File_Name = F;
        try {
            PrintWriter file = new PrintWriter(new FileWriter(File_Name));
            System.out.println("\nFile opened Successfully");
            file.close();
        } catch (IOException e) {
            System.out.println("\nFile creation Error");
        }
    }

    public void Create() {
        try {
            Student S = new Student();
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(File_Name));
            S.get_Data();
            file.writeObject(S);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Display() {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(File_Name));
            System.out.println("\nRoll No\t Student Name");
            try {
                while (true) {
                    Student S = (Student) file.readObject();
                    S.put_Data();
                }
            } catch (EOFException e) {
                file.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Add() {
        try {
            Student S = new Student();
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(File_Name, true));
            S.get_Data();
            file.writeObject(S);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Remove(int Roll) {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(File_Name));
            ObjectOutputStream temp = new ObjectOutputStream(new FileOutputStream("Temp.Txt"));
            boolean flag = false;
            try {
                while (true) {
                    Student S = (Student) file.readObject();
                    if (Roll == S.return_Roll()) {
                        S.put_Data();
                        flag = true;
                    } else {
                        temp.writeObject(S);
                    }
                }
            } catch (EOFException e) {
                file.close();
                temp.close();
                if (!flag)
                    System.out.println("Roll No. " + Roll + " does not present");
                File oldFile = new File(File_Name);
                oldFile.delete();
                File newFile = new File("Temp.Txt");
                newFile.renameTo(new File(File_Name));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Modify(int Roll) {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(File_Name));
            ObjectOutputStream temp = new ObjectOutputStream(new FileOutputStream("Temp.Txt"));
            boolean flag = false;
            try {
                while (true) {
                    Student S = (Student) file.readObject();
                    if (Roll == S.return_Roll()) {
                        S.put_Data();
                        System.out.println("\nEnter data to modify");
                        S.get_Data();
                        flag = true;
                    }
                    temp.writeObject(S);
                }
            } catch (EOFException e) {
                file.close();
                temp.close();
                if (!flag)
                    System.out.println("Roll No. " + Roll + " does not present");
                File oldFile = new File(File_Name);
                oldFile.delete();
                File newFile = new File("Temp.Txt");
                newFile.renameTo(new File(File_Name));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Choice;
        String F;
        int R;
        System.out.print("\nEnter File Name: ");
        F = scanner.next();
        Seq_File sFile = new Seq_File(F);
        do {
            System.out.println(
                    "\n1: Create Database\n2: Display Database\n3: Add a record\n4: Remove a record\n5: Modify a record\n6: Exit");
            System.out.print("Enter your choice: ");
            Choice = scanner.nextInt();

            switch (Choice) {
                case 1:
                    sFile.Create();
                    break;
                case 2:
                    sFile.Display();
                    break;
                case 3:
                    sFile.Add();
                    break;
                case 4:
                    System.out.print("Enter Roll No. to remove: ");
                    R = scanner.nextInt();
                    sFile.Remove(R);
                    break;
                case 5:
                    System.out.print("Enter Roll No. to modify: ");
                    R = scanner.nextInt();
                    sFile.Modify(R);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (Choice != 6);
    }
}
