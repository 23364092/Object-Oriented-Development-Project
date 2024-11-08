import java.time.LocalDate;

public class payslip {
    private int employee_id;
    private LocalDate date;
    private double annualSalary;

    public payslip(int employee_id,double annualSalary, int day, int month, int year){
        this.employee_id = employee_id;
        this.annualSalary = annualSalary;
        this.date = LocalDate.of(year, month, day);
    }

    public int getEmployee_id(){
        return employee_id;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public LocalDate getDate(){
        return date;
    }

    /**
     * Uses tax class to calculate nett pay for given salary
     * @return nettPay
     */
    public double getNettPay(){
        deductions d = new deductions(this);
        return d.calculateNettPay(this.getAnnualSalary());
    }

    @Override
    public String toString(){
        deductions t = new deductions(this);
        return "Employee ID: " + employee_id + "\n" +
                "Date: " + date + "\n" +
                "Salary: €" + annualSalary + "\n"
                + t.toString();
    }
}
