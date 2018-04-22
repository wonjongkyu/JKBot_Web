package jk.framework.web.price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jk.framework.web.admin.entity.PriceCompareEntity;
import jk.framework.web.price.mapper.PriceInfoMapper;

@Service
public class PriceService {
	
	// exchange apiKey
    @Value("${exchange.apiUrl}")
    private String apiUrl ;
	
    @Autowired
    PriceInfoMapper mapper;
    
    public List<PriceCompareEntity> selectAllCoinPrice(String coinSymbol){
    	List<PriceCompareEntity> list = mapper.selectAllCoinPrice(coinSymbol);
    	return list;
    }
}
