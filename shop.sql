-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2018 at 04:29 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `total` double NOT NULL,
  `payment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` longtext COLLATE utf8_unicode_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_id`, `user_id`, `total`, `payment`, `address`, `date`) VALUES
(1544532030023, 0, 25, 'Bank transfer', 'Taiwan', '2018-12-11 12:40:30'),
(1544534541789, 1544534529973, 24, 'Bank transfer', 'Taiwan', '2018-12-11 13:22:21');

-- --------------------------------------------------------

--
-- Table structure for table `bill_detail`
--

CREATE TABLE `bill_detail` (
  `bill_detail_id` bigint(20) NOT NULL,
  `bill_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill_detail`
--

INSERT INTO `bill_detail` (`bill_detail_id`, `bill_id`, `product_id`, `price`, `quantity`) VALUES
(1544532030023, 1544532030023, 1, 8, 2),
(1544534541789, 1544534541789, 1, 8, 3);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` bigint(11) NOT NULL,
  `category_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_name`) VALUES
(1, 'Name 1'),
(2, 'Name 2'),
(3, 'Name 3');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `product_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `product_image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `product_price` double NOT NULL,
  `product_description` longtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `category_id`, `product_name`, `product_image`, `product_price`, `product_description`) VALUES
(1, 1, 'Macbook PRO', 'images/apple-macbook.jpg', 8, 'The MacBook Pro (sometimes abbreviated as MBP)[1] is a line of Macintosh portable computers introduced in January 2006 by Apple Inc. It is the high-end model of the MacBook family and is currently available in 13- and 15-inch screen sizes. A 17-inch version was available between April 2006 and June 2012.'),
(2, 1, 'Macbook pro', 'images/apple-macbook.jpg', 2000, 'The MacBook Pro (sometimes abbreviated as MBP)[1] is a line of Macintosh portable computers introduced in January 2006 by Apple Inc. It is the high-end model of the MacBook family and is currently available in 13- and 15-inch screen sizes. A 17-inch version was available between April 2006 and June 2012.'),
(3, 1, 'Mac PC', 'images/MacPC.jpg', 1500, 'Macintosh, hay Mac, là một dòng sản phẩm máy tính cá nhân được thiết kế, phát triển, và đưa ra thị trường bởi Apple Inc'),
(4, 1, 'google laptop', 'images/google_laptop.jpg', 1000, 'google laptop'),
(5, 1, 'samsung laptop', 'images/samsungLaptop.jpg', 700, 'Designed to help you do it all, these three new Samsung Notebooks are incredibly powerful, surprisingly light, and offer Full HD displays. '),
(6, 1, 'laptop dell', 'images/dell.jpg', 500, 'Dell Inspiron 3576 i5 8250U là laptop mang phong cách gọn nhẹ, tiện lợi có thiết kế đơn giản nhưng vẫn mang vẻ đẹp hiện đại. Đi cùng ngoại hình là một cấu hình CPU thế hệ 8 tiết kiệm điện năng đáng giá.'),
(7, 1, 'laptop HP', 'images/HP.jpg', 800, 'HP Envy 13 là phiên bản máy tính xách tay có thiết kế cao cấp - sang trọng đầy ấn tượng cùng với một cấu hình mạnh mẽ cho hiệu suất hoạt động mạnh mẽ để xử lý công việc hay giải trí mượt mà.'),
(8, 1, 'laptop asus', 'images/asus.jpg', 900, 'Laptop Asus VivoBook S15 S510UQ i5 8250U/4GB/1TB/940MX/Win10/(BQ475T)'),
(9, 1, 'laptop lenovo', 'images/lelovo.jpg', 650, 'Lenovo ideapad 320 15.6\" Laptop, Windows 10, Intel Celeron N3350 Dual-Core Processor, 4GB RAM, 1TB Hard Drive');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `user_email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_pass` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_role` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_email`, `user_pass`, `user_role`) VALUES
(1, 'huuuocn@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', b'1'),
(2, 'uocnh@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', b'1'),
(1544498930984, 'bichngocxinhgai@gmail.com', '202cb962ac59075b964b07152d234b70', b'0'),
(1544534529973, 'tienganh08ptit@gmail.com', '202cb962ac59075b964b07152d234b70', b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD PRIMARY KEY (`bill_detail_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2147483647;

--
-- AUTO_INCREMENT for table `bill_detail`
--
ALTER TABLE `bill_detail`
  MODIFY `bill_detail_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2147483647;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2147483647;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
