-- gzq.gzq definition

CREATE TABLE `gzq` (
                       `id` int NOT NULLs,
                       `name` varchar(50) NOT NULL,
                       `text` varchar(255) NOT NULL,
                       `age` varchar(20) DEFAULT NULL,
                       PRIMARY KEY (`id`),
                       KEY `myindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;