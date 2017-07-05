-- auto Generated on 2017-06-27 14:06:52 
-- DROP TABLE IF EXISTS `user_privilege`; 
CREATE TABLE `t_user_privilege`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    `privilege_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'privilegeId',
    `status` INT (4) NOT NULL DEFAULT -1 COMMENT 'status',
    `get_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'getTime',
    `start_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'startTime',
    `expire_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'expireTime',
    `used_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'usedTime',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user_privilege`';
