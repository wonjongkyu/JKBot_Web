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
	
	public void insert(UserInfoEntity entity) {
		mapper.insert(entity);
	}
	
	public List<UserInfoEntity> selectAll(){
		return mapper.selectAll();
	}
	
	public UserInfoEntity selectOne(UserInfoEntity entity){
		return mapper.selectOne(entity);
	}
	 
	public void delete(UserInfoEntity entity){
		mapper.delete(entity);
	}
	
	public void update(UserInfoEntity entity) {
		mapper.update(entity);
	}
	
}
