-- phpMyAdmin SQL Dump
-- version 4.3.3
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2016-11-21 05:55:18
-- 服务器版本： 5.7.10
-- PHP Version: 5.4.45

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `seckeep`
--

-- --------------------------------------------------------

--
-- 表的结构 `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `pid` int(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `title` varchar(500) NOT NULL,
  `content` varchar(5000) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `post`
--

INSERT INTO `post` (`pid`, `username`, `title`, `content`, `date`) VALUES
(8, 'admin', 'SQL injection', 'SQL injection', '2016-10-31 09:21:37'),
(9, 'admin', 'XSS attack', 'XSS attack', '2016-10-31 09:22:44');

-- --------------------------------------------------------

--
-- 表的结构 `repost`
--

CREATE TABLE IF NOT EXISTS `repost` (
  `rid` int(10) NOT NULL,
  `pid` int(10) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `username` varchar(100) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `repost`
--

INSERT INTO `repost` (`rid`, `pid`, `content`, `username`, `date`) VALUES
(26, 4, 'this is a   css\r\n<script>alert(document.cookie)</script>', 'admin', '2016-08-10 09:31:38'),
(27, 6, 'aaaa', 'admin', '2016-08-22 13:10:11'),
(28, 7, '阿斯顿发啥地方', 'admin', '2016-08-23 02:48:16'),
(29, 6, '得到的', 'admin', '2016-08-23 02:50:19'),
(30, 4, 'this is a css &lt;script&gt;alert(document.cookie)&lt;/script&gt;', 'admin', '2016-08-23 08:10:56'),
(31, 4, 'img <img src=”javascript:alert(‘xss’)” />', 'admin', '2016-10-31 09:02:47'),
(32, 4, 'img2"><img src="javascript:alert(1)"/>', 'admin', '2016-10-31 09:03:59'),
(33, 4, 'src <img src="http://imgbbs.heiguang.net/forum/201510/06/104432cjc7c8tx7xxqqkgq.jpg">', 'admin', '2016-10-31 09:13:06'),
(34, 6, 'src <img src="http://imgbbs.heiguang.net/forum/201510/06/104432cjc7c8tx7xxqqkgq.jpg">', 'admin', '2016-10-31 09:16:31'),
(35, 4, 'src 2<img \r\n \r\nsrc \r\n="http://imgbbs.heiguang.net/forum/201510/06/104432cjc7c8tx7xxqqkgq.jpg"/>', 'admin', '2016-10-31 09:18:30'),
(36, 9, 'xss <script>alert(document.cookie)</script>', 'admin', '2016-10-31 09:23:18'),
(37, 9, 'alert(aa)', 'admin', '2016-10-31 09:25:34'),
(38, 9, 'onload()', 'admin', '2016-10-31 09:26:04'),
(39, 9, 'onload = ', 'admin', '2016-10-31 09:27:48'),
(40, 9, 'eval(aa)', 'admin', '2016-10-31 09:32:42'),
(41, 9, 'eval ( adf  ‘)', 'admin', '2016-10-31 09:33:30'),
(42, 8, 'img <img src="http://bbsimg.wacdn.com/forum/201610/27/163504zf70909f42db7c4b.png" />', 'admin', '2016-11-01 02:22:15'),
(43, 8, 'img2 <img src="http://imgbbs.heiguang.net/forum/201510/06/104432cjc7c8tx7xxqqkgq.jpg">', 'admin', '2016-11-01 02:40:41');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `uid` int(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`uid`, `username`, `password`) VALUES
(1, 'admin', '21232F297A57A5A743894A0E4A801FC3'),
(2, 'ddd', '77963B7A931377AD4AB5AD6A9CD718AA'),
(3, 'aaa', '47BCE5C74F589F4867DBD57E9CA9F808'),
(4, 'sam', '332532DCFAA1CBF61E2A266BD723612C');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`pid`), ADD KEY `pid` (`pid`);

--
-- Indexes for table `repost`
--
ALTER TABLE `repost`
  ADD PRIMARY KEY (`rid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `pid` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `repost`
--
ALTER TABLE `repost`
  MODIFY `rid` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
