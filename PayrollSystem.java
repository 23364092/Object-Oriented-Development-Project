import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PayrollSystem {
    private ArrayList<Employee> employees;
    private final String password = "0000";

    public PayrollSystem() {
        employees = new ArrayList<Employee>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }


    public Employee getEmployee(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)){
                return employee;
            }
        }
        return null;
    }

    public boolean employeeCheck(String employeeId){
        boolean result = false;
        for (Employee e : employees) {
            String id = e.getEmployeeId();
            if (id.equals(employeeId)) {
                result = true;
                break;
            }
        }
        if (!result) {
            System.err.println("Warning: Employee ID not found.");
        }
        return result;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    //Wait until HR and Salary Scale class is done
    public void promoteEmployee (String employeeId, String salaryScale){
        //HR.promoteEmployee(employeeId, salaryScale);
    }

    public void populatePayslips(){
        CSVReaderPayslip reader = new CSVReaderPayslip();
        reader.readPayslipsFromCSV(this);
    }

    public void writePayslips(){
        CSVWriterPayslip writer = new CSVWriterPayslip();
        writer.writePayslipsToCSV(this);
    }

    public boolean passwordCheck(String otherPassword){
        return otherPassword.equals(password);
    }

    public StringBuilder getEmployeeIds(){
        StringBuilder s = new StringBuilder();
        for (Employee e : employees){
            s.append(e.getEmployeeId()).append("\n");
        }
        return s;
    }

    public String getEmployeeDetails(String employeeId){
        return getEmployee(employeeId).toString();
    }
}