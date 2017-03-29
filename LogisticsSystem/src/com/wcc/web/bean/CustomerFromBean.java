package com.wcc.web.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.STRING;

//import com.sun.org.apache.bcel.internal.generic.NEW;

public class CustomerFromBean {
	private String id;
	private String name;
	private String gender;
	private String birthday;
	private String cellphone;
	private String email;
	
	private String hobbies[];
	private String type;
	private String description;
	
	private Map<String, String> errors = new  HashMap<String, String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	 
	/**
	 *  验证用户输入的数据是否是正确的。
	 *  这里先省略一下。
	 */
	public boolean inilateData(){
		//return false;
		return errors.isEmpty();
	}
	

}
