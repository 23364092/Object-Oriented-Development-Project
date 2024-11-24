public class FullTimeEmployee extends Employee{
    private double salary;

    public FullTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment, String contractType) {
        super(employeeId, name, position, salaryScale, dateOfEmployment, contractType);
        this.salary = getSalary(position, salaryScale);
    }

    public double getSalary(String position, int salaryScale) {
        return reader.getSalaryScaleForPoint(position, salaryScale);
    }

    @Override
    public String toString() {
       return "Full Time Employee" +  "\n"
               + super.toString() +
               "Salary: " + salary;
    }
}
