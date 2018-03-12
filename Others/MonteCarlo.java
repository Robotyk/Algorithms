import java.util.ArrayList;
import java.util.List;

/**
 *  Calculating PI number using Monte Carlo method
 */
public class MonteCarlo {

    private Integer squareSize;
    private Integer circleRadius;

    MonteCarlo(Integer squareSize) {
        this.squareSize = squareSize;
        this.circleRadius = squareSize / 2;
    }

    public Double calculatePi(Integer numberOfPointsToDraw) {
        List<List<Double>> randomPoints = getRandomPoints(numberOfPointsToDraw);
        List<Double> distanceFromCenter = getDistancesFromCenter(randomPoints);
        Integer pointsInsideCircle = countPointsInsideCircle(distanceFromCenter);
        return calculate(numberOfPointsToDraw, pointsInsideCircle);
    }

    private List<List<Double>> getRandomPoints(Integer numberOfPoints) {
        List<List<Double>> randomPoints = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            randomPoints.add(new ArrayList<>());
            randomPoints.get(i).add(Math.random() * squareSize);
            randomPoints.get(i).add(Math.random() * squareSize);
        }
        return randomPoints;
    }

    private List<Double> getDistancesFromCenter(List<List<Double>> randomPoints) {
        List<Double> distanceFromCenter = new ArrayList<>();
        for (List<Double> randomPoint : randomPoints) {
            distanceFromCenter.add(calculateDistance(randomPoint.get(0), randomPoint.get(1)));
        }
        return distanceFromCenter;
    }

    private Double calculateDistance(Double axisX, Double axisY) {
        return Math.sqrt(Math.pow(axisX - squareSize / 2, 2) + Math.pow(axisY - squareSize / 2, 2));
    }

    private Integer countPointsInsideCircle(List<Double> distanceFromCenter) {
        Integer pointsInsideCircle = 0;
        for (Double point : distanceFromCenter) {
            if (point <= circleRadius) {
                pointsInsideCircle++;
            }
        }
        return pointsInsideCircle;
    }

    private Double calculate(Integer numberOfPoints, Integer pointsInsideCircle) {
        return ((double) pointsInsideCircle / numberOfPoints) * 4;
    }
}
