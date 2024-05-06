package PRs;

import java.util.Stack;

class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int item) {
        data = item;
        left = right = null;
    }
}

public class ExpressionTreeP1 {
    public static TreeNode constructTreeFromPostfix(int[] postfix) {

        Stack<TreeNode> stack = new Stack<>();

        for (int c : postfix) {
            if (c >= 0) {
                stack.push(new TreeNode(c));
            } else {
                TreeNode operand1 = stack.pop();
                TreeNode operand2 = stack.pop();
                TreeNode operator = new TreeNode(c);
                operator.left = operand2;
                operator.right = operand1;
                stack.push(operator);

            }

        }
        return stack.pop();

    }
}
