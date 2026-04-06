# Database Setup Guide (Hướng dẫn thiết lập cơ sở dữ liệu)

## English

### Prerequisites
- MySQL 5.7 or later installed
- MySQL running and accessible

### How to Import the Database

#### Option 1: Using MySQL Command Line
```bash
mysql -u root -p < todo_db.sql
```

#### Option 2: Using MySQL Workbench
1. Open MySQL Workbench
2. Go to `Server` → `Data Import`
3. Select `Import from Self-Contained File`
4. Choose the `todo_db.sql` file
5. Click `Start Import`

#### Option 3: Using phpMyAdmin
1. Open phpMyAdmin in your browser
2. Click on `Import` tab
3. Select the `todo_db.sql` file
4. Click `Go`

### Database Credentials
- **Host**: localhost
- **Port**: 3306
- **Database**: todo_db
- **Root Username**: root
- **Root Password**: (empty)

### Sample Users
The database includes three sample users:
1. **Admin Account**
   - Username: `admin`
   - Password: `password123`
   - Role: SUPER_ADMIN

2. **User 1**
   - Username: `user1`
   - Password: `password123`
   - Role: Member

3. **User 2**
   - Username: `user2`
   - Password: `password123`
   - Role: Member

### Sample Data
The database includes:
- 2 sample projects
- 2 sprints
- 4 sample issues with different statuses and priorities
- 3 sample comments
- Project member assignments

---

## Tiếng Việt (Vietnamese)

### Yêu Cầu Tiên Quyết
- MySQL 5.7 hoặc cao hơn đã được cài đặt
- MySQL đang chạy và có thể truy cập

### Cách Nhập Cơ Sở Dữ Liệu

#### Tùy Chọn 1: Sử Dụng Dòng Lệnh MySQL
```bash
mysql -u root -p < todo_db.sql
```

#### Tùy Chọn 2: Sử Dụng MySQL Workbench
1. Mở MySQL Workbench
2. Đi tới `Server` → `Data Import`
3. Chọn `Import from Self-Contained File`
4. Chọn tệp `todo_db.sql`
5. Nhấp `Start Import`

#### Tùy Chọn 3: Sử Dụng phpMyAdmin
1. Mở phpMyAdmin trong trình duyệt
2. Nhấp vào tab `Import`
3. Chọn tệp `todo_db.sql`
4. Nhấp `Go`

### Thông Tin Đăng Nhập Cơ Sở Dữ Liệu
- **Host**: localhost
- **Port**: 3306
- **Database**: todo_db
- **Username (root)**: root
- **Password (root)**: (trống)

### Người Dùng Mẫu
Cơ sở dữ liệu bao gồm ba người dùng mẫu:
1. **Tài Khoản Admin**
   - Tên đăng nhập: `admin`
   - Mật khẩu: `password123`
   - Vai trò: SUPER_ADMIN

2. **Người Dùng 1**
   - Tên đăng nhập: `user1`
   - Mật khẩu: `password123`
   - Vai trò: Thành viên

3. **Người Dùng 2**
   - Tên đăng nhập: `user2`
   - Mật khẩu: `password123`
   - Vai trò: Thành viên

### Dữ Liệu Mẫu
Cơ sở dữ liệu bao gồm:
- 2 dự án mẫu
- 2 sprints
- 4 vấn đề mẫu với các trạng thái và mức độ ưu tiên khác nhau
- 3 bình luận mẫu
- Gán thành viên dự án

---

## Table of Contents

### Database Tables
- **users**: Lưu trữ thông tin người dùng
- **projects**: Lưu trữ dự án
- **issues**: Lưu trữ các vấn đề/task
- **comments**: Lưu trữ bình luận cho các vấn đề
- **sprints**: Lưu trữ các sprint
- **boards**: Lưu trữ bảng cho người dùng
- **status_type**: Các trạng thái của vấn đề (TODO, IN_PROGRESS, DONE, REVIEW)
- **priority_type**: Các mức độ ưu tiên (LOW, MEDIUM, HIGH)
- **issue_type**: Các loại vấn đề (TASK, BUG, STORY)
- **project_member_role**: Các vai trò thành viên dự án
- **project_members**: Các thành viên trong dự án
- **issue_assignees**: Gán vấn đề cho người dùng

---

## Notes
- Các password được mã hóa bằng BCrypt
- Khóa ngoại (Foreign Keys) đã được cấu hình để duy trì tính toàn vẹn dữ liệu
- Character set: utf8mb4 (hỗ trợ tiếng Việt và các ký tự đặc biệt)
