package com.clody.springboot.coursmc.models.dto;

import java.io.Serializable;

import com.clody.springboot.coursmc.models.Category;

public class CategoryDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	public CategoryDto() {
	 
	}
	public CategoryDto(Category category) {
	id = category.getId();
	name = category.getName();
	
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
