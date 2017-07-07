
DROP TABLE IF EXISTS `t_user_order`;
CREATE TABLE `t_user_order`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    `phone_num` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'phoneNum',
    `product_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'productId',
    `code` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'code',
    `subType` INT (4) NOT NULL DEFAULT -1 COMMENT 'subType',
    `sms_code` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'smsCode',
    `state` INT (4) NOT NULL DEFAULT -1 COMMENT 'state',
    `order_time` DATETIME COMMENT 'orderTime',
    `back_time` DATETIME COMMENT 'backTime',
    `back_code` VARCHAR (50) COMMENT 'backCode',
    `start_time` DATETIME COMMENT 'startTime',
    `expire_time` DATETIME COMMENT 'expireTime',
    `message` VARCHAR (500) COMMENT 'message',
    INDEX(user_id),
    INDEX(product_id),
    INDEX(state),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user_order`';
