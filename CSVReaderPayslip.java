import java.io.*;
import java.util.*;

public class CSVReaderPayslip {

    private static final String FILE_PATH = "src/payslip.csv";  // File path where payslips are stored

    // Method to read payslips from CSV file and add them to the payroll system
    public void readPayslipsFromCSV(PayrollSystem payrollSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine();  // Skip the header line

            // Read each line and add payslips to the appropriate employee
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 5) {
                    String employeeId = tokens[0].trim();
                    double salary = Double.parseDouble(tokens[1].trim());
                    int year = Integer.parseInt(tokens[2].trim());
                    int month = Integer.parseInt(tokens[3].trim());
                    int day = Integer.parseInt(tokens[4].trim());

                    // Create a new Payslip
                    Payslip payslip = new Payslip(employeeId, salary, year, month, day);

                    // Find the corresponding Employee in PayrollSystem
                    Employee employee = payrollSystem.getEmployee(employeeId);
                    if (employee != null) {
                        // Add the Payslip to the Employee's PayslipSets
                        employee.getPayslipSet().addPayslip(payslip);
                    } else {
                        System.err.println("Warning: No employee found with ID " + employeeId);
                    }
                } else {
                    System.err.println("Warning: Invalid line format in CSV.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
