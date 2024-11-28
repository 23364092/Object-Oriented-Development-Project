import java.time.LocalDate;

public class FullTimeEmployee extends Employee {
    private int salaryScale;
    private int topPromotionCounter;
    private String promotion;


    public FullTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment, String contractType, String promotion, int topPromotionCounter) {
        super(employeeId, name, position, dateOfEmployment, contractType);
        this.salaryScale = salaryScale;
        setSalary(getSalary());
        this.topPromotionCounter = topPromotionCounter;
        this.promotion = promotion;

    }

    @Override
    public double getSalary() {
        return reader.getSalaryScaleForPoint(getPosition(), salaryScale);
    }

    @Override
    public void createPayslip(){
        LocalDate date = LocalDate.now();
        Payslip p = new Payslip(getEmployeeId(), getSalary(), date.getYear(), date.getMonthValue(), 25);
        addPayslip(p);
    }

    public void tempPromoteEmployee(int newSalaryScale) {
        double newSalary = reader.getNewSalary(getPosition(), newSalaryScale, topPromotionCounter);
        System.out.println("Temporary Promotion: New Salary would be " + newSalary);
    }

    public void permPromoteEmployee() {
        position = reader.getNewPosition();
        salaryScale = reader.getNewSalaryScale();
        topPromotionCounter = reader.getTopPromotionCounter();
        CSVWriterEmployee writer = new CSVWriterEmployee();
        writer.updateEmployeeInCSV(getEmployeeId(), position, salaryScale);// topPromotionCounter);
    }

    public int getSalaryScale() {
        return salaryScale;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        return "Full-Time Employee\n" +
                "-----------------\n" +
                super.toString() +
                "\nSalary: " + getSalary() + "\n" +
                "Scale Point: " + salaryScale +
                "\n-----------------\n" + "\n\n";
    }
}