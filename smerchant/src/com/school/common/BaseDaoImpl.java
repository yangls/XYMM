package com.school.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@PersistenceContext
	protected EntityManager em;
	
	//private Class<T> entityClass= GenericsUtils.getSuperClassGenricType(this.getClass());
	private Class<T> entityClass;
	
	
	
	public BaseDaoImpl() {
		super();
		Type type=this.getClass().getGenericSuperclass();
		ParameterizedType pt=(ParameterizedType) type;
		entityClass=(Class<T>)pt.getActualTypeArguments()[0];
	}

	/* (non-Javadoc)
	 * @see com.sf.common.BaseDao#delete(java.io.Serializable)
	 */
	public void delete(Serializable... ids) {
		for (Serializable id : ids) {
			em.remove(em.getReference(entityClass, id));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sf.common.BaseDao#find(java.io.Serializable)
	 */
	public T find(Serializable id) {
		return em.find(entityClass, id);
	}

	/* (non-Javadoc)
	 * @see com.sf.common.BaseDao#save(T)
	 */
	public void save(T entity) {
		em.persist(entity);
	}

	/* (non-Javadoc)
	 * @see com.sf.common.BaseDao#update(T)
	 */
	public void update(T entity) {
		em.merge(entity);
		em.flush();
	}

	/**
	 * 获取总条数
	 * @param string
	 * @param mapWhere
	 * @return
	 */
	public Long countDate(String string, Map<String, Object> mapWhere) {
		String sql = "select count(id) "+ string;
		Query query=em.createQuery(sql);
		for(Map.Entry<String, Object> map:mapWhere.entrySet()){
			query.setParameter(map.getKey(), map.getValue());
		}
		return (Long) query.getSingleResult();
	}
}
