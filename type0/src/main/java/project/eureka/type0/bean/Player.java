package project.eureka.type0.bean;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable{
	private int id;
	private String name;
	private String level;
	private List<Champion> ls;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<Champion> getLs() {
		return ls;
	}
	public void setLs(List<Champion> ls) {
		this.ls = ls;
	}
}
