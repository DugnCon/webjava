package main.java.dao;

import java.util.ArrayList;

public interface AddBookInterface<T> {
	public int insert1(T t);
	public int insert2(T t);
	public int update(T t);
	public int delete(T t);
	public ArrayList<T> selectAll();
	public T selectById(T t);
	public ArrayList<T> selectByCondition(String condition);
}