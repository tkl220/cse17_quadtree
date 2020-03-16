package map;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestStreetNodes  extends TestCase{

    private StreetNodes sn1;
    private StreetNodes sn2;
    private StreetNodes sn3;
    private Node<Point> n1;
    private Node<Point> n2;
    private Node<Point> n3;
    private Point point1;
    private Point point2;
    private Point point3;
    private ArrayList<String> descriptions1;
    private ArrayList<String> descriptions2;
    private ArrayList<String> descriptions3;
    private ArrayList<Node<Point>> narr1;

   
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
        descriptions3 = new ArrayList<String>();
        descriptions3.add("ziggurat");
        n1 = new Node<Point>(point1, descriptions1);
        n2 = new Node<Point>(point2, descriptions2);
        n3 = new Node<Point>(point3, descriptions3);
        narr1 = new ArrayList<Node<Point>>();
        narr1.add(n1);
        narr1.add(n2);
        narr1.add(n3);
        sn1 = new StreetNodes("Mermaid-Stoney Batter Creek Rd.");
        sn2 = new StreetNodes("Lion St.");
        sn3 = new StreetNodes("Privet Dr.");
        sn1.locations(narr1);
    }

    /**
     * Test for getter Point method
     * O(1)
     */
    public void testGetStreet() {
        assertEquals("Mermaid-Stoney Batter Creek Rd.", sn1.street());
    }
    
    /**
     * Test for setter Point method
     * O(1)
     */
    public void testSetStreet() {
        sn1.street("Beelzebub Blv.");
        assertEquals("Beelzebub Blv.", sn1.street());
    }
    
    /**
     * Test for the getter Point method
     * O(1)
     */
    public void testGetLocationsn() {
        assertEquals(narr1, sn1.locations());
    }

    /**
     * Test for the setter of the description method
     * O(1)
     */
    public void testSetLocationsn() {        
        ArrayList<Node<Point>> narr2 = new ArrayList<Node<Point>>();
        narr2.add(n2);
        narr2.add(n1);
        narr2.add(n3);
        sn1.locations(narr2);
        assertEquals(narr2, sn1.locations());
    }
    
    /**
     * Test getter method for distance
     * O(1)
     */
    public void testAddPoint() {
        Point point4 = new Point(0, 0);
        ArrayList<String> descriptions4 = new ArrayList<String>();
        descriptions4.add("Maxime Martin's mum");
        Node<Point> n4 = new Node<Point>(point4, descriptions4);
        Node<Point> n5 = null;
        assertTrue(sn1.addPoint(n4));
        assertFalse(sn1.addPoint(n5));
    }
    
    /**
     * Test for toString method
     * O(1)
     */
    public void testToString() {
        assertEquals("The locations on Mermaid-Stoney Batter Creek Rd. are : "
                     + "[(1,1) La petite cuisine , (2,2) null , (-2,-2) ziggurat ]", sn1.toString());
    }
    
    /**
     * Test compareTo method of StreetNodes
     * O(1)
     */
    public void testCompareTo() {
        assertTrue(sn1.compareTo(sn2) > 0);
        assertTrue(sn2.compareTo(sn1) < 0);
        sn3.locations(narr1);
        sn3.street("Mermaid-Stoney Batter Creek Rd.");
        assertTrue(sn1.compareTo(sn3) == 0);
    }
}