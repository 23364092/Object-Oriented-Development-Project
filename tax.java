public class tax {
    private double PAYE;
    private double USC;
    private double PRSI;
    private double incomeTax;
    private double nettPay;

    /**
     * Constructor to get required tax for given salary
     * @param p provides salary
     */
    public tax(payslip p) {
        double grossPay = p.getGrossPay();
        this.PAYE = calculatePAYE(grossPay);
        this.USC = calculateUSC(grossPay);
        this.PRSI = calculatePRSI(grossPay);
        this.incomeTax = getIncomeTax(grossPay);
        this.nettPay = calculateNettPay(grossPay);
    }

    public double calculatePRSI(double grossPay) {
        return grossPay * 0.04;
    }

    public double getIncomeTax(double grossPay) {
        return grossPay * 0.04; // Adjust as per actual income tax rules
    }

    public double calculateUSC(double grossPay) {
        double usc = 0.0;

        if (grossPay <= 12012) {
            return grossPay * 0.005;
        } else {
            usc += 12012 * 0.005;
        }

        if (grossPay > 12012) {
            double taxableAmount = Math.min(grossPay - 12012, 1736);
            usc += taxableAmount * 0.02;
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
        return grossPay - totalTax; // Add tax credits after deductions
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
                "Net Pay after deductions: " + String.format("€%.2f", nettPay);
    }
}
