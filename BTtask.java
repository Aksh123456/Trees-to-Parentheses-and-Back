public class BTtask {
    static class Tree {
    }

    static class Leaf extends Tree {
    }

    static class Branch extends Tree {
        Tree left;
        Tree right;

        Branch(Tree left, Tree right) {
            this.left = left;
            this.right = right;
        }
    }

    // Function to convert a binary tree to a string of balanced parentheses
    public static String treeToParenthesis(Tree root) {
        if (root instanceof Leaf) {
            return "()";
        } else if (root instanceof Branch) {
            Branch branch = (Branch) root;
            String leftParens = treeToParenthesis(branch.left);
            String rightParens = treeToParenthesis(branch.right);
            return "(" + leftParens + rightParens + ")";
        }
        return "";
    }

    // Function to convert a string of balanced parentheses to a binary tree
    public static Tree parenthesisToTree(String parens) {
        return parenthesisToTreeHelper(parens, 0, parens.length() - 1);
    }

    private static Tree parenthesisToTreeHelper(String parens, int start, int end) {
        if (start > end) {
            return null;
        }

        if (parens.charAt(start) == '(' && parens.charAt(end) == ')') {
            int balance = 0;
            int split = -1;

            for (int i = start + 1; i <= end - 1; i++) {
                if (parens.charAt(i) == '(') {
                    balance++;
                } else if (parens.charAt(i) == ')') {
                    balance--;
                }

                if (balance == 0) {
                    split = i;
                    break;
                }
            }

            if (split != -1) {
                Tree left = parenthesisToTreeHelper(parens, start + 1, split - 1);
                Tree right = parenthesisToTreeHelper(parens, split + 1, end - 1);
                return new Branch(left, right);
            }
        }

        return new Leaf();
    }

    public static void main(String[] args) {
        Tree tree = new Branch(new Leaf(), new Branch(new Leaf(), new Leaf()));

        String parens = treeToParenthesis(tree);
        System.out.println("treeToParenthesis: " + parens);

        Tree reconstructedTree = parenthesisToTree(parens);
        String reconstructedParens = treeToParenthesis(reconstructedTree);
        System.out.println("parenthesisToTree: " + reconstructedParens);

        
    }
}
