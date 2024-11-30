import java.io.*;

/**
 * The {@code CSVReaderPayslip} class reads payslip data from a CSV file
 * and adds it to the appropriate employee in the {@link PayrollSystem}.
 */
public class CSVReaderPayslip {

    private static final String FILE_PATH = "src/Payslip.csv";  // File path where payslips are stored

    /**
     * Reads payslip data from a CSV file and associates it with employees in the given {@link PayrollSystem}.
     *
     * @param payrollSystem the {@link PayrollSystem} to populate with payslip data
     */
    public void readPayslipsFromCSV(PayrollSystem payrollSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine();  // Skip the header line

            // Iterates through each line in the CSV file
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                
                // Validates the line format
                if (tokens.length == 5) {
                    String employeeId = tokens[0].trim();
                    double salary = Double.parseDouble(tokens[1].trim());
                    int year = Integer.parseInt(tokens[2].trim());
                    int month = Integer.parseInt(tokens[3].trim());
                    int day = Integer.parseInt(tokens[4].trim());

                    // Creates a new Payslip object
                    Payslip payslip = new Payslip(employeeId, salary, year, month, day);

                    // Retrieves the corresponding Employee from the PayrollSystem
                    Employee employee = payrollSystem.getEmployee(employeeId);
                    if (employee != null) {
                        // Adds the Payslip to the Employee's PayslipSet
                        employee.getPayslipSet().addPayslip(payslip);
                    } else {
                        // Logs a warning if no matching Employee is found
                        System.err.println("Warning: No employee found with ID " + employeeId);
                    }
                } else {
                    // Logs a warning for invalid line formats
                    System.err.println("Warning: Invalid line format in CSV.");
                }
            }

        } catch (IOException e) {
            // Logs an error if the file cannot be read
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
