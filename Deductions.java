public class Deductions {
    private double PAYE;
    private double USC;
    private double PRSI;
    private double incomeTax;
    private double nettPay;

    /**
     * Constructor to get required tax for given salary
     * @param p provides salary
     */
    public Deductions(Payslip p) {
        double grossPay = p.getAnnualSalary();
        this.PAYE = calculatePAYE(grossPay);
        this.USC = calculateUSC(grossPay);
        this.PRSI = calculatePRSI(grossPay);
        this.incomeTax = getIncomeTax(grossPay);
        this.nettPay = calculateNettPay(grossPay);
    }

    public double calculatePRSI(double grossPay) {
        return grossPay * 0.041;
    }

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

    public double calculatePAYE(double grossPay) {
        if (grossPay <= 40000) {
            return grossPay * 0.20;
        } else {
            return (40000 * 0.20) + ((grossPay - 40000) * 0.40);
        }
    }

    public double calculateNettPay(double grossPay) {
        double totalTax = PAYE + USC + PRSI;
        return grossPay - ((totalTax + 80 + (grossPay * 0.008)) - 4000 ) ;
        /**
         * €80 a month for health insurance
         * grossPay * 0.008 is 0.8% for union fees
         * €4000 for tax credits
         */
    }

    public double getPAYE() {
        return PAYE;
    }

    public double getUSC() {
        return USC;
    }

    public double getPRSI() {
        return PRSI;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    @Override
    public String toString() {
        return "USC: " + String.format("€%.2f", USC) + "\n" +
                "PAYE: " + String.format("€%.2f", PAYE) + "\n" +
                "Income Tax: " + String.format("€%.2f", incomeTax) + "\n" +
                "PRSI: " + String.format("€%.2f", PRSI) + "\n" +
                "Net Pay after Deductions: " + String.format("€%.2f", nettPay);
    }
}
