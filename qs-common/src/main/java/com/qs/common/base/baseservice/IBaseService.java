package com.qs.common.base.baseservice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;



public interface IBaseService<R,PK> {

	/**
	 * 增加数据记录
	 * @param record
	 * @return
	 */
	int insert(R record);

	/**
	 * 将record中包含的数据更新到数据库中
	 * @param record
	 * @return
	 */
    int insertSelective(R record);
	
    /**
     * 根据主键更新数据记录(record实体中值为null的字段不更新)
     * @param record
     * @return
     */
	int updateByPrimaryKeySelective(R record);

	/**
	 * 根据主键更新数据记录(record实体中值为null的字段也更新)
	 * @param record
	 * @return
	 */
    int updateByPrimaryKey(R record);

    /**
     * 根据主键删除数据记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(PK id);
    
    /**
     * 根据主键查询数据记录
     * @param id
     * @return
     */
    R selectByPrimaryKey(PK id);
    
    /**
     * 
     * @Title: queryListAll
     * @Description: 根据参数查询全部对象
     * @param parameter	查询参数map
     * @return	List<T>	返回查询的对象集合
     * @throws
     */
    public List<R> queryListAll(Map<String, Object> parameter);
    /**
     * 
     * @Title: queryListAll
     * @Description: 根据分页参数查询对象
     * @param parameter	查询参数map
     * @return	List<T>	返回查询的对象集合
     * @throws
     */
    public List<R> queryListByPage(Map<String, Object> parameter);
    /**
     * 
     * @Title: count
     * @Description: 根据参数查询对象总条数
     * @param parameter	查询参数map
     * @return	int	返回查询的对象总条数
     * @throws
     */
    int count(Map<String, Object> parameter);
    
   
	
}
