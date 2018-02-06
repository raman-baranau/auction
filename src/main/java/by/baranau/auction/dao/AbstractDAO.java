package by.baranau.auction.dao;

import java.util.List;

import by.baranau.auction.data.Entity;

public abstract class AbstractDAO <K, T extends Entity> {

	public abstract List<T> findAll();
	public abstract T findEntityById(K id);
	public abstract boolean delete(K id);
	public abstract boolean delete(T entity);
	public abstract boolean create(T entity);
	public abstract T update(T entity);
}
