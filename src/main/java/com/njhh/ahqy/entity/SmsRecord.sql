
DROP TABLE IF EXISTS `t_sms_record`;
CREATE TABLE `t_sms_record`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sms_num` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'smsNum',
    `phone_num` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'phoneNum',
    `status` INT (4) NOT NULL DEFAULT -1 COMMENT 'status',
    `send_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'sendDate',
    `msg` VARCHAR (150) NOT NULL DEFAULT '' COMMENT 'msg',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`sms_record`';
