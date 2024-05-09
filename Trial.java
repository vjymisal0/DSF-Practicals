import java.util.*;

class TreeNode {
    char data;
    TreeNode left, right;

    TreeNode(char value) {
        this.data = value;
        this.left = this.right = null;
    }
}

public class Trial {
    private TreeNode root;

    Trial() {
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

    public void inorderNonRecursive() {
        Stack<TreeNode> st = new Stack<>();
        if (root != null)
            st.push(root);
        while (st.size() > 0) {
            TreeNode temp = st.peek();
            if (temp.left != null) {
                st.push(temp.left);
                temp.left = null;
            } else {
                System.out.println(temp.data);
                st.pop();
                if (temp.right != null)
                    st.push(temp.right);
            }
        }
    }

    public void postorderNonRecursive() {
        if (root == null) {
            return;
        }
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
            System.out.print("\t" + stack2.pop().data);
        }
    }

    public static void main(String[] args) {
        Trial t1 = new Trial();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Postfix Expression: ");
        String postfixExpr = sc.nextLine();
        t1.root = t1.constructExpressionTreePostfix(postfixExpr);
        t1.inorderNonRecursive();
        t1.postorderNonRecursive();
    }
}