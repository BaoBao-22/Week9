# Bài 9.8: Đóng gói sản phẩm thực thi

## 1. Mục tiêu
Cấu hình Maven để tạo ra tệp `.jar` có thể chạy độc lập, giúp ứng dụng có thể triển khai mà không cần môi trường phát triển (IDE).

## 2. Cách thực hiện
- **Cấu hình Manifest**: Trong `pom.xml`, plugin `maven-jar-plugin` đã được thiết lập để chỉ định `banksystem.Bank` là lớp khởi chạy chính.
- **Lệnh đóng gói**: `mvn clean package`.
- **Lệnh thực thi**: `java -jar target/Bai9.8-1.0-SNAPSHOT.jar`.

## 3. Giải thích khái niệm

### Thư mục `target`
- Là thư mục được Maven tự động tạo ra để chứa các kết quả của quá trình build.
- **Nội dung**: Bao gồm các file `.class` (trong `classes/`), các file báo cáo kiểm thử (`surefire-reports/`), và quan trọng nhất là file đóng gói cuối cùng (`.jar`).
- **Lưu ý**: Thư mục này thường bị loại bỏ khỏi Git (qua `.gitignore`) vì nó có thể được tái tạo bất cứ lúc nào từ mã nguồn bằng lệnh build.

### Pha `package` (Packaging Phase)
- Đây là một giai đoạn trong vòng đời mặc định (Default Lifecycle) của Maven.
- **Nhiệm vụ**: Lấy toàn bộ mã nguồn đã biên dịch và đóng gói chúng thành một định dạng nhất định (như JAR, WAR, EAR).
- **Quy trình**: Khi bạn chạy lệnh `mvn package`, Maven sẽ tự động thực hiện các pha trước đó:
  1. `validate`: Kiểm tra dự án.
  2. `compile`: Biên dịch mã nguồn.
  3. `test`: Chạy các unit test.
  4. `package`: Đóng gói nếu các bước trên thành công.

## 4. Kiểm chứng
Bạn có thể chạy file `run.sh` trong thư mục này để tự động thực hiện quá trình đóng gói và khởi chạy ứng dụng.
