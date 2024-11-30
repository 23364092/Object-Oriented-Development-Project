import java.time.LocalDate;

public class Payslip implements Comparable<Payslip>{
    private String employee_id;
    private LocalDate date;
    private double salary;

    public Payslip(String employee_id, double salary, int year, int month, int day){
        this.employee_id = employee_id;
        this.salary = salary;
        this.date = LocalDate.of(year, month, day);
    }

    public String  getEmployee_id(){
        return employee_id;
    }

    public double getAnnualSalary() {
        return salary;
    }

    public LocalDate getDate(){
        return date;
    }

    @Override
    public String toString(){
        Deductions d = new Deductions(this);
        return "Date: " + date + "\n" +
                "Gross Pay: €" + String.format("%.2f",salary) + "\n"
                + d.toString() + "\n";
    }

    @Override
    public int compareTo(Payslip other){
        return this.date.compareTo(other.date); //will be used to sort PayslipSet by date
    }
}
