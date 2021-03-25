
import java.util.Stack;
import java.util.Scanner;

/**
 * This class contains an array of arithmetic expression and can 
 * convert infix to postfix and vice versa.
 * @author Karanveer Sandhu
 */
public class Arithmetic {

    // An arithmetic expression in infix
    private String expression;
    
    // the tree that holds the expression. Parent nodes are operators,
    // and the numbers are leaves.
    private Node BinaryTree;
    
   /**
    * Converts the String so that the String will have no white spaces.
    * @param expression -the arithmetic expression.
    * @return the converted String without any white spaces.
    */
    private static String noWhiteSpaces(String expression)
    {
        String output = expression; // the output will be the original string
        
        // throughout the string.
        for (int i = 0; i < output.length(); i++)
        {
            String a = output.substring(i, i+1); // each character
            if (a.equals(" ")) // if it is a space
            {
                // the string will be everthing but that space
                output = output.substring(0, i) + output.substring(i+1);
                // decrement i so we don't skip any characters on the string
                i--; 
            }
        } // end of for loop
        
        return output;
    } // end of noWhiteSpaces()
    
    /**
     * Checks if operator a has higher than or equal to precedence than 
     * operator b.
     * @param a the first operator
     * @param b the second operator
     * @return whether or not operator a had a higher than or equal to
     * precedence than operator b.
     */
    private static boolean isHigherPrecedence(String a, String b)
    {
        if (a.equals("%") || a.equals("/") || a.equals("*"))
        {
            return true;
        }
        else if (a.equals("+") || a.equals("-"))
        {
            if (b.equals("+") || b.equals("-"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    } // end of isHigherPrecedence()
    
    /**
     * Determines if this string is a number.
     * @param num the string to test if it is a number
     * @return whether the string is a number or not.
     */
    private static boolean isNumber(String num)
    {
        try 
        {
            Integer.parseInt(num);
            return true;    
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        
    }
    
    /**
     * Determines if this string is one of the operators +, -, *, /, or %.
     * @param operator the string to test if it is an operator
     * @return whether it is an operator or not.
     */
    private static boolean isOperator(String operator)
    {
        return ("+-*/%".contains(operator));
    }
    
    /**
     * Gets three strings and evaluate the operation that is there.
     * @param t1 - the first number/operand
     * @param op - the operator
     * @param t2 - the second number/operand
     * @return the value of the evaluation of the operation.
     */
    private static String doOperation(String t1, String op, String t2)
    {
        int num1 = Integer.parseInt(t1);
        int num2 = Integer.parseInt(t2);
        
        if (op.equals("+"))
        {
            return (num1 + num2) + "";
        }
        else if (op.equals("-"))
        {
            return (num1 - num2) + "";
        }
        else if (op.equals("*"))
        {
            return (num1 * num2) + "";
        }
        else if (op.equals("/"))
        {
            return (num1 / num2) + "";
        }
        else // %
        {
            return (num1 % num2) + "";
        }
    } // end of doOperation()
    
    /**
     * Creates an Arithmetic Object
     * @param expression the array of arithmetic expressions
     */
    public Arithmetic(String expression)
    {
        this.expression = expression;
        BinaryTree = new Node("");
    }
    
    /**
     * Determines whether the arithmetic expression is properly written
     * regarding parenthesis.
     * @return whether or not the infix expression is properly written
     * regarding parenthesis.
     */
    public boolean isBalance()
    {
        Stack<Object> stack = new Stack<>();
        
        String math = noWhiteSpaces(expression); // remove the white spaces
        
        // Throughout the whole expression
        for (int i = 0; i < math.length(); i++)
        {
            String a = math.substring(i, i+1); // the current char
            if (a.equals("(")) // if current char is (
            {
                stack.push("("); // push on stack
            }
            if (a.equals(")")) // if it is )
            {
                if (stack.empty()) // then first check if stack is empty
                {
                    return false; // if it is, then it is not balanced
                }
                else
                {
                    stack.pop(); // otherwise remove a ( from the stack
                } // end of second if-else statements
            } // end of first set of  if statments
        } // end of for loop
        
        if (!stack.empty()) // if the stack is not empty
        {
            return false; // expression was not balanced
        }
        else // otherwise
        {
            return true; // it is balanced.
        } // end of if statement
    } // end isBalanced
    
    /**
     * Goes through this current and puts the numbers and operators of the
     * expression into the node.
     * @param arith the arithmetic expression.
     * @param node the node in which to order the arithmetic expression.
     * @return whether or not arith was properly written
     */
    public static boolean doParenthesis(String arith, Node node)
    {
        /**
         * Making the tree in order of PEMDAS is not easy. We can't easily
         * access parent nodes of a node.
         * However, we can make the tree easily if we can do things in
         * reverse order. So this function tries find which operator is
         * the last one to do in the expression arith. Then it will put
         * whatever is left or right of that operator to the left and
         * right child node respectively.
         */
        
        // keeps track of whether or not we are in the parenthesis of the 
        // arithmetic expression.
        int parenCount = 0;
        
        // keeps track of which operator it is on. first operator is 
        // considered 0.
        int opTokenInd = -1;
        
        // Keeps track of the last to do operator. 
        String theLastOp = "";
        
        // If arith has opening/closing parenthesis on its whole
        if (arith.substring(0, 1).equals("(") && 
                arith.substring(arith.length() -1).equals(")"))
        {
            // remove them.
            arith = arith.substring(1, arith.length() - 1);
        }
        
        // don't forget to initialize the node.
        if (node == null)
        {
            node = new Node(arith);
        }
        
        // BASE Case: if the expression is just blank
        if (arith.equals(""))
        {
            return false;
        }
        
        // BASE CASE: if the expression is just a number
        if (isNumber(arith))
        {
            node.content = arith; // that node is just that number
            return true;          // making binary tree was possible.
        }
        
        // Base Case: if the expression is just an operator.
        if (isOperator(arith))
        {
            return false; // something is wrong.
        }
        
        // Go through the string token by token
        Scanner scan = new Scanner(arith);
        int tokenIndex = 0; // keep track of which token it is.
        while (scan.hasNext())
        {
            String a = scan.next(); // current token.
            
            if (parenCount <= 0) // if we are not in parenthesis
            {
                if (isOperator(a)) // if current token is operator
                {
                    if (opTokenInd == -1) // we never encountered an operator.
                    {
                        theLastOp = a;  // that is our last to do operator
                        opTokenInd = tokenIndex; // keep track of it.
                        tokenIndex++; // onto next token.
                    }
                    else // we did encounter an operator
                    {
                        // if the last to do Operator is higher or equal
                        // precedence than the current token.
                        if (isHigherPrecedence(theLastOp, a))
                        {
                            theLastOp = a;  // that is our last to do operator
                            opTokenInd = tokenIndex; // keep track of it.
                            tokenIndex++; // onto next token.
                        }
                        else // last to do Operator is lower precedence than a.
                        {
                            tokenIndex++; // ignore it and move onto next token
                        }
                    }
                }
                else if (a.equals("(")) // if it is an (
                {
                    parenCount++; // put parencount up.
                    tokenIndex++; // onto next token.
                }
                else if (a.equals(")")) // if it is an )
                {
                    parenCount--; // put parencount down.
                    tokenIndex++; // onto next token.
                }
                else // its a number
                {
                    tokenIndex++; // ignore it and move onto next token
                }
            }
            else // we are in a bunch of parenthesis
            {
                if (a.equals("(")) // if it is an (
                {
                    parenCount++; // put parencount up
                    tokenIndex++; // onto next token
                }
                else if (a.equals(")")) // if it is an )
                {
                    parenCount--; // put parenCount down
                    tokenIndex++; // onto the next token.
                }
                else // it's a number or operator in parenthesis
                {
                    tokenIndex++; // ignore it and move on.
                }
            }
        } // end of while loop trying to find the last to do operator
        
        // If we didn't find such operator (perhaps there was none).
        if (opTokenInd < 0)
        {
            return false; // expression was malformed.
        }
        
        /**
         * By now we have found which operator is the last one to do. Now we
         * must look to the left and right of that operator. to see which are
         * the left and right child nodes's doParenthesis() function.
         */
        
        // our node has the operator, definitely.
        node.content = theLastOp;
        
        // the left and right expressions for the left and right child nodes.
        String leftPart = "";
        String rightPart = "";
        
        // Finding the left token index with a new scanner
        Scanner scan2 = new Scanner(arith);
        boolean foundL = false;
        int tokenIndex2 = 0; // another index for tokens.
        while (scan2.hasNext() && !foundL)
        {
            String a = scan2.next(); // current token
            
            // if current token is that operator
            if (theLastOp.equals(a) && (tokenIndex2 == opTokenInd)) 
            {
                foundL = true; // then we've found it and stops the loop
            }
            else // it is not
            {
                leftPart += a + " "; // add on the token to the left part
                tokenIndex2++;  // onto the next token
            }
        }
        
        // Left part is done, onto the right part
        Scanner scan3 = new Scanner(arith);
        boolean foundR = false;
        int tokenIndex3 = 0;
        while (scan3.hasNext())
        {
            String a = scan3.next(); // current token
            
            // if we already found the operator
            if (foundR)
            {
                rightPart += a + " "; // then add on the next piece
                tokenIndex3++;        // onto the next token.
            }
            else if (tokenIndex3 == opTokenInd) // if we found the operator.
            {
                foundR = true;   // we've found it
                tokenIndex3++;   // onto the next token.
            }
            else // we haven't found the operator and this token ain't it.
            {
                tokenIndex3++; // onto the next token.
            }
        }
        
        // remove right and left part's ending space.
        rightPart = rightPart.substring(0, rightPart.length() - 1);
        leftPart = leftPart.substring(0, leftPart.length() - 1);
        
        
        // Now both the left side and right side has correct expressions.

        // all we have to do is do the same to the left and right child nodes
        // with their parts.
        node.left = new Node("");
        node.right = new Node("");
        return (doParenthesis(leftPart, node.left) && 
                doParenthesis(rightPart, node.right));
    }
    
    /**
     * Goes through the node in postTransversal order and returns the strings'
     * contents in that order
     * @param node the node to traverse
     * @return the postfixtransversal of the node
     */
    private static String postFixTransversal(Node node)
    {
        String toReturn = "";
        
        // If the node isn't there.
        if (node == null)
        {
            return ""; // there's nothing to write.
        }
        
        // Add on the left child's postfixTransversal
        toReturn += postFixTransversal(node.left);
        
        // Add on the right child's postfixTransversal
        toReturn += postFixTransversal(node.right);
        
        // Add on the node's content
        toReturn += node.content + " ";
        
        return toReturn;
    }
     
    /**
     * Converts the arithmetic expression from infix into postfix
     * @return whether or not the postFixExpression could be done.
     */
    public boolean postFixExpression()
    {
        // first set Binarytree and say if you can do it
        boolean can = doParenthesis(expression, BinaryTree);
        if (can)
        {
            expression = postFixTransversal(BinaryTree); // then set expression
            return true;
        }
        else
            return false;
    } // end of postFixExpression()
    
    
    private static String evaluateOurNode(Node node)
    {
        // Base Case: if node is nothing
        if (node == null)
        {
            return ""; // return an empty string
        }
        
        // Base Case: if node is a number
        if (isNumber(node.content))
        {
            return node.content; // return that number.
        }
        
        // first evaluate the left subtree and puts its evaluation there.
        node.left.content = evaluateOurNode(node.left);
        
        // then evaluate the right subtree and put its evaluation there.
        node.right.content = evaluateOurNode(node.right);
        
        // finally just evaluate the parent node with those evaluated
        // child nodes.
        node.content = doOperation(node.left.content, node.content, 
            node.right.content);
        
        return node.content;
    }
    
    /**
     * Evaluates the expression if it is in postfix 
     * @return the evaluation of the expression
     */
    public String evaluateRPN()
    {
        // first evaluate the Binary Tree
        BinaryTree.content = evaluateOurNode(BinaryTree);
        
        // then return the result.
        return BinaryTree.content;
    } // end of evaluateRPN()
    
    /**
     * An accessor for the expression instance variable
     * @return the expression instance variable
     */
    public String getExpression()
    {
        return expression;
    }
}
