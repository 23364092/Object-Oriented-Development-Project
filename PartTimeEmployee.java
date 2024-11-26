public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private double newHourlyRate;
    private boolean hasSubmittedClaim = false;
    private double claimedHours = 0;

    public PartTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment, String contractType) {
        super(employeeId, name, position, salaryScale, dateOfEmployment, contractType);
        this.hourlyRate = getHourlyRate(position, salaryScale);
    }

    public double getHourlyRate(String position, int salaryScale){
        return reader.getSalaryScaleForPoint(position, salaryScale);
    }

    @Override
    public void permPromoteEmployee() {
        super.permPromoteEmployee();
        hourlyRate = newHourlyRate;

    }

    public void tempPromoteEmployee(int salaryScale) {
        newHourlyRate = reader.getNewSalary(getPosition(), salaryScale);
    }


    public double calculateGrossPay(double hoursWorked) {
        return hoursWorked * hourlyRate;
    }

    @Override
    public String toString() {
        return "Part Time Employee" + "\n" + super.toString() +
                "Hourly Rate: " + hourlyRate;
    }

    @Override
    public String promotionString() {
        return super.promotionString() +
                "New Hourly Rate: " + newHourlyRate + "\n";
    }
}


