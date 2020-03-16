package map;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

/**
 * test class for testing all methods in Quad class
 * 
 * Part 1:
 * @author T. Kingsley Leighton 80%
 * @author Wesley Olsen 0%
 * @author Maxime Martin 20%
 * @version 18 April 2018
 * Part 2:
 * @author T. Kingsley Leighton 0%
 * @author Amy Chai 30%
 * @author Zachary Weinert 0%
 * @version 26 April 2018 
 */
public class TestQuad extends TestCase {
    private Quad evenMap;
    private Quad oddMap;
    private Quad map3;
    private Quad map4;
    private Quad map5;
    private String[] places;
    private String[] streets;
    /**
     * setUp ran before every test case
     * O(1)
     */
    public void setUp() {
        evenMap = new Quad(new Point(0, 0), new Point(64, 64));
        oddMap = new Quad(new Point(0, 0), new Point(3, 3));
        map3 = new Quad(new Point(0, 0), new Point(1, 1));
        map4 = new Quad(new Point(0, 0), new Point(8, 8));
        map5 = new Quad(new Point(0, 0), new Point(8, 8));
        places = new String[16];
        places[0] = "cat";
        places[1] = "fat";
        places[2] = "pat";
        places[3] = "god";
        places[4] = "dog";
        places[5] = "log";
        places[6] = "fog";
        places[7] = "tag";
        places[8] = "gag";
        places[9] = "tac";
        places[10] = "tic";
        places[11] = "hat";
        places[12] = "two";
        places[13] = "pit";
        places[14] = "you";
        places[15] = "mat";
        streets = new String[16];
        streets[0] = "A Ave.";
        streets[1] = "B Blvd.";
        streets[2] = "C Ct.";
        streets[3] = "D Dr.";
        streets[4] = "E St.";
        streets[5] = "F Cr.";
        streets[6] = "G";
        streets[7] = "H Hwy.";
        streets[8] = "I";
        streets[9] = "J";
        streets[10] = "K";
        streets[11] = "L Ln.";
        streets[12] = "M";
        streets[13] = "N";
        streets[14] = "O";
        streets[15] = "P Pl.";
    }
    /**
     * test insert() of Quad
     * O(1)
     */
    public void testInsert() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                oddMap.insert(i, j, places[4*i+j]);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                evenMap.insert(i, j, places[4*i+j]);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map3.insert(i, j, places[4*i+j], streets[4*i+j]);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map4.insert(i, j, places[4*i+j], streets[4*i+j]);
            }
        }
        map4.insert(7, 6, "gym", "A Ave.", "B Blvd.", "C Ct.", "D Dr.", "E St.", "F Cr.", "G");
        map4.insert(6, 6, null, null);
        map5.insert(3,4, "guy", "A Ave.");
    }
    /**
     * tests all three streetSearch() methods the order:
     * streetSearch(String)
     * streetSearch(String, String)
     * streetSearch(int, int, String, String)
     * O(1)
     */
    public void testStreetSearch() { 
        testInsert();
        
        assertTrue(map3.streetSearch("A Ave.").size() == 0);
        assertTrue(map3.streetSearch("B Blvd.").size() == 0);
        assertTrue(map3.streetSearch("E St.").size() == 0);
        assertTrue(map3.streetSearch("Fouh Sho").size() == 0);
        assertTrue(map3.streetSearch("F Cr.").size() == 1 && map3.streetSearch("F Cr.").get(0).descriptions().contains("log"));
        
        for(int i = 0; i < 16; i++) {
            ArrayList<String> place = new ArrayList<String>();
            place.add(places[i]);
            ArrayList<Node<Point>> result = map4.streetSearch(streets[i]);
            // first five streets only exist for gym node
            if (i < 5) {
                assertEquals(result.size(), 1);
                assertTrue(result.get(0).descriptions().contains("gym"));
            }
            // 5 and 6 contain gym node and the respective places element
            else if (i <= 6) {
                assertEquals(result.size(), 2);
                assertTrue(result.get(0).descriptions().contains(places[i]) || result.get(1).descriptions().contains(places[i]));
                assertTrue(result.get(0).descriptions().contains("gym") || result.get(1).descriptions().contains("gym"));
            }
            // 8 and 12 don't exist on map4
            else if (i == 8 || i == 12) {
                assertTrue(result.size() == 0);
            }
            // remaining node contain only there respective places element
            else {
                assertEquals(result.size(), 1);
                assertTrue(result.get(0).descriptions().contains(places[i]));
                assertFalse(result.get(0).descriptions().contains("gym"));
            }
        }
        // map3 only contain one node (1, 1) [log] and one street "F Cr."
        ArrayList<Node<Point>> result = map3.streetSearch("F Cr.", "log");
        assertTrue(result.size() == 1 && result.get(0).descriptions().contains("log"));
        assertFalse(result.get(0).descriptions().contains("gym"));
        result = map3.streetSearch("F Cr.", "gym");
        assertTrue(result.isEmpty());
        assertTrue(map3.streetSearch("A Ave.", "cat").size() == 0);
        // place that exist and the roads on which they exist
        result = map4.streetSearch("F Cr.", "log");
        assertTrue(result.size() == 1 && result.get(0).descriptions().contains("log"));
        result = map4.streetSearch("F Cr.", "gym");
        assertTrue(result.size() == 1 && result.get(0).descriptions().contains("gym"));
        // places that exist on roads where they don't exist
        result = map4.streetSearch("A Ave.", "log");
        assertTrue(result.isEmpty());
        result = map4.streetSearch("H Hwy.", "gym");
        // places that exist on roads that don't exist
        assertTrue(result.isEmpty());
        result = map4.streetSearch("R Rt.", "fog");
        assertTrue(result.size() == 0);
        result = map4.streetSearch("S St.", "tic");
        assertTrue(result.size() == 0);
        // places that don't exist on roads that exist
        result = map4.streetSearch("A Ave.", "yyy");
        assertTrue(result.isEmpty());
        result = map4.streetSearch("B Blvd.", "xxx");
        assertTrue(result.isEmpty());
        // places that don't exist on roads that don't exist
        result = map4.streetSearch("R Rt.", "yyy");
        assertTrue(result.size() == 0);
        result = map4.streetSearch("S St.", "xxx");
        assertTrue(result.size() == 0);
        
        // adding additional nodes to help test streetSearch
        map4.insert(6, 6, "log", "P Pl.");
        map4.insert(7, 6, "log", "P Pl.");
        map4.insert(6, 7, "log", "P Pl.");
        map4.insert(7, 7, "log", "P Pl.");

        result = map4.streetSearch(1, 2, "P Pl.", "log");
        // ordering should correspond to minHeap on distance
        assertTrue(result.get(0).distance() <= result.get(1).distance() &&
                   result.get(1).distance() <= result.get(2).distance() && 
                   result.get(2).distance() <= result.get(3).distance());
        // confirm correct order of point by coordinates
        assertTrue(result.get(0).point().equals(new Point(6, 6)) &&
                   result.get(1).point().equals(new Point(6, 7)) &&
                   result.get(2).point().equals(new Point(7, 6)) &&
                   result.get(3).point().equals(new Point(7, 7)));
        result = map4.streetSearch(7, 7, "P Pl.", "log");
        // same with origin coincident with one node
        assertTrue(result.get(0).distance() <= result.get(1).distance() &&
                   result.get(1).distance() <= result.get(2).distance() && 
                   result.get(2).distance() <= result.get(3).distance());
        assertTrue(result.get(0).point().equals(new Point(7, 7)) &&
                   (result.get(1).point().equals(new Point(6, 7)) || result.get(1).point().equals(new Point(7, 6))) &&
                   (result.get(2).point().equals(new Point(6, 7)) || result.get(2).point().equals(new Point(7, 6))) &&
                   result.get(3).point().equals(new Point(6, 6)));
        result = map4.streetSearch(98, -7, "P Pl.", "log");
        assertTrue(result.size() == 0);
        // check for out of bound origin properly handled
        result = map4.streetSearch(6, 6, "Log Ln.", "log");
        assertTrue(result.size() == 0);
    }
    /**
     * test search(Point p)
     * O(1)
     */
    public void testSearchPoint() {
        testInsert();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Node<Point> result = oddMap.search(i, j);
                if (result == null) {
                    assertNull(result); 
                } else {
                    assertTrue(result.point().equals(new Point(i, j)));
                }
            }
        }
        assertNull(oddMap.search(-1, -3));
        assertNull(oddMap.search(63, 63));
        assertNull(evenMap.search(63, 63));
        assertFalse(oddMap.search(1, 1).point().equals(new Point(-1, -3)));
    }
    /**
     * test search type(String type_of_place)
     * O(1)
     */
    public void testSearchType() {
        testInsert();
        ArrayList<String> bad = new ArrayList<String>(Arrays.asList("cat", "fat", "pat", "god", "dog", "gag", "two"));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ArrayList<Node<Point>> results = oddMap.search(places[4*i+j]);
                if (bad.contains(places[4*i+j])) {
                    assertEquals(0, results.size());
                }
                else {
                    assertEquals(1, results.size());
                    assertTrue(results.get(0).point().equals(new Point(i, j)));
                    assertTrue(results.get(0).descriptions().contains(places[4*i+j]));
                }
            }
        }
        assertEquals(0, oddMap.search("per").size());
        assertEquals(0, oddMap.search("1294").size());
        assertEquals(0, evenMap.search("tic tac").size());
    }
    /**
     * test contains()
     * O(1)
     */
   public void testContains() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(!(i == 0 || j == 0)) {
                    assertTrue(evenMap.contains(new Point(i, j)));
                }
                else {
                    assertFalse(evenMap.contains(new Point(i, j)));
                }
            }
        }
    }
}