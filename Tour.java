public class Tour {
    private Node start;
    private int size;
    private double length;

    private class Node {
        private Point p;
        private Node next;
    }

    public Tour() {
        start = null;
        size = 0;
        length = 0.0;
    }

    public Tour(Point a, Point b, Point c, Point d) {
        Node nodeA = new Node();
        nodeA.p = a;
        Node nodeB = new Node();
        nodeB.p = b;
        Node nodeC = new Node();
        nodeC.p = c;
        Node nodeD = new Node();
        nodeD.p = d;

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeA;

        start = nodeA;
        size = 4;
        length = a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(d) + d.distanceTo(a);
    }

    public int size() {
        return size;
    }

    public double length() {
        return length;
    }

    public String toString() {
        if (size == 0) return "";

        StringBuilder sb = new StringBuilder();
        Node current = start;
        do {
            sb.append(current.p.toString()).append("\n");
            current = current.next;
        } while (current != start);

        return sb.toString();
    }

    public void draw() {
        if (size == 0) return;

        Node current = start;
        do {
            current.p.drawTo(current.next.p);
            current = current.next;
        } while (current != start);
    }

    public void insertNearest(Point p) {
        if (size == 0) {
            Node newNode = new Node();
            newNode.p = p;
            newNode.next = newNode; // point to itself
            start = newNode;
            size = 1;
            length = 0.0;
            return;
        }

        Node nearestNode = start;
        double minDistance = Double.POSITIVE_INFINITY;
        Node current = start;
        do {
            double distance = current.p.distanceTo(p);
            if (distance < minDistance) {
                minDistance = distance;
                nearestNode = current;
            }
            current = current.next;
        } while (current != start);

        Node newNode = new Node();
        newNode.p = p;
        newNode.next = nearestNode.next;
        nearestNode.next = newNode;

        size++;
        length += minDistance + p.distanceTo(newNode.next.p) - nearestNode.p.distanceTo(
                newNode.next.p);
    }

    public void insertSmallest(Point p) {
        if (size == 0) {
            Node newNode = new Node();
            newNode.p = p;
            newNode.next = newNode; // point to itself
            start = newNode;
            size = 1;
            length = 0.0;
            return;
        }

        Node bestNode = start;
        double minIncrease = Double.POSITIVE_INFINITY;
        Node current = start;
        do {
            double increase = p.distanceTo(current.p) + p.distanceTo(current.next.p)
                    - current.p.distanceTo(current.next.p);
            if (increase < minIncrease) {
                minIncrease = increase;
                bestNode = current;
            }
            current = current.next;
        } while (current != start);

        Node newNode = new Node();
        newNode.p = p;
        newNode.next = bestNode.next;
        bestNode.next = newNode;

        size++;
        length += minIncrease;
    }

    public static void main(String[] args) {
        Point a = new Point(1.0, 1.0);
        Point b = new Point(1.0, 4.0);
        Point c = new Point(4.0, 4.0);
        Point d = new Point(4.0, 1.0);


        Tour squareTour = new Tour(a, b, c, d);
        int size = squareTour.size();
        System.out.println("Number of points = " + size);

        double length = squareTour.length();
        System.out.println("Tour length = " + length);

        System.out.println(squareTour);

        StdDraw.setXscale(0, 6);
        StdDraw.setYscale(0, 6);
        squareTour.draw();
    }
}
