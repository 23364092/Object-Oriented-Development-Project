import java.io.*;

public class CSVWriterPayslip {

    private static final String FILE_PATH = "src/Payslip.csv";  // File path where payslips will be written

    public void writePayslipsToCSV(Payslip p) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {// Append mode
            String payslipEntry = p.getEmployee_id() + "," +
                    p.getAnnualSalary() + "," +
                    p.getDate().getYear() + "," +
                    p.getDate().getMonthValue() + ",25";

            if (!isDuplicatePayslip(payslipEntry)) {
                writer.println(payslipEntry);  // Write new payslip
            }
            System.out.println("Payslip successfully added.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private boolean isDuplicatePayslip(String payslipEntry) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine();  // Skip the header line

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
