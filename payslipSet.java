import java.util.ArrayList;
import java.util.Collections;

public class payslipSet{
    private ArrayList<payslip> payslips;

    public payslipSet(){ payslips = new ArrayList<payslip>();}

    public void addPayslip(payslip p){payslips.add(p);}

    public void removeAllPayslips(){payslips.clear();}

    public ArrayList<payslip> getPayslips(){return payslips;}

    public payslip getFirstPayslip(){
        Collections.sort(payslips);
        return payslips.getFirst();
    }
}
