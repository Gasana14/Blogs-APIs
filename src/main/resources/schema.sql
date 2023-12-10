CREATE TABLE IF NOT EXISTS `roles` (
                         `role_id` varchar(255) NOT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `users` (
                         `user_id` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) NOT NULL,
                         `username` varchar(255) NOT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
                         UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `users_roles` (
                               `user_id` varchar(255) NOT NULL,
                               `role_id` varchar(255) NOT NULL,
                               PRIMARY KEY (`user_id`,`role_id`),
                               KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
                               CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
                               CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;