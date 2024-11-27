import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.time.LocalDate;

public class PayrollSystem {
    private ArrayList<Employee> employees;
    private final String adminPassword = "0000";
    private final String hrPassword = "1111";
    private LocalDate today;

    public PayrollSystem() {
        employees = new ArrayList<>();
        populateEmployeesFromCSV();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public void autoAddPayslips(){
        LocalDate date = LocalDate.now();
        if (date.getDayOfMonth() == 25) {
            for (Employee employee : employees) {
                if (employee.getContractType().equals("FULLTIME")) {
                    //employee.addPayslip(new Payslip());
                }
            }
        }
    }

    public Employee getEmployee(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }


    public boolean employeeCheck(String employeeId) {
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

    public boolean adminCheck(String employeeId) {
        String position;
        boolean result = false;

        if (employeeCheck(employeeId)) {
            position = getEmployee(employeeId).getPosition();

            if (position.contains("ADMINISTRATOR") || position.contains("SENIOR ADMINISTRATOR") || position.contains("EXECUTIVE ADMINISTRATOR") || position.contains("SENIOR EXECUTIVE ADMINISTRATOR") || position.contains("SENIOR ADMINISTRATIVE OFFICER I") || position.contains("SENIOR ADMINISTRATIVE OFFICER II") || position.contains("SENIOR ADMINISTRATIVE OFFICER III")) {
                result = true;
            }
        }
        return result;
    }

    public StringBuilder printEmployees(){
        StringBuilder s = new StringBuilder();
        for (Employee e : employees) {
            s.append(e.toString());
        }
        return s;
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        } else {
            System.err.println("Attempted to add duplicate employee.");
        }
    }

    public void writeEmployee(Employee employee) {
        CSVWriterEmployee writer = new CSVWriterEmployee();
        writer.writeEmployeeToCSV(employee);
    }

    //Wait until HR and Salary Scale class is done
    public void promoteEmployee(String employeeId, String salaryScale) {
        //HR.promoteEmployee(employeeId, salaryScale);
    }

    public boolean adminPasswordCheck(String otherPassword) {
        if (otherPassword.equals(adminPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public void submitPayClaim(String employeeId, int hoursWorked){
        PartTimeEmployee employee = (PartTimeEmployee) getEmployee(employeeId);
        if (employee.getContractType().equals("PARTTIME")) {
            employee.setHoursWorked(hoursWorked);
        }
        // employee.payClaim(employeeId);
    }

    public boolean hrPasswordCheck(String otherPassword) {
        if (otherPassword.equals(hrPassword)) {
            return true;
        } else {
            return false;
        }
    }


    public String getEmployeeDetails(String employeeId) {
        return getEmployee(employeeId).toString();
    }

    public void populateEmployeesFromCSV() {
        CSVReaderEmployee csvReader = new CSVReaderEmployee();
        csvReader.readEmployeesFromCSV(this); // This uses the PayrollSystem instance to add employees
    }

    public void populatePayslips() {
        CSVReaderPayslip reader = new CSVReaderPayslip();
        reader.readPayslipsFromCSV(this);
    }


    public StringBuilder getEmployeeIds() {
        StringBuilder s = new StringBuilder();
        for (Employee e : employees) {
            System.out.println(employees.size());
            s.append(e.getEmployeeId()).append("\n");
        }
        return s;
    }

    public boolean fridayCheck() {

        today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        LocalDate firstFriday = firstDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        LocalDate secondFriday = firstFriday.plusDays(7);

        if (today.isEqual(secondFriday) || today.isBefore(secondFriday)) {
            System.out.println("The date today is: " + today);
            System.out.println("The second friday of the month is: " + secondFriday);
            return true;
        } else {
            System.out.println("You have missed the window to submit a payclaim for this month");
            return false;
        }
    }
}