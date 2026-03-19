# Hướng Dẫn Test API

## 1. Lưu ý quan trọng

- Base URL: `http://localhost:8080`
- Không test ở `http://localhost:8080/` vì project không có trang chủ
- Test đúng API như: `http://localhost:8080/api/users`
- Trong Postman hoặc Thunder Client:
  - Header: `Content-Type: application/json`
  - Body chỉ được là **JSON thuần**
  - Không dán lệnh PowerShell vào ô JSON

JSON đúng:

```json
{
  "username": "admin",
  "email": "admin@example.com",
  "password": "123456",
  "displayName": "Admin",
  "role": "ADMIN",
  "active": true
}
```

## 2. Thứ tự test

1. Tạo user
2. Tạo project
3. Tạo issue
4. Thêm comment

## 3. Dữ liệu có sẵn

- `statusId`: `1=TODO`, `2=IN_PROGRESS`, `3=DONE`
- `priorityId`: `1=LOW`, `2=MEDIUM`, `3=HIGH`
- `typeId`: `1=TASK`, `2=BUG`, `3=STORY`

## 4. Test nhanh

### Tạo user

- Method: `POST`
- URL: `http://localhost:8080/api/users`
- JSON Content:

```json
{
  "username": "admin",
  "email": "admin@example.com",
  "password": "123456",
  "displayName": "Admin",
  "role": "ADMIN",
  "active": true
}
```

### Tạo project

- Method: `POST`
- URL: `http://localhost:8080/api/projects`
- JSON Content:

```json
{
  "name": "Todo Project",
  "key": "TODO",
  "description": "Project demo",
  "leadId": "put-user-id-here"
}
```

### Tạo issue

- Method: `POST`
- URL: `http://localhost:8080/api/issues`
- JSON Content:

```json
{
  "summary": "Fix login bug",
  "description": "Bug dang nhap",
  "statusId": 1,
  "priorityId": 3,
  "typeId": 2,
  "projectId": "put-project-id-here",
  "reporterId": "put-user-id-here",
  "assigneeId": "put-user-id-here",
  "dueAt": "2026-03-25T17:00:00"
}
```

### Thêm comment

- Method: `POST`
- URL: `http://localhost:8080/api/issues/{issueId}/comments`
- JSON Content:

```json
{
  "userId": "put-user-id-here",
  "content": "Da tiep nhan issue"
}
```

## 5. Các API chính

- `GET http://localhost:8080/api/users`
- `POST http://localhost:8080/api/users`
- `GET http://localhost:8080/api/projects`
- `POST http://localhost:8080/api/projects`
- `GET http://localhost:8080/api/issues`
- `POST http://localhost:8080/api/issues`
- `GET http://localhost:8080/api/issues/status/{statusId}`
- `PUT http://localhost:8080/api/issues/{id}/assign/{userId}`
- `GET http://localhost:8080/api/issues/{issueId}/comments`
- `POST http://localhost:8080/api/issues/{issueId}/comments`

## 6. Lỗi hay gặp

### `404` ở `/`

Bình thường, vì app không có trang chủ.

### `400 Bad Request`

Thường do body không phải JSON thuần.

Sai:

```text
$user = Invoke-RestMethod ...
```

Đúng:

```json
{
  "username": "admin",
  "email": "admin@example.com",
  "password": "123456",
  "displayName": "Admin",
  "role": "ADMIN",
  "active": true
}
```