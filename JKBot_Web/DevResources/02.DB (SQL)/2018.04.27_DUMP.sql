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
	('binance', '에이다', 'ADA', 'BTC', '1', '312.03', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '아크', 'ARK', 'BTC', '0.2', '3684.78', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인캐시', 'BCC', 'BTC', '0.002', '1497248.97', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인', 'BTC', 'BTC', '0.002', '9433947', '2018-04-22 12:53:52', '2018-04-27 16:11:17', '2018-04-18 10:38:14', 'Y'),
	('binance', '비트코인골드', 'BTG', 'BTC', '0.002', '82068.73', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '대시', 'DASH', 'BTC', '0.004', '528941.55', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '이오스', 'EOS', 'BTC', '0.4', '16212.63', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움클래식', 'ETC', 'BTC', '0.02', '22232.13', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '이더리움', 'ETH', 'BTC', '0.02', '710721.34', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'N'),
	('binance', '그로스톨코인', 'GRS', 'BTC', '0.2', '1483.87', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '아이콘', 'ICX', 'BTC', '2', '4743.12', '2018-04-27 17:33:01', '2018-04-27 17:33:01', '2018-04-18 10:38:14', 'Y'),
	('binance', '코모도', 'KMD', 'BTC', '0.004', '4166.66', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'N'),
	('binance', '리스크', 'LSK', 'BTC', '0.2', '12544.78', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '라이트코인', 'LTC', 'BTC', '0.02', '161847.53', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '메탈', 'MTL', 'BTC', '1.6', '5336.51', '2018-04-27 17:33:01', '2018-04-27 17:33:01', '2018-04-18 10:38:14', 'Y'),
	('binance', '네오', 'NEO', 'BTC', '0', '81341.93', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '오미세고', 'OMG', 'BTC', '0.42', '20290.68', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '피벡스', 'PIVX', 'BTC', '0.1', '5885.09', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '파워렛저', 'POWR', 'BTC', '13.6', '582.44', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '퀀텀', 'QTUM', 'BTC', '0.02', '22003.14', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '스테이터스네트워크토큰', 'SNT', 'BTC', '48', '153.42', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '스팀', 'STEEM', 'BTC', '0.02', '4115.88', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '스토리지', 'STORJ', 'BTC', '3.5', '1215.95', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '스톰', 'STORM', 'BTC', '162', '67.7', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '스트라티스', 'STRAT', 'BTC', '0.002', '6903.61', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '트론', 'TRX', 'BTC', '67', '78.75', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '웨이브', 'WAVES', 'BTC', '0.1', '6180.79', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '뉴이코노미무브먼트', 'XEM', 'BTC', '4', '435.98', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '스텔라루멘', 'XLM', 'BTC', '21', '444.54', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '모네로', 'XMR', 'BTC', '0.1', '283711.08', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'N'),
	('binance', '리플', 'XRP', 'BTC', '0.25', '901.03', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '지캐시', 'ZEC', 'BTC', '0.01', '319603.08', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:14', 'Y'),
	('binance', '에이다', 'ADA', 'USDT', NULL, '312.03', '2018-04-27 17:33:00', '2018-04-27 17:33:00', NULL, 'Y'),
	('binance', '비트코인캐시', 'BCC', 'USDT', NULL, '1497248.97', '2018-04-27 17:33:00', '2018-04-27 17:33:00', NULL, 'Y'),
	('binance', '비트코인', 'BTC', 'USDT', '17618', '0', NULL, '2018-04-22 20:57:52', NULL, 'Y'),
	('binance', '이더리움', 'ETH', 'USDT', NULL, '710721.34', '2018-04-27 17:33:00', '2018-04-27 17:33:00', NULL, 'Y'),
	('binance', '라이트코인', 'LTC', 'USDT', NULL, '161847.53', '2018-04-27 17:33:00', '2018-04-27 17:33:00', NULL, 'Y'),
	('binance', '네오', 'NEO', 'USDT', NULL, '81341.93', '2018-04-27 17:33:00', '2018-04-27 17:33:00', NULL, 'Y'),
	('binance', '퀀텀', 'QTUM', 'USDT', NULL, '22003.14', '2018-04-27 17:33:00', '2018-04-27 17:33:00', NULL, 'Y'),
	('upbit', '에이다', 'ADA', 'KRW', '0.2', '315.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아크', 'ARK', 'KRW', '0.1', '3705.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인캐시', 'BCC', 'KRW', '0.003', '1512000.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인', 'BTC', 'KRW', '0.0005', '1.007E7', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '비트코인골드', 'BTG', 'KRW', '0.001', '83400.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '대시', 'DASH', 'KRW', '0.002', '533500.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아인스타이늄', 'EMC2', 'KRW', '0.2', '', NULL, '2018-04-27 16:12:49', '2018-04-18 10:37:40', 'N'),
	('upbit', '이오스', 'EOS', 'KRW', '0.8', '16410.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움클래식', 'ETC', 'KRW', '0.01', '22490.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '이더리움', 'ETH', 'KRW', '0.01', '719400.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '골렘', 'GNT', 'KRW', '12', '', NULL, '2018-04-27 16:12:56', '2018-04-18 10:37:40', 'N'),
	('upbit', '그로스톨코인', 'GRS', 'KRW', '0.2', '1490.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '아이콘', 'ICX', 'KRW', '15', '4720.0', '2018-04-27 17:33:01', '2018-04-27 17:33:01', '2018-04-18 10:38:37', 'Y'),
	('upbit', '코모도', 'KMD', 'KRW', '', '4210.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리스크', 'LSK', 'KRW', '0.1', '12600.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '라이트코인', 'LTC', 'KRW', '0.01', '163550.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '머큐리', 'MER', 'KRW', '0.1', '', NULL, '2018-04-27 16:13:11', '2018-04-18 10:37:40', 'N'),
	('upbit', '메탈', 'MTL', 'KRW', '0.8', '5315.0', '2018-04-27 17:33:01', '2018-04-27 17:33:01', '2018-04-18 10:38:37', 'Y'),
	('upbit', '네오', 'NEO', 'KRW', '0', '82280.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '오미세고', 'OMG', 'KRW', '0.4', '20370.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '피벡스', 'PIVX', 'KRW', '0.03', '5900.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '파워렛저', 'POWR', 'KRW', '5', '586.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '퀀텀', 'QTUM', 'KRW', '0.01', '22240.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '어거', 'REP', 'KRW', '0.1', '', NULL, '2018-04-27 16:13:22', '2018-04-18 10:37:40', 'N'),
	('upbit', '스팀달러', 'SBD', 'KRW', '0.01', '', NULL, '2018-04-27 16:13:23', '2018-04-18 10:37:40', 'N'),
	('upbit', '스테이터스네트워크토큰', 'SNT', 'KRW', '15', '154.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스팀', 'STEEM', 'KRW', '0.01', '4115.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스토리지', 'STORJ', 'KRW', '4', '1215.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스톰', 'STORM', 'KRW', '250', '68.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스트라티스', 'STRAT', 'KRW', '0.2', '6940.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '트론', 'TRX', 'KRW', '80', '79.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '버트코인', 'VTC', 'KRW', '0.02', '', NULL, '2018-04-27 16:13:42', '2018-04-18 10:37:40', 'N'),
	('upbit', '웨이브', 'WAVES', 'KRW', '0.001', '6200.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '뉴이코노미무브먼트', 'XEM', 'KRW', '4', '437.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '스텔라루멘', 'XLM', 'KRW', '0.01', '448.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '모네로', 'XMR', 'KRW', '0.04', '284450.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '리플', 'XRP', 'KRW', '1', '916.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y'),
	('upbit', '지캐시', 'ZEC', 'KRW', '0.001', '321100.0', '2018-04-27 17:33:00', '2018-04-27 17:33:00', '2018-04-18 10:38:37', 'Y');
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
	('BTC', 'ADA', '314.0', '311.39', '0.00003131', NULL, '2.61', '55', NULL, '0.84', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'ARK', '3705.0', '3683.77', '0.00037040', NULL, '21.23', '318', '637', '0.58', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'BCC', '1506500.0', '1493827.26', '0.15020300', NULL, '12672.74', '2850', NULL, '0.85', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'BTC', '1.0059E7', NULL, NULL, NULL, NULL, '4404', NULL, '0.0', 'N', '2018-04-27 17:37:30'),
	('BTC', 'BTG', '83300.0', '82049.46', '0.00825000', NULL, '1250.54', '58', '117', '1.52', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'DASH', '533500.0', '528309.01', '0.05312100', NULL, '5190.99', '880', '1760', '0.98', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'EMC2', NULL, NULL, NULL, NULL, NULL, '64', NULL, NULL, 'N', '2018-04-22 23:11:43'),
	('BTC', 'EOS', '16430.0', '16250.77', '0.00163400', NULL, '179.23', '7680', '4880', '1.1', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'ETC', '22400.0', '22198.11', '0.00223200', NULL, '201.89', '188', '376', '0.91', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'ETH', '718200.0', '710707.44', '0.07146100', NULL, '7492.56', '5700', NULL, '1.05', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'GNT', NULL, NULL, NULL, NULL, NULL, '5496', NULL, NULL, 'N', '2018-04-22 23:11:50'),
	('BTC', 'GRS', '1490.0', '1477.19', '0.00014853', NULL, '12.81', '290', '580', '0.87', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'ICX', '4745.0', '4736.99', '0.00047630', NULL, '8.01', '5145', '6860', '0.17', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'KMD', '4190.0', '4169.11', '0.00041920', NULL, '20.89', NULL, NULL, '0.5', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'LSK', '12560.0', '12548.1', '0.00126170', NULL, '11.9', '1190', '2380', '0.09', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'LTC', '163050.0', '161533.01', '0.01624200', NULL, '1516.99', '1490', NULL, '0.94', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'MER', NULL, NULL, NULL, NULL, NULL, '29', NULL, NULL, 'N', '2018-04-22 23:11:59'),
	('BTC', 'MTL', '5350.0', '5320.78', '0.00053500', NULL, '29.22', '3500', '7000', '0.55', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'NEO', '82490.0', '81184.21', '0.00816300', NULL, '1305.79', '0', NULL, '1.61', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'OMG', '20270.0', '20209.03', '0.00203200', NULL, '60.97', '6444', '6766', '0.3', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'PIVX', '5885.0', '5878.72', '0.00059110', NULL, '6.28', '155', '517', '0.11', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'POWR', '584.0', '582.2', '0.00005854', NULL, '1.8', '2475', '6732', '0.31', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'QTUM', '22110.0', '21879.86', '0.00220000', NULL, '230.14', '188', NULL, '1.05', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'REP', NULL, NULL, NULL, NULL, NULL, '3928', NULL, NULL, 'N', '2018-04-22 23:12:14'),
	('BTC', 'SBD', NULL, NULL, NULL, NULL, NULL, '30', NULL, NULL, 'N', '2018-04-22 23:12:16'),
	('BTC', 'SNT', '154.0', '152.56', '0.00001534', NULL, '1.44', '2115', '6768', '0.94', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'STEEM', '4130.0', '4129.33', '0.00041520', NULL, '0.67', '31', '62', '0.02', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'STORJ', '1215.0', '1215.62', '0.00012223', NULL, '-0.62', '4380', '4500', '-0.05', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'STORM', '68.3', '67.33', '0.00000677', NULL, '0.97', '10500', '6804', '1.44', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'STRAT', '6925.0', '6896.13', '0.00069340', NULL, '28.87', '1210', '12', '0.42', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'TRX', '78.7', '78.67', '0.00000791', NULL, '0.03', '4080', '2907', '0.04', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'VTC', NULL, NULL, NULL, NULL, NULL, '52', NULL, NULL, 'N', '2018-04-22 23:12:30'),
	('BTC', 'WAVES', '6180.0', '6180.06', '0.00062140', NULL, '-0.06', '5', '532', '0.0', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'XEM', '435.0', '434.91', '0.00004373', NULL, '0.09', '1596', '3192', '0.02', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'XLM', '449.0', '445.35', '0.00004478', NULL, '3.65', '8000', '8043', '0.82', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'XMR', '284450.0', '283085.55', '0.02846400', NULL, '1364.45', '9702', NULL, '0.48', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'XRP', '913.0', '901.15', '0.00009061', NULL, '11.85', '764', '382', '1.31', 'Y', '2018-04-27 17:37:30'),
	('BTC', 'ZEC', '319900.0', '319266.88', '0.03210200', NULL, '633.12', '256', '2563', '0.2', 'Y', '2018-04-27 17:37:30'),
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
