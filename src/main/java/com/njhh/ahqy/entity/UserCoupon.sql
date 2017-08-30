-- auto Generated on 2017-08-30 14:25:28
DROP TABLE IF EXISTS `t_user_coupon`;
CREATE TABLE `t_user_coupon`(
    `id` INT (11) UNIQUE NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    `coupon_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'couponId',
    `source` INT (4) NOT NULL DEFAULT -1 COMMENT 'source',
    `status` INT (4) NOT NULL DEFAULT -1 COMMENT 'status',
    `get_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'getTime',
    `start_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'startTime',
    `expire_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'expireTime',
    `used_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'usedTime',
    `remark` VARCHAR (100) DEFAULT '' COMMENT 'remark',
    INDEX(source),
    INDEX(status),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`t_user_coupon`';
