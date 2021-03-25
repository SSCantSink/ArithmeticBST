
/**
 * This class represents a binary tree and is meant for arithmetic expressions
 * only. 
 * @author Karanveer Sandhu
 */
public class Node 
{
    
    String content; // the content of the node, a number or an operator
    Node left;      // the left child node
    Node right;     // the right child node.
    
    /**
     * Constructor for a Node/Binary Tree
     * @param content the number or operator the node contains. 
     */
    public Node(String content)
    {
        this.content = content;
        left = null;
        right = null;
    }
    
    /**
     * Sets the left child node to the param
     * @param left the soon to become left child node.
     */
    public void setLeft(Node left)
    {
        this.left = left;
    }
    
    /**
     * Sets the right child node to the param
     * @param right the soon to become right child node.
     */
    public void setRight(Node right)
    {
        this.right = right;
    }
    
    /**
     * Sets the contents of the node with the parameter
     * @param content the soon to be contents of the node.
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
    /**
     * An accessor to the left child node
     * @return the left child node
     */
    public Node getLeft()
    {
        return left;
    }
    
    /**
     * An accessor to the right child node
     * @return the right child node
     */
    public Node getRight()
    {
        return right;
    }
    
    /**
     * An accessor to the contents of the node.
     * @return the content String of the node.
     */
    public String getContent()
    {
        return content;
    }
    
    /**
     * Goes through the Node in postTransversal writing left node, right node,
     * then the parent itself.
     * @return a String of the postfix transversal
     */
    public String postfixTransversal()
    {
        String toReturn = "";
        
        // If the node isn't there.
        if (this == null)
        {
            return ""; // there's nothing to write.
        }
        
        // Add on the left child's postfixTransversal
        toReturn += left.postfixTransversal();
        
        // Add on the right child's postfixTransversal
        toReturn += right.postfixTransversal();
        
        // Add on the node's content
        toReturn += content + " ";
        
        return toReturn;
    }
    
    
    
}
