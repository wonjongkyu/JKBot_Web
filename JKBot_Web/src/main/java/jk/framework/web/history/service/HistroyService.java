package jk.framework.web.history.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jk.framework.web.history.entity.TradeHistoryEntity;
import jk.framework.web.history.mapper.HistoryMapper;

@Service
public class HistroyService {
	 
    @Autowired
    HistoryMapper mapper;
    
	public void insertTradeHistory(TradeHistoryEntity list){
	 
		mapper.insertTradeHistory(list);
		// list와 historyList를 비교하여 아래 mapper를 실행할 list에 세팅해준다.
	}
}
