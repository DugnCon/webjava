package main.java.dao;

import java.util.ArrayList;

public interface UserAccountInterface<T> {
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
	public String searchAcc(T t);
	public int searchId(T t);
	public ArrayList<T> selectAll();
	public T selectById(T t);
	public ArrayList<T> selectByCondition(String condition);
}
