-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.6.39-log - MySQL Community Server (GPL)
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
	('binance', '에이다', 'ADA', 'BTC', '278', '320.99', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '아크', 'ARK', 'BTC', '637', '3826.76', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인캐시', 'BCC', 'BTC', '1900', '1652165.64', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인', 'BTC', 'BTC', '17618', '9433947', '2018-04-22 12:53:52', '2018-04-22 20:57:52', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인골드', 'BTG', 'BTC', '117', '93014.1', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '대시', 'DASH', 'BTC', '1760', '574872.88', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '이오스', 'EOS', 'BTC', '4880', '14592.4', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움클래식', 'ETC', 'BTC', '376', '23266.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움', 'ETH', 'BTC', '11400', '735089.89', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'N'),
	('binance', '그로스톨코인', 'GRS', 'BTC', '580', '1645.39', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '아이콘', 'ICX', 'BTC', '6860', '4711.09', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '코모도', 'KMD', 'BTC', '16', '4436.61', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'N'),
	('binance', '리스크', 'LSK', 'BTC', '2380', '13685.12', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '라이트코인', 'LTC', 'BTC', '2980', '173112.63', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '메탈', 'MTL', 'BTC', '7000', '5237.1', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '네오', 'NEO', 'BTC', '0', '86057.26', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '오미세고', 'OMG', 'BTC', '6766', '18415.18', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '피벡스', 'PIVX', 'BTC', '517', '6203.27', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '파워렛저', 'POWR', 'BTC', '6732', '637.39', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '퀀텀', 'QTUM', 'BTC', '376', '23206.12', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '스테이터스네트워크토큰', 'SNT', 'BTC', '6768', '166.88', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '스팀', 'STEEM', 'BTC', '62', '3584.22', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '스토리지', 'STORJ', 'BTC', '4500', '1289.16', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '스톰', 'STORM', 'BTC', '6804', '64.28', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '스트라티스', 'STRAT', 'BTC', '12', '7036.69', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '트론', 'TRX', 'BTC', '2907', '68.07', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '웨이브', 'WAVES', 'BTC', '532', '6171.33', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '뉴이코노미무브먼트', 'XEM', 'BTC', '1596', '440.07', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '스텔라루멘', 'XLM', 'BTC', '8043', '415.61', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '모네로', 'XMR', 'BTC', '24255', '313327.48', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'N'),
	('binance', '리플', 'XRP', 'BTC', '191', '981.64', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '지캐시', 'ZEC', 'BTC', '2563', '327560.56', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:14', 'Y'),
	('binance', '에이다', 'ADA', 'USDT', NULL, '320.99', '2018-04-24 18:51:23', '2018-04-24 18:51:23', NULL, 'Y'),
	('binance', '비트코인캐시', 'BCC', 'USDT', NULL, '1652165.64', '2018-04-24 18:51:23', '2018-04-24 18:51:23', NULL, 'Y'),
	('binance', '비트코인', 'BTC', 'USDT', '17618', '0', NULL, '2018-04-22 20:57:52', NULL, 'Y'),
	('binance', '이더리움', 'ETH', 'USDT', NULL, '735089.89', '2018-04-24 18:51:23', '2018-04-24 18:51:23', NULL, 'Y'),
	('binance', '라이트코인', 'LTC', 'USDT', NULL, '173112.63', '2018-04-24 18:51:23', '2018-04-24 18:51:23', NULL, 'Y'),
	('binance', '네오', 'NEO', 'USDT', NULL, '86057.26', '2018-04-24 18:51:23', '2018-04-24 18:51:23', NULL, 'Y'),
	('binance', '퀀텀', 'QTUM', 'USDT', NULL, '23206.12', '2018-04-24 18:51:23', '2018-04-24 18:51:23', NULL, 'Y'),
	('upbit', '에이다', 'ADA', 'KRW', '55', '321.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아크', 'ARK', 'KRW', '318', '3820.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인캐시', 'BCC', 'KRW', '2850', '1659500.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인', 'BTC', 'KRW', '4404', '1.0065E7', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인골드', 'BTG', 'KRW', '58', '93540.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '대시', 'DASH', 'KRW', '880', '578100.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아인스타이늄', 'EMC2', 'KRW', '64', '', NULL, '2018-04-22 20:14:26', '2018-04-18 10:37:40', 'N'),
	('upbit', '이오스', 'EOS', 'KRW', '7680', '14680.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움클래식', 'ETC', 'KRW', '188', '23360.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움', 'ETH', 'KRW', '5700', '739700.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '골렘', 'GNT', 'KRW', '5496', '', NULL, '2018-04-22 20:14:37', '2018-04-18 10:37:40', 'N'),
	('upbit', '그로스톨코인', 'GRS', 'KRW', '290', '1670.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아이콘', 'ICX', 'KRW', '5145', '4695.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '코모도', 'KMD', 'KRW', '', '4450.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리스크', 'LSK', 'KRW', '1190', '13850.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '라이트코인', 'LTC', 'KRW', '1490', '174550.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '머큐리', 'MER', 'KRW', '29', '', NULL, '2018-04-22 20:13:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '메탈', 'MTL', 'KRW', '3500', '5280.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '네오', 'NEO', 'KRW', '0', '86820.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '오미세고', 'OMG', 'KRW', '6444', '18400.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '피벡스', 'PIVX', 'KRW', '155', '6240.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '파워렛저', 'POWR', 'KRW', '2475', '637.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '퀀텀', 'QTUM', 'KRW', '188', '23350.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '어거', 'REP', 'KRW', '3928', '', NULL, '2018-04-22 20:13:52', '2018-04-18 10:37:40', 'N'),
	('upbit', '스팀달러', 'SBD', 'KRW', '30', '', NULL, '2018-04-22 20:14:50', '2018-04-18 10:37:40', 'N'),
	('upbit', '스테이터스네트워크토큰', 'SNT', 'KRW', '2115', '167.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스팀', 'STEEM', 'KRW', '31', '3610.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스토리지', 'STORJ', 'KRW', '4380', '1290.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스톰', 'STORM', 'KRW', '10500', '64.9', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스트라티스', 'STRAT', 'KRW', '1210', '7100.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '트론', 'TRX', 'KRW', '4080', '69.6', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '버트코인', 'VTC', 'KRW', '52', '', NULL, '2018-04-22 20:20:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '웨이브', 'WAVES', 'KRW', '5', '6175.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '뉴이코노미무브먼트', 'XEM', 'KRW', '1596', '443.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스텔라루멘', 'XLM', 'KRW', '8000', '416.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '모네로', 'XMR', 'KRW', '9702', '315450.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리플', 'XRP', 'KRW', '764', '987.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y'),
	('upbit', '지캐시', 'ZEC', 'KRW', '256', '329000.0', '2018-04-24 18:51:23', '2018-04-24 18:51:23', '2018-04-18 10:38:37', 'Y');
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
	('BTC', 'ADA', '323.0', '320.82', '0.00003215', NULL, '2.18', '55', NULL, '0.68', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'ARK', '3840.0', '3826.93', '0.00038350', NULL, '13.07', '318', '637', '0.34', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'BCC', '1656000.0', '1650290.8', '0.16537700', NULL, '5709.2', '2850', NULL, '0.35', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'BTC', '1.007E7', NULL, NULL, NULL, NULL, '4404', NULL, '0.0', 'N', '2018-04-24 19:00:23'),
	('BTC', 'BTG', '92800.0', '92554.87', '0.00927500', NULL, '245.13', '58', '117', '0.26', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'DASH', '579100.0', '572702.61', '0.05739100', NULL, '6397.39', '880', '1760', '1.12', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'EMC2', NULL, NULL, NULL, NULL, NULL, '64', NULL, NULL, 'N', '2018-04-22 23:11:43'),
	('BTC', 'EOS', '14670.0', '14617.18', '0.00146480', NULL, '52.82', '7680', '4880', '0.36', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'ETC', '23450.0', '23231.02', '0.00232800', NULL, '218.98', '188', '376', '0.94', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'ETH', '741500.0', '734980.49', '0.07365300', NULL, '6519.51', '5700', NULL, '0.89', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'GNT', NULL, NULL, NULL, NULL, NULL, '5496', NULL, NULL, 'N', '2018-04-22 23:11:50'),
	('BTC', 'GRS', '1670.0', '1646.03', '0.00016495', NULL, '23.97', '290', '580', '1.46', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'ICX', '4715.0', '4751.98', '0.00047620', NULL, '-36.98', '5145', '6860', '-0.78', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'KMD', '4435.0', '4429.66', '0.00044390', NULL, '5.34', NULL, NULL, '0.12', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'LSK', '13950.0', '13731.05', '0.00137600', NULL, '218.95', '1190', '2380', '1.59', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'LTC', '174450.0', '172835.62', '0.01732000', NULL, '1614.38', '1490', NULL, '0.93', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'MER', NULL, NULL, NULL, NULL, NULL, '29', NULL, NULL, 'N', '2018-04-22 23:11:59'),
	('BTC', 'MTL', '5290.0', '5226.98', '0.00052380', NULL, '63.02', '3500', '7000', '1.21', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'NEO', '86720.0', '85928.84', '0.00861100', NULL, '791.16', '0', NULL, '0.92', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'OMG', '18430.0', '18391.23', '0.00184300', NULL, '38.77', '6444', '6766', '0.21', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'PIVX', '6285.0', '6236.85', '0.00062500', NULL, '48.15', '155', '517', '0.77', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'POWR', '634.0', '635.76', '0.00006371', NULL, '-1.76', '2475', '6732', '-0.28', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'QTUM', NULL, '23191.11', '0.00232400', NULL, NULL, '188', NULL, '0.0', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'REP', NULL, NULL, NULL, NULL, NULL, '3928', NULL, NULL, 'N', '2018-04-22 23:12:14'),
	('BTC', 'SBD', NULL, NULL, NULL, NULL, NULL, '30', NULL, NULL, 'N', '2018-04-22 23:12:16'),
	('BTC', 'SNT', '167.0', '166.65', '0.00001670', NULL, '0.35', '2115', '6768', '0.21', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'STEEM', '3615.0', '3580.45', '0.00035880', NULL, '34.55', '31', '62', '0.96', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'STORJ', '1290.0', '1287.29', '0.00012900', NULL, '2.71', '4380', '4500', '0.21', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'STORM', '64.8', '64.36', '0.00000645', NULL, '0.44', '10500', '6804', '0.68', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'STRAT', '7130.0', '7086.06', '0.00071010', NULL, '43.94', '1210', '12', '0.62', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'TRX', '68.9', '67.86', '0.00000680', NULL, '1.04', '4080', '2907', '1.53', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'VTC', NULL, NULL, NULL, NULL, NULL, '52', NULL, NULL, 'N', '2018-04-22 23:12:30'),
	('BTC', 'WAVES', '6180.0', '6175.98', '0.00061890', NULL, '4.02', '5', '532', '0.07', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'XEM', '443.0', '440.07', '0.00004410', NULL, '2.93', '1596', '3192', '0.67', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'XLM', '417.0', '414.93', '0.00004158', NULL, '2.07', '8000', '8043', '0.5', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'XMR', '315950.0', '312860.42', '0.03135200', NULL, '3089.58', '9702', NULL, '0.99', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'XRP', '987.0', '980.83', '0.00009829', NULL, '6.17', '764', '382', '0.63', 'Y', '2018-04-24 19:00:23'),
	('BTC', 'ZEC', '330250.0', '328028.44', '0.03287200', NULL, '2221.56', '256', '2563', '0.68', 'Y', '2018-04-24 19:00:23'),
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

-- 테이블 데이터 jkbot.jk_user_balance:~0 rows (대략적) 내보내기
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
