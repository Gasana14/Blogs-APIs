-- Role Table
INSERT IGNORE INTO roles (role_id, name) VALUES
('a677aa9d-bf52-42ca-afc5-1560f8e860d4', 'ROLE_ADMIN'),
('ede4a736-2ace-47b3-9ef5-c7c644c6f112', 'ROLE_USER');

-- Users Table
INSERT IGNORE INTO users (user_id, email,name,password,username) VALUES
('b2000a4e-0510-43ca-8109-69c71e3c1821', 'admin@gmail.com','Administrator','$10$amQghko8VzRzQlKnZ6fy9uuFc69jWcDQgwcBh0GjH9CnnGGhO98We','admin');

-- Users_Roles Table

INSERT IGNORE INTO users_roles (user_id, role_id) VALUES
('b2000a4e-0510-43ca-8109-69c71e3c1821', 'a677aa9d-bf52-42ca-afc5-1560f8e860d4');