package com.weimob.bs.multidb.mysql.service;

import com.weimob.bs.multidb.mysql.mapper.BaseMapper;
import com.weimob.bs.multidb.mysql.model.BaseBean;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Adam on 2016/4/22.
 */
public abstract class BaseService<T extends BaseBean> {

    public abstract BaseMapper<T> getMapper();

    /**
     * 查询总数据数
     *
     * @return 总数据数
     */
    public Integer selectCount() {
        return this.getMapper().selectCount(null);
    }


    /**
     * 根据条件查询总数据数
     *
     * @param record 记录
     * @return 总数据数
     */
    public Integer selectCountByWhere(T record) {
        return this.getMapper().selectCount(record);
    }


    /**
     * 根据id查询数据
     *
     * @param id 主键
     * @return 记录
     */
    public T selectByPrimaryKey(Serializable id) {
        return this.getMapper().selectByPrimaryKey(id);
    }


    /**
     * 查询所有数据
     *
     * @return 记录列表
     */
    public List<T> selectAll() {
        return this.getMapper().selectAll();
    }


    /**
     * 根据条件查询一条数据
     * 查询结果有多条,将抛异常
     *
     * @param record 记录
     * @return 记录
     */
    public T selectOne(T record) {
        return this.getMapper().selectOne(record);
    }


    /**
     * 根据条件查询数据
     *
     * @param record 记录
     * @return 记录列表
     */
    public List<T> selectListByWhere(T record) {
        return this.getMapper().select(record);
    }


    /**
     * 新增数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer insert(T record) {
        record.setCreateTime(new Date());
        return this.getMapper().insert(record);
    }


    /**
     * 选择不为null的属性作为插入数据的字段，新增数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer insertSelective(T record) {
        record.setCreateTime(new Date());
        return this.getMapper().insertSelective(record);
    }


    /**
     * 更新数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer update(T record) {
        record.setUpdateTime(new Date());
        return this.getMapper().updateByPrimaryKey(record);
    }


    /**
     * 选择不为null的属性作为更新数据的字段
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer updateSelective(T record) {
        record.setUpdateTime(new Date());
        return this.getMapper().updateByPrimaryKeySelective(record);
    }


    /**
     * 根据id删除数据
     *
     * @param id 主键
     * @return 受影响的记录行数
     */
    public Integer deleteByPrimaryKey(Serializable id) {
        return this.getMapper().deleteByPrimaryKey(id);
    }


    /**
     * 根据条件删除数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer deleteByWhere(T record) {
        return this.getMapper().delete(record);
    }


    /**
     * 通过条件查询数据列表
     *
     * @param example 条件
     * @return 记录列表
     */
    public List<T> selectByExample(Object example) {
        return this.getMapper().selectByExample(example);
    }


    /**
     * 通过条件统计数据条数
     *
     * @param example 条件
     * @return 受影响的记录行数
     */
    public Integer selectCountByExample(Object example) {
        return this.getMapper().selectCountByExample(example);
    }


    /**
     * 通过条件更新数据
     *
     * @param example 条件
     * @param record  记录
     * @return 受影响的记录行数
     */
    public Integer updateByExample(T record, Object example) {
        record.setUpdateTime(new Date());
        return this.getMapper().updateByExample(record, example);
    }


    /**
     * 选择不为null的属性,通过条件更新数据
     *
     * @param example 条件
     * @param record  记录
     * @return 受影响的记录行数
     */
    public Integer updateByExampleSelective(T record, Object example) {
        record.setUpdateTime(new Date());
        return this.getMapper().updateByExampleSelective(record, example);
    }


    /**
     * 通过条件删除数据
     *
     * @param example 条件
     * @return 受影响的记录行数
     */
    public Integer deleteByExample(Object example) {
        return this.getMapper().deleteByExample(example);
    }


    /**
     * 分页查询数据
     *
     * @param record    记录
     * @param rowBounds 每页记录数
     * @return 记录列表
     */
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return this.getMapper().selectByRowBounds(record, rowBounds);
    }


    /**
     * 根据条件分页查询数据
     *
     * @param example   条件
     * @param rowBounds 每页记录数
     * @return 记录列表
     */
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return this.getMapper().selectByExampleAndRowBounds(example, rowBounds);
    }


    /**
     * 批量插入数据
     *
     * @param recordList 记录列表
     * @return 受影响的记录行数
     */
    public Integer insertList(List<T> recordList) {
        for (T record : recordList) {
            record.setCreateTime(new Date());
        }
        return this.getMapper().insertList(recordList);
    }
}
