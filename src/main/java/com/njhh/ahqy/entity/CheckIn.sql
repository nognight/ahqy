-- auto Generated on 2017-06-26 10:54:43 
-- DROP TABLE IF EXISTS `check_in`; 
CREATE TABLE `t_check_in`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    `check_in_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'checkInDate',
    `get_score` INT (11) NOT NULL DEFAULT -1 COMMENT 'getScore',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`check_in`';
