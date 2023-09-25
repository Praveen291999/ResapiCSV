package Restapi.csv;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CsvExportService {
	private static final Logger LOGGER = LogManager.getLogger();

  //  private static final Logger log = getLogger(CsvExportService.class);

    private final EmployeeRepository employeeRepository;

    public CsvExportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void writeEmployeesToCsv(Writer writer) {

        List<Employee> employees = employeeRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "First Name", "Last Name","Email","Department");
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getDepartment());
            }
        } catch (IOException e) {
        	LOGGER.error("Error While writing CSV ", e);
        }
    }
}