package bearmaps.proj2ab;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import edu.princeton.cs.algs4.Stopwatch;

public class KDTree implements PointSet{

    private static final boolean splitX = true;
    private Node root;

    private class Node{
        private Point point;
        private boolean splitDir;
        private Node left;
        private Node right;

        public Node(Point point, boolean splitDir) {
            this.point = point;
            this.splitDir = splitDir;
            this.left = null;
            this.right = null;
        }

        public Point getPoint() {
            return this.point;
        }
        public boolean getDir() {
            return this.splitDir;
        }
        public Node getLeft() {
            return this.left;
        }
        public Node getRight() {
            return this.right;
        }
    }

    public KDTree(List<Point> points) {
        for (Point pt: points) {
            root = push(root, pt, splitX);
        }
    }

    private Node push(Node root, Point pt, boolean splitDir) {
        if (root == null) {
            return new Node(pt, splitDir);
        } else if (root.getPoint().equals(pt)) {
            return root;
        }
        int cmp = comparePoints(pt, root.getPoint(), splitDir);
        if (cmp < 0) {
            root.left = push(root.left, pt, !splitDir);
        } else {
            root.right = push(root.right, pt, !splitDir);
        }
        return root;
    }

    private int comparePoints(Point p1, Point p2, boolean splitDir) {
        if (splitDir == splitX) {
            return Double.compare(p1.getX(), p2.getX());
        } else {
            return Double.compare(p1.getY(), p2.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return nearest(root, target, root.getPoint());
    }
    private Point nearest(Node root, Point target, Point best) {
        if (root == null) {
            return best;
        }
        double bestDist = Point.distance(target, best);
        double currDist = Point.distance(target, root.getPoint());
        if (Double.compare(currDist, bestDist) < 0) {
            best = root.getPoint();
        }
        Node goodSideNode;
        Node badSideNode;
        int cmp = comparePoints(target, root.getPoint(), root.getDir());
        if (cmp < 0) {
            goodSideNode = root.getLeft();
            badSideNode = root.getRight();
        } else {
            goodSideNode = root.getRight();
            badSideNode = root.getLeft();
        }
        best = nearest(goodSideNode, target, best);
        if (isWorthLooking(root, target, best)) {
            best = nearest(badSideNode, target, best);
        }
        return best;
    }
    private boolean isWorthLooking(Node node, Point target, Point best) {
        double bestDist = Point.distance(target, best);
        double badDist;
        if (node.getDir() == splitX) {
            badDist = Point.distance(target, new Point(node.getPoint().getX(), target.getY()));
        } else {
            badDist = Point.distance(target, new Point(target.getX(), node.getPoint().getY()));
        }
        return Double.compare(badDist, bestDist) < 0;
    }

    public static void main(String[] args) {
        Random r = new Random(500);
        int[] Nlist = new int[]{31250, 62500, 125000, 250000, 500000, 1000000, 2000000};
        for (int n : Nlist) {
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                points.add(new Point(r.nextDouble(), r.nextDouble()));
            }
            Stopwatch timer = new Stopwatch();
            KDTree kdt = new KDTree(points);
            double time = timer.elapsedTime();
            System.out.println("number of points added: " + n + ", time used: " + time + 's');
        }
    }

}
