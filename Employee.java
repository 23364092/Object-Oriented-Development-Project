import java.time.LocalDate;
import java.util.List;

public class Employee {
    private String employeeId;
    private String name;
    private double salary;
    private String position;
    private PayslipSet payslips;
    private String dateOfEmployment;
    protected SalaryScaleReader reader = new SalaryScaleReader();

    public Employee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = getSalary(position, salaryScale);
        this.dateOfEmployment = dateOfEmployment;
        this.payslips = new PayslipSet();
    }

    public double getSalary(String position, int salaryScale) {
        return reader.getSalaryScaleForPoint(position, salaryScale);
    }

    public PayslipSet getPayslipSet() {
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

    public LocalDate getFirstPayslipDate() {
        return payslips.getFirstPayslip().getDate();
    }

    public void printAllPayslips() {
        List<Payslip> payslipList = payslips.getPayslips();
        if (payslipList.isEmpty()) {
            System.out.println("No payslips available for Employee ID: " + employeeId);
        } else {
            System.out.println("Payslips for Employee ID: " + employeeId);
            for (Payslip payslip : payslipList) {
                System.out.println(payslip.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + "\n" +
                "Name: " + name + "\n" +
                "Position: " + position + "\n" +
                "Salary: " + salary;
    }
}
