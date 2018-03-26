-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.7.21-log - MySQL Community Server (GPL)
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- jkbot 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `jkbot`;
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
) ENGINE=InnoDB AUTO_INCREMENT=1873 DEFAULT CHARSET=utf8 COMMENT='코인 가격을 누적하여 쌓는 테이블';

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 jkbot.jk_coin_type 구조 내보내기
DROP TABLE IF EXISTS `jk_coin_type`;
CREATE TABLE IF NOT EXISTS `jk_coin_type` (
  `COIN_KOR_NAME` varchar(50) NOT NULL COMMENT '코인 한글명',
  `COIN_SYMBOL_NAME` varchar(50) NOT NULL COMMENT '코인 심볼명',
  PRIMARY KEY (`COIN_SYMBOL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='코인 종류 정리 (수동으로 관리가 필요함)\r\n';

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 jkbot.jk_exchange_mapping_info 구조 내보내기
DROP TABLE IF EXISTS `jk_exchange_mapping_info`;
CREATE TABLE IF NOT EXISTS `jk_exchange_mapping_info` (
  `EXCHANGE_NAME` varchar(50) NOT NULL COMMENT '거래소 명',
  `COIN_SYMBOL` varchar(50) DEFAULT NULL COMMENT '코인 심볼명',
  PRIMARY KEY (`EXCHANGE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='거래소 - 거래소에서 취급하는 코인 관리 테이블';

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 jkbot.jk_user_balance 구조 내보내기
DROP TABLE IF EXISTS `jk_user_balance`;
CREATE TABLE IF NOT EXISTS `jk_user_balance` (
  `USER_ID` varchar(50) NOT NULL COMMENT '사용자 ID',
  `EXCHANGE_NAME` varchar(50) NOT NULL COMMENT '거래소 명',
  `COIN_TYPE` varchar(50) NOT NULL COMMENT '코인 종류',
  `TOTAL_CURRENCY` varchar(50) NOT NULL COMMENT '보유중인 통화 수',
  `IN_USE_CURRENCY` varchar(50) NOT NULL COMMENT '사용중 통화 수',
  `AVAILABLE_CURRENCY` varchar(50) NOT NULL COMMENT '사용가능 통화 수',
  `XOIN_LAST` varchar(50) NOT NULL COMMENT '마지막 거래체결 금액(bithumb)',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 지갑 잔고';

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 jkbot.jk_user_info 구조 내보내기
DROP TABLE IF EXISTS `jk_user_info`;
CREATE TABLE IF NOT EXISTS `jk_user_info` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(50) DEFAULT NULL,
  `USER_PWD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 테이블';

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
