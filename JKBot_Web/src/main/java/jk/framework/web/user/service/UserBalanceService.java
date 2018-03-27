package jk.framework.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jk.framework.web.user.entity.UserBalanceEntity;
import jk.framework.web.user.mapper.UserBalanceMapper;

@Service
public class UserBalanceService {
	
	@Autowired
	UserBalanceMapper mapper;
	
	public UserBalanceEntity selectOne(UserBalanceEntity entity){
		return mapper.selectOne(entity);
	}
	 
	public void update(UserBalanceEntity entity) {
		mapper.update(entity);
	}
	
}
