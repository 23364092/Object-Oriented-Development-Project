import java.io.IOException;

public class PayrollSystemSimulation {

    /**
     * The main method to launch the Payroll System Simulation.
     * 
     * @param args Command-line arguments (not used).
     * @throws IOException If an input or output error occurs during execution.
     */
    public static void main(String[] args) throws IOException {
        // Initialize the Payroll System
        PayrollSystem payroll = new PayrollSystem();

        // Initialize the menu for the users
        PayrollSystemMenu menu = new PayrollSystemMenu();

        // Loads the employee data into it
        CSVReaderEmployee reader = new CSVReaderEmployee();

        // Runs the menu to finish
        menu.run(payroll);
    }
}

