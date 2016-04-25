package com.weimob.bs.multidb.mysql.facade;

import com.weimob.bs.multidb.mysql.model.BaseBean;
import com.weimob.bs.multidb.mysql.service.BaseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adam on 2016/4/22.
 */
public abstract class BaseFacadeService<T extends BaseBean> {

    public abstract BaseService<T> getService();

    /**
     * 查询总数据数
     *
     * @return 总数据数
     */
    @Transactional(readOnly = true)
    public Integer selectCount() {
        return this.getService().selectCount();
    }


    /**
     * 根据条件查询总数据数
     *
     * @param record 记录
     * @return 总数据数
     */
    @Transactional(readOnly = true)
    public Integer selectCountByWhere(T record) {
        return this.getService().selectCountByWhere(record);
    }


    /**
     * 根据id查询数据
     *
     * @param id 主键
     * @return 记录
     */
    @Transactional(readOnly = true)
    public T selectByPrimaryKey(Serializable id) {
        return this.getService().selectByPrimaryKey(id);
    }


    /**
     * 查询所有数据
     *
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    public List<T> selectAll() {
        return this.getService().selectAll();
    }


    /**
     * 根据条件查询一条数据
     * 查询结果有多条,将抛异常
     *
     * @param record 记录
     * @return 记录
     */
    @Transactional(readOnly = true)
    public T selectOne(T record) {
        return this.getService().selectOne(record);
    }


    /**
     * 根据条件查询数据
     *
     * @param record 记录
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    public List<T> selectListByWhere(T record) {
        return this.getService().selectListByWhere(record);
    }


    /**
     * 新增数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insert(T record) {
        return this.getService().insert(record);
    }


    /**
     * 选择不为null的属性作为插入数据的字段，新增数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertSelective(T record) {
        return this.getService().insertSelective(record);
    }


    /**
     * 更新数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer update(T record) {
        return this.getService().update(record);
    }


    /**
     * 选择不为null的属性作为更新数据的字段
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateSelective(T record) {
        return this.getService().updateSelective(record);
    }


    /**
     * 根据id删除数据
     *
     * @param id 主键
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteByPrimaryKey(Serializable id) {
        return this.getService().deleteByPrimaryKey(id);
    }


    /**
     * 根据条件删除数据
     *
     * @param record 记录
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteByWhere(T record) {
        return this.getService().deleteByWhere(record);
    }


    /**
     * 通过条件查询数据列表
     *
     * @param example 条件
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    public List<T> selectByExample(Object example) {
        return this.getService().selectByExample(example);
    }


    /**
     * 通过条件统计数据条数
     *
     * @param example 条件
     * @return 受影响的记录行数
     */
    @Transactional(readOnly = true)
    public Integer selectCountByExample(Object example) {
        return this.getService().selectCountByExample(example);
    }


    /**
     * 通过条件更新数据
     *
     * @param example 条件
     * @param record  记录
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateByExample(T record, Object example) {
        return this.getService().updateByExample(record, example);
    }


    /**
     * 选择不为null的属性,通过条件更新数据
     *
     * @param example 条件
     * @param record  记录
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateByExampleSelective(T record, Object example) {
        return this.getService().updateByExampleSelective(record, example);
    }


    /**
     * 通过条件删除数据
     *
     * @param example 条件
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteByExample(Object example) {
        return this.getService().deleteByExample(example);
    }


    /**
     * 分页查询数据
     *
     * @param record    记录
     * @param rowBounds 每页记录数
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return this.getService().selectByRowBounds(record, rowBounds);
    }


    /**
     * 根据条件分页查询数据
     *
     * @param example   条件
     * @param rowBounds 每页记录数
     * @return 记录列表
     */
    @Transactional(readOnly = true)
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return this.getService().selectByExampleAndRowBounds(example, rowBounds);
    }


    /**
     * 批量插入数据
     *
     * @param recordList 记录列表
     * @return 受影响的记录行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertList(List<T> recordList) {
        return this.getService().insertList(recordList);
    }
}
