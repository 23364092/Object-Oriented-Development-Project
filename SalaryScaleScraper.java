import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SalaryScaleScraper {
    private Scanner input;
    public SalaryScaleScraper(String filename) throws FileNotFoundException {
        input = new Scanner(new File(filename));
    }
    public void populateSalaryScale(SalaryScale salaryScale) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] tokens = line.split(",");

            if (tokens.length == 3) {
                String position = tokens[0].trim();
                int scale = Integer.parseInt(tokens[1].trim());
                double salary = Double.parseDouble(tokens[2].trim());

                salaryScale.addSalaryScale(position, scale, salary);
            }
        }
        input.close();
    }
}
