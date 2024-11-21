import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PayrollSystem {
    private ArrayList<Employee> employees;
    private final String password = "0000";

    public PayrollSystem() {
        employees = new ArrayList<Employee>();
    }

    public Employee getEmployee(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)){
                return employee;
            }
        }
        return null;
    }

    public boolean employeeCheck(String employeeId){
        boolean result = false;
        for (Employee e : employees) {
            String id = e.getEmployeeId();
            if (id.equals(employeeId)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void readCSVFile() {
        try (Scanner input = new Scanner(new File("src/payslip.csv"))) {
            String header = input.nextLine();
            while (input.hasNextLine()) {
                String[] tokens = input.nextLine().split(",");
                if (tokens.length == 5) {
                    String employeeId = tokens[0].trim();
                    double salary = Double.parseDouble(tokens[1].trim());
                    int year = Integer.parseInt(tokens[2].trim());
                    int month = Integer.parseInt(tokens[3].trim());
                    int day = Integer.parseInt(tokens[4].trim());

                    Payslip payslip = new Payslip(employeeId, salary, year, month, day);

                    Employee employee = getEmployee(employeeId);
                    if (employee != null) {
                        employee.getPayslipSet().addPayslip(payslip);
                    } else {
                        System.err.println("Warning: No employee found with ID " + employeeId);
                    }
                } else {
                    System.err.println("Warning: Invalid line format in CSV.");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format in CSV.");
        }
    }

    public boolean passwordCheck(String otherPassword){
        return otherPassword.equals(password);
    }

    public StringBuilder getEmployeeIds(){
        StringBuilder s = new StringBuilder();
        for (Employee e : employees){
            s.append(e.getEmployeeId()).append("\n");
        }
        return s;
    }

    public String getEmployeeDetails(String employeeId){
        return getEmployee(employeeId).toString();
    }
}
