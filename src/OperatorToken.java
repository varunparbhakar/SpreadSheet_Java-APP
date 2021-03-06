/**
 * The Operator token class manages the Operator token object.
 * @version 03/07/2022
 * @author Varun Parbhakar, Andrew Dibble, and Minh Trung Le.
 */
public class OperatorToken extends Token {
    public static final char Plus = '+';
    public static final char Minus = '-';
    public static final char Mult = '*';
    public static final char Div = '/';
    public static final char LeftParen = '(';

    private final char operatorToken; //Char representation of the operator

    /**
     * Constructor for operator token class.
     * @param operatorToken
     */
    public OperatorToken(char operatorToken) {
        this.operatorToken = operatorToken;
    }

    /**
     * Return true if the char ch is an operator of a formula.
     * Current operators are: +, -, *, /, (.
     *
     * @param ch a char
     * @return whether ch is an operator
     */
    public static boolean isOperator(char ch) {
        return ((ch == Plus) ||
                (ch == Minus) ||
                (ch == Mult) ||
                (ch == Div) ||
                (ch == LeftParen));
    }

    /**
     * Given an operator, return its priority.
     * <p>
     * priorities:
     * +, - : 0
     * *, / : 1
     * (    : 2
     *
     * @param ch a char
     * @return the priority of the operator
     */
    public static int operatorPriority(char ch) {
        if (!isOperator(ch)) {
            // This case should NEVER happen
            System.out.println("Error in operatorPriority.");
            System.exit(0);
        }
        switch (ch) {
            case Plus:
                return 0;
            case Minus:
                return 0;
            case Mult:
                return 1;
            case Div:
                return 1;
            case LeftParen:
                return 2;
            default:
                // This case should NEVER happen
                System.out.println("Error in operatorPriority.");
                System.exit(0);
                return -1;
            //break;
        }
    }

    /**
     * returns a char corresponding to the OperatorToken, 0 if it is not an operator
     *
     * @return a char corresponding to an operator
     */
    public char getOperatorToken() {
        if (isOperator(this.operatorToken)) {
            return this.operatorToken;
        } else {
            return '0';  //bad token
        }
    }

    /**
     * Return the priority of this OperatorToken.
     *
     * priorities:
     *   +, - : 0
     *   *, / : 1
     *   (    : 2
     *
     * @return the priority of operatorToken
     */
    public int priority() {
        switch (this.operatorToken) {
            case Plus:
                return 0;
            case Minus:
                return 0;
            case Mult:
                return 1;
            case Div:
                return 1;
            case LeftParen:
                return 2;

            default:
                // This case should NEVER happen
                System.out.println("Error in priority.");
                System.exit(0);
                return -1;
            //break;
        }

    }

    /**
     * toString for operator token.
     * @return
     */
    @Override
    public String toString() {
        return Character.toString(operatorToken);
    }
}

