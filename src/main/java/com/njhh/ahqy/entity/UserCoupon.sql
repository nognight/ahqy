-- auto Generated on 2017-06-26 11:32:50 
-- DROP TABLE IF EXISTS `user_coupon`; 
CREATE TABLE `t_user_coupon`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    `coupon_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'couponId',
    `status` INT (4) NOT NULL DEFAULT -1 COMMENT 'status',
    `get_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'getTime',
    `start_time` DATETIME COMMENT 'startTime',
    `expire_time` DATETIME COMMENT 'expireTime',
    `used_time` DATETIME COMMENT 'usedTime',
    INDEX(user_id),
    INDEX(coupon_id),
    INDEX(status),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user_coupon`';
