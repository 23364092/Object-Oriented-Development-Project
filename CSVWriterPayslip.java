import java.io.*;

/**
 * The {@code CSVWriterPayslip} class is responsible for writing payslip data to a CSV file.
 * It ensures that duplicate payslip entries are not added to the file.
 */
public class CSVWriterPayslip {

    private static final String FILE_PATH = "src/Payslip.csv";  // File path where payslips will be written

    /**
     * Writes a payslip entry to the CSV file.
     * Checks for duplicates before appending the new payslip entry.
     *
     * @param p the {@link Payslip} object to be written to the CSV file
     */
    public void writePayslipsToCSV(Payslip p) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) { // Append mode
            // Construct the payslip entry as a CSV line
            String payslipEntry = p.getEmployee_id() + "," +
                    p.getAnnualSalary() + "," +
                    p.getDate().getYear() + "," +
                    p.getDate().getMonthValue() + ",25";

            // Check if the payslip entry is a duplicate before writing
            if (!isDuplicatePayslip(payslipEntry)) {
                writer.println(payslipEntry);  // Write new payslip
            }
            System.out.println("Payslip successfully added.");
        } catch (IOException e) {
            // Logs an error message if writing to the file fails
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Checks if a payslip entry already exists in the CSV file.
     *
     * @param payslipEntry the payslip data in CSV format
     * @return {@code true} if the entry is a duplicate, {@code false} otherwise
     */
    private boolean isDuplicatePayslip(String payslipEntry) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine();  // Skip the header line

            // Iterate through each line to check for a match
            while ((line = reader.readLine()) != null) {
                if (line.equals(payslipEntry)) {
                    return true;  // Duplicate found
                }
            }
        } catch (IOException e) {
            // Logs an error message if reading from the file fails
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return false;  // No duplicate found
    }
}
