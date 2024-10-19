package main.java.dao;

import java.util.ArrayList;

public interface AddBookInterface<T> {
	public int insert1(T t);
	public String search(T t);
	public int update(T t);
	public int Delete(T t);
	public ArrayList<T> selectAll();
	public T selectById(T t);
	public ArrayList<T> selectByCondition(String condition);
	public ArrayList<T> selectByCondition1(String condition);
}
