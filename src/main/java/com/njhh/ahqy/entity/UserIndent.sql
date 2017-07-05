-- auto Generated on 2017-06-28 15:17:57 
-- DROP TABLE IF EXISTS `user_indent`; 
CREATE TABLE `t_user_indent`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `use_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'useId',
    `indent_id` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'indentId',
    `time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'time',
    `type` INT (4) NOT NULL DEFAULT -1 COMMENT 'type',
    `goods_ids` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'goodsIds',
    `total_price` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'totalPrice',
    `discount_type` INT (4) DEFAULT -1 COMMENT 'discountType',
    `discount_price` VARCHAR (50) DEFAULT '' COMMENT 'discountPrice',
    `status` INT (4) NOT NULL DEFAULT -1 COMMENT 'status',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user_indent`';
