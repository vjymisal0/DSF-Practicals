
//write java code to perform Construct an expression tree from postfix/prefix expression and perform recursive inorder, preorder
import java.util.*;

class TreeNode {
    char data;
    TreeNode left, right;

    public TreeNode(char item) {
        data = item;
        left = right = null;
    }
}

class Practical1 {
    public static TreeNode constructTreeFromPostfix(String postfix) {
        Stack<TreeNode> stack = new Stack<>();

        for (char c : postfix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                stack.push(new TreeNode(c));
            } else {
                TreeNode operand2 = stack.pop();
                TreeNode operand1 = stack.pop();
                TreeNode operator = new TreeNode(c);
                operator.left = operand1;
                operator.right = operand2;
                stack.push(operator);
            }
        }

        return stack.pop();
    }

    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }
    }

    public static void preorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    public static void postorderTraversal(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter postfix expression: ");
        String postfixExpression = sc.nextLine();
        TreeNode root = constructTreeFromPostfix(postfixExpression);

        System.out.println("Inorder Traversal:");
        inorderTraversal(root);

        System.out.println("\nPreorder Traversal:");
        preorderTraversal(root);

        System.out.println("\nPostorder Traversal:");
        postorderTraversal(root);
    }
}
