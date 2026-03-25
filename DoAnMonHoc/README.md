# Jolliebee

Jolliebee la he thong quan ly du an duoc xay dung bang Spring Boot, Thymeleaf va MySQL. Ung dung huong den bai toan quan ly du an theo kieu Jira mini, cho phep nguoi dung dang nhap, quan ly du an, theo doi issue va cap nhat thong tin ca nhan trong mot giao dien web thong nhat.

## 1. Tong quan he thong

Project duoc chia thanh 2 phan chinh:

- Backend Java Spring Boot xu ly nghiep vu, bao mat, truy cap du lieu va cung cap REST API.
- Frontend Thymeleaf + JavaScript thuan dung de render giao dien va goi API noi bo.

Muc tieu cua he thong:

- Quan ly nguoi dung trong he thong.
- Quan ly danh sach du an.
- Quan ly issue trong tung du an.
- Ho tro theo doi tien do cong viec cua nhom.
- Mo rong ve sau cho sprint va board.

## 2. Cong nghe su dung

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- Lombok
- HTML, CSS, JavaScript

## 3. Cau truc chuc nang chinh

He thong hien tai xoay quanh 4 nhom nghiep vu chinh:

### 3.1. Quan ly nguoi dung

Cho phep:

- Dang ky tai khoan moi
- Dang nhap vao he thong
- Xem thong tin ca nhan
- Cap nhat ten hien thi, email, avatar
- Quan ly vai tro va trang thai hoat dong

Thuc the chinh:

- `User`

Thuoc tinh quan trong:

- `id`
- `username`
- `email`
- `password`
- `displayName`
- `avatarUrl`
- `role`
- `active`
- `createdAt`

### 3.2. Quan ly du an

Cho phep:

- Tao du an
- Xem danh sach du an
- Xem chi tiet du an
- Cap nhat du an
- Xoa du an

Thuc the chinh:

- `Project`

Thuoc tinh quan trong:

- `id`
- `name`
- `key`
- `description`
- `lead`
- `createdAt`

Moi du an co the:

- Gan voi mot nguoi phu trach (`lead`)
- Chua nhieu issue
- Chua nhieu sprint

### 3.3. Quan ly issue

Cho phep:

- Tao issue trong du an
- Xem danh sach issue
- Xem chi tiet issue
- Sua issue
- Gan nguoi xu ly
- Loc issue theo trang thai

Thuc the chinh:

- `Issue`

Thuoc tinh quan trong:

- `id`
- `issueKey`
- `summary`
- `description`
- `status`
- `priority`
- `type`
- `project`
- `reporter`
- `assignee`
- `createdAt`
- `dueAt`

Mot issue gan voi:

- 1 du an
- 1 trang thai
- 1 do uu tien
- 1 loai issue
- 1 nguoi tao
- 1 nguoi duoc giao

### 3.4. Quan ly binh luan

Cho phep:

- Them comment cho issue
- Lay danh sach comment cua issue

Thuc the chinh:

- `Comment`

Moi comment gan voi:

- 1 issue
- 1 user

## 4. Cac bang lookup nghiep vu

He thong co cac bang tra cuu de chuan hoa issue:

### Trang thai issue

- `TODO`
- `IN_PROGRESS`
- `DONE`

### Do uu tien

- `LOW`
- `MEDIUM`
- `HIGH`

### Loai issue

- `TASK`
- `BUG`
- `STORY`

Du lieu nay duoc khoi tao san trong:

- `DataSeeder`

## 5. Cac thuc the da duoc thiet ke nhung chua khai thac day du

Ngoai cac module dang hoat dong, project da co san model cho:

- `Sprint`
- `Board`

Dieu nay cho thay huong phat trien cua he thong la mo rong thanh mot ung dung quan ly du an day du hon trong tuong lai.

## 6. Luong hoat dong cua he thong

Luong tong quat:

1. Nguoi dung vao trang `login`
2. Dang nhap bang Spring Security
3. Sau khi dang nhap thanh cong, he thong dieu huong den `dashboard`
4. Cac trang Thymeleaf render giao dien
5. File JavaScript `app.js` goi REST API de lay va cap nhat du lieu
6. Du lieu duoc hien thi tren cac man hinh: dashboard, du an, issues, ho so

## 7. Cac man hinh giao dien hien tai

