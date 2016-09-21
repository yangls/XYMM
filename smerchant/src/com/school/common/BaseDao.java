package com.school.common;

import java.io.Serializable;

public interface BaseDao<T> {

	public abstract void delete(Serializable... ids);

	public abstract T find(Serializable id);

	public abstract void save(T entity);

	public abstract void update(T entity);

}