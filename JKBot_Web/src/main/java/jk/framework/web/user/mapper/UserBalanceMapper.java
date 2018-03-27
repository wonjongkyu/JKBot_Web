package jk.framework.web.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import jk.framework.web.user.entity.UserBalanceEntity;

@Mapper
public interface UserBalanceMapper {
	
	public UserBalanceEntity selectOne(UserBalanceEntity entity);
	
	public void update(UserBalanceEntity entity);
}
