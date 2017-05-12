package com.appricots.intq.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, ID extends Serializable> {
	ID create (T element);
	void delete(T element);
	void update(T element);
	T get(ID id);
	List<T> getAll(int maxLimit);
	
}
