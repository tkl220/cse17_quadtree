package map;

/**
 * Point class for assigning coordinates x and y
 * 
 * Part 1:
 * @author T. Kingsley Leighton 0%
 * @author Wesley Olsen 70%
 * @author Maxime Martin 30%
 * @version 18 April 2018
 */

public class Point {
    private int x;
    private int y;

    /**
     * 
     * @param x its our x coordinate
     * @param y its our y coordinate 
     * They are both used for locating what we
     * wish to find
     * O(1)
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x the coordinate
     * This is the getter method and will return the x
     * value when called
     * O(1)
     */
    public int x() {
        return x;
    }

    /**
     * 
     * @param x the coordinate
     * This is the setter method and will enable us to
     * change the value of x when called
     * O(1)
     */
    public void x(int x) {
        this.x = x;
    }

    /**
     * @return y the coordinate
     * This is the getter method and will return the y
     * value when called
     * O(1)
     */
    public int y() {
        return y;
    }

    /**
     * 
     * @param y the coordinate
     * This is the setter method and will enable us to
     * change the value of y when called
     * O(1)
     */
    public void y(int y) {
        this.y = y;
    }

    /**
     * @param o object to be compared
     * @return true or false based on whether this point object equals o or not
     * This method check if the x and y value are the same or not
     * O(1)
     */
    public boolean equals(Object o) {
        if (o instanceof Point) {
            if (x == ((Point) o).x() && y == ((Point) o).y()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return point in string format (x,y)
     * This is the toString method that will print a String representation of
     * our x and y value
     * O(1)
     */
    public String toString() {
        return "(" + x() + "," + y() + ")";
    }
}