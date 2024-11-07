import java.util.Scanner;
import java.io.IOException;

public class PayrollSystemMenu {

    private Scanner input;


    public PayrollSystemMenu() {
        input = new Scanner(System.in);
    }

    public void run(PayrollSystem payroll)
        throws IOException {

        boolean more = true;

        while (more) {
            System.out.println("Input user type: E)mployee   H)uman Resources   A)dmin");
            String command = input.nextLine().toUpperCase();

            if (command.equals("E")){
                System.out.println("Input Employee Id");
                String employeeId = input.nextLine().toUpperCase();

                if (PayrollSystem.employeeCheck(employeeId) == true)
                {
                    System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   Q)uit");
                    command = input.nextLine().toUpperCase();

                    if (command.equals("S")){
                        System.out.println(getPaySlip(employeeId));
                    }
                    else if (command.equals("E")) {
                        System.out.println(Employee.toString());
                    }
                    else if (command.equals("P")) {
                        System.out.println(getPayslipHistory(employeeId));
                    }
                    else if (command.equals("Q")){
                        more = false;
                    }
                }
            }
        }
    }
}
