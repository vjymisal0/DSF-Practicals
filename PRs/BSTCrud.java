import java.util.Scanner;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinarySearchTree {
    Node root;

    void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data > root.data)
            root.right = insertRec(root.right, data);
        return root;
    }

    void display() {
        displayInorder(root);
        System.out.println();
    }

    private void displayInorder(Node root) {
        if (root != null) {
            displayInorder(root.left);
            System.out.print(root.data + " ");
            displayInorder(root.right);
        }
    }

    boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node root, int data) {
        if (root == null)
            return false;
        if (root.data == data)
            return true;
        if (data < root.data)
            return searchRec(root.left, data);
        else
            return searchRec(root.right, data);
    }

    void delete(int data) {
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node root, int data) {
        if (root == null)
            return null;
        if (data < root.data)
            root.left = deleteRec(root.left, data);
        else if (data > root.data)
            root.right = deleteRec(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    private int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }
}

public class BSTCrud {
    public static void main(String[] arg) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Insert");
            System.out.println("2. Display");
            System.out.println("3. Search");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter the element to insert: ");
                    int data = sc.nextInt();
                    bst.insert(data);
                    break;
                case 2:
                    System.out.println("Inorder Traversal:");
                    bst.display();
                    break;
                case 3:
                    System.out.println("Enter the element to search: ");
                    int data1 = sc.nextInt();
                    if (bst.search(data1))
                        System.out.println("Found");
                    else
                        System.out.println("Not found");
                    break;
                case 4:
                    System.out.println("Enter the element to delete: ");
                    int data2 = sc.nextInt();
                    bst.delete(data2);
                    break;
                case 5:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
