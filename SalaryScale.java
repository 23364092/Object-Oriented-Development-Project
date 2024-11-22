import java.util.ArrayList;

public class SalaryScale {
    private ArrayList<SalaryEntry> salaryEntries;
    // List to store all salary entries

    public SalaryScale() {
        salaryEntries = new ArrayList<>();
    }

    public void addSalaryScale(String position, int scale, double salary) {
        salaryEntries.add(new SalaryEntry(position, scale, salary));
    }

    public double getSalary(String position, int scale) {
        for (SalaryEntry entry : salaryEntries) {
            if (entry.getPosition().equalsIgnoreCase(position) && entry.getScale() == scale) {
                return entry.getSalary();
            }
        }
        return 0.0;
    }

    public boolean hasSalaryScale(String position, int scale) {
        for (SalaryEntry entry : salaryEntries) {
            if (entry.getPosition().equalsIgnoreCase(position) && entry.getScale() == scale) {
                return true;
            }
        }
        return false;
    }

    private static class SalaryEntry {
        private String position;
        private int scale;
        private double salary;

        public SalaryEntry(String position, int scale, double salary) {
            this.position = position;
            this.scale = scale;
            this.salary = salary;
        }

        public String getPosition() {
            return position;
        }

        public int getScale() {
            return scale;
        }

        public double getSalary() {
            return salary;
        }
    }
}
