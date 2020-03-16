package map;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * tests methods of node class
 * 
 * Part 1:
 * @author T. Kingsley Leighton 10%
 * @author Wesley Olsen 20%
 * @author Maxime Martin 70%
 * @version 18 April 2018
 * Part 2:
 * @author T. Kingsley Leighton 0%
 * @author Amy Chai 0%
 * @author Zachary Weinert 100%
 * @version 26 April 2018 
 */
public class TestNode extends TestCase{
    
    private Node<Point> nd1;
    private Node<Point> nd2;
    private Node<Point> nd3;
    private Point point1;
    private Point point2;
    private Point point3;
    private ArrayList<String> descriptions1;
    private ArrayList<String> descriptions2;
   
    /**
     * setUp runs before each test
     * O(1)
     */
    public void setUp() {
        point1 = new Point(1,1);
        point2 = new Point(2,2);
        point3 = new Point(-2,-2);
        descriptions1 = new ArrayList<String>();
        descriptions1.add("La petite cuisine");
        descriptions2 = new ArrayList<String>();
        descriptions2.add(null);
        nd1 = new Node<Point>(point1, descriptions1);
        nd2 = new Node<Point>(point3, descriptions1);
        nd3 = new Node<Point>(point1, descriptions2);
    }

    /**
     * Test for getter Point method
     * O(1)
     */
    public void testGetPoint() {
        assertEquals(point1, nd1.point());
        assertEquals(point3, nd2.point());
    }
    
    /**
     * Test for setter Point method
     * O(1)
     */
    public void testSetPoint() {
        nd1.point(point2);
        assertEquals("(2,2)", nd1.point().toString());
        nd2.point(point3);
        assertEquals("(-2,-2)", nd2.point().toString());

    }
    
    /**
     * Test for the getter Point method
     * O(1)
     */
    public void testGetDescription() {
        assertEquals("[La petite cuisine]", nd1.descriptions().toString());
        assertEquals("[null]", nd3.descriptions().toString());
    }

    /**
     * Test for the setter of the description method
     * O(1)
     */
    public void testSetDescription() {
        nd1.descriptions(descriptions1);
        assertEquals("[La petite cuisine]", nd1.descriptions().toString());        
        nd2.descriptions(descriptions1);
        assertEquals("[null]", nd3.descriptions().toString());        
    
    }
    
    /**
     * Test getter method for distance
     * O(1)
     */
    public void testGetDistance() {
        assertEquals(0, nd1.distance(), .01);
        assertEquals(0, nd2.distance(), .01);
    }
    
    /**
     * Test setter method for distance
     * O(1)
     */
    public void testSetDistance() {
        nd1.distance(1.2);
        nd2.distance(32.89);
        assertEquals(1.2, nd1.distance(), .01);
        assertEquals(32.89, nd2.distance(), .01);
    }
    
    /**
     * Test getter method for streets
     * O(1)
     */
    public void testGetStreets() {
        assertTrue(nd1.streets().isEmpty());
        assertTrue(nd2.streets().isEmpty());
    }

    /**
     * Test setter method for streets
     * O(1)
     */
    public void testSetStreets() {
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        s1.add("Mermaid-Stoney Batter Creek Rd.");
        s2.add("Lion Dr.");
        nd1.streets(s1);
        nd2.streets(s2);
        assertEquals("[Mermaid-Stoney Batter Creek Rd.]", nd1.streets().toString());
        assertEquals("[Lion Dr.]", nd2.streets().toString());
    }
    /**
     * Test for toString method
     * O(1)
     */
    public void testToString() {
        nd1.toString();
        assertEquals("(1,1) La petite cuisine ", nd1.toString());
        nd2.toString();
        assertEquals("(-2,-2) La petite cuisine ", nd2.toString());
        nd3.toString();
        assertEquals("(1,1) null ", nd3.toString());
    }
    
    /**
     * Test compareTo method of Node<T>
     * O(1)
     */
    public void testCompareTo() {
        nd1.distance(1.2);
        nd2.distance(32.89);
        assertTrue(nd1.compareTo(nd2) < 0);
        assertTrue(nd2.compareTo(nd1) > 0);
        nd2.distance(1.2);
        assertTrue(nd1.compareTo(nd1) == 0);
    }
}