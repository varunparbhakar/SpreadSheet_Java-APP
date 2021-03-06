/**
 * The ExpressionTree class manages the ExpressionTree object.
 * @version 03/07/2022
 * @author Varun Parbhakar, Andrew Dibble, and Minh Trung Le.
 */
public class ExpressionTree {

    private ExpressionTreeNode root; //Root of the Expression Node


    /**
     * This is constructor for ExpressionTree
     * @param root
     */
    public ExpressionTree(ExpressionTreeNode root) {
        this.root = root;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (root == null)
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param node the node that roots the tree.
     */
    private void printTree(ExpressionTreeNode node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(Token.printExpressionTreeToken(node.getToken()));
            printTree(node.right);
        }
    }

    /**
     * Evaluate the expression of formula
     *
     * @return value after calculation
     */
    public int Evaluate(Spreadsheet spreadsheet) {

        if (root == null) {
            return 0;
        } else {
            return Evaluate(spreadsheet, root);
        }
    }

    /**
     * Internal method for evaluate the formula
     *
     * @param node Node of Expression Tree
     * @return value after calculation
     */
    private int Evaluate(Spreadsheet spreadsheet, ExpressionTreeNode node) {
        int result = 0;
        char operator = '0';
        int literalToken = 0;
        CellToken cellTokenInExpression = null;
        Cell cellInExpression;

        if (node != null) {
            Token token = node.getToken();

            //For literal Token, return int value of literalToken
            if (token instanceof LiteralToken) {
                literalToken = LiteralToken.getValue((LiteralToken) token);
                return literalToken;
            }

            //For Cell Token, return the value of the Cell in Expression
            if (token instanceof CellToken) {
                cellTokenInExpression = (CellToken) token;
                cellInExpression = spreadsheet.getCellValue(cellTokenInExpression);
                if (cellInExpression == null) {
                    return 0;
                }
                return cellInExpression.getValue();
            }

            //For Operator Token, calculate the left and right node
            if (token instanceof OperatorToken) {
                operator = ((OperatorToken) token).getOperatorToken();

                if (operator == '+') {
                    result = Evaluate(spreadsheet, node.left) + Evaluate(spreadsheet, node.right);
                } else if (operator == '-') {
                    result = Evaluate(spreadsheet, node.left) - Evaluate(spreadsheet, node.right);
                } else if (operator == '*') {
                    result = Evaluate(spreadsheet, node.left) * Evaluate(spreadsheet, node.right);
                } else if (operator == '/') {
                    result = Evaluate(spreadsheet, node.left) / Evaluate(spreadsheet, node.right);
                }
            }
        }
        return result;

    }

    /**
     * Build an expression tree from a stack of ExpressionTreeTokens
     * @param s
     */
    void BuildExpressionTree(Stack s) {
        root = GetExpressionTree(s);
        if (!s.isEmpty()) {
            System.out.println("Error in BuildExpressionTree.");
        }
    }

    /**
     * This method is used to traverse the expression tree.
     * @param s
     */
    public ExpressionTreeNode GetExpressionTree(Stack s) {
        ExpressionTreeNode returnTree;
        Token token;
        if (s.isEmpty())
            return null;
        token = (Token) s.topAndPop(); // need to handle stack underflow
        if ((token instanceof LiteralToken) ||
                (token instanceof CellToken)) {
            // Literals and Cells are leaves in the expression tree
            returnTree = new ExpressionTreeNode(token, null, null);
            return returnTree;
        } else if (token instanceof OperatorToken) {
            // Continue finding tokens that will form the
            // right subtree and left subtree.
            ExpressionTreeNode rightSubtree = GetExpressionTree(s);
            ExpressionTreeNode leftSubtree = GetExpressionTree(s);
            returnTree = new ExpressionTreeNode(token, leftSubtree, rightSubtree);
            return returnTree;
        } else {
            return null;
        }
    }


}
