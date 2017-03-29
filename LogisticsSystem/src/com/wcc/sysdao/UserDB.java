package com.wcc.sysdao;

import java.util.ArrayList;
import java.util.List;

import com.wcc.daomain.User;

public class UserDB {
	private static List<User> users = new ArrayList<User>();
	static{
		users.add(new User("wcc", "123"));
		users.add(new User("大王", "123"));
	}
	public static User findUser(String username,String password){
		for(User u:users){
			if(username.equals(u.getUsername())&&password.equals(u.getPassword())){
				return u;
			}
		}
		return null;
	}
	public static User findUser(String username){
		for(User u:users){
			if(username.equals(u.getUsername())){
				return u;
			}
		}
		return null;
	}
}
