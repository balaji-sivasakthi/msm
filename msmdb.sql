-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2021 at 12:04 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobile_erp_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `businessinfo`
--

CREATE TABLE `businessinfo` (
  `id` bigint(11) NOT NULL,
  `shopname` varchar(255) NOT NULL,
  `shopownername` varchar(255) NOT NULL,
  `shopaddress` varchar(255) NOT NULL,
  `shopemail` varchar(255) NOT NULL,
  `shopbranch` varchar(255) NOT NULL,
  `shopwebsite` varchar(255) NOT NULL,
  `shopimg` varchar(255) NOT NULL,
  `gst` int(11) NOT NULL,
  `gstin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `itemid` varchar(15) NOT NULL,
  `itemname` varchar(255) NOT NULL,
  `itemimei1` int(15) NOT NULL,
  `itemimei2` int(15) NOT NULL,
  `itemrate` bigint(255) NOT NULL,
  `discount` double(10,2) NOT NULL,
  `quantity` varchar(255) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `specification` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`itemid`, `itemname`, `itemimei1`, `itemimei2`, `itemrate`, `discount`, `quantity`, `brand`, `model`, `specification`) VALUES
('IT002', 'Nishanth', 12345, 54321, 200, 10.00, '77', 'Nishanth', 'sebastn', 'Nishantrh sebastb'),
('IT003', 'Nishanth', 1234, 4321, 20000, 10.00, '85', 'Nishanth', 'sebastin', 'Nishanth sebastn');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `salesid` bigint(11) NOT NULL,
  `subtotal` varchar(255) NOT NULL,
  `payment` varchar(255) NOT NULL,
  `balance` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`salesid`, `subtotal`, `payment`, `balance`, `date`, `time`) VALUES
(1, '202000', '202000', '0', '2021-08-24', '01:49:07'),
(2, '202000', '202000', '0', '2021-08-24', '01:50:47');

-- --------------------------------------------------------

--
-- Table structure for table `salesitem`
--

CREATE TABLE `salesitem` (
  `id` int(11) NOT NULL,
  `salesid` varchar(255) NOT NULL,
  `itmid` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `quantity` varchar(255) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salesitem`
--

INSERT INTO `salesitem` (`id`, `salesid`, `itmid`, `price`, `quantity`, `total`) VALUES
(1, '1', 'IT002', '200', '10', 2000),
(2, '1', 'It003', '20000', '10', 200000),
(3, '2', 'IT002', '200', '10', 2000),
(4, '2', 'It003', '20000', '10', 200000);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `vendorname` varchar(255) NOT NULL,
  `subtotal` int(11) NOT NULL,
  `payment` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `vendorname`, `subtotal`, `payment`, `balance`, `date`, `time`) VALUES
(1, 'Nishanth', 2000, 2000, 0, '2021-08-10', '04:05:20.000000'),
(2, 'Nishanth', 102000, 102000, 0, '2021-08-10', '04:08:33.000000'),
(3, 'Nishanth', 42000, 42000, 0, '2021-08-10', '04:11:43.000000'),
(4, 'Nishanth', 22000, 22000, 0, '2021-08-10', '04:14:21.000000'),
(5, 'Nishanth', 202000, 202000, 0, '2021-08-11', '06:08:30.000000'),
(6, 'Nishanth', 702000, 702000, 0, '2021-08-12', '04:03:42.000000'),
(7, 'Nishanth', 202000, 202000, 0, '2021-08-12', '06:06:27.000000'),
(8, 'Nishanth', 204000, 204000, 0, '2021-08-12', '08:49:43.000000'),
(9, 'Nishanth', 202000, 202000, 0, '2021-08-23', '01:05:16.000000'),
(10, 'Nishanth', 202000, 202000, 0, '2021-08-23', '01:07:10.000000'),
(11, 'Nishanth', 21000, 21000, 0, '2021-08-23', '01:37:42.000000'),
(12, 'Nishanth', 21000, 21000, 0, '2021-08-23', '01:43:11.000000'),
(13, 'Nishanth', 21000, 21000, 0, '2021-08-23', '01:47:59.000000'),
(14, 'Nishanth', 21000, 21000, 0, '2021-08-23', '04:01:23.000000'),
(15, 'Nishanth', 21000, 21000, 0, '2021-08-23', '04:11:43.000000'),
(16, 'Nishanth', 40200, 40200, 0, '2021-08-23', '04:15:31.000000'),
(17, 'Nishanth', 201000, 201000, 0, '2021-08-24', '01:29:52.000000');

-- --------------------------------------------------------

--
-- Table structure for table `stockitem`
--

CREATE TABLE `stockitem` (
  `id` int(11) NOT NULL,
  `stockid` varchar(255) NOT NULL,
  `itmid` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `quantity` varchar(255) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stockitem`
--

INSERT INTO `stockitem` (`id`, `stockid`, `itmid`, `price`, `quantity`, `total`) VALUES
(1, '3', 'IT002', '200', '10', 2000),
(2, '3', 'IT003', '20000', '2', 40000),
(3, '4', 'IT002', '200', '10', 2000),
(4, '4', 'IT003', '20000', '1', 20000),
(5, '5', 'IT003', '20000', '10', 200000),
(6, '5', 'IT002', '200', '10', 2000),
(7, '6', 'IT002', '200', '10', 2000),
(8, '6', 'IT003', '20000', '10', 200000),
(9, '6', 'IT003', '20000', '25', 500000),
(10, '7', 'IT002', '200', '10', 2000),
(11, '7', 'IT003', '20000', '10', 200000),
(12, '8', 'IT002', '200', '20', 4000),
(13, '8', 'IT003', '20000', '10', 200000),
(14, '9', 'IT002', '200', '10', 2000),
(15, '9', 'IT003', '20000', '10', 200000),
(16, '10', 'IT002', '200', '10', 2000),
(17, '10', 'IT003', '20000', '10', 200000),
(18, '16', 'IT002', '200', '1', 200),
(19, '16', 'It003', '20000', '2', 40000),
(20, '17', 'IT002', '200', '5', 1000),
(21, '17', 'IT003', '20000', '10', 200000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(255) NOT NULL,
  `user name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `email id` varchar(255) NOT NULL,
  `aadhaar number` bigint(20) NOT NULL,
  `doj` date NOT NULL,
  `ph.no` bigint(20) NOT NULL,
  `position` varchar(255) NOT NULL,
  `salary` float NOT NULL,
  `user type` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` int(15) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`id`, `name`, `number`, `email`, `address`, `date`, `time`) VALUES
('VD001', 'Nishanth', 98765443, 'Nishanthgmail.com', 'India', '2021-08-10', '03:56:19.000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `businessinfo`
--
ALTER TABLE `businessinfo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`itemid`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`salesid`);

--
-- Indexes for table `salesitem`
--
ALTER TABLE `salesitem`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stockitem`
--
ALTER TABLE `stockitem`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `businessinfo`
--
ALTER TABLE `businessinfo`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `salesid` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `salesitem`
--
ALTER TABLE `salesitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `stockitem`
--
ALTER TABLE `stockitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
