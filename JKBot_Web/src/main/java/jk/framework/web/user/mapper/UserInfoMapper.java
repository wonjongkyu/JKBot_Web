package jk.framework.web.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import jk.framework.web.user.entity.UserInfoEntity;

@Mapper
public interface UserInfoMapper {
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 추가
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insert
	 * @date : 2018. 3. 29.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 29.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param entity
	 * @throws DataAccessException
	 */ 	
	public void insert(UserInfoEntity entity) throws DataAccessException;
	
	/**
	 * <pre>
	 * 1. 개요 : 모든 사용자 정보 가져오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getAllList
	 * @date : 2018. 3. 29.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 29.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws DataAccessException
	 */ 	
	public List<UserInfoEntity> getAllList() throws DataAccessException;
	

	/**
	 * <pre>
	 * 1. 개요 : 특정 사용자 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getUserInfo
	 * @date : 2018. 3. 29.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 29.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */ 	
	public UserInfoEntity getUserInfo(UserInfoEntity entity) throws DataAccessException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : delete
	 * @date : 2018. 3. 29.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 29.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param entity
	 * @throws DataAccessException
	 */ 	
	public void delete(UserInfoEntity entity) throws DataAccessException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateUserInfo
	 * @date : 2018. 3. 29.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 29.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param entity
	 * @throws DataAccessException
	 */ 	
	public void updateUserInfo(UserInfoEntity entity) throws DataAccessException;
}
