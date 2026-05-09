# Bài 9.6: CI/CD Pipeline Optimization & Caching

## 1. Đề bài
Khi dự án phát triển, việc tải xuống các Maven dependency từ đầu cho mỗi lần chạy CI tiêu tốn nhiều thời gian và làm chậm chu kỳ phát triển.

**Yêu cầu:**
- Cấu hình dependency caching trong workflow GitHub Actions bằng cách thêm tham số `cache: 'maven'` vào action `setup-java`.
- Thực hiện 2 lần push code liên tiếp.
- Ghi lại và so sánh thời gian thực thi.
- Phân tích log để chứng minh dependency được lấy từ cache.

## 2. Kết quả so sánh hiệu năng

| Lần chạy | Trạng thái Cache | Tổng thời gian thực thi | Ghi chú |
| :--- | :--- | :--- | :--- |
| **Lần 1** | Chưa có (Miss) | ... giây | Tải mới toàn bộ từ Maven Central |
| **Lần 2** | Đã có (Hit) | ... giây | Khôi phục từ GitHub Cache |

## 3. Phân tích Log
Sau khi chạy lần 2, bạn hãy kiểm tra log của bước **Set up JDK 11** và tìm các dòng tương tự như sau:
- `Resolved from cache`
- `Cache restored from key: maven-xxxx`

Điều này chứng minh hệ thống đã không tải lại các file JAR từ internet mà lấy trực tiếp từ bộ nhớ đệm của GitHub.
