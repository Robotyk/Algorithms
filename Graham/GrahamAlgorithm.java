import java.util.ArrayList;
import java.util.Stack;

/**
 *  Graham's scan is a method of finding the convex hull of a finite set of points
 */
public class GrahamAlgorithm {

    private ArrayList<Point> pointsFromFile;

    public Stack<Point> doGrahamAlgorithm(String dataPath) {
        getPointsFromFile(dataPath);
        Point firstPoint = findFirstPoint();
        bubbleSortPoints(firstPoint);
        return performGrahamAlgorithm(firstPoint);
    }

    private void getPointsFromFile(String dataPath) {
        DataReader dataReader = new DataReader(dataPath);
        pointsFromFile = dataReader.getDataFromFile();
    }

    private Point findFirstPoint() {
        Point point = pointsFromFile.get(0);

        for (Point p: pointsFromFile) {
            if (p.getX() > point.getX() || (p.getX() == point.getX() && p.getY() < point.getY())) {
                point = p;
            }
        }
        pointsFromFile.remove(point);
        return point;
    }

    private void bubbleSortPoints(Point point) {
        for (int i = 0; i < pointsFromFile.size(); i++) {
            for (int j = i + 1; j < pointsFromFile.size(); j++) {
                if (!isLeft(pointsFromFile.get(i), pointsFromFile.get(j), point)) {
                    Point temp = new Point(pointsFromFile.get(i).getX(), pointsFromFile.get(i).getY());
                    pointsFromFile.set(i, pointsFromFile.get(j));
                    pointsFromFile.set(j, temp);
                }
            }
        }
    }

    private boolean isLeft(Point a, Point b, Point p) {
        return ((p.getX() - a.getX()) * (b.getY() - a.getY()) - (p.getY() - a.getY()) * (b.getX() - a.getX())) < 0;
    }

    private Stack<Point> performGrahamAlgorithm(Point point) {
        Stack<Point> stack = new Stack<>();
        stack.push(point);
        stack.push(pointsFromFile.get(0));
        int i = 0;
        while (i < pointsFromFile.size()) {
            if (stack.peek().equals(point)) {
                stack.push(pointsFromFile.get(i));
                i++;
            }
            Point peekPoint = stack.pop();
            if (isLeft(peekPoint, pointsFromFile.get(i), stack.peek())) {
                stack.push(peekPoint);
                stack.push(pointsFromFile.get(i));
                i++;
            }
        }
        return stack;
    }

    public void printResult(Stack<Point> points) {
        points.forEach(point -> System.out.println(point.getX() + " " + point.getY()));
    }
}
