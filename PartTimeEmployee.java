import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;
    private boolean claim;

    public PartTimeEmployee(String employeeId, String name, String position, double hourlyRate, String dateOfEmployment, String contractType) {
        super(employeeId, name, position, dateOfEmployment, contractType);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
        this.claim = false;
        setSalary(getSalary());
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double getSalary() {
        return hoursWorked * hourlyRate;
    }

    @Override
    public void createPayslip(){
        if (!claim) {
            claim = true;
            LocalDate date = LocalDate.now();
            Payslip p = new Payslip(getEmployeeId(), getSalary(), date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            addPayslip(p);
            CSVWriterPayslip writer = new CSVWriterPayslip();
            writer.writePayslipsToCSV(p);
        }
    }

    public double getHourlyRate(){
        return hourlyRate;
    }

    @Override
    public String toString() {
        return "Part-Time Employee\n" +
                "-----------------\n" +
                super.toString() +
                "\nHourly Rate: €" + hourlyRate +
                "\n-----------------\n" + "\n\n";
    }
}
