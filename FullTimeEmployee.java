import java.time.LocalDate;

/**
 * Represents a full-time employee with additional attributes and behaviors
 * such as salary scale and promotion capabilities.
 */

public class FullTimeEmployee extends Employee {
    private int salaryScale;

    /**
     * Constructs a FullTimeEmployee with the specified details
     *
     * @param employeeId        The unique authentication of employee
     * @param name              The name of the employee
     * @param position          The job position of the employee
     * @param salaryScale       The scale for the employees salary
     * @param dateOfEmployment  The date the employee was employed
     * @param contractType      The type of employment contract
     */


    public FullTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment, String contractType) {
        super(employeeId, name, position, dateOfEmployment, contractType);
        this.salaryScale = salaryScale;
        setSalary(getSalary());
    }

    /**
     * Calculates and returns the salary of the full-time employee
     * based on there position in the salary scale
     * @return The Calculated salary of the employee
     */

    @Override
    public double getSalary() {
        return reader.getSalaryScaleForPoint(getPosition(), salaryScale);
    }

    /**
     * Returns the salary scale of the full-time Employee
     * @return The salary scale point
     */

    public int getSalaryScale(){
        return salaryScale;
    }

    /**
     * Creates a payslip for the current month and adds it to the employees record
     */

    @Override
    public void createPayslip(){
        LocalDate date = LocalDate.now();
        Payslip p = new Payslip(getEmployeeId(), getSalary(), date.getYear(), date.getMonthValue(), 25);
        addPayslip(p);
    }

    /**
     * Temporarily promotes the employee to the new salary scale and prints
     * the potential new salary to the console
     * @param newSalaryScale the temporary new salary scale point
     */

    public void tempPromoteEmployee(int newSalaryScale) {
        double newSalary = reader.getNewSalary(getPosition(), newSalaryScale);
        System.out.println("Temporary Promotion: New Salary would be " + newSalary);
    }

    /**
     * Permanently promotes the employee by updating their position and salary scale
     * and saving the changes to a CSV file
     */

    public void permPromoteEmployee() {
        position = reader.getNewPosition();
        salaryScale = reader.getNewSalaryScale();
        CSVWriterEmployee writer = new CSVWriterEmployee();
        writer.updateEmployeeInCSV(getEmployeeId(), position, salaryScale);
    }

    /**
     * Returns a string representing the full-time employee including their
     * personal details, position, salary, and salary scale
     * @return A string representation of the full-time employee
     */


    @Override
    public String toString() {
        return "Full-Time Employee\n" +
                "-----------------\n" +
                super.toString() +
                "\nSalary: " + getSalary() + "\n" +
                "Scale Point: " + salaryScale +
                "\n-----------------\n" + "\n\n";
    }
}
