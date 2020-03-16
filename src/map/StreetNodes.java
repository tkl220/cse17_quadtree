
package map;

import java.util.ArrayList;

/**
 * StreetNodes class for instances of streets and the places
 * on those streets
 * 
 * @author T. Kingsley Leighton 0%
 * @author Zachary Weinert 100%
 * @author Amy Chai 0%
 * @version 26 April 2018
 */
public class StreetNodes implements Comparable<StreetNodes>{

    private String street;
    private ArrayList<Node<Point>> locations;
    
    /**
     * @param street the name of the street name
     * 1 arg constructor for StreetNodes
     */
    public StreetNodes(String street) {
        this.street = street;
        locations = new ArrayList<Node<Point>>();
    }
    /**
     * getter for street
     * @return the street
     * O(1)
     */
    public String street() {
        return street;
    }
    /**
     * setter for street
     * @param street set street
     * O(1)
     */
    public void street(String street) {
        this.street = street;
    }
    /**
     * getter for locations
     * @return the locations
     * O(1)
     */
    public ArrayList<Node<Point>> locations() {
        return locations;
    }
    /**
     * setter for locations
     * @param locations set locations
     * O(1)
     */
    public void locations(ArrayList<Node<Point>> locations) {
        this.locations = locations;
    }
    /**
     * adds a node n to locations
     * @param n the node to be added
     * @return true if node successfully added
     * O(1)
     */
    public boolean addPoint(Node<Point> n) {
        if(n == null) {
            return false;
        }
        locations.add(n);
        return true;
    }
    /**
     * converts fields of StreetNodes to a string
     * O(1)
     * @return String of information representing StreetNodes
     */
    public String toString() {
        return "The locations on " + street + " are : " + locations.toString();
    }
    /**
     * compares street names of StreetNodes
     * @param o the StreetNodes to be compared to
     * @return integer representing the result of comparing
     * street to the street name of o
     * O(1)
     */
    public int compareTo(StreetNodes o) {
        return street.compareTo(o.street());
    }
}