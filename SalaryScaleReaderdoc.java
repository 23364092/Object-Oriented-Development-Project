import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;


/**
 * A utility class for reading and processing salary scale data
 * from a specified file. This class provides methods to retrieve
 * salary information and manage promotions based on the employee's
 * position and scale point.
 */

public class SalaryScaleReader {
    private String filePath = "Project/src/SalaryScale";
    private double salary = -1;
    private String line;
    private String newPosition;
    private int newSalaryScale;
    private int topPromotionCounter;

    /**
     * Constructs a SalaryScaleReader with default settings.
     */
  
    public SalaryScaleReader() {
    }
    /**
     * Retrieves the salary for a specific position and salary scale point.
     *
     * @param position    The position of the employee.
     * @param salaryScale The scale point of the employee's salary.
     * @return The salary for the specified position and scale point, or -1 if not found.
     */
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

    /**
     * Determines the new salary for a promoted employee based on their
     * position, new salary scale, and promotion eligibility.
     *
     * @param position          The current position of the employee.
     * @param newSalaryScale    The new salary scale point to evaluate.
     * @param topPromotionCount The number of promotions an employee has had.
     * @return The new salary after promotion or -1 if not applicable.
     */

    // Find position of new salary scale within same position if new salary scale exists in the employees current position
    // If salary scale doesnt exist in current position the current position needs to change to the new position category and set the salary scale to the first in that position category
    public double getNewSalary(String position, int newSalaryScale, int topPromotionCounter) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            line = br.readLine();

            while (line != null) {
                line = br.readLine();
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length > 2) {
                    String currentPosition = data[0].trim();
                    int scalePoint = Integer.parseInt(data[1].trim());
                    String promotionPosition = data[3].trim();
                    if (currentPosition.equals(position) && scalePoint == newSalaryScale) {
                        try {
                            System.out.println(currentPosition);
                            salary = Double.parseDouble(data[2].trim());
                            newPosition = currentPosition;
                            this.newSalaryScale = newSalaryScale;
                            break;
                        } catch (NumberFormatException e) {
                            System.err.println("Number format problem");
                        }
                    } else if (currentPosition.equals(position) && !promotionPosition.equals("NULL")) {
                        if (topPromotionCounter == 3) {
                            System.out.println(promotionPosition);
                            this.topPromotionCounter = 0;
                            newPosition = promotionPosition;
                            this.newSalaryScale = 1;
                            salary = getSalaryScaleForPoint(promotionPosition, this.newSalaryScale);
                            return salary;
                        } else {
                            this.topPromotionCounter = topPromotionCounter + 1;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the csv file");
        }
        return salary;
    }
    /**
     * Retrieves the position of the employee after a promotion.
     *
     * @return The new position of the employee.
     */
  
    public String getNewPosition() {
        return newPosition;
    }

    /**
     * Retrieves the new salary scale after a promotion.
     *
     * @return The new salary scale point.
     */
    public int getNewSalaryScale() {
        return newSalaryScale;
    }
    /**
     * Retrieves the top promotion counter, which tracks the number
     * of promotions an employee has received.
     *
     * @return The number of top promotions.
     */
    public int getTopPromotionCounter() {
        return topPromotionCounter;
    }
}
