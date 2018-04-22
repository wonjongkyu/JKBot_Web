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
	('binance', '에이다', 'ADA', 'BTC', '278', '310.56', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '아크', 'ARK', 'BTC', '637', '3575.19', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인캐시', 'BCC', 'BTC', '1900', '1282469.49', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인', 'BTC', 'BTC', '17618', '9433947', '2018-04-22 12:53:52', '2018-04-22 20:57:52', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인골드', 'BTG', 'BTC', '117', '71857.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '대시', 'DASH', 'BTC', '1760', '501357.44', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '이오스', 'EOS', 'BTC', '4880', '12407.24', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움클래식', 'ETC', 'BTC', '376', '20462.4', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움', 'ETH', 'BTC', '11400', '675049.24', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'N'),
	('binance', '그로스톨코인', 'GRS', 'BTC', '580', '1594.9', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '아이콘', 'ICX', 'BTC', '6860', '3795.66', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '코모도', 'KMD', 'BTC', '16', '4189.83', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'N'),
	('binance', '리스크', 'LSK', 'BTC', '2380', '12535.13', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '라이트코인', 'LTC', 'BTC', '2980', '161313.2', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '메탈', 'MTL', 'BTC', '7000', '5017.3', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '네오', 'NEO', 'BTC', '0', '81859.15', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '오미세고', 'OMG', 'BTC', '6766', '16902.48', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '피벡스', 'PIVX', 'BTC', '517', '5924.93', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '파워렛저', 'POWR', 'BTC', '6732', '542.86', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '퀀텀', 'QTUM', 'BTC', '376', '20863.25', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '스테이터스네트워크토큰', 'SNT', 'BTC', '6768', '163.2', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '스팀', 'STEEM', 'BTC', '62', '3287.92', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '스토리지', 'STORJ', 'BTC', '4500', '1204.84', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '스톰', 'STORM', 'BTC', '6804', '59.94', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '스트라티스', 'STRAT', 'BTC', '12', '6271.38', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '트론', 'TRX', 'BTC', '2907', '57.46', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '웨이브', 'WAVES', 'BTC', '532', '5577.53', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '뉴이코노미무브먼트', 'XEM', 'BTC', '3192', '426.81', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '스텔라루멘', 'XLM', 'BTC', '8043', '404.29', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '모네로', 'XMR', 'BTC', '24255', '300121.57', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'N'),
	('binance', '리플', 'XRP', 'BTC', '16808', '956.69', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '지캐시', 'ZEC', 'BTC', '2563', '295196.85', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:14', 'Y'),
	('binance', '에이다', 'ADA', 'USDT', NULL, '310.56', '2018-04-22 23:12:25', '2018-04-22 23:12:25', NULL, 'Y'),
	('binance', '비트코인캐시', 'BCC', 'USDT', NULL, '1282469.49', '2018-04-22 23:12:25', '2018-04-22 23:12:25', NULL, 'Y'),
	('binance', '비트코인', 'BTC', 'USDT', '17618', '0', NULL, '2018-04-22 20:57:52', NULL, 'Y'),
	('binance', '이더리움', 'ETH', 'USDT', NULL, '675049.24', '2018-04-22 23:12:25', '2018-04-22 23:12:25', NULL, 'Y'),
	('binance', '라이트코인', 'LTC', 'USDT', NULL, '161313.2', '2018-04-22 23:12:25', '2018-04-22 23:12:25', NULL, 'Y'),
	('binance', '네오', 'NEO', 'USDT', NULL, '81859.15', '2018-04-22 23:12:25', '2018-04-22 23:12:25', NULL, 'Y'),
	('binance', '퀀텀', 'QTUM', 'USDT', NULL, '20863.25', '2018-04-22 23:12:25', '2018-04-22 23:12:25', NULL, 'Y'),
	('upbit', '에이다', 'ADA', 'KRW', '55', '312.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아크', 'ARK', 'KRW', '318', '3615.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인캐시', 'BCC', 'KRW', '2850', '1298000.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인', 'BTC', 'KRW', '4404', '9621000.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인골드', 'BTG', 'KRW', '58', '72490.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '대시', 'DASH', 'KRW', '880', '501200.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아인스타이늄', 'EMC2', 'KRW', '64', '', NULL, '2018-04-22 20:14:26', '2018-04-18 10:37:40', 'N'),
	('upbit', '이오스', 'EOS', 'KRW', '7680', '12490.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움클래식', 'ETC', 'KRW', '188', '20610.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움', 'ETH', 'KRW', '5700', '681700.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '골렘', 'GNT', 'KRW', '5496', '', NULL, '2018-04-22 20:14:37', '2018-04-18 10:37:40', 'N'),
	('upbit', '그로스톨코인', 'GRS', 'KRW', '290', '1590.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아이콘', 'ICX', 'KRW', '5145', '3860.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '코모도', 'KMD', 'KRW', '', '4210.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리스크', 'LSK', 'KRW', '1190', '12630.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '라이트코인', 'LTC', 'KRW', '1490', '162900.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '머큐리', 'MER', 'KRW', '29', '', NULL, '2018-04-22 20:13:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '메탈', 'MTL', 'KRW', '3500', '5010.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '네오', 'NEO', 'KRW', '0', '82010.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '오미세고', 'OMG', 'KRW', '6444', '16870.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '피벡스', 'PIVX', 'KRW', '155', '6020.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '파워렛저', 'POWR', 'KRW', '2475', '543.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '퀀텀', 'QTUM', 'KRW', '188', '21070.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '어거', 'REP', 'KRW', '3928', '', NULL, '2018-04-22 20:13:52', '2018-04-18 10:37:40', 'N'),
	('upbit', '스팀달러', 'SBD', 'KRW', '30', '', NULL, '2018-04-22 20:14:50', '2018-04-18 10:37:40', 'N'),
	('upbit', '스테이터스네트워크토큰', 'SNT', 'KRW', '2115', '164.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스팀', 'STEEM', 'KRW', '31', '3310.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스토리지', 'STORJ', 'KRW', '4380', '1195.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스톰', 'STORM', 'KRW', '10500', '60.1', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스트라티스', 'STRAT', 'KRW', '1210', '6325.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '트론', 'TRX', 'KRW', '4080', '57.8', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '버트코인', 'VTC', 'KRW', '52', '', NULL, '2018-04-22 20:20:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '웨이브', 'WAVES', 'KRW', '5', '5610.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '뉴이코노미무브먼트', 'XEM', 'KRW', '1596', '428.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스텔라루멘', 'XLM', 'KRW', '8000', '405.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '모네로', 'XMR', 'KRW', '9702', '303350.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리플', 'XRP', 'KRW', '764', '965.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y'),
	('upbit', '지캐시', 'ZEC', 'KRW', '256', '296450.0', '2018-04-22 23:12:25', '2018-04-22 23:12:25', '2018-04-18 10:38:37', 'Y');
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
	('BTC', 'ADA', '313.0', '309.99', '0.00003248', NULL, '3.01', NULL, NULL, '0.97', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'ARK', '3615.0', '3568.51', '0.00037390', NULL, '46.49', NULL, '637', '1.3', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'BCC', '1298500.0', '1284349.66', '0.13457100', NULL, '14150.34', NULL, NULL, '1.1', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'BTC', '9625000.0', NULL, NULL, NULL, NULL, '4404', NULL, '0.0', 'N', '2018-04-22 23:12:55'),
	('BTC', 'BTG', '72010.0', '71981.07', '0.00754200', NULL, '28.93', NULL, '117', '0.04', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'DASH', '501200.0', '501576.95', '0.05255400', NULL, '-376.95', NULL, '1760', '-0.08', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'EMC2', NULL, NULL, NULL, NULL, NULL, '64', NULL, NULL, 'N', '2018-04-22 23:11:43'),
	('BTC', 'EOS', '12510.0', '12399.6', '0.00129920', NULL, '110.4', NULL, '4880', '0.89', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'ETC', '20600.0', '20452.86', '0.00214300', NULL, '147.14', NULL, '376', '0.72', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'ETH', '679400.0', '674600.67', '0.07068300', NULL, '4799.33', NULL, NULL, '0.71', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'GNT', NULL, NULL, NULL, NULL, NULL, '5496', NULL, NULL, 'N', '2018-04-22 23:11:50'),
	('BTC', 'GRS', '1595.0', '1595.0', '0.00016712', NULL, '0.0', NULL, '580', '0.0', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'ICX', '3860.0', '3803.3', '0.00039850', NULL, '56.7', NULL, '6860', '1.49', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'KMD', '4210.0', '4189.83', '0.00043900', NULL, '20.17', NULL, NULL, '0.48', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'LSK', '12630.0', '12571.4', '0.00131720', NULL, '58.6', NULL, '2380', '0.47', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'LTC', '162900.0', '161217.75', '0.01689200', NULL, '1682.25', NULL, NULL, '1.04', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'MER', NULL, NULL, NULL, NULL, NULL, '29', NULL, NULL, 'N', '2018-04-22 23:11:59'),
	('BTC', 'MTL', '5020.0', '5017.3', '0.00052570', NULL, '2.7', NULL, '7000', '0.05', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'NEO', '82100.0', '81935.5', '0.00858500', NULL, '164.5', NULL, NULL, '0.2', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'OMG', '16870.0', '16873.85', '0.00176800', NULL, '-3.85', NULL, '6766', '-0.02', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'PIVX', '6020.0', '5924.93', '0.00062080', NULL, '95.07', NULL, '517', '1.6', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'POWR', '543.0', '542.48', '0.00005684', NULL, '0.52', NULL, '6732', '0.1', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'QTUM', '21120.0', '20891.88', '0.00218900', NULL, '228.12', NULL, NULL, '1.09', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'REP', NULL, NULL, NULL, NULL, NULL, '3928', NULL, NULL, 'N', '2018-04-22 23:12:14'),
	('BTC', 'SBD', NULL, NULL, NULL, NULL, NULL, '30', NULL, NULL, 'N', '2018-04-22 23:12:16'),
	('BTC', 'SNT', '164.0', '163.3', '0.00001711', NULL, '0.7', NULL, '6768', '0.43', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'STEEM', '3310.0', '3287.92', '0.00034450', NULL, '22.08', NULL, '62', '0.67', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'STORJ', '1195.0', '1204.07', '0.00012616', NULL, '-9.07', NULL, '4500', '-0.75', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'STORM', '60.2', '59.94', '0.00000628', NULL, '0.26', NULL, '6804', '0.43', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'STRAT', '6325.0', '6264.7', '0.00065640', NULL, '60.3', NULL, '12', '0.96', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'TRX', '57.9', '57.46', '0.00000602', NULL, '0.44', NULL, '2907', '0.77', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'VTC', NULL, NULL, NULL, NULL, NULL, '52', NULL, NULL, 'N', '2018-04-22 23:12:30'),
	('BTC', 'WAVES', '5610.0', '5577.53', '0.00058440', NULL, '32.47', NULL, '532', '0.58', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'XEM', '428.0', '426.43', '0.00004468', NULL, '1.57', NULL, '3192', '0.37', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'XLM', '404.0', '403.43', '0.00004227', NULL, '0.57', NULL, '8043', '0.14', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'XMR', '303300.0', '300121.57', '0.03144600', NULL, '3178.43', NULL, NULL, '1.06', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'XRP', '966.0', '956.6', '0.00010023', NULL, '9.4', NULL, '16808', '0.98', 'Y', '2018-04-22 23:12:55'),
	('BTC', 'ZEC', '296450.0', '295196.85', '0.03093000', NULL, '1253.15', NULL, '2563', '0.42', 'Y', '2018-04-22 23:12:55'),
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
