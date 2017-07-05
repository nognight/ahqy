-- auto Generated on 2017-06-28 15:20:48 
-- DROP TABLE IF EXISTS `user_shopping_cart`; 
CREATE TABLE `t_user_shopping_cart`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    `type` INT (11) NOT NULL DEFAULT -1 COMMENT 'type',
    `goods_ids` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'goodsIds',
    `total_price` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'totalPrice',
    `discount_type` INT (4) NOT NULL DEFAULT -1 COMMENT 'discountType',
    `discount_price` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'discountPrice',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user_shopping_cart`';
