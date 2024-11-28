import java.util.ArrayList;
import java.util.Collections;

/**
 * The {@code PayslipSet} class manages a collection of {@link Payslip} objects
 * It provides functionality to add, remove, and retrieve payslips, while ensuring
 * they remain sorted for easy access
 */
public class PayslipSet {

    /** A list to store payslips, maintained in sorted order
     */
    private ArrayList<Payslip> payslips;

    /**
     * Constructs an empty {@code PayslipSet}
     * Initializes an empty list of payslips
     */
    public PayslipSet(){
        payslips = new ArrayList<Payslip>();
    }

    /**
     * Adds a payslip to the set and ensures the list remains sorted
     *
     * @param p the {@link Payslip} to be added
     */
    public void addPayslip(Payslip p){
        payslips.add(p);
        Collections.sort(payslips);
    }

    public void removeAllPayslips(){ payslips.clear(); }

    public ArrayList<Payslip> getPayslips(){ return payslips; }

    public Payslip getRecentPayslip(){ return payslips.getLast(); }
}
