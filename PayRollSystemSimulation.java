import java.io.IOException;

public class PayRollSystemSimulation {

    public static void main(String[] args)
        throws IOException {
        PayrollSystem payroll = new PayrollSystem();
        PayrollSystemMenu menu = new PayrollSystemMenu();
        CSVReaderEmployee reader = new CSVReaderEmployee();

        menu.run(payroll);

    }
}
