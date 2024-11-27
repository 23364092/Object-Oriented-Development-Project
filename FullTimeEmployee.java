import java.time.LocalDate;
import java.util.Scanner;

public class FullTimeEmployee extends Employee {
    private int salaryScale;


    public FullTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment, String contractType) {
        super(employeeId, name, position, dateOfEmployment, contractType);
        this.salaryScale = salaryScale;
        setSalary(getSalary());
    }

    @Override
    public double getSalary() {
       return reader.getSalaryScaleForPoint(getPosition(), salaryScale);
    }

    @Override
    public void createPayslip(){
        LocalDate date = LocalDate.now();
        Payslip p = new Payslip(getEmployeeId(), getSalary(), date.getYear(), date.getMonthValue(), 25);
        addPayslip(p);
    }

    public void tempPromoteEmployee(int newSalaryScale) {
        double newSalary = reader.getSalaryScaleForPoint(getPosition(), newSalaryScale);
        System.out.println("Temporary Promotion: New Salary would be " + newSalary);
    }

    @Override
    public String toString() {
        return "Full-Time Employee\n" +
                "-----------------\n" +
                super.toString() +
                "\nScale Point: " + salaryScale +
                "\n-----------------\n" + "\n\n";
    }
}
