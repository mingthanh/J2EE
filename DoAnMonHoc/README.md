# Jolliebee

Jolliebee la ung dung quan ly cong viec theo phong cach Jira mini, xay dung bang Spring Boot, Thymeleaf va MySQL. He thong ho tro dang ky, dang nhap, quan ly project, issue board keo tha, comment va thong bao realtime khi giao viec.

## Tinh nang chinh

- Dang ky, dang nhap bang form va Google OAuth2
- Quan ly project
- Tao, sua, xoa issue
- Board keo tha theo cac cot `TODO`, `IN_PROGRESS`, `REVIEW`, `DONE`
- Phan cong issue cho thanh vien trong project
- Comment trong issue
- Thong bao realtime khi issue duoc assign

## Mo hinh quyen

Global:

- `SUPER_ADMIN`

Project:

- `PROJECT_ADMIN`
- `PM`
- `MEMBER`
- `VIEWER`

Goi y: hien tai repo chua co module `Workspace` rieng, nen chua can tach them nhom role `OWNER / ADMIN / MEMBER / GUEST` o cap workspace.

## Cong nghe su dung

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- Lombok
- HTML, CSS, JavaScript
- SSE cho realtime notification

## Cai dat nhanh

1. Cai Java 17 va MySQL.
2. Tao database:

```sql
CREATE DATABASE todo_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. Kiem tra file cau hinh [application.properties](d:/WorkSpace/J2EE/DoAnMonHoc/src/main/resources/application.properties).
4. Cap nhat lai thong tin MySQL neu may cua ban khac:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh
spring.datasource.username=root
spring.datasource.password=
server.port=8080
spring.jpa.hibernate.ddl-auto=update
```

5. Neu dung Google login, thay `client-id` va `client-secret` bang thong tin cua ban truoc khi deploy/public repo.

## Chay project

Neu Maven wrapper cua may hoat dong:

```bash
./mvnw spring-boot:run
```

Tren Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Sau khi chay, mo:

```text
http://localhost:8080
```

## Tai khoan mau

Neu bang `users` chua co du lieu, he thong se seed san:

- `admin / password123`
- `user1 / password123`
- `user2 / password123`

Trong do:

- `admin` co global role `SUPER_ADMIN`
- `user1`, `user2` la tai khoan thuong, quyen chu yeu duoc cap theo project

## Cac man hinh chinh

- `/login`
- `/register`
- `/dashboard`
- `/projects`
- `/project/{id}`
- `/issues`
- `/issue/{id}`
- `/profile`

## Luu y

- He thong dung `spring.jpa.hibernate.ddl-auto=update`, phu hop de demo va phat trien, khong nen giu nguyen khi deploy production.
- Realtime notification dang dung SSE.
- Neu `mvnw.cmd` loi tren may ban, co the cai Maven rieng va chay bang lenh `mvn spring-boot:run`.

