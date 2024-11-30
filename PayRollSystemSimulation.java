public class PayRollSystemSimulation {

    public static void main(String[] args) {
        PayrollSystem payroll = new PayrollSystem();
        PayrollSystemMenu menu = new PayrollSystemMenu();

        menu.run(payroll);
    }
}