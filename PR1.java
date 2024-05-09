import java.util.*;

class TreeNode {
    char value;
    TreeNode left, right;

    TreeNode(char value) {
        this.value = value;
        this.left = this.right = null;
    }
}

public class PR1 {
    private TreeNode root;

    PR1() {
        this.root = null;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public TreeNode constructExpressionTreePostfix(String expression) {
        Stack<TreeNode> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (!isOperator(c)) {
                stack.push(new TreeNode(c));
            } else {
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();
                TreeNode operatorNode = new TreeNode(c);
                operatorNode.left = left;
                operatorNode.right = right;
                stack.push(operatorNode);
            }
        }

        return stack.pop();
    }

    public void inorderRecursive(TreeNode root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.value + " ");
            inorderRecursive(root.right);
        }
    }

    public void preorderRecursive(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorderRecursive(root.left);
            preorderRecursive(root.right);
        }
    }

    public void postorderRecursive(TreeNode root) {
        if (root != null) {
            postorderRecursive(root.left);
            postorderRecursive(root.right);
            System.out.print(root.value + " ");
        }
    }

    public void inorderNonRecursive() {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.value + " ");
            current = current.right;
        }
    }

    public void preorderNonRecursive() {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.value + " ");

            if (current.right != null)
                stack.push(current.right);

            if (current.left != null)
                stack.push(current.left);
        }
    }

    public void postorderNonRecursive() {
        if (root == null)
            return;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);

            if (current.left != null)
                stack1.push(current.left);

            if (current.right != null)
                stack1.push(current.right);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    public static void main(String[] args) {
        PR1 tree = new PR1();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter postfix expression: ");
        String postfixExpression = scanner.nextLine();

        tree.root = tree.constructExpressionTreePostfix(postfixExpression);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Recursive Inorder Traversal");
            System.out.println("2. Recursive Preorder Traversal");
            System.out.println("3. Recursive Postorder Traversal");
            System.out.println("4. Non-recursive Inorder Traversal");
            System.out.println("5. Non-recursive Preorder Traversal");
            System.out.println("6. Non-recursive Postorder Traversal");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Recursive Inorder Traversal: ");
                    tree.inorderRecursive(tree.root);
                    break;
                case 2:
                    System.out.print("Recursive Preorder Traversal: ");
                    tree.preorderRecursive(tree.root);
                    break;
                case 3:
                    System.out.print("Recursive Postorder Traversal: ");
                    tree.postorderRecursive(tree.root);
                    break;
                case 4:
                    System.out.print("Non-recursive Inorder Traversal: ");
                    tree.inorderNonRecursive();
                    break;
                case 5:
                    System.out.print("Non-recursive Preorder Traversal: ");
                    tree.preorderNonRecursive();
                    break;
                case 6:
                    System.out.print("Non-recursive Postorder Traversal: ");
                    tree.postorderNonRecursive();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
        scanner.close();
    }
}
