-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2023-04-21 23:16:34
-- 服务器版本： 5.7.40-log
-- PHP 版本： 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `medicine`
--

-- --------------------------------------------------------

--
-- 表的结构 `collection`
--
drop table  if exists `collection`;

CREATE TABLE `collection` (
  `id` int(11) NOT NULL,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';

--
-- 转存表中的数据 `collection`
--

INSERT INTO `collection` (`id`, `post_id`, `user_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--
drop table  if exists `comment`;
CREATE TABLE `comment` (
  `cid` int(11) NOT NULL,
  `parent_id` bigint(20) NOT NULL COMMENT '父类id',
  `ctype` int(11) NOT NULL COMMENT '父类类型',
  `commentator` bigint(20) NOT NULL COMMENT '评论人id',
  `gmt_create` varchar(64) NOT NULL COMMENT '创建时间',
  `gmt_modified` varchar(64) NOT NULL COMMENT '更新时间',
  `like_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `content` varchar(1024) NOT NULL,
  `comment_count` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`cid`, `parent_id`, `ctype`, `commentator`, `gmt_create`, `gmt_modified`, `like_count`, `content`, `comment_count`) VALUES
(1, 1, 0, 2, '1606641726000', '1606641726000', 0, 'sad', 0);

-- --------------------------------------------------------

--
-- 表的结构 `likes`
--
drop table  if exists `likes`;
CREATE TABLE `likes` (
  `lid` int(11) NOT NULL COMMENT '点赞信息ID',
  `info_id` int(11) DEFAULT NULL COMMENT '点赞对象id',
  `create_time` varchar(64) DEFAULT NULL COMMENT '时间',
  `like_user_id` int(11) DEFAULT NULL COMMENT '点赞人ID',
  `update_time` varchar(64) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0 取消 1 点赞'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞记录表' ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `likes`
--

INSERT INTO `likes` (`lid`, `info_id`, `create_time`, `like_user_id`, `update_time`, `status`) VALUES
(1, 1, NULL, 1, NULL, 1),
(2, 2, NULL, 1, NULL, 1),
(3, 2, NULL, 2, NULL, 1),
(4, 1, NULL, 2, NULL, 1);

-- --------------------------------------------------------

--
-- 表的结构 `talk`
--
drop table  if exists `talk`;
CREATE TABLE `talk` (
  `tid` int(11) NOT NULL,
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `description` varchar(1024) NOT NULL COMMENT '描述',
  `images` varchar(2048) DEFAULT NULL COMMENT '图片地址',
  `ttype` enum('运动','饮食','心理','养生','医药') DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `permission` int(11) NOT NULL DEFAULT '0' COMMENT '权限',
  `creator` bigint(20) NOT NULL COMMENT '发布者',
  `comment_count` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `like_count` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `gmt_create` varchar(64) NOT NULL COMMENT '创建时间',
  `gmt_modified` varchar(64) NOT NULL COMMENT '修改时间',
  `introduction` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `talk`
--

INSERT INTO `talk` (`tid`, `title`, `description`, `images`, `ttype`, `status`, `permission`, `creator`, `comment_count`, `like_count`, `gmt_create`, `gmt_modified`, `introduction`) VALUES
(1, 'Happy', 'i am happy today', 'kkkk', '运动', 1, 0, 1, 0, 0, '1606641726000', '1606641726000', 'Happy'),
(2, 'Sad', 'i am sad today', 'llll', '心理', 0, 0, 2, 0, 0, '1606641726000', '1606641726000', 'Sad');

-- --------------------------------------------------------

--
-- 表的结构 `users`
--
drop table  if exists `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `gmt_create` varchar(64) DEFAULT NULL,
  `gmt_modified` varchar(64) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT '/images/default-avatar.png',
  `email` varchar(32) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `self_introduction` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`id`, `account_id`, `name`, `token`, `gmt_create`, `gmt_modified`, `avatar_url`, `email`, `phone`, `password`, `self_introduction`) VALUES
(1, 'admini', 'Tim', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjgyMDg3MjAwfQ.4rVaLDC4fGMZAV57Age_ilylHKz2-rk8_zxDkicV28c', 0, 1606641726000, '\\images\\thumbs\\avatar3.png', 'e001', 'p001', '123456', '厉害'),
(2, NULL, 'Judy', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyIiwiZXhwIjoxNjgyMDgwODI4fQ.QtoOqTyPefbjD8JikGCBiGK3adb7eKRjGeciWsA7z1w', 0, NULL, '/images/default-avatar.png', NULL, '002', '000000', NULL),
(5, NULL, 'Amy', NULL, '1682089633094', NULL, '/images/default-avatar.png', NULL, NULL, '111111', NULL),
(6, NULL, 'Jack', NULL, '1682089865006', NULL, '/images/default-avatar.png', NULL, NULL, '111111', NULL);

--
-- 转储表的索引
--

--
-- 表的索引 `collection`
--
ALTER TABLE `collection`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`cid`);

--
-- 表的索引 `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`lid`) USING BTREE,
  ADD UNIQUE KEY `agdkey` (`like_user_id`,`info_id`) USING BTREE;

--
-- 表的索引 `talk`
--
ALTER TABLE `talk`
  ADD PRIMARY KEY (`tid`);

--
-- 表的索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `collection`
--
ALTER TABLE `collection`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- 使用表AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `likes`
--
ALTER TABLE `likes`
  MODIFY `lid` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞信息ID', AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `talk`
--
ALTER TABLE `talk`
  MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
