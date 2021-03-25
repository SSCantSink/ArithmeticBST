/**
 * Name: Karanveer Sandhu
 * PID: 6096890
 * I hereby declare that the following code and classes associated with this
 * main class is made by only me with no help from the Internet or other 
 * people.
 */

/**
 * This class is a tester for infix and postfix expressions
 * @author nosta
 */
public class RPN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Array of arithmetic expressions.
        String s[] = {"5 + ) * ( 2",
                        "2 + ( -3 * 5 )",
                        "( ( 2 + 3 ) * 5 ) * 8",
                        "5 * 10 + ( 15 - 20 ) ) - 25",
                        "5 + ( 5 * 10 + ( 15 - 20 ) - 25 ) * 9"};
        
        // for each arithmetic expression
        for (int i = 0; i < s.length; i++)
        {
            Arithmetic a = new Arithmetic(s[i]);
            
            if (a.isBalance()) // if it is balanced based on parenthesis
            {
                // Print out the postfix and evaluation
                System.out.println("Expression " + s[i] + " is balanced");
                if (a.postFixExpression()) // if it can be evaluated.
                {
                    System.out.println("The post fixed expression is "
                        + a.getExpression());
                    
                    System.out.println("Evaluation: " + a.evaluateRPN() + "\n");
                }
                else
                {
                    System.out.println("Expression is malformed.\n");
                }
                        
            }
            else // otherwise
            {
                System.out.println("Expression " + s[i] + " is not balanced\n");
            } // end of if-else statements
        } // end of for loop
    } // end of main method
    
}

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// ~Thank you for grading my program.~
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~