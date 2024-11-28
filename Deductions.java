/**
 * The {@code Deductions} class calculates various tax deductions and nett pay
 * based on the provided gross annual salary from a {@link Payslip} object
 * It includes calculations for PAYE, USC, PRSI, and additional charges
 */
public class Deductions {

    /** PAYE (Pay As You Earn) tax deduction */
    private double PAYE;

    /** USC (Universal Social Charge) deduction */
    private double USC;

    /** PRSI (Pay-Related Social Insurance) deduction */
    private double PRSI;

    /** Total income tax based on gross salary */
    private double incomeTax;

    /** The calculated nett pay after all deductions */
    private double nettPay;

    /**
     * Constructs a {@code Deductions} object by calculating deductions for the given salary
     *
     * @param p a {@link Payslip} object providing the gross annual salary
     */
    public Deductions(Payslip p) {
        double grossPay = p.getAnnualSalary();
        this.PAYE = calculatePAYE(grossPay);
        this.USC = calculateUSC(grossPay);
        this.PRSI = calculatePRSI(grossPay);
        this.incomeTax = getIncomeTax(grossPay);
        this.nettPay = calculateNettPay(grossPay);
    }

    /**
     * Calculates the PRSI deduction
     *
     * @param grossPay the gross annual salary
     * @return the PRSI amount as 4.1% of the gross salary
     */
    public double calculatePRSI(double grossPay) {
        return grossPay * 0.041;
    }

    /**
     * Calculates the total income tax based on gross salary
     *
     * @param grossPay the gross annual salary
     * @return the total income tax based on PAYE rules
     */
    public double getIncomeTax(double grossPay) {
        if ((grossPay - 42000) < 0){
            return grossPay * 0.2;
        } else {
            return (42000 * 0.2) + ((grossPay - 42000) * 0.4);
        }
    }

    public double calculateUSC(double grossPay) {
        double usc = 0.0;

        if (grossPay <= 12012) {
            return grossPay * 0.005;
        } else {
            usc += 12012 * 0.005;
        }

        if (grossPay > 12012) {
            double taxableAmount = Math.min(grossPay - 12012, 1736);//1736 being difference between first threshold and second threshold
            usc += taxableAmount * 0.02;        //if gross pay - first threshold larger than difference, use difference to calculate usc
        }

        if (grossPay > 13748) {
            double taxableAmount = Math.min(grossPay - 13748, 30536);
            usc += taxableAmount * 0.04;
        }

        if (grossPay > 44284) {
            usc += (grossPay - 44284) * 0.08;
        }
        return usc;
    }

    /**
     * Calculates the PAYE (Pay As You Earn) tax deduction
     *
     * @param grossPay the gross annual salary
     * @return the PAYE amount based on income brackets
     */
    public double calculatePAYE(double grossPay) {
        if (grossPay <= 40000) {
            return grossPay * 0.20;
        } else {
            return (40000 * 0.20) + ((grossPay - 40000) * 0.40);
        }
    }

    /**
     * Calculates the nett pay after all deductions
     *
     * @param grossPay the gross annual salary
     * @return the nett pay after deducting PAYE, USC, PRSI, health insurance, and union fees
     */
    public double calculateNettPay(double grossPay) {
        double totalTax = PAYE + USC + PRSI;
        System.out.println(totalTax);
        return grossPay - ((totalTax + 80 + (grossPay * 0.008))) ;
        /**
         * Health insurance: €80/month
         * Union fees: 0.8% of gross salary
         */
    }

    /**
     * Returns the PAYE deduction amount
     *
     * @return the PAYE amount
     */
    public double getPAYE() {
        return PAYE;
    }

    /**
     * Returns the USC deduction amount
     *
     * @return the USC amount
     */
    public double getUSC() {
        return USC;
    }

    /**
     * Returns the PRSI deduction amount
     *
     * @return the PRSI amount
     */
    public double getPRSI() {
        return PRSI;
    }

    /**
     * Returns the total income tax amount
     *
     * @return the income tax amount
     */
    public double getIncomeTax() {
        return incomeTax;
    }

    /**
     * Returns a formatted string summarizing all deductions and the nett pay
     *
     * @return a string representation of the deductions and nett pay
     */
    @Override
    public String toString() {
        return "USC: " + String.format("€%.2f", USC) + "\n" +
                "PAYE: " + String.format("€%.2f", PAYE) + "\n" +
                "Income Tax: " + String.format("€%.2f", incomeTax) + "\n" +
                "PRSI: " + String.format("€%.2f", PRSI) + "\n" +
                "Nett Pay: " + String.format("€%.2f", nettPay);
    }
}