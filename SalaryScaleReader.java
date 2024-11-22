import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

public class SalaryScaleReader {
    private String filePath = "src/SalaryScale";
    private double salary = -1;
    private String line;

    public SalaryScaleReader() {
    }

    public double getSalaryScaleForPoint(String position, int salaryScale) {


        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length > 2) {
                    String currentPosition = data[0].trim();
                    int scalePoint = Integer.parseInt(data[1].trim());

                    if (currentPosition.equals(position)) {
                        try {

                            if (scalePoint == salaryScale) {
                                salary = Double.parseDouble(data[2].trim());
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Number format problem");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the csv file");
        }
        return salary;
    }
}