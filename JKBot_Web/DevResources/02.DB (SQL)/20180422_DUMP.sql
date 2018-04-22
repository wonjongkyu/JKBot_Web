-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.59-log - MySQL Community Server (GPL)
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- jkbot 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `jkbot` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jkbot`;

-- 테이블 jkbot.jk_coin_price 구조 내보내기
DROP TABLE IF EXISTS `jk_coin_price`;
CREATE TABLE IF NOT EXISTS `jk_coin_price` (
  `no` int(10) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `EXCHANGE_NAME` varchar(50) NOT NULL DEFAULT '0' COMMENT '거래소 이름',
  `CURRENCY_NAME` varchar(50) DEFAULT '0' COMMENT '코인 종류',
  `OPENING_PRICE` varchar(50) DEFAULT '0' COMMENT '최근 24시간 내 시작 거래금액',
  `CLOSING_PRICE` varchar(50) DEFAULT '0' COMMENT '최근 24시간 내 마지막 거래금액',
  `MIN_PRICE` varchar(50) DEFAULT '0' COMMENT '최근 24시간 내 최저 거래금액',
  `MAX_PRICE` varchar(50) DEFAULT '0' COMMENT '최근 24시간 내 최고 거래금액',
  `AVERAGE_PRICE` varchar(50) DEFAULT '0' COMMENT '최근 24시간 내 평균 거래금액',
  `UNITS_TRADED` varchar(50) DEFAULT '0' COMMENT '최근 24시간 내 Currency 거래량',
  `VOLUME_1DAY` varchar(50) DEFAULT '0' COMMENT '최근 1일간 Currency 거래량',
  `VOLUME_7DAY` varchar(50) DEFAULT '0' COMMENT '최근 7일간 Currency 거래량',
  `BUY_PRICE` varchar(50) DEFAULT '0' COMMENT '거래 대기건 최고 구매가',
  `SELL_PRICE` varchar(50) DEFAULT '0' COMMENT '거래 대기건 최소 판매가',
  `DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '현재 시간 Timestamp',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='코인 가격을 누적하여 쌓는 테이블';

-- 테이블 데이터 jkbot.jk_coin_price:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `jk_coin_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `jk_coin_price` ENABLE KEYS */;

-- 테이블 jkbot.jk_exchange_info 구조 내보내기
DROP TABLE IF EXISTS `jk_exchange_info`;
CREATE TABLE IF NOT EXISTS `jk_exchange_info` (
  `EXCHANGE_NAME` varchar(50) NOT NULL COMMENT '거래소 명',
  `COIN_KOR_NAME` varchar(50) DEFAULT NULL COMMENT '코인 한글명',
  `COIN_SYMBOL_NAME` varchar(50) NOT NULL COMMENT '코인 심볼명',
  `COIN_EXCHANGE_TYPE` varchar(50) NOT NULL COMMENT '코인 거래단위',
  `COIN_TRANS_FEE_KRW` varchar(50) DEFAULT NULL COMMENT '코인 전송 수수료 (KRW)',
  `COIN_PRICE_KRW` varchar(50) DEFAULT NULL,
  `COIN_PRICE_UPDATE_DT` timestamp NULL DEFAULT NULL,
  `INSERT_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_DT` timestamp NULL DEFAULT NULL,
  `USE_YN` char(50) DEFAULT 'Y',
  PRIMARY KEY (`EXCHANGE_NAME`,`COIN_EXCHANGE_TYPE`,`COIN_SYMBOL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='거래소 - 거래소에서 취급하는 코인 관리 테이블';

