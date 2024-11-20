import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

public class CSVReaderEmployee {
    String filePath = "Object-Oriented-Development-Project\\Project\\src\\Employees.csv";

    //Constructor for creating reader object
    public CSVReaderEmployee() {
    }

    //Read from csv method with no argument because path is already set and no return type becuse we dont need to return anything
    public void readEmployeesFromCSV() {
        String line;

        //br.readLine() skips the first line ie.header then starts reading the data with the while loop
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            //While there is data in the line each piece is saved to a respective variable
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String employeeId = data[0].trim();
                String name = data[1].trim();
                String position = data[2].trim();
                int salaryScale = Integer.parseInt(data[3].trim());
                String dateOfEmployment = data[4].trim();

                //Then add each employee object created to the payroll system employees array
                PayrollSystem.employees.add(new Employee(employeeId, name, position, salaryScale, dateOfEmployment));
            }
        } catch (IOException e) {
            //Throw an error if the file cant be read properly
            System.err.println("Error reading the csv file");
        }
    }

}
