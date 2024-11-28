import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVWriterEmployee {
    private static final String FILE_PATH = "src/Employees.csv";

    public void writeEmployeeToCSV(Employee emp) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) { // Append mode
            String employeeEntry = emp.getEmployeeId() + "," +
                    emp.getName().toUpperCase() + "," +
                    emp.getPosition().toUpperCase();

            // Add scale point or hourly rate
            if (emp instanceof FullTimeEmployee) {
                FullTimeEmployee fullTime = (FullTimeEmployee) emp;
                employeeEntry += "," + fullTime.getSalaryScale(); // Add salary scale point
            } else if (emp instanceof PartTimeEmployee) {
                PartTimeEmployee partTime = (PartTimeEmployee) emp;
                employeeEntry += "," + partTime.getHourlyRate(); // Add hourly rate
            }
            employeeEntry += "," + emp.getDateOfEmployment() + "," +
                            emp.getContractType().toUpperCase();
            if (emp instanceof FullTimeEmployee) {
                FullTimeEmployee fullTime = (FullTimeEmployee) emp;
                employeeEntry += "," + fullTime.getPromotion() + ","
                                + fullTime.getTopPromotionCounter();
            } else if (emp instanceof PartTimeEmployee) {
                employeeEntry += ",NULL,0";
            }

            if (!isDuplicateEmployee(employeeEntry)) {
                writer.println(employeeEntry);
            } else {
                System.out.println("Duplicate employee entry detected. No data added.");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public boolean isDuplicateEmployee(String employeeEntry) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(employeeEntry)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return false;
    }

    public void updateEmployeeInCSV(String employeeId, String newPosition, int newSalaryScale, String promotion, int topCounter) {
        List<String> lines = new ArrayList<>();
        boolean isUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length > 0 && tokens[0].equals(employeeId)) {
                    tokens[2] = newPosition;
                    if (tokens.length > 3) {
                        tokens[3] = String.valueOf(newSalaryScale);
                    }
                    if (!tokens[6].equals(promotion)) {
                        tokens[6] = promotion;
                    }
                    if (Integer.parseInt(tokens[7]) != topCounter) {
                        tokens[7] = String.valueOf(topCounter);
                    }
                    line = String.join(",", tokens); // Reconstruct the line
                    isUpdated = true;
                }
                lines.add(line); // Add the line (updated or not) to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        if (isUpdated) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
                for (String updatedLine : lines) {
                    writer.println(updatedLine);
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Employee with ID " + employeeId + " not found. No changes made.");
        }
    }
}
