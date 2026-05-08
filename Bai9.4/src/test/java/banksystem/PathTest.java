package banksystem;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class PathTest {

    @Test
    public void testFilePathFormat() {
        // REFACTORED: Use Path.of() or Paths.get() for cross-platform compatibility
        Path path = Paths.get("logs", "bank.log");
        String actualPath = path.toString();
        
        // Expected path should also be constructed dynamically for the test to pass on all OS
        String expectedPath = "logs" + File.separator + "bank.log";
        
        System.out.println("Testing path compatibility (REFACTORED)...");
        System.out.println("Expected: " + expectedPath);
        System.out.println("Actual:   " + actualPath);
        
        assertEquals(expectedPath, actualPath, "Path format should be cross-platform compatible");
    }
}
