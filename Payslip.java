import java.time.LocalDate;

public class Payslip implements Comparable<Payslip>{
    private String employee_id;
    private LocalDate date;
    private double annualSalary;

    public Payslip(String employee_id, double annualSalary, int year, int month, int day){
        this.employee_id = employee_id;
        this.annualSalary = annualSalary;
        this.date = LocalDate.of(year, month, day);
    }

    public String  getEmployee_id(){
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
        Deductions d = new Deductions(this);
        return d.calculateNettPay(this.getAnnualSalary());
    }

    @Override
    public String toString(){
        Deductions d = new Deductions(this);
        return "Date: " + date + "\n" +
                "Salary: €" + String.format("€%.2f",annualSalary) + "\n"
                + d.toString() + "\n";
    }

    @Override
    public int compareTo(Payslip other){
        return this.date.compareTo(other.date); //will be used to sort PayslipSet by date
    }
}
