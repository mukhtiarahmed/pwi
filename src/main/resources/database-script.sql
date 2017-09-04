
CREATE DATABASE inventory CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER  'vantibolli'@'127.0.0.1' IDENTIFIED BY 'vantibolli';

GRANT ALL PRIVILEGES ON *.* TO 'vantibolli'@'127.0.0.1';

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', '2017-08-26 15:23:36', 'Brand1');
INSERT INTO `brand` VALUES ('2', '2017-08-26 15:23:52', 'Brand2');

-- ----------------------------
-- Table structure for `country`
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `country_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', '2017-08-26 15:24:20', 'USA');
INSERT INTO `country` VALUES ('2', '2017-08-26 15:24:38', 'Ireland');
INSERT INTO `country` VALUES ('3', '2017-09-26 15:25:55', 'Netherlands');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `item_variants_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1g9hylnb5ngkx90uprq2dhvtf` (`product_id`,`item_variants_id`),
  KEY `FK_jv7i2hsx859ve4n1e8gihx1sf` (`item_variants_id`),
  CONSTRAINT `FK_24gbjyosotxyxtdwqti4wt555` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_jv7i2hsx859ve4n1e8gihx1sf` FOREIGN KEY (`item_variants_id`) REFERENCES `item_variants` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '2017-08-26 16:38:10', '1', '1');
INSERT INTO `item` VALUES ('2', '2017-08-26 16:38:45', '2', '1');
INSERT INTO `item` VALUES ('3', '2017-08-26 16:38:54', '3', '1');
INSERT INTO `item` VALUES ('4', '2017-08-26 16:39:03', '1', '2');
INSERT INTO `item` VALUES ('5', '2017-08-26 16:39:32', '2', '2');
INSERT INTO `item` VALUES ('6', '2017-08-26 16:40:03', '3', '2');

-- ----------------------------
-- Table structure for `item_variants`
-- ----------------------------
DROP TABLE IF EXISTS `item_variants`;
CREATE TABLE `item_variants` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_variants
-- ----------------------------
INSERT INTO `item_variants` VALUES ('1', '2017-08-26 15:35:36', '10 ml');
INSERT INTO `item_variants` VALUES ('2', '2017-08-26 15:35:55', '20 ml');
INSERT INTO `item_variants` VALUES ('3', '2017-08-26 15:36:04', '30 ml');
INSERT INTO `item_variants` VALUES ('4', '2017-08-26 15:36:16', 'Small');
INSERT INTO `item_variants` VALUES ('5', '2017-08-26 15:36:47', 'Large');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `item_type` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmivyxk9rmgysrmsqw15lqr5b` (`name`),
  KEY `FK_1td6gorl25rsvufiiive2svlx` (`brand_id`),
  CONSTRAINT `FK_1td6gorl25rsvufiiive2svlx` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '2017-08-26 16:35:42', '0', 'ProductA', '1');
INSERT INTO `product` VALUES ('2', '2017-08-02 16:36:06', '0', 'ProductB', '1');

-- ----------------------------
-- Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `avl_qty` int(11) DEFAULT NULL,
  `in_stock` int(11) DEFAULT NULL,
  `in_transit` int(11) DEFAULT NULL,
  `moq` int(11) DEFAULT NULL,
  `qpb` int(11) DEFAULT NULL,
  `reorder_point` int(11) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jh0oqe50axuulnvkt5to5yjdn` (`item_id`,`warehouse_id`),
  KEY `FK_cooyd3c7g6sdwwriglm24ly4` (`warehouse_id`),
  CONSTRAINT `FK_cooyd3c7g6sdwwriglm24ly4` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`id`),
  CONSTRAINT `FK_kgdl449jtmfvcqwv0m8od5ime` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('1', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '1', '1');
INSERT INTO `stock` VALUES ('2', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '1', '2');
INSERT INTO `stock` VALUES ('3', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '1', '3');
INSERT INTO `stock` VALUES ('4', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '1', '4');
INSERT INTO `stock` VALUES ('5', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '1', '5');
INSERT INTO `stock` VALUES ('6', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '2', '1');
INSERT INTO `stock` VALUES ('7', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '2', '2');
INSERT INTO `stock` VALUES ('8', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '2', '3');
INSERT INTO `stock` VALUES ('9', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '2', '4');
INSERT INTO `stock` VALUES ('10', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '2', '5');
INSERT INTO `stock` VALUES ('11', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '3', '1');
INSERT INTO `stock` VALUES ('12', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '3', '2');
INSERT INTO `stock` VALUES ('13', '2017-08-26 19:35:43', '100', '150', '50', '1', '1', '5', '3', '3');
INSERT INTO `stock` VALUES ('14', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '3', '4');
INSERT INTO `stock` VALUES ('15', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '3', '5');
INSERT INTO `stock` VALUES ('16', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '4', '1');
INSERT INTO `stock` VALUES ('17', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '4', '2');
INSERT INTO `stock` VALUES ('18', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '4', '3');
INSERT INTO `stock` VALUES ('19', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '4', '4');
INSERT INTO `stock` VALUES ('20', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '4', '5');
INSERT INTO `stock` VALUES ('21', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '5', '1');
INSERT INTO `stock` VALUES ('22', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '5', '2');
INSERT INTO `stock` VALUES ('23', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '5', '3');
INSERT INTO `stock` VALUES ('24', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '5', '4');
INSERT INTO `stock` VALUES ('25', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '5', '5');
INSERT INTO `stock` VALUES ('26', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '6', '1');
INSERT INTO `stock` VALUES ('27', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '6', '2');
INSERT INTO `stock` VALUES ('28', '2017-08-26 19:35:43', '100', '100', '0', '1', '1', '5', '6', '3');
INSERT INTO `stock` VALUES ('29', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '6', '4');
INSERT INTO `stock` VALUES ('30', '2017-08-26 19:35:54', '100', '100', '0', '1', '1', '5', '6', '5');

-- ----------------------------
-- Table structure for `warehouse`
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tfyjis4itqy4yel55y0l1w881` (`country_id`),
  CONSTRAINT `FK_tfyjis4itqy4yel55y0l1w881` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('1', '2017-08-26 16:40:51', 'Warehouse1', '1');
INSERT INTO `warehouse` VALUES ('2', '2017-08-26 16:41:16', 'Warehouse2', '1');
INSERT INTO `warehouse` VALUES ('3', '2017-08-26 16:41:33', 'Warehouse3', '1');
INSERT INTO `warehouse` VALUES ('4', '2017-08-26 16:42:02', 'Warehouse4', '2');
INSERT INTO `warehouse` VALUES ('5', '2017-08-26 16:42:10', 'Warehouse5', '2');
