DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `type` INT (11) NOT NULL DEFAULT -1 COMMENT 'type',
    `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
    `description` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'description',
    `status` INT (11) NOT NULL DEFAULT -1 COMMENT 'status',
    `is_transferable` INT (11) NOT NULL DEFAULT -1 COMMENT 'isTransferable',
    `is_purchasable` INT (11) NOT NULL DEFAULT -1 COMMENT 'isPurchasable',
    `retail_price` INT (11) NOT NULL DEFAULT -1 COMMENT 'retailPrice',
    `retail_type` INT (11) NOT NULL DEFAULT -1 COMMENT 'retailType',
    `online_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'onlineDate',
    `offline_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'offlineDate',
    `pic_url` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'picUrl',
    `product_ids` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'productIds',
    `pay_type` INT (11) NOT NULL DEFAULT -1 COMMENT 'payType',
    `net_type` INT (11) NOT NULL DEFAULT -1 COMMENT 'netType',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`coupon`';
