import java.time.LocalTime;
import java.time.LocalDate;

public class payslip {
    private int employee_id;
    private LocalDate date;
    private double rate;
    private double hours;

    public payslip(int employee_id, double rate, double hours, int day, int month, int year){
        this.employee_id = employee_id;
        this.rate = rate;
        this.hours = hours;
        this.date = LocalDate.of(year, month, day);
    }

    public int getEmployee_id(){
        return employee_id;
    }

    public double getHours() {
        return hours;
    }

    public double getRate() {
        return rate;
    }

    public LocalDate getDate(){
        return date;
    }

    public double getGrossPay(){
        return hours * rate;
    }

    /**
     * Uses tax class to calculate nett pay for given salary
     * @return nettPay
     */
    public double getNettPay(){
        tax t = new tax(this);
        return t.calculateNettPay(this.getGrossPay());
    }

    @Override
    public String toString(){
        tax t = new tax(this);
        return "Employee ID: " + employee_id + "\n" +
                "Date: " + date + "\n" +
                "Rate: €" + rate + "\n" +
                "Hours: " + hours + "\n" +
                "Gross Pay: " + String.format("€%.2f", this.getGrossPay()) + "\n"
                + t.toString();
    }
}
