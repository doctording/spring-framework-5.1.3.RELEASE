CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;