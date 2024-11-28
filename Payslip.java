import java.time.LocalDate;

/**
 * Represents a payslip for an employee, containing details about the employee's
 * salary, date of payment, and tax deductions
 */
public class Payslip implements Comparable<Payslip>{
    private String employee_id;
    private LocalDate date;
    private double salary;

    /**
     * Constructs a Payslip with the specified employee ID, salary, and date
     *
     * @param employee_id The unique identifier of the employee
     * @param salary      The gross salary of the employee
     * @param year        The year of the payslip
     * @param month       The month of the payslip
     * @param day         The day of the payslip
     */
    public Payslip(String employee_id, double salary, int year, int month, int day){
        this.employee_id = employee_id;
        this.salary = salary;
        this.date = LocalDate.of(year, month, day);
    }

    /**
     * Returns the employee ID associated with the payslip
     *
     * @return The employee ID
     */
    public String  getEmployee_id(){
        return employee_id;
    }

    /**
     * Returns the gross annual salary associated with the payslip
     *
     * @return The gross annual salary
     */
    public double getAnnualSalary() {
        return salary;
    }

    /**
     * Returns the date of the payslip
     *
     * @return The date of the payslip
     */
    public LocalDate getDate(){
        return date;
    }

    /**
     * Calculates and returns the nett pay (after deductions) based on the salary
     *
     * @return The nett pay after deductions
     */
    public double getNettPay(){
        Deductions d = new Deductions(this);
        return d.calculateNettPay(this.getAnnualSalary());
    }

    /**
     * Returns the month of the payslip
     *
     * @return The month value as an integer (1-12)
     */
    public int getMonth(){
        return date.getMonthValue();
    }

    /**
     * Returns a string representation of the payslip, including the date,
     * gross pay, and deductions
     *
     * @return A string representation of the payslip
     */
    @Override
    public String toString(){
        Deductions d = new Deductions(this);
        return "Date: " + date + "\n" +
                "Gross Pay: €" + String.format("%.2f",salary) + "\n"
                + d.toString() + "\n";
    }

    /**
     * Compares this payslip with another based on their dates
     * Useful for sorting payslips correctly
     *
     * @param other The other payslip to compare
     * @return A negative integer, zero, or a positive integer as this payslip
     * is earlier than, equal to, or later than the specified payslip
     */
    @Override
    public int compareTo(Payslip other){
        return this.date.compareTo(other.date); //will be used to sort PayslipSet by date
    }
}
