import java.io.*;
import java.util.*;

public class CSVWriterPayslip {

    private static final String FILE_PATH = "src/payslip.csv";  // File path where payslips will be written

    // Write new payslips to CSV file
    public void writePayslipsToCSV(PayrollSystem payrollSystem) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {  // Append mode
            // Write the header if the file is empty (i.e., first time writing)
            if (new File(FILE_PATH).length() == 0) {
                writer.println("employee_id,salary,year,month,day");
            }

            // Iterate over each employee in the payroll system
            for (Employee employee : payrollSystem.getEmployees()) {
                // Get the payslips for this employee
                ArrayList<Payslip> payslips = employee.getPayslipSet().getPayslips();

                // Write each payslip to the CSV file if it is not a duplicate
                for (Payslip payslip : payslips) {
                    String payslipEntry = payslip.getEmployee_id() + "," +
                            payslip.getAnnualSalary() + "," +
                            payslip.getDate().getYear() + "," +
                            payslip.getDate().getMonthValue() + "," +
                            payslip.getDate().getDayOfMonth();

                    // Check if the payslip already exists in the file
                    if (!isDuplicatePayslip(payslipEntry)) {
                        writer.println(payslipEntry);  // Write new payslip
                    }
                }
            }

            System.out.println("New payslips successfully written to " + FILE_PATH);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to check if a payslip already exists in the file
    private boolean isDuplicatePayslip(String payslipEntry) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine();  // Skip the header line

            // Read each line and check if the payslip already exists
            while ((line = reader.readLine()) != null) {
                if (line.equals(payslipEntry)) {
                    return true;  // Duplicate found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return false;  // No duplicate found
    }
}
