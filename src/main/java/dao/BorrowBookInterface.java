package main.java.dao;

import java.util.ArrayList;

public interface BorrowBookInterface<T> {
	public int insert(T t);
	public String search(T t);
	public int update(T t);
	public int Delete(T t);
	public ArrayList<T> selectAll();
	public T selectById(T t);
	public ArrayList<T> selectByCondition(String condition);
}
