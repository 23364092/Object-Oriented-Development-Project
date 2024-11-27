import java.io.*;
import java.util.*;

public class CSVWriterPayslip {

    private static final String FILE_PATH = "src/Payslip.csv";  // File path where payslips will be written

    // Write new payslips to CSV file
    public void writePayslipsToCSV(Payslip p) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {// Append mode
            String payslipEntry = p.getEmployee_id() + "," +
                    p.getAnnualSalary() + "," +
                    p.getDate().getYear() + "," +
                    p.getDate().getMonthValue() + "," +
                    p.getDate().getDayOfMonth();

            // Check if the payslip already exists in the file
            if (!isDuplicatePayslip(payslipEntry)) {
                writer.println(payslipEntry);  // Write new payslip
            }
            // Iterate over each employee in the payroll system
            System.out.println("Payslip successfully added.");
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

        return false;
    }
}
