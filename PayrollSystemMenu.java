import java.time.LocalDate;
import java.util.Scanner;
import java.io.IOException;

public class PayrollSystemMenu {

    private Scanner input;
    private String employeeId;
    private String command;
    private String password;

    public PayrollSystemMenu() {
        input = new Scanner(System.in);

    }

    public void run(PayrollSystem payroll)
            throws IOException {

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
                            command = input.nextLine().toUpperCase();

                            if (command.equals("S")) {
                                System.out.println("\n" + payroll.getEmployee(employeeId).getRecentPayslip().toString());
                            } else if (command.equals("E")) {
                                System.out.println(payroll.getEmployeeDetails(employeeId));
                            } else if (command.equals("P")) {
                                payroll.getEmployee(employeeId).printAllPayslips();
                            } else if (command.equals("A")) {
                                System.out.println("Input Employee Details.\n");

                                System.out.println("Enter Employee Id: ");
                                String newEmployeeId = input.nextLine();
                                if (payroll.employeeCheck(newEmployeeId)){
                                    System.err.println("Warning: Employee with ID " + newEmployeeId + " already exists.");
                                } else {
                                    System.out.println("Enter Employee Name: ");
                                    String newEmployeeName = input.nextLine();

                                    System.out.println("Enter Contract Type: ");
                                    System.out.println("1) Full-Time");
                                    System.out.println("2) Part Time");

                                    int newContractType = Integer.parseInt(input.nextLine());

                                    System.out.println("Enter Employee Position: ");
                                    String newEmployeePosition = input.nextLine();

                                    int newSalaryScale = 0;
                                    double newHourlyRate = 0;
                                    if (newContractType == 1) {
                                        System.out.println("Enter Employee Salary Scale: ");
                                        newSalaryScale = Integer.parseInt(input.nextLine());
                                    } else if (newContractType == 2) {
                                        System.out.println("Enter Hourly Rate: ");
                                        newHourlyRate = Double.parseDouble(input.nextLine());
                                    }

                                    System.out.println("Enter Date Of Employment: ");
                                    String newDateOfEmployment = input.nextLine();

                                    Employee newEmployee;
                                    if (newContractType == 1) {
                                        newEmployee = new FullTimeEmployee(newEmployeeId, newEmployeeName, newEmployeePosition, newSalaryScale, newDateOfEmployment, "FULLTIME");
                                    } else if (newContractType == 2) {
                                        newEmployee = new PartTimeEmployee(newEmployeeId, newEmployeeName, newEmployeePosition, newHourlyRate, newDateOfEmployment, "PARTTIME");
                                    } else {
                                        System.out.println("Invalid contract type selected!");
                                        return;
                                    }
                                    payroll.addEmployee(newEmployee);
                                    CSVWriterEmployee writer = new CSVWriterEmployee();
                                    writer.writeEmployeeToCSV(newEmployee);
                                }


                            } else if (command.equals("R")) {
                                System.out.println(payroll.printEmployees());

                            } else if (command.equals("Q")) {
                                adminMenu = false;
                            }
                        }
                    }
                }
            }

            if (command.equals("E") || command.equals("H")) {
                System.out.println("Input Employee ID:");
                employeeId = input.nextLine().toUpperCase();

                if (payroll.employeeCheck(employeeId)) {
                    if (command.equals("E")) {
                        if (payroll.getEmployee(employeeId).getContractType().equals("FULLTIME")) {
                            FullTimeEmployee employee = (FullTimeEmployee) payroll.getEmployee(employeeId);

                            if (payroll.getEmployee(employeeId).getPromotionOffer()) {
                                System.out.println("You have received a promotion offer");
                                System.out.println(employee.promotionString());

                                System.out.println("Would you like to accept new promotion : Y)es   N)o");
                                command = input.nextLine().toUpperCase();

                                if (command.equals("Y")) {
                                    employee.permPromoteEmployee();
                                    employee.setPromotionOffer(false);
                                } else if (command.equals("N")) {
                                    employee.setPromotionOffer(false);
                                }
                            }
                            boolean employeeMenu = true;

                            while (employeeMenu) {
                                System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   Q)uit");
                                command = input.nextLine().toUpperCase();

                                if (command.equals("S")) {
                                    System.out.println("\n" + payroll.getEmployee(employeeId).getRecentPayslip().toString());
                                } else if (command.equals("E")) {
                                    System.out.println(payroll.getEmployeeDetails(employeeId));
                                } else if (command.equals("P")) {
                                    payroll.getEmployee(employeeId).printAllPayslips();
                                } else if (command.equals("Q")) {
                                    employeeMenu = false;
                                }
                            }
                        } else if (payroll.getEmployee(employeeId).getContractType().equals("PARTTIME")) {
                            PartTimeEmployee employee = (PartTimeEmployee) payroll.getEmployee(employeeId);

                            boolean employeeMenu = true;

                            while (employeeMenu) {
                                System.out.print("S)how most recent payslip   E)mployee Details   P)ayslip History");
                                if (employee.getRecentPayslip().getDate().getDayOfMonth() < LocalDate.now().getMonthValue() || employee.getRecentPayslip().getDate().getDayOfMonth() < LocalDate.now().getYear()) {
                                    System.out.print("  SU)bmit Claim");
                                }
                                System.out.println("   Q)uit");
                                command = input.nextLine().toUpperCase();

                                if (command.equals("S")) {
                                    System.out.println("\n" + payroll.getEmployee(employeeId).getRecentPayslip().toString());
                                } else if (command.equals("E")) {
                                    System.out.println(payroll.getEmployeeDetails(employeeId));
                                } else if (command.equals("P")) {
                                    payroll.getEmployee(employeeId).printAllPayslips();
                                } else if (command.equals("SU") && (employee.getRecentPayslip().getDate().getMonthValue() < LocalDate.now().getDayOfMonth() || employee.getRecentPayslip().getDate().getDayOfMonth() < LocalDate.now().getYear())) {
                                    //Check date on system. If its second friday of month or gone past then this function isnt possible
                                    boolean result = payroll.fridayCheck();

                                    if (result) {
                                        System.out.println("Input hours worked this month: ");
                                        int hoursWorked = Integer.parseInt(input.nextLine());

                                        employee = (PartTimeEmployee) payroll.getEmployee(employeeId);
                                        employee.setHoursWorked(hoursWorked);
                                        employee.createPayslip();
                                    }
                                } else if (command.equals("Q")) {
                                    employeeMenu = false;
                                }
                            }

                        }
                    } else if (command.equals("H")) {

                        System.out.println("Input password: ");
                        password = input.nextLine();

                        if (payroll.hrPasswordCheck(password)) {
                            boolean hrMenu = true;

                            while (hrMenu) {

                                System.out.println("S)how most recent payslip   E)mployee Details   P)ayslip History   PR)omote options   Q)uit");
                                command = input.nextLine().toUpperCase();

                                if (command.equals("S")) {
                                    System.out.println("\n" + payroll.getEmployee(employeeId).getRecentPayslip().toString());
                                } else if (command.equals("E")) {
                                    System.out.println(payroll.getEmployeeDetails(employeeId));
                                } else if (command.equals("P")) {
                                    payroll.getEmployee(employeeId).printAllPayslips();
                                } else if (command.equals("Q")) {
                                    hrMenu = false;
                                } else if (command.equals("PR")) {

                                    System.out.println("Input Employee Id:");
                                    String promotionId = input.nextLine().toUpperCase();

                                    // Print out how long the employee has been a part of the system also
                                    System.out.println("Input New Salary Scale:");
                                    int salaryScale = Integer.parseInt(input.nextLine());

                                    System.out.println("Would you like to confirm this promotion: Y)es or N)o");
                                    command = input.nextLine().toUpperCase();

                                    if (command.equals("Y")) {
                                        if (payroll.getEmployee(promotionId).getContractType().equals("FULLTIME")) {
                                            FullTimeEmployee employee = (FullTimeEmployee) payroll.getEmployee(promotionId);
                                            employee.setPromotionOffer(true);
                                            employee.tempPromoteEmployee(salaryScale);
                                        }

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
    }
}