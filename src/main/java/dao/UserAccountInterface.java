package main.java.dao;

import java.util.ArrayList;

public interface UserAccountInterface<T> {
	public int insertSign(T t);
	public int insertLog(T t);
	public int insertLock(T t);
	public int update(T t);
	public int delete(T t);
	public String searchAcc(T t);
	public int searchId(T t);
	public ArrayList<T> selectAll();
	public T selectById(T t);
	public T selectByUser(String condition);
	public ArrayList<T> selectByCondition(String condition);
	public ArrayList<T> selectByCondition1(String condition);
}
