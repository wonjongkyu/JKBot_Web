package jk.framework.rest.bithumb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData.BithumbTickerEntity;

@Mapper
public interface BithumbPrivateRestMapper {
	
	@Select("SELECT * FROM jk_coin_price WHERE bno = #{bno}")
	public List<BithumbTickerResultEntity> findByBno(@Param("bno") int bno); 
	
	public List<BithumbTickerResultEntity> findByBno2(int bno); 
	
	public List<BithumbTickerResultEntity> findAll();
	
	public void save(List<BithumbTickerEntity> list);
	
	public BithumbTickerResultEntity findOne(BithumbTickerResultEntity entity);
	
	public void delete(BithumbTickerResultEntity entity);
}