-- 테이블 데이터 jkbot.jk_exchange_info:~77 rows (대략적) 내보내기
/*!40000 ALTER TABLE `jk_exchange_info` DISABLE KEYS */;
INSERT INTO `jk_exchange_info` (`EXCHANGE_NAME`, `COIN_KOR_NAME`, `COIN_SYMBOL_NAME`, `COIN_EXCHANGE_TYPE`, `COIN_TRANS_FEE_KRW`, `COIN_PRICE_KRW`, `COIN_PRICE_UPDATE_DT`, `INSERT_DT`, `UPDATE_DT`, `USE_YN`) VALUES
	('binance', '에이다', 'ADA', 'BTC', '278', '309.38', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '아크', 'ARK', 'BTC', '637', '3525.83', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인캐시', 'BCC', 'BTC', '1900', '1279830.13', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인', 'BTC', 'BTC', '17618', '9433947', '2018-04-22 12:53:52', '2018-04-22 20:57:52', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인골드', 'BTG', 'BTC', '117', '', NULL, '2018-04-18 10:38:14', '2018-04-18 10:38:14', 'Y'),
	('binance', '대시', 'DASH', 'BTC', '1760', '496408.14', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '이오스', 'EOS', 'BTC', '4880', '12342.78', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움클래식', 'ETC', 'BTC', '376', '20368.72', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움', 'ETH', 'BTC', '11400', '641760', '2018-04-22 12:53:52', '2018-04-22 13:40:32', '2018-04-18 10:38:14', 'N'),
	('binance', '그로스톨코인', 'GRS', 'BTC', '580', '1570.62', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '아이콘', 'ICX', 'BTC', '6860', '3767.03', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '코모도', 'KMD', 'BTC', '16', '', NULL, '2018-04-22 13:40:41', '2018-04-18 10:38:14', 'N'),
	('binance', '리스크', 'LSK', 'BTC', '2380', '12442.49', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '라이트코인', 'LTC', 'BTC', '2980', '160176.99', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '메탈', 'MTL', 'BTC', '7000', '4992.95', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '네오', 'NEO', 'BTC', '0', '80810.19', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '오미세고', 'OMG', 'BTC', '6766', '16522.88', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '피벡스', 'PIVX', 'BTC', '517', '5941.59', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '파워렛저', 'POWR', 'BTC', '6732', '540.79', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '퀀텀', 'QTUM', 'BTC', '376', '20606.12', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '스테이터스네트워크토큰', 'SNT', 'BTC', '6768', '162.29', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '스팀', 'STEEM', 'BTC', '62', '3283.69', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '스토리지', 'STORJ', 'BTC', '4500', '1191.17', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '스톰', 'STORM', 'BTC', '6804', '59.16', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '스트라티스', 'STRAT', 'BTC', '12', '6248.31', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '트론', 'TRX', 'BTC', '2907', '56.98', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '웨이브', 'WAVES', 'BTC', '532', '5533.27', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '뉴이코노미무브먼트', 'XEM', 'BTC', '3192', '426.27', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '스텔라루멘', 'XLM', 'BTC', '8043', '399.59', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '모네로', 'XMR', 'BTC', '24255', '', NULL, '2018-04-22 13:40:50', '2018-04-18 10:38:14', 'N'),
	('binance', '리플', 'XRP', 'BTC', '16808', '948.64', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '지캐시', 'ZEC', 'BTC', '2563', '292787.36', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:14', 'Y'),
	('binance', '에이다', 'ADA', 'USDT', NULL, '309.38', '2018-04-22 22:15:10', '2018-04-22 22:15:10', NULL, 'Y'),
	('binance', '비트코인캐시', 'BCC', 'USDT', NULL, '1279830.13', '2018-04-22 22:15:10', '2018-04-22 22:15:10', NULL, 'Y'),
	('binance', '비트코인', 'BTC', 'USDT', '17618', '0', NULL, '2018-04-22 20:57:52', NULL, 'Y'),
	('binance', '이더리움', 'ETH', 'USDT', NULL, NULL, NULL, '2018-04-22 13:25:55', NULL, 'Y'),
	('binance', '라이트코인', 'LTC', 'USDT', NULL, '160176.99', '2018-04-22 22:15:10', '2018-04-22 22:15:10', NULL, 'Y'),
	('binance', '네오', 'NEO', 'USDT', NULL, '80810.19', '2018-04-22 22:15:10', '2018-04-22 22:15:10', NULL, 'Y'),
	('binance', '퀀텀', 'QTUM', 'USDT', NULL, '20606.12', '2018-04-22 22:15:10', '2018-04-22 22:15:10', NULL, 'Y'),
	('upbit', '에이다', 'ADA', 'KRW', '55', '311.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아크', 'ARK', 'KRW', '318', '3530.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인캐시', 'BCC', 'KRW', '2850', '1291000.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인', 'BTC', 'KRW', '4404', '9559000.0', '2018-04-22 12:53:52', '2018-04-22 20:57:53', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인골드', 'BTG', 'KRW', '58', '', NULL, '2018-04-18 10:38:37', '2018-04-18 10:38:37', 'Y'),
	('upbit', '대시', 'DASH', 'KRW', '880', '505600.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아인스타이늄', 'EMC2', 'KRW', '64', '', NULL, '2018-04-22 20:14:26', '2018-04-18 10:37:40', 'N'),
	('upbit', '이오스', 'EOS', 'KRW', '7680', '12500.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움클래식', 'ETC', 'KRW', '188', '20540.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움', 'ETH', 'KRW', '5700', '649900.0', '2018-04-22 12:53:52', '2018-04-22 12:53:52', '2018-04-18 10:38:37', 'Y'),
	('upbit', '골렘', 'GNT', 'KRW', '5496', '', NULL, '2018-04-22 20:14:37', '2018-04-18 10:37:40', 'N'),
	('upbit', '그로스톨코인', 'GRS', 'KRW', '290', '1575.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아이콘', 'ICX', 'KRW', '5145', '3780.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '코모도', 'KMD', 'KRW', '', '', NULL, '2018-04-18 10:38:37', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리스크', 'LSK', 'KRW', '1190', '12540.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '라이트코인', 'LTC', 'KRW', '1490', '161300.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '머큐리', 'MER', 'KRW', '29', '', NULL, '2018-04-22 20:13:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '메탈', 'MTL', 'KRW', '3500', '5000.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '네오', 'NEO', 'KRW', '0', '81440.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '오미세고', 'OMG', 'KRW', '6444', '16590.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '피벡스', 'PIVX', 'KRW', '155', '6035.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '파워렛저', 'POWR', 'KRW', '2475', '542.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '퀀텀', 'QTUM', 'KRW', '188', '20800.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '어거', 'REP', 'KRW', '3928', '', NULL, '2018-04-22 20:13:52', '2018-04-18 10:37:40', 'N'),
	('upbit', '스팀달러', 'SBD', 'KRW', '30', '', NULL, '2018-04-22 20:14:50', '2018-04-18 10:37:40', 'N'),
	('upbit', '스테이터스네트워크토큰', 'SNT', 'KRW', '2115', '163.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스팀', 'STEEM', 'KRW', '31', '3280.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스토리지', 'STORJ', 'KRW', '4380', '1195.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스톰', 'STORM', 'KRW', '10500', '59.7', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스트라티스', 'STRAT', 'KRW', '1210', '6285.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '트론', 'TRX', 'KRW', '4080', '57.5', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '버트코인', 'VTC', 'KRW', '52', '', NULL, '2018-04-22 20:20:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '웨이브', 'WAVES', 'KRW', '5', '5600.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '뉴이코노미무브먼트', 'XEM', 'KRW', '1596', '428.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스텔라루멘', 'XLM', 'KRW', '8000', '402.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '모네로', 'XMR', 'KRW', '9702', '', NULL, '2018-04-18 10:38:37', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리플', 'XRP', 'KRW', '764', '957.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y'),
	('upbit', '지캐시', 'ZEC', 'KRW', '256', '295550.0', '2018-04-22 22:15:10', '2018-04-22 22:15:10', '2018-04-18 10:38:37', 'Y');
/*!40000 ALTER TABLE `jk_exchange_info` ENABLE KEYS */;

-- 테이블 jkbot.jk_service_coin_price 구조 내보내기
DROP TABLE IF EXISTS `jk_service_coin_price`;
CREATE TABLE IF NOT EXISTS `jk_service_coin_price` (
  `COIN_EXCHANGE_TYPE` varchar(50) NOT NULL,
  `COIN_SYMBOL` varchar(50) NOT NULL COMMENT '코인명',
  `COIN_TRANS_FEE_A` varchar(50) DEFAULT NULL,
  `COIN_TRANS_FEE_B` varchar(50) DEFAULT NULL,
  `PRICE_KRW_A` varchar(50) DEFAULT NULL COMMENT '한국거래소 원화 가격 ',
  `PRICE_KRW_B` varchar(50) DEFAULT NULL COMMENT '비교대상 코인 원화 가격',
  `PRICE_BTC_B` varchar(50) DEFAULT NULL COMMENT '비교대상 코인 BTC 가격',
  `PRICE_USDT_B` varchar(50) DEFAULT NULL COMMENT '비교대상 코인 USDT 가격',
  `PRICE_GAP_KRW` varchar(50) DEFAULT NULL COMMENT '원화 가격 차이',
  `TRANSFER_FEE_A` varchar(50) DEFAULT NULL COMMENT '전송 수수료 (업비트)',
  `TRANSFER_FEE_B` varchar(50) DEFAULT NULL COMMENT '전송 수수료 (바이낸스)',
  `PRICE_GAP_PERCENT` varchar(50) DEFAULT NULL COMMENT '김프 (%)',
  `USE_YN` char(50) DEFAULT 'Y',
  `UPDATE_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`COIN_EXCHANGE_TYPE`,`COIN_SYMBOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='업비트 - 바이낸스 코인 재정거래 서비스 테이블';

-- 테이블 데이터 jkbot.jk_service_coin_price:~44 rows (대략적) 내보내기
/*!40000 ALTER TABLE `jk_service_coin_price` DISABLE KEYS */;
INSERT INTO `jk_service_coin_price` (`COIN_EXCHANGE_TYPE`, `COIN_SYMBOL`, `COIN_TRANS_FEE_A`, `COIN_TRANS_FEE_B`, `PRICE_KRW_A`, `PRICE_KRW_B`, `PRICE_BTC_B`, `PRICE_USDT_B`, `PRICE_GAP_KRW`, `TRANSFER_FEE_A`, `TRANSFER_FEE_B`, `PRICE_GAP_PERCENT`, `USE_YN`, `UPDATE_DT`) VALUES
	('BTC', 'ADA', '0.2', '1', '310.0', '308.14', '0.00003242', NULL, '1.86', NULL, NULL, '0.6', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'ARK', '0.1', '0.2', '3535.0', '3516.66', '0.00037000', NULL, '18.34', NULL, '637', '0.52', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'BCC', '0.003', '0.002', '1284000.0', '1277119.26', '0.13437000', NULL, '6880.74', NULL, NULL, '0.54', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'BTC', '0.0005', '0.002', '9581000.0', NULL, NULL, NULL, NULL, '4404', NULL, '0.0', 'N', '2018-04-22 22:39:11'),
	('BTC', 'BTG', '0.001', '0.002', '72100.0', '71578.37', '0.00753100', NULL, '521.63', NULL, '117', '0.73', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'DASH', '0.002', '0.004', '505200.0', '499271.23', '0.05253000', NULL, '5928.77', NULL, '1760', '1.19', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'EMC2', '0.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'N', '2018-04-22 22:11:49'),
	('BTC', 'EOS', '0.8', '0.4', '12440.0', '12327.33', '0.00129700', NULL, '112.67', NULL, '4880', '0.91', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'ETC', '0.01', '0.02', '20550.0', '20434.67', '0.00215000', NULL, '115.33', NULL, '376', '0.56', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'ETH', '0.01', '0.02', '675900.0', '670741.86', '0.07057100', NULL, '5158.14', NULL, NULL, '0.77', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'GNT', '12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'N', '2018-04-22 22:11:58'),
	('BTC', 'GRS', '0.2', '0.2', '1580.0', '1580.88', '0.00016633', NULL, '-0.88', NULL, '580', '-0.06', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'ICX', '1.5', '2', '3795.0', '3798.95', '0.00039970', NULL, '-3.95', NULL, '6860', '-0.1', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'KMD', NULL, '0.004', '4190.0', '4205.74', '0.00044250', NULL, '-15.74', NULL, NULL, '-0.37', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'LSK', '0.1', '0.2', '12450.0', '12503.17', '0.00131550', NULL, '-53.17', NULL, '2380', '-0.43', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'LTC', '0.01', '0.02', '162950.0', '161196.27', '0.01696000', NULL, '1753.73', NULL, NULL, '1.09', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'MER', '0.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'N', '2018-04-22 22:12:13'),
	('BTC', 'MTL', '0.8', '1.6', '5000.0', '5044.99', '0.00053080', NULL, '-44.99', NULL, '7000', '-0.89', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'NEO', '0', '0', '81990.0', '81425.03', '0.00856700', NULL, '564.97', NULL, NULL, '0.69', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'OMG', '0.4', '0.42', '16760.0', '16585.35', '0.00174500', NULL, '174.65', NULL, '6766', '1.05', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'PIVX', '0.03', '0.1', '5990.0', '5979.28', '0.00062910', NULL, '10.72', NULL, '517', '0.18', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'POWR', '5', '13.6', '540.0', '542.42', '0.00005707', NULL, '-2.42', NULL, '6732', '-0.45', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'QTUM', '0.01', '0.02', '20940.0', '20719.8', '0.00218000', NULL, '220.2', NULL, NULL, '1.06', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'REP', '0.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'N', '2018-04-22 22:12:26'),
	('BTC', 'SBD', '0.01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'N', '2018-04-22 22:12:28'),
	('BTC', 'SNT', '15', '48', '163.0', '162.34', '0.00001708', NULL, '0.66', NULL, '6768', '0.41', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'STEEM', '0.01', '0.02', '3285.0', '3261.94', '0.00034320', NULL, '23.06', NULL, '62', '0.71', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'STORJ', '4', '3.5', '1190.0', '1194.62', '0.00012569', NULL, '-4.62', NULL, '4500', '-0.39', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'STORM', '250', '162', '59.9', '59.59', '0.00000627', NULL, '0.31', NULL, '6804', '0.52', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'STRAT', '0.2', '0.002', '6290.0', '6278.67', '0.00066060', NULL, '11.33', NULL, '12', '0.18', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'TRX', '80', '57', '57.6', '57.03', '0.00000600', NULL, '0.57', NULL, '2907', '1.0', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'VTC', '0.02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'N', '2018-04-22 22:12:50'),
	('BTC', 'WAVES', '0.001', '0.1', NULL, '5580.09', '0.00058710', NULL, NULL, '5', '532', '0.0', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'XEM', '4', '8', '423.0', '423.24', '0.00004453', NULL, '-0.24', NULL, '3192', '-0.06', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'XLM', '0.01', '21', '403.0', '402.71', '0.00004237', NULL, '0.29', NULL, '8043', '0.07', 'Y', '2018-04-22 22:39:11'),
	('BTC', 'XMR', '0.04', '0.1', '301700.0', '297471.75', '0.03129800', NULL, '4228.25', NULL, NULL, '1.42', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'XRP', '1', '22', '960.0', '953.11', '0.00010028', NULL, '6.89', NULL, '16808', '0.72', 'Y', '2018-04-22 22:39:10'),
	('BTC', 'ZEC', '0.001', '0.01', '296400.0', '293593.91', '0.03089000', NULL, '2806.09', NULL, '2563', '0.96', 'Y', '2018-04-22 22:39:10'),
	('USDT', 'ADA', NULL, NULL, '310.0', '309', NULL, '0.29', '1.0', NULL, NULL, '0.32', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'BCC', NULL, NULL, '1271000.0', '1264267', NULL, '1182.0', '6733.0', NULL, NULL, '0.53', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'BTC', NULL, NULL, '9549000.0', '9476025', NULL, '8859.41', '72975.0', NULL, NULL, '0.77', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'ETH', NULL, NULL, '670000.0', '664168', NULL, '620.95', '5832.0', NULL, NULL, '0.88', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'LTC', NULL, NULL, '160750.0', '159488', NULL, '149.11', '1262.0', NULL, NULL, '0.79', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'NEO', NULL, NULL, '81360.0', '80914', NULL, '75.65', '446.0', NULL, NULL, '0.55', 'Y', '2018-04-22 21:42:51');
/*!40000 ALTER TABLE `jk_service_coin_price` ENABLE KEYS */;

-- 테이블 jkbot.jk_user_balance 구조 내보내기
DROP TABLE IF EXISTS `jk_user_balance`;
CREATE TABLE IF NOT EXISTS `jk_user_balance` (
  `USER_ID` varchar(50) NOT NULL COMMENT '사용자 ID',
  `EXCHANGE_NAME` varchar(50) NOT NULL COMMENT '거래소 명',
  `COIN_SYMBOL_NAME` varchar(50) NOT NULL COMMENT '코인 심볼명',
  `TOTAL_CURRENCY` varchar(50) NOT NULL DEFAULT '0' COMMENT '보유중인 통화 수',
  `IN_USE_CURRENCY` varchar(50) NOT NULL DEFAULT '0' COMMENT '사용중 통화 수',
  `AVAILABLE_CURRENCY` varchar(50) NOT NULL DEFAULT '0' COMMENT '사용가능 통화 수',
  `XOIN_LAST` varchar(50) NOT NULL DEFAULT '0' COMMENT '마지막 거래체결 금액(bithumb)',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 지갑 잔고';

-- 테이블 데이터 jkbot.jk_user_balance:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `jk_user_balance` DISABLE KEYS */;
INSERT INTO `jk_user_balance` (`USER_ID`, `EXCHANGE_NAME`, `COIN_SYMBOL_NAME`, `TOTAL_CURRENCY`, `IN_USE_CURRENCY`, `AVAILABLE_CURRENCY`, `XOIN_LAST`) VALUES
	('wonjongkyu', 'Bithumb', 'KRW', '1000000', '0', '1000000', '0');
/*!40000 ALTER TABLE `jk_user_balance` ENABLE KEYS */;

-- 테이블 jkbot.jk_user_info 구조 내보내기
DROP TABLE IF EXISTS `jk_user_info`;
CREATE TABLE IF NOT EXISTS `jk_user_info` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(50) DEFAULT NULL,
  `USER_PWD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='사용자 테이블';

-- 테이블 데이터 jkbot.jk_user_info:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `jk_user_info` DISABLE KEYS */;
INSERT INTO `jk_user_info` (`no`, `USER_ID`, `USER_PWD`) VALUES
	(1, 'wonjongkyu', '1234'),
	(2, 'test', '1234');
/*!40000 ALTER TABLE `jk_user_info` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
