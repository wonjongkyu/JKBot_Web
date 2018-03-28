package jk.framework.web.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData.BithumbTickerEntity;
import jk.framework.web.user.entity.UserInfoEntity;
import jk.framework.web.user.mapper.UserInfoMapper;

@Service
public class UserInfoService {
	
	@Autowired
	UserInfoMapper mapper;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 추가
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insert
	 * @date : 2018. 3. 27.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 27.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param entity
	 */ 	
	public void insert(UserInfoEntity entity) {
		mapper.insert(entity);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 모든 사용자 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAll
	 * @date : 2018. 3. 27.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 27.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */ 	
	public List<UserInfoEntity> selectAll(){
		return mapper.selectAll();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 특정 사용자 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectOne
	 * @date : 2018. 3. 27.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 27.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param entity
	 * @return
	 */ 	
	public UserInfoEntity selectOne(UserInfoEntity entity){
		return mapper.selectOne(entity);
	}
	 
	/**
	 * <pre>
	 * 1. 개요 : 사용자 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : delete
	 * @date : 2018. 3. 27.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 27.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param entity
	 */ 	
	public void delete(UserInfoEntity entity){
		mapper.delete(entity);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : update
	 * @date : 2018. 3. 27.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 27.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param entity
	 */ 	
	public void update(UserInfoEntity entity) {
		mapper.update(entity);
	}
	
}
