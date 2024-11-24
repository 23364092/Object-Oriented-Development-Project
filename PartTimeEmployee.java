public class PartTimeEmployee extends Employee {
        private double hourlyRate;

        public PartTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment, String contractType) {
            super(employeeId, name, position, salaryScale, dateOfEmployment, contractType);
            this.hourlyRate = getHourlyRate(position, salaryScale);
        }

        public double getHourlyRate(String position, int salaryScale){
            return reader.getSalaryScaleForPoint(position, salaryScale);
        }

        public double calculateGrossPay(double hoursWorked) {
            return hoursWorked * hourlyRate;
        }

        @Override
        public String toString() {
            return "Part Time Employee" + "\n" + super.toString() +
                    "Hourly Rate: " + hourlyRate;
        }
}


