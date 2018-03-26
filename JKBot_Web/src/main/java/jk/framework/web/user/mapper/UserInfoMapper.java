package jk.framework.web.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;
import jk.framework.web.user.entity.UserInfoEntity;

@Mapper
public interface UserInfoMapper {
	
	/*@Select("SELECT * FROM jk_coin_price WHERE bno = #{bno}")
	public List<BithumbTickerResultEntity> findByBno(@Param("bno") int bno); 
	
	public List<BithumbTickerResultEntity> findByBno2(int bno); */
	
	public void insert(UserInfoEntity entity);
	
	public List<UserInfoEntity> selectAll();
	
	public UserInfoEntity selectOne(UserInfoEntity entity);
	
	public void delete(UserInfoEntity entity);
	
	public void update(UserInfoEntity entity);
}
