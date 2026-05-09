# Bài 9.7: Automated Code Review via Pull Request

## 1. Mục tiêu
Tự động hóa việc kiểm tra chất lượng mã nguồn (linting) và cung cấp phản hồi trực tiếp (inline comments) trên các Pull Request.

## 2. Các thành phần đã thiết lập
- **Plugin Checkstyle**: Đã tích hợp vào `pom.xml` sử dụng tiêu chuẩn Google Java Style.
- **Workflow GitHub Actions**: [bai9.7-pr-review.yml](file:///d:/NguyenKimDat_25023210_Week9/.github/workflows/bai9.7-pr-review.yml) tự động chạy khi có PR vào nhánh `main`.

## 3. Hướng dẫn cấu hình Branch Protection
Để khóa nút Merge khi có lỗi, bạn cần thực hiện trên giao diện GitHub:
1. Vào **Settings** -> **Branches**.
2. Tại mục **Branch protection rules**, nhấn **Add branch protection rule**.
3. **Branch name pattern**: Nhập `main` (hoặc `master`).
4. Tích chọn: **Require status checks to pass before merging**.
5. Trong ô tìm kiếm bên dưới, tìm và chọn: `Checkstyle Review`.
6. Nhấn **Create** để lưu.

## 4. Quy trình kiểm chứng (Verification)
1. Tạo một nhánh mới: `git checkout -b test-review`.
2. Mở một file Java trong `Bai9.7/src/main/java/...` và cố ý tạo lỗi định dạng. 
   - *Ví dụ*: Đặt tên biến là `int my_var = 0;` (vi phạm quy tắc camelCase của Google).
3. Commit và Push nhánh mới lên GitHub.
4. Tạo một **Pull Request (PR)** từ nhánh `test-review` vào `main`.
5. **Kết quả mong đợi**:
   - Workflow sẽ chạy và phát hiện lỗi.
   - Một con Bot sẽ tự động viết bình luận (comment) ngay tại dòng code có biến `my_var`.
   - Nút **Merge** sẽ bị xám màu (khóa) nếu bạn đã thiết lập Branch Protection ở bước 3.
