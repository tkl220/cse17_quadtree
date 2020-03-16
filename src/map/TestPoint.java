package map;

import junit.framework.TestCase;

/**
 * tests methods of point class
 * 
 * Part 1:
 * @author T. Kingsley Leighton 0%
 * @author Wesley Olson 30%
 * @author Maxime Martin 70%
 * @version 18 April 2018
 * Part 2:
 * @author T. Kingsley Leighton 
 * @author Amy Chai 
 * @author Zachary Weinert
 * @version 26 April 2018 
 */
public class TestPoint extends TestCase {
    
    private Point ptn1;
    private Point ptn2;
    private Point ptn3;
    private Point ptn4;
    
    /**
     * Setting up example test points 
     * O(1)
     */
    public void setUp() {
        ptn1 = new Point(1,1);
        ptn2 = new Point(3,2);
        ptn3 = new Point(-1,-1);
        ptn4 = new Point(0,0);
    }
    
    /**
     * This is the test method for the 
     * getter method of x
     * O(1)
     */
    public void testGetX() {
        assertEquals(1, ptn1.x());
        assertEquals(-1, ptn3.x());
        assertEquals(0, ptn4.x());
    }
    
    /**
     * This is the test method for the 
     * getter method of y
     * O(1)
     */
    public void testGetY() {
        assertEquals(1, ptn1.y());
        assertEquals(-1, ptn3.y());
        assertEquals(0, ptn4.y());
    }
    
    /**
     * This is the test method for the 
     * setter method of x
     * O(1)
     */
    public void testSetX() {
        ptn1.x(1);
        assertEquals(1, ptn1.x());
        ptn3.x(-1);
        assertEquals(-1, ptn3.x());
    }
    
    /**
     * This is the test method for the 
     * setter method of y
     * O(1)
     */
    public void testSetY() {
        ptn1.y(1);
        assertEquals(1, ptn1.y());
        
    }
    
    /**
     * This is the test for the toString method
     * O(1)
     */
    public void testToString() {
        assertEquals("(1,1)", ptn1.toString());
    }
    
    /**
     * This is the test methods for equals
     * O(1)
     */
    public void testEquals() {
        assertEquals(false, ptn2.equals(ptn1));
        assertEquals(true, ptn2.equals(ptn2));
        assertEquals(false, ptn3.equals(null));
    }
}