public class PartTimeEmployee extends Employee {
        private double hourlyRate;
        private double hoursWorked;

        public PartTimeEmployee(String employeeId, String name, String position, int salaryScale, String dateOfEmployment, double hourlyRate) {
            super(employeeId, name, position, salaryScale, dateOfEmployment);
            this.hourlyRate = hourlyRate;
        }

        public double getHourlyRate() {
            return hourlyRate;
        }

        public double calculateGrossPay(int hoursWorked) {
            return hoursWorked * hourlyRate;
        }

        @Override
        public String toString() {
            return super.toString() + "\n" +
                    "Hourly Rate: " + hourlyRate;
        }
}


