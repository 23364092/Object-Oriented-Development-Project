import java.util.ArrayList;
import java.util.Collections;

public class PayslipSet {
    private ArrayList<Payslip> payslips;

    public PayslipSet(){ payslips = new ArrayList<Payslip>(); }

    public void addPayslip(Payslip p){
        payslips.add(p);
        Collections.sort(payslips);
    }

    public void removeAllPayslips(){ payslips.clear(); }

    public ArrayList<Payslip> getPayslips(){ return payslips; }

    public Payslip getFirstPayslip(){ return payslips.getFirst(); }
}
