CREATE TABLE `item` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` numeric(19,2) NOT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `uc_item_name` (`name`)
);