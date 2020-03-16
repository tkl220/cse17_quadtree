package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Quad class for construction of a quadTree for a map
 * utilizing other classes
 * 
 * Part 1:
 * @author T. Kingsley Leighton 70%
 * @author Wesley Olsen 0%
 * @author Maxime Martin 30%
 * @version 18 April 2018
 * Part 2:
 * @author T. Kingsley Leighton 60%
 * @author Amy Chai 30%
 * @author Zachary Weinert 10%
 * @version 26 April 2018 
 */
public class Quad {
    private static HashMap<Node<Point>, ArrayList<Node<Point>>> hMap = new HashMap<Node<Point>, ArrayList<Node<Point>>>();;
    private BinarySearchTree<StreetNodes> streetNodesBST;
    private Node<Point> place;
    private Point topLeft;
    private Point botRight;
    private Quad topLeftTree;
    private Quad topRightTree;
    private Quad botLeftTree;
    private Quad botRightTree;
    
    /**
     * constructor 
     * @param topLeft top left coordinate of quad
     * @param botRight bottom right coordinate of quad
     */
    public Quad(Point topLeft, Point botRight) {
        this.topLeft = topLeft;
        this.botRight = botRight;
        topLeftTree = null;
        topRightTree = null;
        botLeftTree = null;
        botRightTree = null;
        streetNodesBST = new BinarySearchTree<StreetNodes>();
    }
    /**
     * insert method insuring all nodes are on correct streets and vice versa
     * @param x the x coordinate
     * @param y the y coordinate
     * @param description of the place being inserted
     * @param streets the streets that the place is on
     * O(n + log4(Q)) n is size of vararg streets and Q is the number of quads in the quad tree
     */
    public void insert(int x, int y, String description, String... streets) {
        if(description == null || streets == null) {
            return;
        }
        
        ArrayList<String> desc = new ArrayList<String>();
        desc.add(description);
        Node<Point> newNode = new Node<Point>(new Point(x, y), desc);
        if (!contains(newNode.point())) {
            return;
        }

        if(!(search(description).contains(search(new Point(x,y))))) {
            //System.out.println("it was not true");
            Quad quad = insert(newNode);
            for(String s: streets) {
                newNode.streets().add(s);
                StreetNodes newSn = new StreetNodes(s);
                StreetNodes sn = quad.streetNodesBST.find(newSn);
                if (sn == null) {
                    //System.out.println("adding new street node");
                    newSn.addPoint(newNode);
                    quad.streetNodesBST.insert(newSn);
                }
                else {
                    sn.addPoint(newNode);
                    //System.out.println("street node already exists");
                }
            }
        }
    }
    /**
     * 
     * @param node
     * @param neighbor
     */
    private void addNeighbor(Node<Point> node) {
        ArrayList<Node<Point>> neighbors;
        for(String street: node.streets()) {
            Node<Point> neighbor = findNeighbor(node, street);
            
            if (neighbor == null) {
                continue;
            }
            if ((neighbors = hMap.get(node)) == null) {
                neighbors = new ArrayList<Node<Point>>();
                hMap.put(node, neighbors);
            }
            neighbors.add(neighbor);
            hMap.get(neighbor).add(node);
        }
    }
    private Node<Point> findNeighbor(Node<Point> node, String street) {
        double distance;
        double closest = Math.pow((botRight.x() - topLeft.x()), 2) +
                         Math.pow((botRight.y() - topLeft.y()), 2);
        Node<Point> neighbor = null;
        ArrayList<Node<Point>> candidates;
        candidates = streetSearch(street);
        for (Node<Point> c : candidates) {
            distance = Math.sqrt(Math.pow((node.point().x() - c.point().x()), 2) +
                                 Math.pow((node.point().y() - c.point().y()), 2));
            if (distance < closest) {
                int count = 0;
                for (Node<Point> cNeighbor : hMap.get(c)) {
                    if (cNeighbor.streets().contains(street)) {
                        if (++count > 1) {
                            break;
                        }
                    }
                }
                if (count < 2) {
                    closest = distance;
                    neighbor = c;
                }
            }
        }
        return neighbor;
    }
    /**
     * O(log4(n) + B) n is size of Quad and B is size of binarySearchTree
     * 
     * finds all nodes on the specified street wrapper method
     * @param streetName the street to be searched
     * @return locations on street searched
     */
    public ArrayList<Node<Point>> streetSearch(String streetName) {
        ArrayList<Node<Point>> places = new ArrayList<Node<Point>>();
        return streetSearch(streetName, places);
    }
    /**
     * O(log4(n) + B) n is number of quads in quad tree and B is the number of nodes in binarySearchTree (Unbalanced)
     * 
     * streetSearch helper method, searches based on the type_of_place
     * 
     * @param streetName the street to be searched for
     * @param places ArrayList of nodes
     * @return places ArrayList of nodes
     */
    public ArrayList<Node<Point>> streetSearch(String streetName, ArrayList<Node<Point>> places) {
        //base case
        if (!streetNodesBST.isEmpty()) {
            if (streetNodesBST.find(new StreetNodes(streetName)) != null) {
                return streetNodesBST.find(new StreetNodes(streetName)).locations();
            }
        }
        //recursive case
        if (topLeftTree != null) {
            places.addAll(topLeftTree.streetSearch(streetName, places));
        }
        if (topRightTree != null) {
            places.addAll(topRightTree.streetSearch(streetName, places));
        }
        if (botLeftTree != null) {
            places.addAll(botLeftTree.streetSearch(streetName, places));
        }
        if (botRightTree != null) {
            places.addAll(botRightTree.streetSearch(streetName, places));
        }
        return places;
    }
    /**
     * finds all nodes containing a specified description on a specified street
     * @param streetName the street to be searched
     * @param type_of_place the type of place to be searched for
     * @return result the array of nodes on specified street with type_of_place
     *         description or null if street does not exist
     * O(n + log4(Q) + B) n is the size of result, Q is number of quads in quad tree 
     * and B is the number of nodes in binarySearchTree (Unbalanced)
     */
    public ArrayList<Node<Point>> streetSearch(String streetName, String type_of_place) {
       ArrayList<Node<Point>> result = streetSearch(streetName);
       ArrayList<Node<Point>> finalResult = new  ArrayList<Node<Point>>();
       for(Node<Point> n: result) {
           if (n.descriptions().contains(type_of_place)) {
               finalResult.add(n);
           }
       }
       return finalResult;
    }
    /**
     * finds all nodes containing a specified description on a specified street,
     * pushes them on to a minHeap based on distance from origin, and returns
     * a list of those nodes in ascending order based on distance
     * @param originX x coordinate of origin
     * @param originY y coordinate of origin
     * @param streetName the street to be searched
     * @param type_of_place the type of place to be searched for
     * @return result the array of nodes on specified street with type_of_place
     *         description ordered in ascending order by distance from origin
     * O(n + m + log4(Q) + B) n is the size of loc, m is number of nodes on the street, Q is number of quads in quad tree 
     * and B is the number of nodes in binarySearchTree (Unbalanced)
     */
    public ArrayList<Node<Point>> streetSearch(int originX, int originY, String streetName, String type_of_place) {
        if(!contains(new Point(originX, originY))) {
            //System.out.println("ERROR: Origin not on map");
            return new ArrayList<Node<Point>>();
        }

        ArrayList<Node<Point>> loc = streetSearch(streetName, type_of_place);
        ArrayList<Node<Point>> updatedNodes = new ArrayList<Node<Point>>();
        for (Node<Point> n: loc) {
            n.distance(Math.sqrt(Math.pow((originX - n.point().x()), 2) + Math.pow((originY - n.point().y()), 2)));
            updatedNodes.add(n);
        }
        
        Node<Point>[] nodes = updatedNodes.toArray(new Node[updatedNodes.size()]);
        MinHeap distanceMinHeap = new MinHeap(nodes, nodes.length, nodes.length);
        Node<Point> n;
        ArrayList<Node<Point>> result = new ArrayList<Node<Point>>();        
        while(distanceMinHeap.heapsize() >= 1) {
            n = (Node) distanceMinHeap.removemin();
            result.add(n);
        }
        
        return result;
    }
    /**
     * O(log4(n))
     * 
     * wrapper method for insert
     * 
     * @param x coordinate
     * @param y coordinate
     * @param description the description of the place to be inserted
     */
    public void insert(int x, int y, String description) {
        ArrayList<String> strArr = new ArrayList<String>();
        strArr.add(description);
        insert(new Node<Point>(new Point(x, y), strArr));
    }
    /**
     * O(log4(n))
     * 
     * helper method of insert, inserts node in corresponding quad based on point,
     * if node already exist adds the description of newNode to existing node
     * 
     * @param newNode the node to be inserted
     * @return this quad, quad of insertion
     */
    public Quad insert(Node<Point> newNode) {
        int size = (botRight.x() - topLeft.x()) * (botRight.y() - topLeft.y());
        int midX = (topLeft.x() + botRight.x()) / 2;
        int midY = (topLeft.y() + botRight.y()) / 2;
        //base case
        if (newNode == null) {
            System.out.println("newNode is null");
            return null;
        }
        else if (!this.contains(newNode.point())) {
            return null;
        }
        else if (size == 1) {
            if (place == null) {
                place = newNode;
                return this;
            }
            else {
                place.descriptions().addAll(newNode.descriptions());
                return this;
            }
        }
        //recursive calls
        if (midX >= newNode.point().x() && midY >= newNode.point().y()) {
            if (topLeftTree == null) {
                topLeftTree = new Quad(topLeft, new Point(midX, midY));
            }
            topLeftTree.insert(newNode);
        }
        else if (midX >= newNode.point().x() && midY < newNode.point().y()) {
            if (botLeftTree == null) {
                botLeftTree = new Quad(new Point(topLeft.x(), midY),
                              new Point(midX, botRight.y()));
            }
            botLeftTree.insert(newNode);
        }
        else if (midX < newNode.point().x() && midY >= newNode.point().y()) {
            if (topRightTree == null) {
                topRightTree = new Quad(new Point(midX, topLeft.y()),
                               new Point(botRight.x(), midY));
            }
            topRightTree.insert(newNode);
        }
        else if (midX < newNode.point().x() && midY < newNode.point().y()) {
            if (botRightTree == null) {
                botRightTree = new Quad(new Point(midX, midY), botRight);
            }
            botRightTree.insert(newNode);
        }
        return this;
    }
    /**
     * O(1)
     * 
     * checks if quad contains a point
     * omits top left corner and as well as top and left borders from map
     * 
     * @param p point to be determined if it is in the quad
     * @return true or false based of if the point is contained in the quad
     */
    public boolean contains(Point p) {
        int left = topLeft.x();
        int top = topLeft.y();
        int right = botRight.x();
        int bot = botRight.y();

        return left < p.x() && p.x() <= right && top < p.y() && p.y() <= bot;
    }
    /**
     * O(1)
     * 
     * search wrapper method for point based search
     * 
     * @param x the x coordinate of the point to be retrieved
     * @param y the y coordinate of the point to be retrieved
     * @return result from recursive search
     */
    public Node<Point> search(int x, int y) {
        return search(new Point(x, y));
    }
    /**
     * O(log4(n))
     * 
     * search helper method, searches based on point
     * 
     * @param p the point where the Node to be retrieved is
     * @return place or null
     */
    public Node<Point> search(Point p){
        int midX = (topLeft.x() + botRight.x()) / 2;
        int midY = (topLeft.y() + botRight.y()) / 2;
        //base cases
        if (!this.contains(p)) {
            return null;
        }
        if (place != null) {
            return place;
        }
        //recursive cases  check for null
        if (midX >= p.x() && midY >= p.y()) {
            if (topLeftTree != null) {
                return topLeftTree.search(p);
            }
            else {
                return null;
            }
        }
        else if (midX >= p.x() && midY < p.y()) {
            if (botLeftTree != null) {
                return botLeftTree.search(p);                  
            }
            else {
                return null;
            }
        }
        else if (midX < p.x() && midY >= p.y()) {
            if (topRightTree != null) {
                return topRightTree.search(p);                  
            }
            else {
                return null;
            }
        }
        else { //if (midX < p.x() && midY < p.y())
            if (botRightTree != null) {
                return botRightTree.search(p);
            }
            else {
                return null;
            }                  
        }
    }
    /**
     * O(1)
     * 
     * search wrapper method for type based search
     * 
     * @param type_of_place
     * @return search result
     */
    public ArrayList<Node<Point>> search(String type_of_place) {
        ArrayList<Node<Point>> places = new ArrayList<Node<Point>>();
        return search(type_of_place, places);
    }
    /**
     * O(log4(n))
     * 
     * search helper method, searches based on the type_of_place
     * 
     * @param type_of_place the type of place to be searched for
     * @param places ArrayList of nodes
     * @return places ArrayList of nodes
     */
    public ArrayList<Node<Point>> search(String type_of_place, ArrayList<Node<Point>> places) {
        //base case
        if (place != null) {
            if (place.descriptions().contains(type_of_place)) {
                places.add(place);
            }
            if (places == null) {
                System.out.println("search: Unexpected null ArrayList");
            }
            return places;
        }
        //recursive case
        if (topLeftTree != null) {
            topLeftTree.search(type_of_place, places);
        }
        if (topRightTree != null) {
            topRightTree.search(type_of_place, places);
        }
        if (botLeftTree != null) {
            botLeftTree.search(type_of_place, places);
        }
        if (botRightTree != null) {
            botRightTree.search(type_of_place, places);
        }
        return places;
    }
    /**
     * getter for streetNodesBST
     * @return the streetNodesBST
     * O(1)
     */
    public BinarySearchTree<StreetNodes> streetNodesBST() {
        return streetNodesBST;
    }
}