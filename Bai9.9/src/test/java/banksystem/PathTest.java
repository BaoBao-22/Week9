package banksystem;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class PathTest {

        private static final Logger logger = LoggerFactory.getLogger(PathTest.class);

    @Test
    public void testFilePathFormat() {
        // REFACTORED: Use Path.of() or Paths.get() for cross-platform compatibility
        Path path = Paths.get("logs", "bank.log");
        String actualPath = path.toString();
        
        // Expected path should also be constructed dynamically for the test to pass on all OS
        String expectedPath = "logs" + File.separator + "bank.log";
        
        logger.info("Testing path compatibility (REFACTORED)...");
        logger.info("Expected: {}", expectedPath);
        logger.info("Actual:   {}", actualPath);
        
        assertEquals(expectedPath, actualPath, "Path format should be cross-platform compatible");
    }
}
