import org.informatics.service.*;

import org.informatics.domain.Employee;
import org.informatics.domain.Manager;
import org.informatics.domain.Operator;
import org.informatics.domain.EmployeeSerializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeSerializationIntegrationTest {

    @TempDir
    Path tempDir;

    private String filePath;
    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        // Sample employees
        Employee emp1 = new Operator("Ivan", 2500, "Forklifting");
        Employee emp2 = new Manager("Petyo", 3500, "SpreadSheeting", 10.25, 4500);

        employeeList = Arrays.asList(emp1, emp2);

        filePath = tempDir.resolve("employees.ser").toString();
    }

    @Test
    void testSerializeAndDeserializeEmployeesSuccessfully() throws Exception {
        // Serialize
        EmployeeSerializer.serializeEmployees(employeeList, filePath);

        // Deserialize
        List<Employee> deserialized = EmployeeSerializer.deserializeEmployees(filePath);

        assertNotNull(deserialized);
        assertEquals(employeeList.size(), deserialized.size());
    }

    @Test
    void testSerializeThrowsExceptionOnEmptyList() {
        List<Employee> emptyList = List.of();

        EmployeeSerializationException exception = assertThrows(
                EmployeeSerializationException.class,
                () -> EmployeeSerializer.serializeEmployees(emptyList, filePath)
        );
        assertTrue(exception.getMessage().contains("No list of employees was provided"));
    }

    @Test
    void testDeserializeThrowsExceptionOnMissingFile() {
        String missingFile = tempDir.resolve("missing.ser").toString();

        EmployeeSerializationException exception = assertThrows(
                EmployeeSerializationException.class,
                () -> EmployeeSerializer.deserializeEmployees(missingFile)
        );
        assertTrue(exception.getMessage().contains("does not exist"));
    }

    @Test
    void testDeserializeThrowsExceptionOnCorruptFile() throws Exception {
        File corruptFile = tempDir.resolve("corrupt.ser").toFile();
        try (FileWriter writer = new FileWriter(corruptFile)) {
            writer.write("Not a serialized object");
        }

        EmployeeSerializationException exception = assertThrows(
                EmployeeSerializationException.class,
                () -> EmployeeSerializer.deserializeEmployees(corruptFile.getAbsolutePath())
        );
        assertTrue(exception.getMessage().contains("Error was encountered during de-serialization"));
    }
}
