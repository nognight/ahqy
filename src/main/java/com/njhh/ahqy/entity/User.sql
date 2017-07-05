
-- auto Generated on 2017-06-24 16:59:54
-- DROP TABLE IF EXISTS `user`;
CREATE TABLE `t_user`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `phone_num` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'phoneNum',
    `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
    `passwd` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'passwd',
    `we_code` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'weCode',
    `channel` INT (4) NOT NULL DEFAULT -1 COMMENT '注册渠道 -1是未知 0是启用',
    `net_type` INT (4) NOT NULL DEFAULT -1 COMMENT 'netType',
    `pay_type` INT (4) NOT NULL DEFAULT -1 COMMENT 'payType',
    `city_code` INT (4) NOT NULL DEFAULT -1 COMMENT 'cityCode',
    `state` INT (4) NOT NULL DEFAULT -1 COMMENT '账号状态 -1是停用  0是启用一般会员',
    INDEX(channel),
    INDEX(net_type),
    INDEX(pay_type),
    INDEX(city_code),
    INDEX(state),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user`';
