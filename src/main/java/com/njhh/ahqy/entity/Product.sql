
DROP TABLE IF EXISTS `product`;
CREATE TABLE `t_product`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `code` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '产品订购code    4G 90267757|51930909|8209022  |用于分割       23g: 9619$$00',
    `type` INT (4) NOT NULL DEFAULT -1 COMMENT '产品类型，定义 1省内包月 2国内包月 3半年包 4闲时 5sp 6日包 7加油包 8首月0',
    `status` INT (4) NOT NULL DEFAULT -1 COMMENT '//状态。0生效',
    `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
    `description` VARCHAR (1000) NOT NULL DEFAULT '' COMMENT 'description',
    `memo` VARCHAR (1000) NOT NULL DEFAULT '' COMMENT 'memo',
    `online_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'onlineDate',
    `offline_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'offlineDate',
    `pic_url` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'picUrl',
    `retail_price` INT (4) NOT NULL DEFAULT -1 COMMENT '售价',
    `retail_type` INT (4) NOT NULL DEFAULT -1 COMMENT '//售价类型，0是包月,1是半年，2是包天''',
    `source` INT (4) NOT NULL DEFAULT -1 COMMENT '来源平台，当前定义: -1 表示未知平台, 1 安徽BSS平台（xyl）, 2 信息化接口, 3 总台智能客服接口, 4 科大讯飞4G半年包，5 AOPSP接口 ，6融合订购接口''',
    `supplier_id` INT (4) NOT NULL DEFAULT -1 COMMENT '供应商标识',
    `support_band` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '支持品牌',
    `is_unsubscribe` INT (4) NOT NULL DEFAULT -1 COMMENT '能否退订',
    `is_repeatable` INT (4) NOT NULL DEFAULT -1 COMMENT '能否重复订购 0为可以 其他值不能',
    `pay_type` INT (4) NOT NULL DEFAULT -1 COMMENT '计费类型, -1 为未知，0表示通用，1 为预付费，2 为后付费''',
    `net_type` INT (4) NOT NULL DEFAULT -1 COMMENT '-1 表示未知,0表示通用， 1 表示针对 2G 网络, 2 表示针对 3G, 3 表示针对 4G,',
    `has_codes` INT (4) NOT NULL DEFAULT -1 COMMENT '是否拥有多个code，0表示多个',
    `codes` VARCHAR (200) NOT NULL DEFAULT '' COMMENT '多个code | 分割  常见于加油包',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`product`';
