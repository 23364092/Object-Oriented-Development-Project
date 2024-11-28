import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderEmployee {
    private final String filePath = "Project/src/Employees.csv";

    // Constructor
    public CSVReaderEmployee() {
    }

    // Reads employee data from the CSV and populates the PayrollSystem
    public void readEmployeesFromCSV(PayrollSystem payrollSystem) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 8) { // Ensure row has the correct number of columns
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                try {
                    String employeeId = data[0].trim();
                    String name = data[1].trim();
                    String position = data[2].trim();
                    String dateOfEmployment = data[4].trim();
                    String contractType = data[5].trim().toUpperCase();
                    String promotion = data[6].trim().toUpperCase();
                    int topCounter = Integer.parseInt(data[7].trim().toUpperCase());

                    if (contractType.equals("FULLTIME")) {
                        int salaryScale = Integer.parseInt(data[3].trim());
                        payrollSystem.addEmployee(new FullTimeEmployee(
                                employeeId, name, position, salaryScale, dateOfEmployment, contractType, promotion, topCounter));
                    } else if (contractType.equals("PARTTIME")) {
                        double hourlyRate = Double.parseDouble(data[3].trim());
                        payrollSystem.addEmployee(new PartTimeEmployee(
                                employeeId, name, position, hourlyRate, dateOfEmployment, contractType
                        ));
                    } else {
                        System.err.println("Unknown contract type for employee ID: " + employeeId);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing number for line: " + line);
                }
            }

            payrollSystem.populatePayslips();

        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}