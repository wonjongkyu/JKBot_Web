package jk.framework.web.history.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import jk.framework.web.history.entity.TradeHistoryEntity;

@Mapper
public interface HistoryMapper {
	
	public void insertTradeHistory(TradeHistoryEntity list) throws DataAccessException;
}

