package com.hfhj.akb.robot.dao.impl;

import com.hfhj.akb.robot.entity.RobotWx;
import com.hfhj.akb.starter.db.EntityUpdate;
import com.hfhj.akb.starter.db.QueryParam;
import com.hfhj.akb.starter.domain.Paginator;
import com.hfhj.akb.starter.domain.dto.PageList;
import com.hfhj.akb.starter.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("all")
public class BaseDaoImpl {

    private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;

    public List list(com.hfhj.akb.starter.db.Query query) {
        Query _query = null;
        if (query.isSql()) {
            _query = entityManager.createNativeQuery(query.querySql());
        } else {
            _query = entityManager.createQuery(query.querySql());
        }
        setQueryValue(query, _query, null);

        if (null != query.getLimit()) {
            _query.setMaxResults(query.getLimit());
        }
        if (null != query.getStart()) {
            _query.setFirstResult(query.getStart());
        }

        List list = _query.getResultList();

        if (list.size() > 50) {
            logger.info("===>" + list.size() + "=>" + query.querySql());
        }
        return list;
    }

    public PageList page(com.hfhj.akb.starter.db.Query query) {
        Query _query = null;
        Query countQuery = null;
        if (query.isSql()) {
            _query = entityManager.createNativeQuery(query.querySql());
            countQuery = entityManager.createNativeQuery(query.countSql());
        } else {
            _query = entityManager.createQuery(query.querySql());
            countQuery = entityManager.createQuery(query.countSql());
        }
        setQueryValue(query, _query, countQuery);
        return findPageData(_query, countQuery, query.getPageNum(),
                query.getLimit(), query);
    }


    public PageList list(com.hfhj.akb.starter.db.Query query,String baseSql,Class entityCls) {
        StringBuffer sb = new StringBuffer();
        sb.append(baseSql);
        query.base(sb.toString()).exeSql();
        PageList result = this.page(query);
        List robotWxList = EntityUtils.caseEntity(result.getData(),entityCls,false);
        result.setData(robotWxList);
        return result;
    }

    private PageList findPageData(Query query, Query countQuery,
                                  Integer pageNum, Integer pageSize,
                                  com.hfhj.akb.starter.db.Query usefulQuery) {
        Long totalCount = Long.parseLong(countQuery.getResultList().get(0)
                .toString());
        Paginator paginator = new Paginator(pageNum, pageSize,
                totalCount.intValue());
        PageList pageList = new PageList(paginator);

        if (totalCount != null && totalCount.intValue() > 0) {
            if (null == pageSize) {
                pageSize = Paginator.DEFAULT_PAGE_SIZE;
            }
            List list = query.setFirstResult(paginator.getOffset())
                    .setMaxResults(pageSize).getResultList();
            pageList.addAll(list);
        }
        return pageList;
    }

    public <T> T get(com.hfhj.akb.starter.db.Query query) {
        query.max(1);
        List list = list(query);
        if (null == list || list.size() == 0) {
            return null;
        }
        Object o = list.get(0);
        Class cls = o.getClass();
        return (T) list.get(0);
    }

    public <T> T get(com.hfhj.akb.starter.db.Query query,Class dtoCls) {
        query.max(1);
        List list = list(query);
        if (null == list || list.size() == 0) {
            return null;
        }
        return (T)EntityUtils.caseSingleEntity((Object[]) list.get(0),dtoCls);
        //return (T) list.get(0);
    }

    public Long count(com.hfhj.akb.starter.db.Query query) {
        Query _query = null;
        if (query.isSql()) {
            _query = entityManager.createNativeQuery(query.countSql());
        } else {
            _query = entityManager.createQuery(query.countSql());
        }
        setQueryValue(query, _query, null);

        List list = _query.getResultList();
        if (list.size() > 0) {
            return Long.parseLong(list.get(0) + "");
        }

        return 0l;
    }

