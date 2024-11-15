import java.io.*;
import java.util.*;

public class SalaryScale {
    private String role;
    private double minSalary;
    private double maxSalary;

    // Constructor
    public SalaryScale(String role, double minSalary, double maxSalary) {
        this.role = role;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    // Display details
    public void display() {
        System.out.println("Role: " + role + ", Min: " + minSalary + ", Max: " + maxSalary);
    }

    // Read salary scales from a CSV file
    public static List<SalaryScale> fromCSV(String fileName) {
        List<SalaryScale> scales = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                scales.add(new SalaryScale(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return scales;
    }

    public static void main(String[] args) {
        // Example usage
        List<SalaryScale> scales = SalaryScale.fromCSV("salary_scales.csv");
        for (SalaryScale scale : scales) {
            scale.display();
        }
    }
}
