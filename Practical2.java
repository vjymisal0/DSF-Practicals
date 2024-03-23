
import java.util.*;

class Node {

    Node root;
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    Node() {
        root = null;
    }

    void insert(int data) {
        Node n = new Node(data);
        if (root == null) {
            root = n;
        } else {
            Node temp = root;
            while (temp != null) {
                if (data < temp.data) {
                    if (temp.left == null) {
                        temp.left = n;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == null) {
                        temp.right = n;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }
    }

    void display(Node root) {
        if (root != null) {
            display(root.left);
            System.out.println(root.data);
            display(root.right);
        }
    }

    void search(int data) {
        Node temp = root;
        while (temp != null) {
            if (data == temp.data) {
                System.out.println("Found");
                break;
            } else if (data < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
    }

    void delete(int data) {
        Node temp = root;
        Node parent = null;
        while (temp != null) {
            if (data == temp.data) {
                break;
            } else if (data < temp.data) {
                parent = temp;
                temp = temp.left;
            } else {
                parent = temp;
                temp = temp.right;
            }
        }
        if (temp == null) {
            System.out.println("Not found");
        } else {
            if (temp.left == null && temp.right == null) {
                if (parent.left == temp) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (temp.left == null) {
                if (parent.left == temp) {
                    parent.left = temp.right;
                } else {
                    parent.right = temp.right;
                }
            } else if (temp.right == null) {
                if (parent.left == temp) {
                    parent.left = temp.left;
                } else {
                    parent.right = temp.left;
                }
            } else {
                Node successor = temp.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                int val = successor.data;
                delete(successor.data);
                temp.data = val;
            }
        }
    }

}

class Practical2 {
    public static void main(String[] arg) {
        Node n = new Node();
        n.insert(10);
        n.insert(5);
        n.insert(15);
        n.display(n.root);
        n.search(5);
        n.delete(5);

    }
}