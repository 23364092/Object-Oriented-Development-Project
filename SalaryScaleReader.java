import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class SalaryScaleReader {
    private String filePath = "src/SalaryScale.csv";
    private double salary = -1;
    private String line;
    private String newPosition;
    private int newSalaryScale;
    private int topPromotionCounter;

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
                            System.err.println("Number format problem.");
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

    public String getNewPosition() {
        return newPosition;
    }

    public int getNewSalaryScale() {
        return newSalaryScale;
    }

    public int getTopPromotionCounter() {
        return topPromotionCounter;
    }
}