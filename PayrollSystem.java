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

    public boolean employeeCheck(String employeeId) {
        boolean result = false;

        for (Employee e : employees){
            String id = e.getEmployeeId();
            if (id.equals(employeeId)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }


    //Wait until HR and Salary Scale class is done
    public void promoteEmployee(Employee employee){
        HR.promoteEmployee(employee);
    }
}

