package banksystem;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class PathTest {

    @Test
    public void testFilePathFormat() {
        // INTENTIONALLY HARDCODED FOR WINDOWS - Will fail on Linux/macOS
        String expectedPath = "logs\\bank.log";
        String actualPath = "logs" + File.separator + "bank.log";
        
        System.out.println("Testing path compatibility...");
        System.out.println("Expected: " + expectedPath);
        System.out.println("Actual:   " + actualPath);
        
        assertEquals(expectedPath, actualPath, "Path format should match (this will fail on Linux/macOS)");
    }
}