He thong hien co cac man hinh chinh:

- Dang nhap
- Dang ky
- Dashboard
- Danh sach du an
- Chi tiet du an
- Chinh sua du an
- Danh sach issue
- Chi tiet issue
- Ho so ca nhan

## 8. Bao mat va phan quyen

He thong su dung Spring Security de:

- Bao ve cac duong dan `/api/**`
- Cho phep public voi:
  - `/login`
  - `/register`
  - tai nguyen tinh (`/css/**`, `/js/**`, `/images/**`)
  - `POST /api/users`
  - `/api/login`

Dang nhap web hien dang su dung:

- Form login cua Spring Security

Ngoai ra, project co them:

- API `GET /api/users/me` de lay thong tin user hien tai

Phan quyen hien tai moi dung o muc:

- Chi can da xac thuc la duoc goi API

Chua co phan quyen nghiep vu chi tiet nhu:

- Admin moi duoc xoa user
- Lead moi duoc sua project
- Assignee moi duoc cap nhat issue

## 9. Diem manh hien tai cua project

- Chia layer kha ro rang: controller, service, repository, entity
- Co dang nhap va bao mat co ban
- Co CRUD cho user, project, issue
- Co seed du lieu mau de demo nhanh
- Co huong mo rong tot cho board va sprint
- Co giao dien web da duoc thiet ke lai theo huong hien dai hon

## 10. Cac diem chua khop hoac con dang do

Day la nhung diem quan trong can luu y khi tiep tuc phat trien:

### 10.1. Frontend va backend issue chua map hoan toan dong bo

Frontend dang co xu huong dung cac field don gian nhu:

- `title`
- `priority`
- `status`

Trong khi backend dang thiet ke theo model chuan hon:

- `summary`
- `priorityId`
- `statusId`
- `typeId`

Dieu nay co the gay lech du lieu neu khong dong bo lai.

### 10.2. Trang thai du an tren giao dien chua dung voi entity

Frontend dang dung `active` de hien thi trang thai du an, nhung entity `Project` hien tai chua co truong nay.

### 10.3. Gia tri trang thai issue giua UI va DB chua thong nhat

Database dang seed:

- `TODO`
- `IN_PROGRESS`
- `DONE`

Nhung UI co luc dang hien thi:

- `Open`
- `In Progress`
- `Done`

Can chuan hoa lai de tranh sai nghiep vu.

### 10.4. Mot so thanh phan chua hoan thien

- `IssueMapper` dang de trong
- `GlobalExceptionHandler` dang de trong
- Chua co UI day du cho comment
- Chua co flow sprint/board hoan chinh

## 11. Dinh huong nghiep vu cua he thong

Neu mo ta ngan gon, Jolliebee la:

> He thong quan ly du an theo huong Jira mini, tap trung vao user, project, issue va theo doi tien do cong viec trong nhom.

Neu mo ta day du hon:

> Jolliebee la he thong quan ly du an cho phep nguoi dung dang nhap, quan ly thong tin ca nhan, tao va theo doi du an, quan ly issue theo trang thai va do uu tien, dong thoi ho tro phan vai tro nguoi tao va nguoi duoc giao viec. He thong duoc xay dung theo mo hinh nhieu lop voi backend Spring Boot, bao mat bang Spring Security, luu tru du lieu tren MySQL va hien thi giao dien bang Thymeleaf ket hop JavaScript goi REST API.

## 12. Cach chay project

Yeu cau:

- Java 17
- MySQL
- Tao san database `todo_db`

Cau hinh hien tai:

- Port: `8080`
- DB: `todo_db`
- `spring.jpa.hibernate.ddl-auto=update`

File cau hinh:

- `src/main/resources/application.properties`

## 13. Tai khoan mau

Du lieu mau duoc seed san neu bang `users` chua co du lieu:

- `admin / password123`
- `user1 / password123`
- `user2 / password123`

## 14. Kết luận

Ve mat nghiep vu, app hien tai da co nen tang tot cho mot he thong quan ly du an co ban:

- Quan ly user
- Quan ly project
- Quan ly issue
- Quan ly comment

Dong thoi da co huong mo rong thanh he thong day du hon voi:

- Sprint
- Board
- Phan quyen chi tiet
- Dong bo frontend/backend chat che hon

