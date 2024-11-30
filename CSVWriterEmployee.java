import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CSVWriterEmployee} class is responsible for writing and updating employee data
 * in a CSV file. It ensures no duplicate entries are added and allows updating existing records.
 */
public class CSVWriterEmployee {
    private static final String FILE_PATH = "src/Employees.csv";  // File path where employee data is stored

    /**
     * Writes a new employee entry to the CSV file. Ensures no duplicate entries are added.
     *
     * @param emp the {@link Employee} object to be written to the CSV file
     */
    public void writeEmployeeToCSV(Employee emp) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) { // Append mode
            // Construct the employee entry as a CSV line
            String employeeEntry = emp.getEmployeeId() + "," +
                    emp.getName().toUpperCase() + "," +
                    emp.getPosition().toUpperCase();

            // Add salary scale for full-time or hourly rate for part-time employees
            if (emp instanceof FullTimeEmployee) {
                FullTimeEmployee fullTime = (FullTimeEmployee) emp;
                employeeEntry += "," + fullTime.getSalaryScale(); // Add salary scale
            } else if (emp instanceof PartTimeEmployee) {
                PartTimeEmployee partTime = (PartTimeEmployee) emp;
                employeeEntry += "," + partTime.getHourlyRate(); // Add hourly rate
            }

            // Add remaining attributes
            employeeEntry += "," + emp.getDateOfEmployment() + "," +
                    emp.getContractType().toUpperCase();

            // Add promotion details for full-time employees
            if (emp instanceof FullTimeEmployee) {
                FullTimeEmployee fullTime = (FullTimeEmployee) emp;
                employeeEntry += "," + fullTime.getPromotion() + "," +
                        fullTime.getTopPromotionCounter();
            } else if (emp instanceof PartTimeEmployee) {
                employeeEntry += ",NULL,0";
            }

            // Check for duplicate entries before writing
            if (!isDuplicateEmployee(employeeEntry)) {
                writer.println(employeeEntry);  // Write the new entry
            } else {
                System.out.println("Duplicate employee entry detected. No data added.");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Checks if the given employee entry already exists in the CSV file.
     *
     * @param employeeEntry the employee data in CSV format
     * @return {@code true} if the entry is a duplicate, {@code false} otherwise
     */
    public boolean isDuplicateEmployee(String employeeEntry) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            // Check each line for an exact match
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

    /**
     * Updates an existing employee's record in the CSV file.
     * Updates position, salary scale, promotion status, and top promotion counter if applicable.
     *
     * @param employeeId    the unique ID of the employee
     * @param newPosition   the updated position/title of the employee
     * @param newSalaryScale the updated salary scale point for full-time employees
     * @param promotion     the updated promotion status
     * @param topCounter    the updated top promotion counter
     */
    public void updateEmployeeInCSV(String employeeId, String newPosition, int newSalaryScale, String promotion, int topCounter) {
        List<String> lines = new ArrayList<>();
        boolean isUpdated = false;

        // Read the file and apply updates to the corresponding record
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length > 0 && tokens[0].equals(employeeId)) {
                    // Update the fields of the matching employee
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
                    line = String.join(",", tokens); // Reconstruct the updated line
                    isUpdated = true;
                }
                lines.add(line); // Add the updated or original line to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Write back the updated file if any changes were made
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
