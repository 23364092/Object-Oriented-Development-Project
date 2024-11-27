
import java.util.List;

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

    public Employee(String employeeId, String name,String position, String dateOfEmployment, String contractType) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = 0;
        this.dateOfEmployment = dateOfEmployment;
        this.payslips = new PayslipSet();
        this.contractType = contractType;
    }

    public abstract double getSalary();

    public void setSalary(double s){
        salary = s;
    }

    public PayslipSet getPayslipSet() {
        return payslips;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDateOfEmployment(){
        return dateOfEmployment;
    }

    public String getContractType() {
        return contractType;
    }

    public Payslip getRecentPayslip() {
        return payslips.getRecentPayslip();
    }

    public abstract void createPayslip();

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


    public void setPromotionOffer(boolean promotionOffer) {
        this.promotionOffer = promotionOffer;
    }

    public boolean getPromotionOffer() {
        return promotionOffer;
    }

    public void addPayslip(Payslip p) {
        payslips.addPayslip(p);
        CSVWriterPayslip writer = new CSVWriterPayslip();
        writer.writePayslipsToCSV(p);
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + "\n" +
                "Name: " + name + "\n" +
                "Position: " + position + "\n" +
                "Contract Type: " + contractType;
    }


    public  String promotionString() {
        return "Employee ID: " + employeeId + "\n" +
                "New Position: " + reader.getNewPosition() + "\n";
    }
}