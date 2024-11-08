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

                System.out.println("Input Employee Id");
                String employeeId = input.nextLine().toUpperCase();

                if (payroll.employeeCheck(employeeId)) {
                    if (command.equals("E")) {
                        System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   Q)uit");
                        command = input.nextLine().toUpperCase();

                        if (command.equals("S")) {
                            System.out.println(payroll.getPaySlip(employeeId));
                        } else if (command.equals("E")) {
                            System.out.println(payroll.toString());
                        } else if (command.equals("P")) {
                            System.out.println(payroll.getPayslipHistory(employeeId));
                        } else if (command.equals("Q")) {
                            more = false;
                        }
                    } else if (command.equals("H")) {
                        System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   PR)omote options   Q)uit");
                        command = input.nextLine().toUpperCase();

                        if (command.equals("S")) {
                            System.out.println(payroll.getPaySlip(employeeId));
                        } else if (command.equals("E")) {
                            System.out.println(payroll.toString());
                        } else if (command.equals("P")) {
                            System.out.println(payroll.getPayslipHistory(employeeId));
                        } else if (command.equals("PR")) {
                            System.out.println("Input Employee Id that you would like to promote");
                            command = input.nextLine().toUpperCase();

                            System.out.println(payroll.toString());

        // Print out how long the employee has been a part of the system also
                            System.out.println("Input next Salary Scale you would like the employee to be promoted to");
                            command = input.nextLine().toUpperCase();


                        }else if (command.equals("Q")) {
                            more = false;
                        }

                    }
            }
        }
    }
}
