import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String name;
    private double salary;
    private int salaryScale;
    private String position;
    private payslipSet payslips;

    public Employee(String employeeId, String name,String position, int salaryScale, String dateOfEmployment) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = getSalary(position, salaryScale);
    }

    public double getSalary(String position, int salaryScale) {
        return 2.0;//SalaryScale.getSalaryScaleForPoint(position, salaryScale);
    }

    public payslipSet getPayslipSet(){
        return payslips;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getFirstPayslipDate(){
        return payslips.getFirstPayslip().getDate();
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + "\n" +
                "Name: " + name + "\n" +
                "Position: " + position + "\n" +
                "Salary: " + salary;
    }
}