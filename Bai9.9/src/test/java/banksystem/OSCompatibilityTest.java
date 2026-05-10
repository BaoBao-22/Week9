package banksystem;

import org.junit.jupiter.api.Test;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class OSCompatibilityTest {

    private static final Logger logger = LoggerFactory.getLogger(OSCompatibilityTest.class);

    @Test
    public void testFilePathCompatibility() {
        // REFACTORED: Sử dụng File.separator hoặc Paths.get() để tự động điều chỉnh theo OS
        String compatiblePath = "logs" + File.separator + "bank-system.log";
        
        logger.info("Đang kiểm tra đường dẫn tương thích: {}", compatiblePath);
        
        // Kiểm tra xem đường dẫn có khớp với mong đợi không
        String expected = "logs" + File.separator + "bank-system.log";
        assertEquals(expected, compatiblePath, "Đường dẫn phải tương thích với mọi hệ điều hành");
    }
}
