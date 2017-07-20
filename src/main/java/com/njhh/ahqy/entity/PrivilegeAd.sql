DROP TABLE IF EXISTS `t_privilege_ad`;
CREATE TABLE `t_privilege_ad`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `source` INT (4) DEFAULT -1 COMMENT 'source渠道 为-1时表示未知，为1表示本工程，为2+ 表示其他渠道，',
    `source_name` VARCHAR (50) DEFAULT '' COMMENT 'sourceName 渠道名称',
    `ad_name` VARCHAR (50) DEFAULT '' COMMENT 'adName',
    `ad_url` VARCHAR (50) DEFAULT '' COMMENT 'adUrl 广告链接',
    `ad_pic` VARCHAR (50) DEFAULT '' COMMENT 'adPic 广告图片',
    `ad_info` VARCHAR (250) DEFAULT '' COMMENT 'adInfo',
    `status` INT (4) DEFAULT -1 COMMENT 'status 状态0是上线。',
    `online_date` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'onlineDate',
    `offline_date` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'offlineDate',
    `remark` VARCHAR (250) DEFAULT '' COMMENT 'remark',
    INDEX(source),
    INDEX(status),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`t_privilege_ad`';
-- auto Generated on 2017-07-19 17:18:45 
-- DROP TABLE IF EXISTS `privilege_ad`; 
CREATE TABLE `privilege_ad`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `source` INT (4) DEFAULT -1 COMMENT 'source',
    `source_name` VARCHAR (50) DEFAULT '' COMMENT 'sourceName',
    `type` INT (4) DEFAULT -1 COMMENT 'type',
    `ad_name` VARCHAR (50) DEFAULT '' COMMENT 'adName',
    `ad_url` VARCHAR (50) DEFAULT '' COMMENT 'adUrl',
    `ad_pic` VARCHAR (50) UNIQUE DEFAULT '' COMMENT 'adPic',
    `ad_info` VARCHAR (250) DEFAULT '' COMMENT 'adInfo',
    `status` INT (4) DEFAULT -1 COMMENT 'status',
    `online_date` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'onlineDate',
    `offline_date` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'offlineDate',
    `remark` VARCHAR (250) DEFAULT '' COMMENT 'remark',
    INDEX(source),
    INDEX(type),
    INDEX(status),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`privilege_ad`';
