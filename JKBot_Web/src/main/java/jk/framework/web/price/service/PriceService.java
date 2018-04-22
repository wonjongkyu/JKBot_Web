package jk.framework.web.price.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("param", coinSymbol);
    	List<PriceCompareEntity> list = mapper.selectAllCoinPrice(parameters);
    	return list;
    }
}
