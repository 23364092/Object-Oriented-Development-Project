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

    public void run(PayrollSystem payroll) {

        boolean more = true;

        while (more) {
            System.out.println("Input user type: E)mployee   H)uman Resources   A)dmin   Q)uit");
            command = input.nextLine().toUpperCase();

            if (command.equals("Q")) {
                more = false;
            }

            if (command.equals("A")) {
                Boolean valid = false;

                while (!valid) {
                    System.out.println("Q)uit\nInput employee Id: ");
                    employeeId = input.nextLine().toUpperCase();

                    // Check for "Q" to quit
                    if (employeeId.equalsIgnoreCase("Q")) {
                        return; // Go back to the main menu
                    }

                    if (payroll.adminCheck(employeeId)) {
                        valid = true;
                    } else {
                        System.err.println("Warning: Employee does not have admin privileges or is not found.");
                    }
                }

                if (payroll.adminCheck(employeeId)) {
                    valid = false;

                    while (!valid) {
                        System.out.println("Q)uit\nInput password: ");
                        password = input.nextLine().toUpperCase();

                        // Check for "Q" to quit
                        if (password.equalsIgnoreCase("Q")) {
                            return; // Go back to the main menu
                        }

                        if (payroll.adminPasswordCheck(password)) {
                            valid = true;
                        } else {
                            System.err.println("Incorrect password, please try again.");
                        }
                    }

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
                                String newEmployeeId = "";
                                valid = false;

                                while (true) {
                                    System.out.println("Press 'Q' to quit at any time.");

                                    // Get Employee ID
                                    while (!valid) {
                                        System.out.println("Enter Employee Id: ");
                                        newEmployeeId = input.nextLine();

                                        // Check for "Q" to quit
                                        if (newEmployeeId.equalsIgnoreCase("Q")) {
                                            break; // Exit back to the admin menu
                                        }

                                        if (!payroll.employeeCheck(newEmployeeId)) {
                                            valid = true;
                                        } else {
                                            System.err.println("Warning: Employee with ID " + newEmployeeId + " already exists.");
                                        }
                                    }

                                    if (newEmployeeId.equalsIgnoreCase("Q")) {
                                        break; // Exit back to the admin menu
                                    }

                                    // Collect remaining employee details
                                    System.out.println("Enter Employee Name: ");
                                    String newEmployeeName = input.nextLine();
                                    if (newEmployeeName.equalsIgnoreCase("Q")) break;

                                    System.out.println("Enter Contract Type: ");
                                    System.out.println("1) Full-Time");
                                    System.out.println("2) Part-Time");
                                    String contractInput = input.nextLine();
                                    if (contractInput.equalsIgnoreCase("Q")) break;

                                    int newContractType = Integer.parseInt(contractInput);

                                    System.out.println("Enter Employee Position: ");
                                    String newEmployeePosition = input.nextLine();
                                    if (newEmployeePosition.equalsIgnoreCase("Q")) break;

                                    int newSalaryScale = 0;
                                    double newHourlyRate = 0;

                                    if (newContractType == 1) {
                                        valid = false;
                                        String scaleInput = "";
                                        while (!valid) {
                                            System.out.println("Enter Employee Salary Scale: ");
                                            try {
                                                scaleInput = input.nextLine();
                                                if (scaleInput.equalsIgnoreCase("Q")) break;
                                                newSalaryScale = Integer.parseInt(scaleInput);
                                                valid = true;
                                            } catch (NumberFormatException e) {
                                                System.err.println("Invalid argument: Input must be a number.");
                                            }
                                        }
                                        if (scaleInput.equalsIgnoreCase("Q")) break;
                                    } else if (newContractType == 2) {
                                        System.out.println("Enter Hourly Rate: ");
                                        String rateInput = input.nextLine();
                                        if (rateInput.equalsIgnoreCase("Q")) break;
                                        newHourlyRate = Double.parseDouble(rateInput);
                                    }

                                    String newDateOfEmployment = "";
                                    String promotion = "";

                                    // Get Date of Employment
                                    valid = false;
                                    while (!valid) {
                                        System.out.println("Enter Date Of Employment: yyyy-mm-dd");
                                        newDateOfEmployment = input.nextLine();
                                        if (newDateOfEmployment.equalsIgnoreCase("Q")) break;

                                        String[] date = newDateOfEmployment.split("-");
                                        try {
                                            int yearOfEmployment = Integer.parseInt(date[0].trim());
                                            promotion = (yearOfEmployment == LocalDate.now().getYear()) ? "FALSE" : "TRUE";
                                            valid = true;
                                        } catch (IllegalArgumentException e) {
                                            System.err.println("Input a valid date.");
                                        }
                                    }

                                    if (newDateOfEmployment.equalsIgnoreCase("Q")) break;

                                    if (valid) {
                                        Employee newEmployee;
                                        if (newContractType == 1) {
                                            newEmployee = new FullTimeEmployee(newEmployeeId, newEmployeeName, newEmployeePosition, newSalaryScale, newDateOfEmployment, "FULLTIME", promotion, 0);
                                        } else if (newContractType == 2) {
                                            newEmployee = new PartTimeEmployee(newEmployeeId, newEmployeeName, newEmployeePosition, newHourlyRate, newDateOfEmployment, "PARTTIME");
                                        } else {
                                            System.out.println("Invalid contract type selected!");
                                            break;
                                        }

                                        payroll.addEmployee(newEmployee);
                                        CSVWriterEmployee writer = new CSVWriterEmployee();
                                        writer.writeEmployeeToCSV(newEmployee);
                                        System.out.println("Employee added successfully!");
                                        break;
                                    }
                                }
                            } else if (command.equals("R")) {
                                System.out.println(payroll.printEmployees());
                            } else if (command.equals("Q")) {
                                adminMenu = false; // Exit admin menu and return to the main menu
                            }
                        }
                    }
                }
            } else if (command.equals("E") || command.equals("H")) {
                    while (true) {
                        System.out.println("Q)uit\nInput Employee ID:");
                        employeeId = input.nextLine().toUpperCase();
                        if (payroll.employeeCheck(employeeId) || employeeId.equalsIgnoreCase("Q")) {
                            break;
                        } else {
                            System.err.println("Employee " + employeeId + " not found, please try again.");
                        }
                    }
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
                                    if (employee.getRecentPayslip().getDate().getMonthValue() < LocalDate.now().getMonthValue() || employee.getRecentPayslip().getDate().getYear() != LocalDate.now().getYear()) {
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
                                    } else if ((employee.getRecentPayslip().getDate().getMonthValue() < LocalDate.now().getMonthValue() || employee.getRecentPayslip().getDate().getDayOfMonth() < LocalDate.now().getYear()) && command.equals("SU") && (employee.getRecentPayslip().getDate().getMonthValue() < LocalDate.now().getDayOfMonth() || employee.getRecentPayslip().getDate().getDayOfMonth() < LocalDate.now().getYear())) {
                                        //Check date on system. If its second friday of month or gone past then this function isnt possible
                                        boolean result = payroll.fridayCheck();

                                        while (true) {
                                            if (result) {
                                                try {
                                                    System.out.println("Q)uit\nInput hours worked this month: ");
                                                    String hoursInput = input.nextLine();
                                                    if (hoursInput.equalsIgnoreCase("Q")) {
                                                        break;
                                                    }
                                                    int hoursWorked = Integer.parseInt(hoursInput);

                                                    employee = (PartTimeEmployee) payroll.getEmployee(employeeId);
                                                    employee.setHoursWorked(hoursWorked);
                                                    employee.createPayslip();;
                                                    break;
                                                } catch (NumberFormatException e) {
                                                    System.err.println("Please provide a valid input.");
                                                }
                                            }
                                        }
                                    } else if (command.equals("Q")) {
                                        employeeMenu = false;
                                    }
                                }

                            }
                        } else if (command.equals("H")) {

                            while (true) {
                                System.out.println("Q)uit\nInput password: ");
                                password = input.nextLine();
                                if (payroll.hrPasswordCheck(password) || password.equalsIgnoreCase("Q")) {
                                    break;
                                } else {
                                    System.err.println("Password incorrect. Please try again.");
                                }
                            }
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
                                        String promotionId = "";
                                        while(true) {
                                            while (true) {
                                                System.out.println("Press 'Q' at any time to quit.\nInput Employee Id:");
                                                promotionId = input.nextLine().toUpperCase();
                                                if (payroll.employeeCheck(promotionId) || promotionId.equalsIgnoreCase("Q")) {
                                                    break;
                                                } else {
                                                    System.err.println("Employee " + promotionId + "not found. Please try again.");
                                                }
                                            }
                                            if (promotionId.equalsIgnoreCase("Q")) break;
                                            // Print out how long the employee has been a part of the system also
                                            int salaryScale = 1;
                                            String salaryInput;
                                            while (true) {
                                                try {
                                                    System.out.println("Input New Salary Scale:");
                                                    salaryInput = input.nextLine();
                                                    if (salaryInput.equalsIgnoreCase("Q")) break;
                                                    salaryScale = Integer.parseInt(salaryInput);
                                                    break;
                                                } catch (NumberFormatException e) {
                                                    System.err.println("Please enter a valid salary scale.");
                                                }
                                            }
                                            if (salaryInput.equalsIgnoreCase("Q")) break;

                                            while (true) {
                                                System.out.println("Would you like to confirm this promotion: Y)es or N)o");
                                                command = input.nextLine().toUpperCase();
                                                if (command.equalsIgnoreCase("Y") || command.equalsIgnoreCase("N") || command.equalsIgnoreCase("Q")) {
                                                    break;
                                                } else {
                                                    System.err.println("Please provide a valid input.");
                                                }
                                            }
                                            if (command.equalsIgnoreCase("Q")) break;

                                            if (command.equals("Y")) {
                                                if (payroll.getEmployee(promotionId).getContractType().equals("FULLTIME")) {
                                                    FullTimeEmployee employee = (FullTimeEmployee) payroll.getEmployee(promotionId);
                                                    employee.setPromotionOffer(true);
                                                    employee.tempPromoteEmployee(salaryScale);
                                                    System.out.println("FULLTIME: " + employee.getPromotionOffer());
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
                } else if (!command.equalsIgnoreCase("Q")){
                    System.err.println("Please provide a valid input.");
            }
        }
    }
}