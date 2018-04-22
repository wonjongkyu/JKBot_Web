package jk.framework.web.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import jk.framework.web.admin.entity.ExchangeCoinPriceEntity;
import jk.framework.web.admin.entity.PriceCompareEntity;
import jk.framework.web.admin.entity.PriceExchangeInfoEntity;

@Mapper
public interface ExchangeInfoMapper {
	
	public List<PriceExchangeInfoEntity> getAllExchangeInfo(PriceExchangeInfoEntity entity) throws DataAccessException;
	
	/**
	 * <pre>
	 * 1. 개요 : 10분마다 코인 가격 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateCoinPriceInfo
	 * @date : 2018. 4. 22.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 22.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 * @throws DataAccessException
	 */ 	
	public void updateCoinPriceInfo(List<ExchangeCoinPriceEntity> list) throws DataAccessException;
	
	/**
	 * <pre>
	 * 1. 개요 : BTC 코인 가격 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateCoinPrice
	 * @date : 2018. 4. 22.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 22.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 * @throws DataAccessException
	 */ 	
	public void updateBtcCoinPrice(List<PriceCompareEntity> list) throws DataAccessException;
	
	/**
	 * <pre>
	 * 1. 개요 : BTC 코인 가격 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateCoinPrice
	 * @date : 2018. 4. 22.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 22.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 * @throws DataAccessException
	 */ 	
	public void updateOneBtcCoinPrice(PriceCompareEntity list) throws DataAccessException;
	
	/**
	 * <pre>
	 * 1. 개요 : USDT 코인 가격 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateCoinPrice
	 * @date : 2018. 4. 22.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 22.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 * @throws DataAccessException
	 */ 	
	public void updateUsdtCoinPrice(List<PriceCompareEntity> list) throws DataAccessException;
 
}

