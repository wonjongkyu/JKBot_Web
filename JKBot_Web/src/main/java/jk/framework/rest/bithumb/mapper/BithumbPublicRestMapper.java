package jk.framework.rest.bithumb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;

@Mapper
public interface BithumbPublicRestMapper {
	
	@Select("SELECT * FROM board WHERE bno = #{bno}")
	public List<BithumbTickerResultEntity> findByBno(@Param("bno") int bno); 
	
	public List<BithumbTickerResultEntity> findByBno2(int bno); 
	
	public List<BithumbTickerResultEntity> findAll();
	
	public void save(BithumbTickerResultEntity entity);
	
	public BithumbTickerResultEntity findOne(BithumbTickerResultEntity entity);
	
	public void delete(BithumbTickerResultEntity entity);
}
