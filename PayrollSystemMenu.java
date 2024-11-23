import java.util.Scanner;
import java.io.IOException;

public class PayrollSystemMenu {

    private Scanner input;
    private CSVReaderEmployee employeeReader = new CSVReaderEmployee();
    private String employeeId;
    private String command;
    private String password;

    public PayrollSystemMenu() {
        input = new Scanner(System.in);

    }

    public void run(PayrollSystem payroll)
            throws IOException {

        employeeReader.readEmployeesFromCSV(payroll);
        boolean more = true;

        while (more) {

            System.out.println("Input user type: E)mployee   H)uman Resources   A)dmin   Q)uit");
            command = input.nextLine().toUpperCase();

            if (command.equals("Q")) {
                more = false;
            }

            if (command.equals("A")) {
                System.out.println("Input employee Id: ");
                employeeId = input.nextLine().toUpperCase();

                if (payroll.adminCheck(employeeId)) {
                    System.out.println("Input password: ");
                    password = input.nextLine().toUpperCase();

                    if (payroll.adminPasswordCheck(password)) {
                        boolean adminMenu = true;

                        while (adminMenu) {
                            System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   A)dd Employee   R)eturn all Employees   Q)uit");
                            command= input.nextLine().toUpperCase();

                            if (command.equals("S")) {
                                System.out.println("\n" + payroll.getEmployee(employeeId).getFirstPayslip().toString());
                            } else if (command.equals("E")) {
                                System.out.println(payroll.getEmployeeDetails(employeeId));
                            } else if (command.equals("P")) {
                                payroll.getEmployee(employeeId).printAllPayslips();
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


                            } else if (command.equals("R")) {
                                System.out.println(payroll.getEmployees());

                            } else if (command.equals("Q")) {
                                adminMenu = false;
                            }
                        }
                    }
                }
            }

            if (command.equals("E") || command.equals("H")) {
                System.out.println("Input Employee Id");
                employeeId = input.nextLine().toUpperCase();


                if (payroll.employeeCheck(employeeId)) {
                    if (command.equals("E")) {
                        //if (payroll.getContractType(employeeId) = "FULL-TIME") {
                            boolean employeeMenu = true;

                            while (employeeMenu) {
                                System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   Q)uit");
                                command = input.nextLine().toUpperCase();

                                if (command.equals("S")) {
                                    System.out.println("\n" + payroll.getEmployee(employeeId).getFirstPayslip().toString());
                                } else if (command.equals("E")) {
                                    System.out.println(payroll.getEmployeeDetails(employeeId));
                                } else if (command.equals("P")) {
                                    payroll.getEmployee(employeeId).printAllPayslips();
                                } else if (command.equals("Q")) {
                                    employeeMenu = false;
                                }
                            }
                        } //else if (payroll.getContractType(employeeId) = "PART-TIME") {
                            boolean employeeMenu = true;

                            while (employeeMenu) {
                                System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   S)ubmit Claim   Q)uit");
                                command = input.nextLine().toUpperCase();

                                if (command.equals("S")) {
                                    System.out.println("\n" + payroll.getEmployee(employeeId).getFirstPayslip().toString());
                                } else if (command.equals("E")) {
                                    System.out.println(payroll.getEmployeeDetails(employeeId));
                                } else if (command.equals("P")) {
                                    payroll.getEmployee(employeeId).printAllPayslips();
                                } else if (command.equals("S")) {
                                    //Check date on system. If its second friday of month or gone past then this function isnt possible

                                    System.out.println("Input hours worked this month: ");
                                    int hoursWorked = Integer.parseInt(input.nextLine());

                                    //payroll.submitPayClaim(hoursWorked);
                                } else if (command.equals("Q")) {
                                    employeeMenu = false;
                                }
                            }

                        }
                    }else if (command.equals("H")) {

                        System.out.println("Input password: ");
                        password = input.nextLine();

                        if (payroll.hrPasswordCheck(password)) {
                            boolean hrMenu = true;

                            while (hrMenu) {

                                System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   PR)omote options   Q)uit");
                                command = input.nextLine().toUpperCase();

                                if (command.equals("S")) {
                                    System.out.println("\n" + payroll.getEmployee(employeeId).getFirstPayslip().toString());
                                } else if (command.equals("E")) {
                                    System.out.println(payroll.getEmployeeDetails(employeeId));
                                } else if (command.equals("P")) {
                                    payroll.getEmployee(employeeId).printAllPayslips();
                                } else if (command.equals("Q")) {
                                    more = false;
                                } else if (command.equals("PR")) {
                                    System.out.println("Input Employee Id that you would like to promote");
                                    String promotionId = input.nextLine().toUpperCase();


                                    // Print out how long the employee has been a part of the system also
                                    System.out.println("Input next Salary Scale you would like the employee to be promoted to");
                                    String salaryScale = input.nextLine().toUpperCase();

                                    System.out.println("Would you like to confirm this promotion: Y)es or N)o");
                                    command = input.nextLine().toUpperCase();

                                    if (command.equals("Y")) {
                                        payroll.promoteEmployee(employeeId, salaryScale);
                                        System.out.println("Promotion Offer has been sent to: " + payroll.getEmployee(promotionId).getName());
                                    } else if (command.equals("N")) {
                                        System.out.println("Promotion Offer has been cancelled.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

