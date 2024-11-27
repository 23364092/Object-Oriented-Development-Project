import java.io.*;

public class CSVWriterEmployee {
    private static final String FILE_PATH = "src/Employees.csv";

    public void writeEmployeeToCSV(Employee emp) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) { // Append mode
            String employeeEntry = emp.getEmployeeId() + "," +
                    emp.getName() + "," +
                    emp.getPosition();

            // Add scale point or hourly rate
            if (emp instanceof FullTimeEmployee) {
                FullTimeEmployee fullTime = (FullTimeEmployee) emp;
                employeeEntry += "," + fullTime.getSalary(); // Add salary scale point
            } else if (emp instanceof PartTimeEmployee) {
                PartTimeEmployee partTime = (PartTimeEmployee) emp;
                employeeEntry += "," + partTime.getHourlyRate(); // Add hourly rate
            }
            employeeEntry += "," + emp.getDateOfEmployment() + "," +
                            emp.getContractType();


            if (!isDuplicateEmployee(employeeEntry)) {
                writer.println(employeeEntry); // Write the entry to the file
                System.out.println("Employee successfully added.");
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
}
