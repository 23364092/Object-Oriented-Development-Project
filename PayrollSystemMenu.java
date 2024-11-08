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

            if (command.equals("A")) {
                System.out.println("Input password: ");
                String password = input.nextLine().toUpperCase();

                if (payroll.passwordCheck(password) == true) {
                    System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History A)dd Employee   Q)uit");
                    command = input.nextLine().toUpperCase();

                    if (command.equals("S")) {
                        //System.out.println(payroll.getPaySlip(employeeId));
                    } else if (command.equals("E")) {
                        System.out.println(payroll.toString());
                    } else if (command.equals("P")) {
                        //System.out.println(payroll.getPayslipHistory(employeeId));
                    } else if (command.equals("A")) {
                        System.out.println("Input details of employee you would like to add");

                        System.out.println("Enter Employee Id: ");
                        String newEmployeeId = input.nextLine();

                        System.out.println("Enter Employee Name: ");
                        String newEmployeeName = input.nextLine();

                        System.out.println("Enter Employee Position: ");
                        String newEmployeePosition = input.nextLine();

                        System.out.println("Enter Employee Salary Scale: ");
                        int newSalaryScale = Integer.parseInt(input.nextLine());

                        System.out.println("Enter Date Of Employment: ");
                        String newDateOfEmployment = input.nextLine();

                        Employee newEmployee = new Employee(newEmployeeId, newEmployeeName, newEmployeePosition, newSalaryScale, newDateOfEmployment);

                        payroll.addEmployee(newEmployee);


                    } else if (command.equals("Q")) {
                        more = false;
                    }
                }
            }

            if (command.equals("E") || command.equals("H")) {
                System.out.println("Input Employee Id");
                String employeeId = input.nextLine().toUpperCase();


                if (payroll.employeeCheck(employeeId)) {
                    if (command.equals("E")) {
                        System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   Q)uit");
                        command = input.nextLine().toUpperCase();

                        if (command.equals("S")) {
                            //System.out.println(payroll.getPaySlip(employeeId));
                        } else if (command.equals("E")) {
                            System.out.println(payroll.getEmployeeDetails(employeeId));
                        } else if (command.equals("P")) {
                            //System.out.println(payroll.getPayslipHistory(employeeId));
                        } else if (command.equals("Q")) {
                            more = false;
                        }
                    } else if (command.equals("H")) {

                        boolean hrMenu = true;

                        while (hrMenu) {

                            System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   PR)omote options   Q)uit");
                            command = input.nextLine().toUpperCase();

                            if (command.equals("S")) {
                                //System.out.println(payroll.getPaySlip(employeeId));
                            } else if (command.equals("E")) {
                                System.out.println(payroll.toString());
                            } else if (command.equals("P")) {
                                //System.out.println(payroll.getPayslipHistory(employeeId));
                            } else if (command.equals("Q")) {
                                more = false;
                            } else if (command.equals("PR")) {
                                System.out.println("Input Employee Id that you would like to promote");
                                String promotionId = input.nextLine().toUpperCase();

                                System.out.println(payroll.toString());

                                // Print out how long the employee has been a part of the system also
                                System.out.println("Input next Salary Scale you would like the employee to be promoted to");
                                String salaryScale = input.nextLine().toUpperCase();

                                System.out.println("Would you like to confirm this promotion: Y)es or N)o");
                                command = input.nextLine().toUpperCase();

                                if (command.equals("Y")) {
                                    payroll.promoteEmployee(employeeId, salaryScale);
                                    hrMenu = false;
                                } else if (command.equals("N")) {
                                    hrMenu = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