    /**
     * 设置值的参数(TODO)
     *
     * @param values
     * @param params
     * @param query  查询1
     * @param query2 查询2(通常是查询总数时)
     * @author lin.qing
     * 2015年6月23日 上午9:06:24
     */
    public void setQueryValue(com.hfhj.akb.starter.db.Query bquery, Query query, Query query2) {
        List<QueryParam> params = bquery.getParams();
        List<Object> values = bquery.getValues();

        int index = 1;
        int countIndex = 1;
        if (null != values) {
            //先直接赋值
            for (int i = 1, size = values.size(); i <= size; i++) {
                query.setParameter(i, values.get(i - 1));
                if (null != query2 && index >= bquery.getCountsValuesBegin()) {
                    query2.setParameter(countIndex, values.get(countIndex));
                    countIndex++;
                }
                index++;
            }
        }

        for (int i = 0; i < params.size(); i++) {
            QueryParam param = params.get(i);
            Object value = param.getValue();

            if (com.hfhj.akb.starter.db.Query.isNotSetValue(param.getOper())) {
                continue;
            }

            if (com.hfhj.akb.starter.db.Query.Oper.LIKE == param.getOper()) {
                value = "%" + value + "%";
            } else if (com.hfhj.akb.starter.db.Query.Oper.LEFT_LIKE == param.getOper()) {
                value = value + "%";
            } else if (com.hfhj.akb.starter.db.Query.Oper.RIGHT_LIKE == param.getOper()) {
                value = "%" + value;
            }

            query.setParameter(index, value);
            if (null != query2 && index >= bquery.getCountsValuesBegin()) {
                query2.setParameter(countIndex, value);
                countIndex++;
            }
            ++index;
        }
    }

    /**
     * 执行sql语句
     *
     * @param sql
     * @param values
     */
    public void exeSqlUpdate(String sql, Object... values) {
        Query query = entityManager.createNativeQuery(sql);
        if (null != values) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }

        query.executeUpdate();
    }

    public void exeHqlUpdate(String sql, Object... values) {
        Query query = entityManager.createQuery(sql);
        if (null != values) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }

        query.executeUpdate();
    }

	/*public Object sum(com.hfhj.akb.starter.db.Query query) {
		Query _query = null;
		if(query.isSql()){
			_query = entityManager.createNativeQuery(query.sumSql());
		}else{
			_query = entityManager.createQuery(query.sumSql());
		}
		setQueryValue(query, _query, null);
		
		List list =  _query.getResultList();
		if (list.size() > 0) {
			if (null == list.get(0)) {
				return 0;
			}
			return list.get(0);
		}
		
		return 0l;
	}*/

    public void update(EntityUpdate eu) {
        int size = 0;
        List<QueryParam> updateList = eu.getUpdateList();
        size = updateList.size();
        List<QueryParam> conditionList = eu.getParams();
        if (null != conditionList) {
            size += conditionList.size();
        }
        Object[] valueArr = new Object[size];
        int index = 0;
        for (QueryParam param : updateList) {
            if (null == param.getValue()) {
                continue;
            }
            valueArr[index++] = param.getValue();
        }

        boolean ifHaveCondition = false;
        if (null != conditionList) {
            for (QueryParam param : conditionList) {
                if (com.hfhj.akb.starter.db.Query.isNotSetValue(param.getOper())) {
                    continue;
                }
                if (!ifHaveCondition && null != param.getValue()) {
                    ifHaveCondition = true;
                }
                valueArr[index++] = param.getValue();
            }
        }

        if (!ifHaveCondition) {
            //无查询条件的不允许更新或者删除，避免误操作.
            logger.error("更新/删除失败:【" + eu.getEntityName() + "】,未设置更新/删除条件");
            return;
        }
        if (eu.isSql()) {
            this.exeSqlUpdate(eu.toUpdateSql(), valueArr);
        } else {
            this.exeHqlUpdate(eu.toUpdateSql(), valueArr);
        }
    }

    /**
     * 设置值
     *
     * @param query
     * @param values
     */
    public void setParams(Query query, Object... values) {
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i + 1, values[i]);
        }
    }
}
