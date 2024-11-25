package main.java.dao;

import java.util.ArrayList;

public interface BorrowReturnInterface<T> {
	public int insert(T t);
	public int search(T t, String condition);
	public int update(T t);
	public int Delete(String condition);
	public int DeleteBookCode(String condition);
	public ArrayList<T> selectAll();
	public T selectById(T t);
	public ArrayList<T> selectByCondition(String condition);
	public ArrayList<T> selectByCondition1(String condition);
	public ArrayList<T> selectByCondition2(String condition);
}
