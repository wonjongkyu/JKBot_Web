package jk.framework.web.price.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import jk.framework.web.price.entity.PriceExchangeInfoEntity;

@Mapper
public interface ExchangeInfoMapper {
	
	public List<PriceExchangeInfoEntity> getAllExchangeInfo(PriceExchangeInfoEntity entity) throws DataAccessException;
 
}
