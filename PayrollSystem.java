import java.util.ArrayList;

public class PayrollSystem {
    protected ArrayList<Employee> employees;
    private final String password = "0000";

    public PayrollSystem() {
        employees = new ArrayList<Employee>();
    }

    public String getEmployees(){
        return employees.toString();
    }

    public Employee getEmployee(String employeeId) {
        boolean breaker = false;
        Employee employee = null;

        for (int i = 0; i < employees.size(); i++) {
                employee = employees.get(i);
            if (employeeId.equals(employee.getEmployeeId())) {
                breaker = true;
            }

            if (breaker == true) {
                break;
            }
        }
        return employee;
    }

        public boolean employeeCheck (String employeeId){
            boolean result = false;

            for (Employee e : employees) {
                String id = e.getEmployeeId();
                if (id.equals(employeeId)) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        public void addEmployee (Employee employee){
            employees.add(employee);
        }


        //Wait until HR and Salary Scale class is done
        public void promoteEmployee (String employeeId, String salaryScale){
            //HR.promoteEmployee(employeeId, salaryScale);
        }

        public boolean passwordCheck (String otherPassword){
            if (otherPassword.equals(password)) {
                return true;
            } else {
                return false;
            }
        }

        public String getEmployeeDetails (String employeeId){
            return getEmployee(employeeId).toString();
        }
    }
