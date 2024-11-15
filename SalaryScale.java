import java.util.ArrayList;

public class SalaryScale {
    private class RoleSalary {
        String role;
        double salary;
        RoleSalary(String role, double salary) {
            this.role = role;
            this.salary = salary;
        }
    }
    private ArrayList<RoleSalary> salaryScale;
    public SalaryScale() {
        salaryScale = new ArrayList<>();
        initializeScales();
    }

    // Initialize some sample salary scales
    private void initializeScales() {
        salaryScale.add(new RoleSalary("Admin", 30000.00));
        salaryScale.add(new RoleSalary("HR", 25000.00));
        salaryScale.add(new RoleSalary("Manager", 50000.00));
        salaryScale.add(new RoleSalary("Developer", 45000.00));
        salaryScale.add(new RoleSalary("Intern", 10000.00));
    }

    // Get the salary for a given role
    public double getSalary(String role) {
        for (RoleSalary rs : salaryScale) {
            if (rs.role.equalsIgnoreCase(role)) {
                return rs.salary;
            }
        }
        return 0.0; // Return 0.0 if the role is not found
    }

    // Add or update a salary scale
    public void addOrUpdateScale(String role, double salary) {
        for (RoleSalary rs : salaryScale) {
            if (rs.role.equalsIgnoreCase(role)) {
                rs.salary = salary; // Update salary if role exists
                return;
            }
        }
        salaryScale.add(new RoleSalary(role, salary)); // Add new role if not found
    }
    // Remove a salary scale
    public boolean removeScale(String role) {
        for (int i = 0; i < salaryScale.size(); i++) {
            if (salaryScale.get(i).role.equalsIgnoreCase(role)) {
                salaryScale.remove(i);
                return true;
            }
        }
        return false; // Return false if the role is not found
    }
    // Display all salary scales
    public void displayScales() {
        System.out.println("Current Salary Scales:");
        for (RoleSalary rs : salaryScale) {
            System.out.println(rs.role + ": $" + rs.salary);
        }
    }
}
