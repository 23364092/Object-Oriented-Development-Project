import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

/**
 * Represents a part-time employee with additional attributes such as hourly rate,
 * hours worked, and the ability to claim salary.
 */

public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;
    private boolean claim;

    /**
     * Constructs a PartTimeEmployee with the specified details.
     *
     * @param employeeId       The unique authentication of the employee.
     * @param name             The name of the employee.
     * @param position         The job position of the employee.
     * @param hourlyRate       The hourly rate for the employee.
     * @param dateOfEmployment The date the employee was employed.
     * @param contractType     The type of employment contract.
     */

    public PartTimeEmployee(String employeeId, String name, String position, double hourlyRate, String dateOfEmployment, String contractType) {
        super(employeeId, name, position, dateOfEmployment, contractType);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
        this.claim = false;
        setSalary(getSalary());
    }

    /**
     * Sets the number of hours worked by the employee for a given period.
     *
     * @param hoursWorked The number of hours worked.
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * Calculates and returns the salary for the part-time employee
     * based on the hourly rate and hours worked.
     *
     * @return The calculated salary.
     */
    @Override
    public double getSalary() {
        return hoursWorked * hourlyRate;
    }

    /**
     * Creates a payslip for the employee if they haven't already claimed their salary
     * for the current period. The payslip is recorded and written to a CSV file.
     */
    @Override
    public void createPayslip(){
        if (!claim) {
            claim = true;
            LocalDate date = LocalDate.now();
            Payslip p = new Payslip(getEmployeeId(), getSalary(), date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            addPayslip(p);
            CSVWriterPayslip writer = new CSVWriterPayslip();
            writer.writePayslipsToCSV(p);
        }
    }

    /**
     * Returns the hourly rate of the part-time employee.
     *
     * @return The hourly rate.
     */
    public double getHourlyRate(){
        return hourlyRate;
    }

    /**
     * Returns a string representation of the part-time employee,
     * including their personal details, position, and hourly rate.
     *
     * @return A string representation of the part-time employee.
     */
    @Override
    public String toString() {
        return "Part-Time Employee\n" +
                "-----------------\n" +
                super.toString() +
                "\nHourly Rate: €" + hourlyRate +
                "\n-----------------\n" + "\n\n";
    }
}
