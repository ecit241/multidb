package com.weimob.bs.multidb.dao.mysql.service;

import com.weimob.bs.multidb.dao.mysql.annotation.NeedCheck;
import com.weimob.bs.multidb.dao.mysql.annotation.UseClassDataSource;
import com.weimob.bs.multidb.dao.mysql.mapper.BaseMapper;
import com.weimob.bs.multidb.dao.mysql.model.BaseBean;
import com.weimob.bs.multidb.dao.utils.SpringBeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Adam on 2016/4/22.
 */
public abstract class BaseServiceImpl<T extends BaseBean> {

    private BaseMapper<T> baseMapper;

    @Autowired
    private SpringBeanUtils springBeanUtils;

    public void setSpringBeanUtils(SpringBeanUtils springBeanUtils) {
        this.springBeanUtils = springBeanUtils;
    }

    @Autowired
    public void setGeneric() {
        if (null == this.baseMapper) {
            this.baseMapper = springBeanUtils.getGenericImpl(BaseMapper.class, this.getClass());
        }
    }

    /**
     * 查询总数据数
     *
     * @return 总数据数
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public Integer selectCount() {
        return this.baseMapper.selectCount(null);
    }


    /**
     * 根据条件查询总数据数
     *
     * @param record 记录
     * @return 总数据数
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public Integer selectCountByWhere(T record) {
        return this.baseMapper.selectCount(record);
    }


    /**
     * 根据id查询数据
     *
     * @param id 主键
     * @return 记录
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public T selectByPrimaryKey(Serializable id) {
        return this.baseMapper.selectByPrimaryKey(id);
    }


    /**
     * 查询所有数据
     *
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public List<T> selectAll() {
        return this.baseMapper.selectAll();
    }


    /**
     * 根据条件查询一条数据
     * 查询结果有多条,将抛异常
     *
     * @param record 记录
     * @return 记录
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public T selectOne(T record) {
        return this.baseMapper.selectOne(record);
    }


    /**
     * 根据条件查询数据
     *
     * @param record 记录
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public List<T> selectListByWhere(T record) {
        return this.baseMapper.select(record);
    }


    /**
     * 新增数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    @NeedCheck
    public Integer insert(T record) {
        record.setCreateTime(new Date());
        return this.baseMapper.insert(record);
    }


    /**
     * 选择不为null的属性作为插入数据的字段，新增数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    @NeedCheck
    public Integer insertSelective(T record) {
        record.setCreateTime(new Date());
        return this.baseMapper.insertSelective(record);
    }


    /**
     * 更新数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    @NeedCheck
    public Integer update(T record) {
        record.setUpdateTime(new Date());
        return this.baseMapper.updateByPrimaryKey(record);
    }


    /**
     * 选择不为null的属性作为更新数据的字段
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    @NeedCheck
    public Integer updateSelective(T record) {
        record.setUpdateTime(new Date());
        return this.baseMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 根据id删除数据
     *
     * @param id 主键
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    public Integer deleteByPrimaryKey(Serializable id) {
        return this.baseMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据条件删除数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    public Integer deleteByWhere(T record) {
        return this.baseMapper.delete(record);
    }


    /**
     * 通过条件查询数据列表
     *
     * @param example 条件
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public List<T> selectByExample(Object example) {
        return this.baseMapper.selectByExample(example);
    }


    /**
     * 通过条件统计数据条数
     *
     * @param example 条件
     * @return 受影响的记录行数
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public Integer selectCountByExample(Object example) {
        return this.baseMapper.selectCountByExample(example);
    }


    /**
     * 通过条件更新数据
     *
     * @param example 条件
     * @param record  记录
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    @NeedCheck
    public Integer updateByExample(T record, Object example) {
        record.setUpdateTime(new Date());
        return this.baseMapper.updateByExample(record, example);
    }


    /**
     * 选择不为null的属性,通过条件更新数据
     *
     * @param example 条件
     * @param record  记录
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    @NeedCheck
    public Integer updateByExampleSelective(T record, Object example) {
        record.setUpdateTime(new Date());
        return this.baseMapper.updateByExampleSelective(record, example);
    }


    /**
     * 通过条件删除数据
     *
     * @param example 条件
     * @return 受影响的记录行数
     */
    @UseClassDataSource
    public Integer deleteByExample(Object example) {
        return this.baseMapper.deleteByExample(example);
    }


    /**
     * 分页查询数据
     *
     * @param record    记录
     * @param rowBounds 每页记录数
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return this.baseMapper.selectByRowBounds(record, rowBounds);
    }


    /**
     * 根据条件分页查询数据
     *
     * @param example   条件
     * @param rowBounds 每页记录数
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    @UseClassDataSource
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return this.baseMapper.selectByExampleAndRowBounds(example, rowBounds);
    }


    /**
     * 批量插入数据
     *
     * @param recordList 记录列表
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @UseClassDataSource
    @NeedCheck
    public Integer insertList(List<T> recordList) {
        for (T record : recordList) {
            record.setCreateTime(new Date());
        }
        return this.baseMapper.insertList(recordList);
    }
}
