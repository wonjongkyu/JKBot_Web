package jk.framework.web.admin.mapper;

import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import jk.framework.web.admin.entity.ExchangeCoinPriceEntity;
import jk.framework.web.admin.entity.KimpEntity;
import jk.framework.web.admin.entity.PriceCompareEntity;
import jk.framework.web.admin.entity.PriceExchangeInfoEntity;
import jk.framework.web.admin.entity.PriceHistoryEntity;

@Mapper
public interface AdminMapper {
	
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
 
	/**
	 * <pre>
	 * 1. 개요 : 가격 히스토리 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertPriceHistory
	 * @date : 2018. 4. 28.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 28.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 * @throws DataAccessException
	 */ 	
	public void insertPriceHistory(List<PriceCompareEntity> list) throws DataAccessException;
	
	
	/**
	 * <pre>
	 * 1. 개요 : 히스토리 테이블에서 이전 최종 가격 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getPriceHistory
	 * @date : 2018. 4. 28.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 28.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 * @return
	 * @throws DataAccessException
	 */ 	
	public List<PriceHistoryEntity> getAllPriceHistory() throws DataAccessException;
	
	
	/**
	 * <pre>
	 * 1. 개요 : 코인별 가중치를 계산하여 리턴
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getPriceHistory
	 * @date : 2018. 4. 28.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 28.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws DataAccessException
	 */ 	
	public List<PriceCompareEntity> getPriceHistory(List<PriceCompareEntity> list) throws DataAccessException;
	
	public PriceCompareEntity getOnePriceHistory(PriceCompareEntity entity) throws DataAccessException;
	
	/**
	 * <pre>
	 * 1. 개요 : 1시간마다 히스토리 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deletePriceHistory
	 * @date : 2018. 4. 28.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 28.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @throws DataAccessException
	 */ 	
	public void deletePriceHistory(HashSet<String> coinList) throws DataAccessException;
	
	public KimpEntity getKimp() throws DataAccessException;
}

