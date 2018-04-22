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
	('binance', '에이다', 'ADA', 'BTC', '278', '310.98', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '아크', 'ARK', 'BTC', '637', '3622.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인캐시', 'BCC', 'BTC', '1900', '1327700.63', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인', 'BTC', 'BTC', '17618', '9433947', '2018-04-22 12:53:52', '2018-04-22 20:57:52', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인골드', 'BTG', 'BTC', '117', '72488.05', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '대시', 'DASH', 'BTC', '1760', '504116.65', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '이오스', 'EOS', 'BTC', '4880', '12416.11', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움클래식', 'ETC', 'BTC', '376', '20613.58', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움', 'ETH', 'BTC', '11400', '682185.75', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'N'),
	('binance', '그로스톨코인', 'GRS', 'BTC', '580', '1603.04', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '아이콘', 'ICX', 'BTC', '6860', '3801.38', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '코모도', 'KMD', 'BTC', '16', '4228.23', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'N'),
	('binance', '리스크', 'LSK', 'BTC', '2380', '12680.85', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '라이트코인', 'LTC', 'BTC', '2980', '161628.12', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '메탈', 'MTL', 'BTC', '7000', '5014.79', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '네오', 'NEO', 'BTC', '0', '81600.62', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '오미세고', 'OMG', 'BTC', '6766', '16863.04', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '피벡스', 'PIVX', 'BTC', '517', '6005.66', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '파워렛저', 'POWR', 'BTC', '6732', '552.03', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '퀀텀', 'QTUM', 'BTC', '376', '21141.15', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '스테이터스네트워크토큰', 'SNT', 'BTC', '6768', '165.56', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '스팀', 'STEEM', 'BTC', '62', '3290.12', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '스토리지', 'STORJ', 'BTC', '4500', '1205.45', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '스톰', 'STORM', 'BTC', '6804', '63.69', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '스트라티스', 'STRAT', 'BTC', '12', '6278.08', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '트론', 'TRX', 'BTC', '2907', '58.03', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '웨이브', 'WAVES', 'BTC', '532', '5599.91', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '뉴이코노미무브먼트', 'XEM', 'BTC', '3192', '428.77', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '스텔라루멘', 'XLM', 'BTC', '8043', '407.57', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '모네로', 'XMR', 'BTC', '24255', '296580.4', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'N'),
	('binance', '리플', 'XRP', 'BTC', '16808', '955.57', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '지캐시', 'ZEC', 'BTC', '2563', '299793.77', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:14', 'Y'),
	('binance', '에이다', 'ADA', 'USDT', NULL, '310.98', '2018-04-23 00:28:58', '2018-04-23 00:28:58', NULL, 'Y'),
	('binance', '비트코인캐시', 'BCC', 'USDT', NULL, '1327700.63', '2018-04-23 00:28:58', '2018-04-23 00:28:58', NULL, 'Y'),
	('binance', '비트코인', 'BTC', 'USDT', '17618', '0', NULL, '2018-04-22 20:57:52', NULL, 'Y'),
	('binance', '이더리움', 'ETH', 'USDT', NULL, '682185.75', '2018-04-23 00:28:58', '2018-04-23 00:28:58', NULL, 'Y'),
	('binance', '라이트코인', 'LTC', 'USDT', NULL, '161628.12', '2018-04-23 00:28:58', '2018-04-23 00:28:58', NULL, 'Y'),
	('binance', '네오', 'NEO', 'USDT', NULL, '81600.62', '2018-04-23 00:28:58', '2018-04-23 00:28:58', NULL, 'Y'),
	('binance', '퀀텀', 'QTUM', 'USDT', NULL, '21141.15', '2018-04-23 00:28:58', '2018-04-23 00:28:58', NULL, 'Y'),
	('upbit', '에이다', 'ADA', 'KRW', '55', '314.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아크', 'ARK', 'KRW', '318', '3665.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인캐시', 'BCC', 'KRW', '2850', '1343500.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인', 'BTC', 'KRW', '4404', '9650000.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인골드', 'BTG', 'KRW', '58', '72850.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '대시', 'DASH', 'KRW', '880', '505100.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아인스타이늄', 'EMC2', 'KRW', '64', '', NULL, '2018-04-22 20:14:26', '2018-04-18 10:37:40', 'N'),
	('upbit', '이오스', 'EOS', 'KRW', '7680', '12530.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움클래식', 'ETC', 'KRW', '188', '20800.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움', 'ETH', 'KRW', '5700', '685500.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '골렘', 'GNT', 'KRW', '5496', '', NULL, '2018-04-22 20:14:37', '2018-04-18 10:37:40', 'N'),
	('upbit', '그로스톨코인', 'GRS', 'KRW', '290', '1605.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아이콘', 'ICX', 'KRW', '5145', '3800.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '코모도', 'KMD', 'KRW', '', '4215.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리스크', 'LSK', 'KRW', '1190', '12700.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '라이트코인', 'LTC', 'KRW', '1490', '162250.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '머큐리', 'MER', 'KRW', '29', '', NULL, '2018-04-22 20:13:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '메탈', 'MTL', 'KRW', '3500', '5015.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '네오', 'NEO', 'KRW', '0', '82020.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '오미세고', 'OMG', 'KRW', '6444', '16870.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '피벡스', 'PIVX', 'KRW', '155', '5980.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '파워렛저', 'POWR', 'KRW', '2475', '550.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '퀀텀', 'QTUM', 'KRW', '188', '21350.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '어거', 'REP', 'KRW', '3928', '', NULL, '2018-04-22 20:13:52', '2018-04-18 10:37:40', 'N'),
	('upbit', '스팀달러', 'SBD', 'KRW', '30', '', NULL, '2018-04-22 20:14:50', '2018-04-18 10:37:40', 'N'),
	('upbit', '스테이터스네트워크토큰', 'SNT', 'KRW', '2115', '166.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스팀', 'STEEM', 'KRW', '31', '3320.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스토리지', 'STORJ', 'KRW', '4380', '1205.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스톰', 'STORM', 'KRW', '10500', '63.6', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스트라티스', 'STRAT', 'KRW', '1210', '6325.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '트론', 'TRX', 'KRW', '4080', '58.2', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '버트코인', 'VTC', 'KRW', '52', '', NULL, '2018-04-22 20:20:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '웨이브', 'WAVES', 'KRW', '5', '5630.0', '2018-04-23 00:18:58', '2018-04-23 00:18:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '뉴이코노미무브먼트', 'XEM', 'KRW', '1596', '430.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스텔라루멘', 'XLM', 'KRW', '8000', '408.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '모네로', 'XMR', 'KRW', '9702', '300500.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리플', 'XRP', 'KRW', '764', '961.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y'),
	('upbit', '지캐시', 'ZEC', 'KRW', '256', '300000.0', '2018-04-23 00:28:58', '2018-04-23 00:28:58', '2018-04-18 10:38:37', 'Y');
/*!40000 ALTER TABLE `jk_exchange_info` ENABLE KEYS */;

-- 테이블 jkbot.jk_service_coin_price 구조 내보내기
DROP TABLE IF EXISTS `jk_service_coin_price`;
CREATE TABLE IF NOT EXISTS `jk_service_coin_price` (
  `COIN_EXCHANGE_TYPE` varchar(50) NOT NULL,
  `COIN_SYMBOL` varchar(50) NOT NULL COMMENT '코인명',
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
INSERT INTO `jk_service_coin_price` (`COIN_EXCHANGE_TYPE`, `COIN_SYMBOL`, `PRICE_KRW_A`, `PRICE_KRW_B`, `PRICE_BTC_B`, `PRICE_USDT_B`, `PRICE_GAP_KRW`, `TRANSFER_FEE_A`, `TRANSFER_FEE_B`, `PRICE_GAP_PERCENT`, `USE_YN`, `UPDATE_DT`) VALUES
	('BTC', 'ADA', '313.0', '310.57', '0.00003245', NULL, '2.43', '55', NULL, '0.78', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'ARK', '3685.0', '3618.72', '0.00037810', NULL, '66.28', '318', '637', '1.83', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'BCC', '1340000.0', '1325726.83', '0.13851800', NULL, '14273.17', '2850', NULL, '1.08', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'BTC', '9655000.0', NULL, NULL, NULL, NULL, '4404', NULL, '0.0', 'N', '2018-04-23 00:33:35'),
	('BTC', 'BTG', '72860.0', '72345.61', '0.00755900', NULL, '514.39', '58', '117', '0.71', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'DASH', '505100.0', '502887.64', '0.05254400', NULL, '2212.36', '880', '1760', '0.44', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'EMC2', NULL, NULL, NULL, NULL, NULL, '64', NULL, NULL, 'N', '2018-04-22 23:11:43'),
	('BTC', 'EOS', '12510.0', '12387.47', '0.00129430', NULL, '122.53', '7680', '4880', '0.99', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'ETC', '20720.0', '20586.77', '0.00215100', NULL, '133.23', '188', '376', '0.65', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'ETH', '685500.0', '680004.7', '0.07105000', NULL, '5495.3', '5700', NULL, '0.81', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'GNT', NULL, NULL, NULL, NULL, NULL, '5496', NULL, NULL, 'N', '2018-04-22 23:11:50'),
	('BTC', 'GRS', '1600.0', '1599.09', '0.00016708', NULL, '0.91', '290', '580', '0.06', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'ICX', '3820.0', '3793.86', '0.00039640', NULL, '26.14', '5145', '6860', '0.69', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'KMD', '4220.0', '4216.89', '0.00044060', NULL, '3.11', NULL, NULL, '0.07', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'LSK', '12720.0', '12654.5', '0.00132220', NULL, '65.5', '1190', '2380', '0.52', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'LTC', '163000.0', '161133.84', '0.01683600', NULL, '1866.16', '1490', NULL, '1.16', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'MER', NULL, NULL, NULL, NULL, NULL, '29', NULL, NULL, 'N', '2018-04-22 23:11:59'),
	('BTC', 'MTL', '5000.0', '4995.95', '0.00052200', NULL, '4.05', '3500', '7000', '0.08', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'NEO', '82030.0', '81447.43', '0.00851000', NULL, '582.57', '0', NULL, '0.72', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'OMG', '16860.0', '16854.16', '0.00176100', NULL, '5.84', '6444', '6766', '0.03', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'PIVX', '6005.0', '6023.86', '0.00062940', NULL, '-18.86', '155', '517', '-0.31', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'POWR', '551.0', '553.19', '0.00005780', NULL, '-2.19', '2475', '6732', '-0.4', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'QTUM', '21320.0', '21161.02', '0.00221100', NULL, '158.98', '188', NULL, '0.75', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'REP', NULL, NULL, NULL, NULL, NULL, '3928', NULL, NULL, 'N', '2018-04-22 23:12:14'),
	('BTC', 'SBD', NULL, NULL, NULL, NULL, NULL, '30', NULL, NULL, 'N', '2018-04-22 23:12:16'),
	('BTC', 'SNT', '165.0', '164.62', '0.00001720', NULL, '0.38', '2115', '6768', '0.23', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'STEEM', '3330.0', '3291.4', '0.00034390', NULL, '38.6', '31', '62', '1.17', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'STORJ', '1205.0', '1202.38', '0.00012563', NULL, '2.62', '4380', '4500', '0.22', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'STORM', '63.3', '63.07', '0.00000659', NULL, '0.23', '10500', '6804', '0.36', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'STRAT', '6300.0', '6289.92', '0.00065720', NULL, '10.08', '1210', '12', '0.16', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'TRX', '58.0', '57.81', '0.00000604', NULL, '0.19', '4080', '2907', '0.33', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'VTC', NULL, NULL, NULL, NULL, NULL, '52', NULL, NULL, 'N', '2018-04-22 23:12:30'),
	('BTC', 'WAVES', '5625.0', '5586.47', '0.00058370', NULL, '38.53', '5', '532', '0.69', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'XEM', '430.0', '428.01', '0.00004472', NULL, '1.99', '1596', '3192', '0.46', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'XLM', '408.0', '406.18', '0.00004244', NULL, '1.82', '8000', '8043', '0.45', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'XMR', '299750.0', '295364.18', '0.03086100', NULL, '4385.82', '9702', NULL, '1.48', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'XRP', '962.0', '954.21', '0.00009970', NULL, '7.79', '764', '382', '0.82', 'Y', '2018-04-23 00:33:35'),
	('BTC', 'ZEC', '301650.0', '298905.37', '0.03123100', NULL, '2744.63', '256', '2563', '0.92', 'Y', '2018-04-23 00:33:35'),
	('USDT', 'ADA', '310.0', '309', NULL, '0.29', '1.0', NULL, NULL, '0.32', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'BCC', '1271000.0', '1264267', NULL, '1182.0', '6733.0', NULL, NULL, '0.53', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'BTC', '9549000.0', '9476025', NULL, '8859.41', '72975.0', NULL, NULL, '0.77', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'ETH', '670000.0', '664168', NULL, '620.95', '5832.0', NULL, NULL, '0.88', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'LTC', '160750.0', '159488', NULL, '149.11', '1262.0', NULL, NULL, '0.79', 'Y', '2018-04-22 21:42:51'),
	('USDT', 'NEO', '81360.0', '80914', NULL, '75.65', '446.0', NULL, NULL, '0.55', 'Y', '2018-04-22 21:42:51');
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
