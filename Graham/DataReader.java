import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

    private final String FILE;
    private String line = "";

    public DataReader(String file) {
        this.FILE = file;
    }

    public ArrayList<Point> getDataFromFile() {
        ArrayList<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                points.add(getPointData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    private Point getPointData() {
        String[] data = line.split(",");
        Double horizontalAxis = Double.parseDouble(data[0]);
        Double verticalAxis = Double.parseDouble(data[1]);
        return new Point(horizontalAxis, verticalAxis);
    }
}