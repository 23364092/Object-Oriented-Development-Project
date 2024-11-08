public class Employee {
    private String employeeId;
    private String name;
    private double salary;
    private int salaryScale;
    private String position;
    private String dateOfEmployment;

    public Employee(String employeeId, String name,String position, int salaryScale, String dateOfEmployment) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = getSalary(position, salaryScale);
        this.dateOfEmployment = getDateOfEmployment(employeeId);
    }

    public double getSalary(String position, int salaryScale) {
        return SalaryScale.getSalaryScaleForPoint(position, salaryScale);
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

    public String toString() {
            return "Employee ID: " + employeeId +
                    "\nName: " + name +
                    "\nPosition: " + position +
                    "\nSalary: " + salary;
        }
    }
}
