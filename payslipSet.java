import java.util.ArrayList;

public class payslipSet {
    private ArrayList<payslip> payslips;

    public payslipSet(){ payslips = new ArrayList<payslip>();}

    public void addPayslip(payslip p){payslips.add(p);}

    public void removeAllPayslips(){payslips.clear();}
}
