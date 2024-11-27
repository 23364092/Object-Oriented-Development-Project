import java.time.LocalDate;

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

    public int getSalaryScale(){
        return salaryScale;
    }

    @Override
    public void createPayslip(){
        LocalDate date = LocalDate.now();
        Payslip p = new Payslip(getEmployeeId(), getSalary(), date.getYear(), date.getMonthValue(), 25);
        addPayslip(p);
    }

    public void tempPromoteEmployee(int newSalaryScale) {
        double newSalary = reader.getNewSalary(getPosition(), newSalaryScale);
        System.out.println("Temporary Promotion: New Salary would be " + newSalary);
    }

    public void permPromoteEmployee() {
        position = reader.getNewPosition();
        salaryScale = reader.getNewSalaryScale();
        CSVWriterEmployee writer = new CSVWriterEmployee();
        writer.updateEmployeeInCSV(getEmployeeId(), position, salaryScale);
    }


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