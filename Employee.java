import java.time.LocalDate;
import java.util.List;

public class Employee {
    private String employeeId;
    private String name;
    private String position;
    private int salaryScale;
    private PayslipSet payslips;
    private String dateOfEmployment;
    protected SalaryScaleReader reader = new SalaryScaleReader();
    private String contractType;
    private boolean promotionOffer;

    public Employee(String employeeId, String name,String position, int salaryScale, String dateOfEmployment, String contractType) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salaryScale = salaryScale;
        this.dateOfEmployment = dateOfEmployment;
        this.payslips = new PayslipSet();
        this.contractType = contractType;
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

    public int getSalaryScale() {
        return salaryScale;
    }

    public String getDateOfEmployment(){
        return dateOfEmployment;
    }

    public String getContractType() {
        return contractType;
    }

    public Payslip getFirstPayslip() {
        return payslips.getRecentPayslip();
    }

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

    public void permPromoteEmployee() {
        position = reader.getNewPosition();
        salaryScale = reader.getNewSalaryScale();
    }

    public void setPromotionOffer(boolean promotionOffer) {
        this.promotionOffer = promotionOffer;
    }

    public boolean getPromotionOffer() {
        return promotionOffer;
    }

    @Override
    public String toString() {
            return "Employee ID: " + employeeId + "\n" +
                    "Name: " + name + "\n" +
                    "Position: " + position + "\n";
    }


    public  String promotionString() {
        return "Employee ID: " + employeeId + "\n" +
                "New Position: " + reader.getNewPosition() + "\n";
     }
}
