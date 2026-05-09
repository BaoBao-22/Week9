# Bài 9.5: Test Coverage & Quality Enforcement (JaCoCo)

Dự án này tích hợp JaCoCo để đo lường và thực thi các quy tắc về độ bao phủ mã nguồn (Code Coverage).

## 1. JaCoCo Maven Plugin

Chúng tôi sử dụng `jacoco-maven-plugin` để:
- Thu thập dữ liệu trong quá trình chạy Unit Test.
- Tạo báo cáo trực quan dưới dạng HTML.
- Kiểm tra các quy tắc nghiêm ngặt về độ bao phủ.

## 2. Quy tắc nghiêm ngặt (Strict Rules)

Dự án được cấu hình để **tự động thất bại (Fail Build)** nếu độ bao phủ mã nguồn theo dòng (Line Coverage) thấp hơn **80%**. 

Cấu hình trong `pom.xml`:
```xml
<rule>
    <element>BUNDLE</element>
    <limits>
        <limit>
            <counter>LINE</counter>
            <value>COVEREDRATIO</value>
            <minimum>0.80</minimum>
        </limit>
    </limits>
</rule>
```

## 3. Tích hợp GitHub Actions

Quy trình CI đã được cập nhật để:
1. Chạy lệnh `mvn verify`. Lệnh này bao gồm việc chạy test, tạo báo cáo JaCoCo và kiểm tra quy tắc 80%.
2. Tải lên (Upload) báo cáo JaCoCo dưới dạng Artifact để có thể tải về và xem lại sau khi build xong.

## 4. Cách xem báo cáo

Sau khi chạy lệnh `mvn verify` tại máy cục bộ hoặc trên GitHub Actions:
- Báo cáo HTML sẽ được tạo tại: `target/site/jacoco/index.html`
- Bạn có thể mở file này bằng trình duyệt để xem chi tiết từng lớp và phương thức được bao phủ bao nhiêu phần trăm.

---
**Người thực hiện:** Đạt
**Ngày:** 09/05/2026
