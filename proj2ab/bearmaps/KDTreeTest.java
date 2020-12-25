package bearmaps;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;


public class KDTreeTest {
    private static Random r = new Random(500);

    @Test
    public void testLectureExample() {
        Point a = new Point(2, 3);
        Point b = new Point(4, 2);
        Point c = new Point(4, 5);
        Point d = new Point(3, 3);
        Point e = new Point(1, 5);
        Point f = new Point(4, 4);

        NaivePointSet nps = new NaivePointSet(List.of(a,b, c, d, e, f));
        Point expected = nps.nearest(0, 7);
        KDTree kdt = new KDTree(List.of(a,b, c, d, e, f));
        Point actual = kdt.nearest(0, 7);
        assertEquals(expected, actual);
    }

    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(randomPoint());
        }
        return points;
    }

    private void randomizedTest(int numPoints, int numQueries) {
        List<Point> points = randomPoints(numPoints);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);
        List<Point> queries = randomPoints(numQueries);
        for (Point pt : queries) {
            Point expected = nps.nearest(pt.getX(), pt.getY());
            Point actual = kdt.nearest(pt.getX(), pt.getY());
            assertEquals(Point.distance(expected, pt), Point.distance(actual, pt), 0.000001);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void randomizedTest1() {
        int numPoints = 30;
        int numQueries = 2;
        randomizedTest(numPoints, numQueries);
    }

    @Test
    public void randomizedTest2() {
        int numPoints = 1000;
        int numQueries = 200;
        randomizedTest(numPoints, numQueries);
    }

    @Test
    public void randomizedTest3() {
        int numPoints = 100000;
        int numQueries = 2000;
        randomizedTest(numPoints, numQueries);
    }


}
