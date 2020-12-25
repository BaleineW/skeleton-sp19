package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Point nearestPoint = points.get(0);
        double nearesrDistance = Point.distance(target, nearestPoint);
        for (int i = 1; i < points.size(); i++) {
            Point currPoint = points.get(i);
            if (Point.distance(target, currPoint) < nearesrDistance) {
                nearesrDistance = Point.distance(target, currPoint);
                nearestPoint = currPoint;
            }
        }
        return nearestPoint;
    }
}
