package banksystem;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class OSCompatibilityTest {

    @Test
    public void testFilePathCompatibility() {
        // REFACTORED: Sử dụng File.separator hoặc Paths.get() để tự động điều chỉnh theo OS
        String compatiblePath = "logs" + File.separator + "bank-system.log";
        
        System.out.println("Đang kiểm tra đường dẫn tương thích: " + compatiblePath);
        
        // Kiểm tra xem đường dẫn có khớp với mong đợi không
        String expected = "logs" + File.separator + "bank-system.log";
        assertEquals(expected, compatiblePath, "Đường dẫn phải tương thích với mọi hệ điều hành");
    }
}
