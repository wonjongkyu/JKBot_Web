package jk.framework.rest.bithumb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jk.framework.common.util.etc.CommonUtil;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData.BithumbTickerEntity;
import jk.framework.rest.bithumb.mapper.BithumbPrivateRestMapper;
import jk.framework.rest.bithumb.mapper.BithumbPublicRestMapper;

@Service
public class BithumbPublicRestService {
	
	@Autowired
	BithumbPublicRestMapper mapper;
	
	public List<BithumbTickerResultEntity> findByBno(int bno){
		return mapper.findByBno(bno);
	}
	
	public List<BithumbTickerResultEntity> findByBno2(int bno){
		return mapper.findByBno2(bno);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : findAll
	 * @date : 2018. 3. 21.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 21.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param bno
	 * @return
	 */ 	
	public List<BithumbTickerResultEntity> findAll(){
		return mapper.findAll();
	}
	
	public void save(BithumbTickerResultEntity entity){
		if(entity != null) {
			BithumbTickerResultData data = entity.getData();
			List<BithumbTickerEntity> list = CommonUtil.BithumbTickerEntityToList(data);
			if(list != null) {
				mapper.save(list);
			}else {
				
			}
		}
	}
	
	public BithumbTickerResultEntity findOne(BithumbTickerResultEntity entity){
		return mapper.findOne(entity);
	}
	
	public void delete(BithumbTickerResultEntity entity){
		mapper.delete(entity);
	}
}
