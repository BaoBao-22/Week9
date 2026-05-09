# Bài 9.4: Kiểm thử đa hệ điều hành với Matrix Strategy

Dự án này minh họa cách sử dụng GitHub Actions Matrix Strategy để chạy kiểm thử tự động trên nhiều hệ điều hành khác nhau (Ubuntu, Windows, macOS) và cách xử lý các vấn đề tương thích đường dẫn tệp tin trong Java.

## 1. Cấu hình Matrix Strategy trong GitHub Actions

Tệp cấu hình workflow tại `.github/workflows/maven.yml` đã được cập nhật để sử dụng chiến lược Matrix. Điều này cho phép một build pipeline chạy đồng thời trên nhiều môi trường:

```yaml
jobs:
  build:
    strategy:
      matrix:
        os: [ubuntu-latest, windows-latest, macos-latest]
    
    runs-on: ${{ matrix.os }}
```

**Lợi ích:**
- Đảm bảo mã nguồn hoạt động đúng trên mọi nền tảng mà người dùng có thể sử dụng.
- Phát hiện sớm các lỗi liên quan đến hệ điều hành (như phân biệt hoa thường trong tên file hoặc dấu phân cách đường dẫn).

## 2. Vấn đề "Nó chạy được trên máy tôi" (It works on my machine)

Một trong những lỗi phổ biến nhất khi làm việc đa nền tảng là sử dụng đường dẫn tệp tin cố định (hardcoded). 

Ví dụ:
- Windows sử dụng dấu gạch chéo ngược: `logs\app.log`
- Linux/macOS sử dụng dấu gạch chéo xuôi: `logs/app.log`

Nếu viết mã nguồn chỉ với `\`, bài test sẽ thất bại khi chạy trên Ubuntu hoặc macOS trong matrix pipeline.

## 3. Các bước thực hiện trong bài này

1.  **Tạo lỗi có chủ đích:** Tạo file `OSCompatibilityTest.java` sử dụng đường dẫn cứng `logs\\bank-system.log`. 
2.  **Quan sát lỗi:** Khi chạy trên GitHub Actions, job `ubuntu-latest` và `macos-latest` sẽ báo lỗi vì `File.separator` của chúng là `/`, không khớp với `\`.
3.  **Tái cấu trúc (Refactor):** Sử dụng các API của Java để xử lý đường dẫn một cách linh hoạt:
    - Sử dụng `File.separator`: `"logs" + File.separator + "bank-system.log"`
    - Hoặc sử dụng `java.nio.file.Path`: `Paths.get("logs", "bank-system.log")`

## 4. Kết quả

Sau khi refactor, mã nguồn đã vượt qua tất cả các bài kiểm tra trên cả 3 hệ điều hành trong GitHub CI Matrix.

---
**Người thực hiện:** Antigravity AI
**Ngày:** 09/05/2026
