-- DROP TABLE IF EXISTS `user_privilege`;
CREATE TABLE `t_user_privilege`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    `privilege_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'privilegeId',
    `source` INT (4) NOT NULL DEFAULT -1 COMMENT '来源   1是短信',
    `status` INT (4) NOT NULL DEFAULT -1 COMMENT '-2增加权益失败，即订购权益失败   -1失效   0可用,即未赠送   1已经使用即已经赠送   2使用失败，赠送失败',
    `get_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'getTime',
    `start_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'startTime',
    `expire_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'expireTime',
    `used_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'usedTime',
    `remark` VARCHAR (100) DEFAULT '' COMMENT 'remark',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user_privilege`';
