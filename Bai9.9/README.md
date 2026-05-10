# Bài 9.9: Triển khai Logging chuyên nghiệp

## 1. Mục tiêu
Thay thế các phương pháp in log cơ bản (`System.out.println`) bằng một khung làm việc logging chuyên nghiệp (SLF4J + Logback) để tăng khả năng quản lý và truy vết hệ thống.

## 2. Các thay đổi chính

### Tối ưu hóa mã nguồn
- **Loại bỏ `System.out.println`**: Tất cả các câu lệnh in trực tiếp trong code chính và code test đã được thay thế bằng `logger.info()`, `logger.debug()` hoặc `logger.error()`.
- **Parameterized Logging**: Sử dụng dấu giữ chỗ `{}` thay vì cộng chuỗi (string concatenation). 
  - *Lợi ích*: Tránh việc tốn tài nguyên tạo ra các chuỗi ký tự phức tạp nếu mức log đó không được bật trong cấu hình.

### Cấu hình Logback (`logback.xml`)
Đã cấu hình 2 Appender:
1. **STDOUT**: Hiển thị log trực quan trên Console để theo dõi tức thời.
2. **FILE**: Ghi log vào file vật lý tại `logs/app.log`. 
   - Định dạng log trong file bao gồm đầy đủ ngày tháng năm để phục vụ việc truy vết sau này.

## 3. Giải thích chuyên môn

### Tại sao không dùng `System.out.println`?
- **Hiệu suất**: `System.out` là đồng bộ và gây nghẽn (blocking).
- **Quản lý**: Không thể tắt/mở theo mức độ (Level) hoặc đổi hướng đầu ra (vào file, qua mạng...) mà không sửa code.
- **Thông tin**: Thiếu các thông tin quan trọng như Thời gian, Thread, Level, Logger Name.

### Mức độ Log (Log Levels)
- **INFO**: Ghi lại các mốc quan trọng của hệ thống (Ví dụ: Ứng dụng bắt đầu chạy, hoàn thành đọc dữ liệu).
- **DEBUG**: Ghi lại chi tiết quá trình xử lý để phục vụ lập trình viên tìm lỗi.
- **ERROR**: Ghi lại các ngoại lệ (Exception) kèm theo Stack Trace để phân tích nguyên nhân thất bại.

## 4. Kiểm chứng
Bạn hãy chạy file `run.sh` để:
1. Chạy Unit Tests và quan sát log trên Console.
2. Chạy ứng dụng độc lập.
3. Xem nội dung file `logs/app.log` được in ra ở cuối kịch bản.
