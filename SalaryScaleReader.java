import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

public class SalaryScaleReader {
    private String filePath = "C:\\Users\\harri\\Desktop\\Object-Oriented-Development-Project\\Project\\src\\SalaryScale";
    private double salary = -1;
    private String line;
    private String newPosition;
    private int newSalaryScale;

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


    // Find position of new salary scale within same position if new salary scale exists in the employees current position
    // If salary scale doesnt exist in current position the current position needs to change to the new position category and set the salary scale to the first in that position category
    public double getNewSalary(String position, int newSalaryScale) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            newPosition = position;
            this.newSalaryScale = newSalaryScale;
            String nextLine = br.readLine();

            while (nextLine != null) {
                nextLine = nextLine.trim();

                if (nextLine.isEmpty()) {
                    continue;
                }

                String[] data = nextLine.split(",");
                line = nextLine;
                nextLine = br.readLine();

                if (data.length > 2) {
                    String currentPosition = data[0].trim();
                    int scalePoint = Integer.parseInt(data[1].trim());
                    if (currentPosition.equals(position) && scalePoint == newSalaryScale) {
                        try {
                            salary = Double.parseDouble(data[2].trim());
                            break;
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

    public String getNewPosition() {
        return newPosition;
    }

    public int getNewSalaryScale() {
        return newSalaryScale;
    }
}
