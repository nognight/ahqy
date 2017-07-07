
DROP TABLE IF EXISTS `t_privilege`;
CREATE TABLE `t_privilege`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR (150) NOT NULL DEFAULT '' COMMENT 'name',
    `description` VARCHAR (500) NOT NULL DEFAULT '' COMMENT 'description',
    `status` INT (4) NOT NULL DEFAULT -1 COMMENT 'status',
    `online_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'onlineDate',
    `offline_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'offlineDate',
    `pic_url` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'picUrl',
    `type` INT (4) NOT NULL DEFAULT -1 COMMENT '权益类型',
    `category` INT (4) NOT NULL DEFAULT -1 COMMENT '种类，即类型的子分类',
    `coupon_ids` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'couponIds',
    `product_ids` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'productIds',
    `net_type` INT (4) NOT NULL DEFAULT -1 COMMENT 'netType',
    `pay_type` INT (4) NOT NULL DEFAULT -1 COMMENT 'payType',
    `discount_type` INT (4)  DEFAULT -1 COMMENT '/打折类型 1是百分比折扣 2是满减',
    `discount_price` VARCHAR (50)  DEFAULT '' COMMENT '折扣价格 90可以是9折，或者90元',
    `gift_type` INT (4)  DEFAULT -1 COMMENT '//礼物类型 1是卡券，2是产品',
    `gift_id` VARCHAR (50)  DEFAULT '' COMMENT '礼物Id | 分割',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`privilege`';
