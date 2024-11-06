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

    public double getNettPay(){
        tax t = new tax(this);
        return t.nettPay(this);
    }
}
