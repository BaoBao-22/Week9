# Bài 10: The Broken Pipeline

Dự án này mô phỏng một quy trình CI/CD bị hỏng (broken pipeline) với các lỗi từ cấu hình Maven (`pom.xml`) đến logic mã nguồn. Mục tiêu là phân tích log để tìm nguyên nhân và sửa lỗi một cách hệ thống.

## Danh sách lỗi và cách khắc phục

### 1. Lỗi Dependency (logback-classic:9.9.9)
*   **Vị trí:** `pom.xml`, dòng 18.
*   **Bằng chứng (Log):**
    ```text
    [ERROR] Failed to execute goal on project shipping-app: Could not resolve dependencies...
    [ERROR] Could not find artifact ch.qos.logback:logback-classic:jar:9.9.9 in central
    ```
*   **Nguyên nhân:** Phiên bản `9.9.9` không tồn tại trên Maven Central.
*   **Khắc phục:** Cập nhật phiên bản về `1.4.12`.

### 2. Lỗi Incompatibility (Surefire Plugin 2.12.4)
*   **Vị trí:** `pom.xml`, dòng 32.
*   **Bằng chứng (Log):**
    ```text
    -------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    Running com.lab.ShippingCalculatorTest
    Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
    ```
*   **Nguyên nhân:** Plugin Surefire cũ không nhận diện được các test case của JUnit 5 (JUnit Jupiter).
*   **Khắc phục:** Nâng cấp `maven-surefire-plugin` lên phiên bản `3.1.2`.

### 3. Lỗi Logic/Tiềm ẩn NPE (NullPointerException)
*   **Vị trí:** `ShippingCalculator.java`, dòng 9.
*   **Bằng chứng (Phân tích mã nguồn):**
    ```java
    if (type.equals("EXPRESS")) // Crash nếu type là null
    ```
*   **Nguyên nhân:** Sử dụng phương thức `.equals()` trực tiếp trên một biến có thể là `null`.
*   **Khắc phục:** Chuyển sang sử dụng `"EXPRESS".equals(type)` để an toàn hơn hoặc kiểm tra null đầu vào.

---

## Lỗi tự tạo (Lỗi thứ 4)

Sau khi pipeline đã xanh, tôi đã thực hiện tạo lỗi giả lập để kiểm tra hệ thống.

### Mô tả lỗi:
Thay đổi đơn giá vận chuyển `STANDARD` từ `3000` thành `3500` trong `ShippingCalculator.java` nhưng không thay đổi giá trị kỳ vọng trong `ShippingCalculatorTest.java`.

### Bằng chứng Pipeline đỏ:
```text
[ERROR] Failures: 
[ERROR]   ShippingCalculatorTest.testStandard:15 Expected: 15000.0, Actual: 17500.0
```

### Giải thích & Khắc phục:
*   **Nguyên nhân:** Sai lệch giữa logic thực tế và kịch bản kiểm thử.
*   **Khắc phục:** Khôi phục giá trị đơn giá về `3000` để đảm bảo đúng yêu cầu nghiệp vụ ban đầu.

---

## Kết quả cuối cùng
Pipeline CI đã chuyển sang trạng thái **GREEN** hoàn toàn. ✅
