public class Employee {
    private int employeeId;
    private String name;
    private double salary;
    private int salaryScale;
    private String position;

    public Employee(int employeeId, String name,String position, int salaryScale) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = getSalary(position, salaryScale);
    }

    public double getSalary(String position, int salaryScale) {
        return SalaryScale.getSalaryScaleForPoint(position, salaryScale);
    }

        public String toString() {
            return "Employee ID: " + employeeId +
                    "\nName: " + name +
                    "\nPosition: " + position +
                    "\nSalary: " + salary;
        }
    }
}
