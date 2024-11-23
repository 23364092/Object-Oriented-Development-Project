public class FullTimeEmployee extends Employee{
    private
    public FullTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment) {
    super(employeeId, name, position, salaryScale, dateOfEmployment);
}
    @Override
    public String toString() {
       return "Full Time Employee";
    }
}
