// Construct an expression tree from postfix/prefix expression and perform recursive inorder, preorder
// and post order traversals. For expression tree, perform non-recursive inorder, preorder and post order
// traversals

import java.util.*;

class TreeNode {
    char data;
    TreeNode left, right;

    public TreeNode(char item) {
        data = item;
        left = right = null;
    }
}

class ExpTree {
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
        System.out.print("Enter the postfix expression: ");
        String postfix = sc.next();
        TreeNode root = constructTreeFromPostfix(postfix);

        System.out.println("Inorder traversal:");
        inorderTraversal(root);
        System.out.println();

        System.out.println("Preorder traversal:");
        preorderTraversal(root);
        System.out.println();

        System.out.println("Postorder traversal:");
        postorderTraversal(root);
        System.out.println();
    }
}
