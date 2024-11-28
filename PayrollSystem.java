import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

/**
 * Represents the Payroll System, managing employees, payslips, and promotions.
 */
public class PayrollSystem {
    private ArrayList<Employee> employees;
    private final String adminPassword = "0000";
    private final String hrPassword = "1111";
    private LocalDate today;

    /**
     * Constructs the Payroll System and initializes employees, payslips, and promotions.
     */
    public PayrollSystem() {
        employees = new ArrayList<>();
        populateEmployeesFromCSV();
        autoAddPayslips();
        autoPromoteEmployees();
    }

    /**
     * Retrieves the list of employees in the system.
     * 
     * @return The list of employees.
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Automatically generates payslips for all full-time employees on the 25th of the month.
     */
    public void autoAddPayslips() {
        LocalDate date = LocalDate.now();
        if (date.getDayOfMonth() == 25) {
            for (Employee employee : employees) {
                if (employee.getContractType().equals("FULLTIME")) {
                    employee.addPayslip(new Payslip(employee.getEmployeeId(), employee.getSalary(), date.getYear(), date.getMonthValue(), date.getDayOfMonth()));
                }
            }
        }
    }

    /**
     * Automatically promotes eligible employees and updates their records.
     */
    public void autoPromoteEmployees() {
        int month = LocalDate.now().getMonthValue();

        if (month == 11) {
            for (Employee employee : employees) {
                if (employee.getContractType().equals("FULLTIME")) {
                    FullTimeEmployee ftEmployee = (FullTimeEmployee) employee;
                    if (ftEmployee.getPromotion().equals("TRUE")) {
                        ftEmployee.tempPromoteEmployee(ftEmployee.getSalaryScale() + 1);
                        ftEmployee.permPromoteEmployee();
                        ftEmployee.setPromotion("FALSE");
                    }
                }
            }
        } else if (month != 10) {
            for (Employee employee : employees) {
                if (employee.getContractType().equals("FULLTIME")) {
                    ((FullTimeEmployee) employee).setPromotion("TRUE");
                }
            }
        }
    }

    /**
     * Retrieves the details of an employee by their ID.
     * 
     * @param employeeId The unique identifier of the employee.
     * @return The employee's details as a string.
     */
    public String getEmployeeDetails(String employeeId) {
        return getEmployee(employeeId).toString();
    }

    /**
     * Loads employees from a CSV file into the payroll system.
     */
    public void populateEmployeesFromCSV() {
        CSVReaderEmployee csvReader = new CSVReaderEmployee();
        csvReader.readEmployeesFromCSV(this);
    }

    /**
     * Checks if today's date is the second Friday of the month.
     * 
     * @return True if today is the second Friday, false otherwise.
     */
    public boolean fridayCheck() {
        today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        LocalDate firstFriday = firstDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        LocalDate secondFriday = firstFriday.plusDays(7);

        return today.isEqual(secondFriday) || today.isBefore(secondFriday);
    }

    /**
     * Adds a new employee to the payroll system if not already present.
     * 
     * @param employee The employee to be added.
     */
    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        } else {
            System.out.println("Attempted to add duplicate employee.");
        }
    }
}
