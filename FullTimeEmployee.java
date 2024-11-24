public class FullTimeEmployee extends Employee{
    private double salary;
    private double newSalary;

    public FullTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment, String contractType) {
        super(employeeId, name, position, salaryScale, dateOfEmployment, contractType);
        this.salary = getSalary(position, salaryScale);
    }

    public double getSalary(String position, int salaryScale) {
        return reader.getSalaryScaleForPoint(position, salaryScale);
    }


    public void tempPromoteEmployee(int salaryScale) {
        newSalary =  reader.getNewSalary(getPosition(), salaryScale);
    }


    @Override
    public void permPromoteEmployee() {
        super.permPromoteEmployee();
        salary = newSalary;
    }

    @Override
    public String toString() {
       return "Full Time Employee" +  "\n"
               + super.toString() +
               "Salary: " + salary;
    }
}
