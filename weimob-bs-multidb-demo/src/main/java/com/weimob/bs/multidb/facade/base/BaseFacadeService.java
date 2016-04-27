package com.weimob.bs.multidb.facade.base;

import com.weimob.bs.multidb.dao.mysql.mapper.BaseMapper;
import com.weimob.bs.multidb.dao.mysql.model.BaseBean;
import com.weimob.bs.multidb.dao.mysql.service.BaseServiceImpl;
import com.weimob.bs.multidb.dao.utils.SpringBeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adam on 2016/4/22.
 */
public abstract class BaseFacadeService<T extends BaseBean> {

    private BaseServiceImpl<T> baseService;

    @Autowired
    private SpringBeanUtils springBeanUtils;

    public void setSpringBeanUtils(SpringBeanUtils springBeanUtils) {
        this.springBeanUtils = springBeanUtils;
    }

    @Autowired
    public void setGeneric() {
        if (null == this.baseService) {
            this.baseService = springBeanUtils.getGenericImpl(BaseServiceImpl.class, this.getClass());
        }
    }

    /**
     * 查询总数据数
     *
     * @return 总数据数
     */
    public Integer selectCount() {
        return this.baseService.selectCount();
    }


    /**
     * 根据条件查询总数据数
     *
     * @param record 记录
     * @return 总数据数
     */
    public Integer selectCountByWhere(T record) {
        return this.baseService.selectCountByWhere(record);
    }


    /**
     * 根据id查询数据
     *
     * @param id 主键
     * @return 记录
     */
    public T selectByPrimaryKey(Serializable id) {
        return this.baseService.selectByPrimaryKey(id);
    }


    /**
     * 查询所有数据
     *
     * @return 记录列表
     */
    public List<T> selectAll() {
        return this.baseService.selectAll();
    }


    /**
     * 根据条件查询一条数据
     * 查询结果有多条,将抛异常
     *
     * @param record 记录
     * @return 记录
     */
    public T selectOne(T record) {
        return this.baseService.selectOne(record);
    }


    /**
     * 根据条件查询数据
     *
     * @param record 记录
     * @return 记录列表
     */
    public List<T> selectListByWhere(T record) {
        return this.baseService.selectListByWhere(record);
    }


    /**
     * 新增数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer insert(T record) {
        return this.baseService.insert(record);
    }


    /**
     * 选择不为null的属性作为插入数据的字段，新增数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer insertSelective(T record) {
        return this.baseService.insertSelective(record);
    }


    /**
     * 更新数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer update(T record) {
        return this.baseService.update(record);
    }


    /**
     * 选择不为null的属性作为更新数据的字段
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer updateSelective(T record) {
        return this.baseService.updateSelective(record);
    }


    /**
     * 根据id删除数据
     *
     * @param id 主键
     * @return 受影响的记录行数
     */
    public Integer deleteByPrimaryKey(Serializable id) {
        return this.baseService.deleteByPrimaryKey(id);
    }


    /**
     * 根据条件删除数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    public Integer deleteByWhere(T record) {
        return this.baseService.deleteByWhere(record);
    }


    /**
     * 通过条件查询数据列表
     *
     * @param example 条件
     * @return 记录列表
     */
    public List<T> selectByExample(Object example) {
        return this.baseService.selectByExample(example);
    }


    /**
     * 通过条件统计数据条数
     *
     * @param example 条件
     * @return 受影响的记录行数
     */
    public Integer selectCountByExample(Object example) {
        return this.baseService.selectCountByExample(example);
    }


    /**
     * 通过条件更新数据
     *
     * @param example 条件
     * @param record  记录
     * @return 受影响的记录行数
     */
    public Integer updateByExample(T record, Object example) {
        return this.baseService.updateByExample(record, example);
    }


    /**
     * 选择不为null的属性,通过条件更新数据
     *
     * @param example 条件
     * @param record  记录
     * @return 受影响的记录行数
     */
    public Integer updateByExampleSelective(T record, Object example) {
        return this.baseService.updateByExampleSelective(record, example);
    }


    /**
     * 通过条件删除数据
     *
     * @param example 条件
     * @return 受影响的记录行数
     */
    public Integer deleteByExample(Object example) {
        return this.baseService.deleteByExample(example);
    }


    /**
     * 分页查询数据
     *
     * @param record    记录
     * @param rowBounds 每页记录数
     * @return 记录列表
     */
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return this.baseService.selectByRowBounds(record, rowBounds);
    }


    /**
     * 根据条件分页查询数据
     *
     * @param example   条件
     * @param rowBounds 每页记录数
     * @return 记录列表
     */
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return this.baseService.selectByExampleAndRowBounds(example, rowBounds);
    }


    /**
     * 批量插入数据
     *
     * @param recordList 记录列表
     * @return 受影响的记录行数
     */
    public Integer insertList(List<T> recordList) {
        return this.baseService.insertList(recordList);
    }
}
