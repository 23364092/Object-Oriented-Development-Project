public class SalaryScale {
    private String role;
    private int experienceLevel; 
    private double baseSalary;
    private double bonus;
    private double deductions;

   
    public SalaryScale(String role, int experienceLevel, double baseSalary, double bonus, double deductions) {
        this.role = role;
        this.experienceLevel = experienceLevel;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.deductions = deductions;
    }

   
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

 
    public double calculateNetSalary() {
        return (baseSalary + bonus) - deductions;
    }

    @Override
    public String toString() {
        return "SalaryScale{" +
                "role='" + role + '\'' +
                ", experienceLevel=" + experienceLevel +
                ", baseSalary=" + baseSalary +
                ", bonus=" + bonus +
                ", deductions=" + deductions +
                ", netSalary=" + calculateNetSalary() +
                '}';
    }
}
