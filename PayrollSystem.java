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
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
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
        if (!result) {
            System.err.println("Warning: Employee ID not found.");
        }
        return result;
    }

    public boolean adminCheck(String employeeId) {
        String position;
        boolean result = false;

        if (employeeCheck(employeeId)) {
            position = getEmployee(employeeId).getPosition();

            if (position.contains("ADMIN")) {
                result = true;
            }
        }
        return result;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
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

    public void populatePaySlips() {
        CSVReaderPayslip reader = new CSVReaderPayslip();
        reader.readPayslipsFromCSV(this);
    }

    public void writePayslips() {
        CSVWriterPayslip writer = new CSVWriterPayslip();
        writer.writePayslipsToCSV(this);
    }

    public StringBuilder getEmployeeIds() {

        StringBuilder s = new StringBuilder();
        for (Employee e : employees) {
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
    public void generatePayslips() {
        LocalDate today = LocalDate.now();

        if (today.getDayOfMonth() == 25) {
        employees.forEach(employee -> {
            if (employee instanceof FullTimeEmployee fullTime) {
                generateFullTimePayslip(fullTime, today);
            } else if (employee instanceof PartTimeEmployee partTime && partTime.hasValidClaim()) {
                generatePartTimePayslip(partTime, today);
            }
        });
        System.out.println("Payslips generated for eligible employees.");
        } else {
        System.out.println("Today is not the 25th.");
        }
    }
    private void generateFullTimePayslip(FullTimeEmployee fullTime, LocalDate date) {
        Payslip slip = new Payslip(
                fullTime.getEmployeeId(),
                fullTime.getSalary(fullTime.getPosition(), fullTime.getSalaryScale()),
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        fullTime.getPayslipSet().addPayslip(slip);
    }

    private void generatePartTimePayslip(PartTimeEmployee partTime, LocalDate date) {
        Payslip slip = new Payslip(
                partTime.getEmployeeId(),
                partTime.getHourlyRate(partTime.getPosition(), partTime.getSalaryScale()),
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        partTime.getPayslipSet().addPayslip(slip);
}
}
