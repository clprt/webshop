DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
    `account_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `last_name` varchar(255) NOT NULL,
    `email_address` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `address` varchar(255) NOT NULL,
    `phone_number` varchar(25) DEFAULT NULL,
    PRIMARY KEY (`account_id`),
    UNIQUE KEY `uc_account_email_address` (`email_address`)
);