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
  `INSERT_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`EXCHANGE_NAME`,`COIN_EXCHANGE_TYPE`,`COIN_SYMBOL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='거래소 - 거래소에서 취급하는 코인 관리 테이블';

-- 테이블 데이터 jkbot.jk_exchange_info:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `jk_exchange_info` DISABLE KEYS */;
INSERT INTO `jk_exchange_info` (`EXCHANGE_NAME`, `COIN_KOR_NAME`, `COIN_SYMBOL_NAME`, `COIN_EXCHANGE_TYPE`, `INSERT_DT`, `UPDATE_DT`) VALUES
	('binance', '에이다', 'ADA', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '아크', 'ARK', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '비트코인캐시', 'BCC', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '비트코인', 'BTC', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '비트코인골드', 'BTG', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '대시', 'DASH', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '이오스', 'EOS', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '이더리움클래식', 'ETC', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '이더리움', 'ETH', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '그로스톨코인', 'GRS', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '아이콘', 'ICX', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '코모도', 'KMD', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '리스크', 'LSK', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '라이트코인', 'LTC', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '메탈', 'MTL', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '네오', 'NEO', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '오미세고', 'OMG', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '피벡스', 'PIVX', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '파워렛저', 'POWR', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '퀀텀', 'QTUM', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '스테이터스네트워크토큰', 'SNT', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '스팀', 'STEEM', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '스토리지', 'STORJ', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '스톰', 'STORM', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '스트라티스', 'STRAT', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '트론', 'TRX', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '웨이브', 'WAVES', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '뉴이코노미무브먼트', 'XEM', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '스텔라루멘', 'XLM', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '모네로', 'XMR', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '리플', 'XRP', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('binance', '지캐시', 'ZEC', 'BTC', '2018-04-18 10:38:14', '2018-04-18 10:38:14'),
	('upbit', '에이다', 'ADA', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '아크', 'ARK', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '비트코인캐시', 'BCC', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '비트코인', 'BTC', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '비트코인골드', 'BTG', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '대시', 'DASH', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '아인스타이늄', 'EMC2', 'KRW', '2018-04-18 10:37:40', '2018-04-18 10:37:40'),
	('upbit', '이오스', 'EOS', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '이더리움클래식', 'ETC', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '이더리움', 'ETH', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '골렘', 'GNT', 'KRW', '2018-04-18 10:37:40', '2018-04-18 10:37:40'),
	('upbit', '그로스톨코인', 'GRS', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '아이콘', 'ICX', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '코모도', 'KMD', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '리스크', 'LSK', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '라이트코인', 'LTC', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '머큐리', 'MER', 'KRW', '2018-04-18 10:37:40', '2018-04-18 10:37:40'),
	('upbit', '메탈', 'MTL', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '네오', 'NEO', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '오미세고', 'OMG', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '피벡스', 'PIVX', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '파워렛저', 'POWR', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '퀀텀', 'QTUM', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '어거', 'REP', 'KRW', '2018-04-18 10:37:40', '2018-04-18 10:37:40'),
	('upbit', '스팀달러', 'SBD', 'KRW', '2018-04-18 10:37:40', '2018-04-18 10:37:40'),
	('upbit', '스테이터스네트워크토큰', 'SNT', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '스팀', 'STEEM', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '스토리지', 'STORJ', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '스톰', 'STORM', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '스트라티스', 'STRAT', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '트론', 'TRX', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '버트코인', 'VTC', 'KRW', '2018-04-18 10:37:40', '2018-04-18 10:37:40'),
	('upbit', '웨이브', 'WAVES', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '뉴이코노미무브먼트', 'XEM', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '스텔라루멘', 'XLM', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '모네로', 'XMR', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '리플', 'XRP', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37'),
	('upbit', '지캐시', 'ZEC', 'KRW', '2018-04-18 10:38:37', '2018-04-18 10:38:37');
/*!40000 ALTER TABLE `jk_exchange_info` ENABLE KEYS */;

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
