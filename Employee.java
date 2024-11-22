public class Employee {
    private String employeeId;
    private String name;
    private double salary;
    private String position;
    private String dateOfEmployment;
    protected SalaryScaleReader reader = new SalaryScaleReader();

    public Employee(String employeeId, String name,String position, int salaryScale, String dateOfEmployment) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = getSalary(position, salaryScale);
        this.dateOfEmployment = dateOfEmployment;
    }

    public double getSalary(String position, int salaryScale) {
        return reader.getSalaryScaleForPoint(position, salaryScale);
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

    public String getDateOfEmployment(){
        return dateOfEmployment;
    }

    public String toString() {
            return "Employee ID: " + employeeId + "\n" +
                    "Name: " + name + "\n" +
                    "Position: " + position + "\n" +
                    "Salary: " + salary;
        }
    }
