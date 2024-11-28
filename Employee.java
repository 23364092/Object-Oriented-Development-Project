import java.util.List;

/**
 * Represents an abstract base class for employees, providing shared attributes
 * and behaviors for different types of employees
 */
public abstract class Employee{
    private String employeeId;
    private String name;
    protected String position;
    private double salary;
    private PayslipSet payslips;
    private String dateOfEmployment;
    protected SalaryScaleReader reader = new SalaryScaleReader();
    private String contractType;
    private boolean promotionOffer;

    /**
     * Constructs an Employee with the specified details
     *
     * @param employeeId       The unique authentication of the employee
     * @param name             The name of the employee
     * @param position         The position of the employee
     * @param dateOfEmployment The date the employee was employed
     * @param contractType     The type of contract for the employee (e.g Full-Time, Part-Time)
     */
    public Employee(String employeeId, String name,String position, String dateOfEmployment, String contractType) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = 0;
        this.dateOfEmployment = dateOfEmployment;
        this.payslips = new PayslipSet();
        this.contractType = contractType;
    }

    /**
     * Calculates and returns the salary of the employee
     * Must be implemented by subclasses
     *
     * @return The salary of the employee
     */
    public abstract double getSalary();

    /**
     * Sets the salary of the employee
     *
     * @param s The salary to set
     */
    public void setSalary(double s){
        salary = s;
    }

    /**
     * Returns the set of payslips associated with the employee
     *
     * @return A PayslipSet containing the employee's payslips
     */
    public PayslipSet getPayslipSet() {
        return payslips;
    }

    /**
     * Returns the unique identifier of the employee
     *
     * @return The employee's ID
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Returns the name of the employee
     *
     * @return The employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the position of the employee
     *
     * @return The employee's position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Returns the date the employee was employed
     *
     * @return The employment date as a string
     */
    public String getDateOfEmployment(){
        return dateOfEmployment;
    }

    /**
     * Returns the contract type of the employee
     *
     * @return The type of employment contract
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * Returns the most recent payslip for the employee
     *
     * @return The latest Payslip object
     */
    public Payslip getRecentPayslip() {
        return payslips.getRecentPayslip();
    }

    /**
     * Must be implemented by subclasses to create and add a payslip
     * for the employee
     */
    public abstract void createPayslip();

    /**
     * Prints all payslips associated with the employee
     * Displays a message if no payslips are available
     */
    public void printAllPayslips() {
        List<Payslip> payslipList = payslips.getPayslips();
        if (payslipList.isEmpty()) {
            System.out.println("No payslips available for Employee ID: " + employeeId);
        } else {
            System.out.println("Payslips for Employee ID: " + employeeId);
            for (Payslip payslip : payslipList) {
                System.out.println(payslip.toString());
            }
        }
    }

    /**
     * Sets the promotion offer status for the employee
     *
     * @param promotionOffer A boolean indicating whether a promotion offer exists
     */
    public void setPromotionOffer(boolean promotionOffer) {
        this.promotionOffer = promotionOffer;
    }

    /**
     * Returns the promotion offer status for the employee
     *
     * @return True if a promotion offer exists, otherwise false
     */
    public boolean getPromotionOffer() {
        return promotionOffer;
    }
    /**
     * Adds a payslip to the employee's payslip set and writes it to a CSV file
     *
     * @param p The payslip to add
     */
    public void addPayslip(Payslip p) {
        payslips.addPayslip(p);
        CSVWriterPayslip writer = new CSVWriterPayslip();
        writer.writePayslipsToCSV(p);
    }

    /**
     * Returns a string representation of the employee, including their
     * ID, name, position, and contract type
     *
     * @return A string representation of the employee
     */
    @Override
    public String toString() {
        return "Employee ID: " + employeeId + "\n" +
                "Name: " + name + "\n" +
                "Position: " + position + "\n" +
                "Contract Type: " + contractType;
    }

    /**
     * Returns a string representation of a promotion for the employee,
     * including their ID and the new position
     *
     * @return A string describing the promotion
     */
    public  String promotionString() {
        return "Employee ID: " + employeeId + "\n" +
                "New Position: " + reader.getNewPosition() + "\n";
    }
}