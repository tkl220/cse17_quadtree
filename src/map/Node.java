package map;

import java.util.ArrayList;

/**
 * 
 * @param <T> generic type of Node
 * Part 1:
 * @author T. Kingsley Leighton 10%
 * @author Wesley Olsen 60%
 * @author Maxime Martin 30%
 * @version 18 April 2018
 * Part 2:
 * @author T. Kingsley Leighton 0%
 * @author Amy Chai 0%
 * @author Zachary Weinert !00%
 * @version 26 April 2018
 * Gives coordinates 
 */
public class Node<T> implements Comparable<Node<T>>{

    private T point;
    private double distance;
    private ArrayList<String> streets;
    private ArrayList<String> descriptions;
    
    /**
     * @param point the point to be set to
     * @param arr the arr of Strings to set descriptions to
     * This is the constructor with
     * 2 parameters
     */
    public Node(T point, ArrayList<String> arr) {
        this.point = point;
        descriptions = arr;
        streets = new ArrayList<String>();
        distance = 0;
    }

    /**
     * 
     * @return the point
     * This is the getter method and will return the
     * point value when called
     * O(1)
     */
    public T point() {
        return point;
    }

    /**
     * 
     * @param point
     * This is the setter method and will enable us 
     * to change the value of point when called
     * O(1)
     */
    public void point(T point) {
        this.point = point;
    }

    /**
     * 
     * @return the descriptions
     * This is the getter method and will return
     * the description value when called
     * O(1)
     */
    public ArrayList<String> descriptions() {
        return descriptions;
    }

    /**
     * 
     * @param descriptions
     * This is the setter method and will enable us 
     * to change the value of description when called
     * O(1)
     */
    public void descriptions(ArrayList<String> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * the getter for streets
     * @return the streets
     * O(1)
     */
    public ArrayList<String> streets() {
        return streets;
    }

    /**
     * the setter for streets
     * @param streets set streets
     * O(1)
     */
    public void streets(ArrayList<String> streets) {
        this.streets = streets;
    }

    /**
     * the getter for distance
     * @return the distance
     * O(1)
     */
    public double distance() {
        return distance;
    }

    /**
     * the setter for distance
     * @param distance set distance
     * O(1)
     */
    public void distance(double distance) {
        this.distance = distance;
    }

    /**
     * @return str description below 
     * This is the toString method and will 
     * return a String representation of our point and 
     * description values
     * O(n)
     */

    public String toString() {
        String str = point.toString() + " ";
        for (String i : descriptions) {
            str += i + " ";
        }
        return str;
    }
    
    /**
     * @param n is the node to be compared to
     * @return an integer value the difference in distances
     * comapreTo method returns an integer value based on 
     * this node's distance minus the node passed to compareTo
     * O(1)
     */
    public int compareTo(Node<T> n) {
        if(distance - n.distance() > 0) {
            return (int) Math.ceil(distance - n.distance());
        }
        return (int) Math.floor(distance - n.distance());
    }
}