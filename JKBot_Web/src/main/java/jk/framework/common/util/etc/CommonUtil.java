package jk.framework.common.util.etc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData.BithumbTickerEntity;

public class CommonUtil {

	public static List<BithumbTickerEntity> BithumbTickerEntityToList(BithumbTickerResultData data) {
		List<BithumbTickerEntity> list = new ArrayList(); 
		Class<? extends Object> piClass = data.getClass();
		Method[] arrayPiMethod = piClass.getDeclaredMethods();
			
		for(Method methodPi : arrayPiMethod){
		    if(methodPi.getName().startsWith("get")) {
		        try {
		        	list.add( (BithumbTickerEntity) methodPi.invoke(data, new Object[]{}));
		        } catch (IllegalArgumentException e) {
		        } catch (IllegalAccessException e) {
		        } catch (InvocationTargetException e) {
		        }
		    }
		}
		return list;
    }
	
	
}
