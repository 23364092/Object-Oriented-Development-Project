import java.util.ArrayList;

public class PayrollSystem {
    private ArrayList<Employee> employees;

    public PayrollSystem() {
        employees = new ArrayList<Employee>();
    }

    public Employee[] getEmployees() {

        Employee[] e = new Employee[employees.size()];

        for (int i = 0; i < employees.size(); i++) {
            e[i] = employees.get(i);
        }
        return e;
    }

    public static boolean employeeCheck(String employeeId) {
        boolean result = false;

        for (Employee e : employees){
            String id = Employee.getEmployeeId(e);
            if (id.equals(employeeId)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addEmployee(Employee employee){
        Admin.addEmployee(employee);
    }

    public void promoteEmployee(Employee employee){
        HR.promoteEmployee(employee);
    }
}

