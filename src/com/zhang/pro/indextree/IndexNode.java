package com.zhang.pro.indextree;

import java.util.ArrayList;
import java.util.HashMap;

public class IndexNode extends Node{

	public IndexNode(){
	}
	int number;
	float kese;
	int yibusinong;
	HashMap<String,? extends Node> Content;
	public float getKese() {
		return kese;
	}
	public void setKese(float kese) {
		this.kese = kese;
	}
	public int getYibusinong() {
		return yibusinong;
	}
	public void setYibusinong(int yibusinong) {
		this.yibusinong = yibusinong;
	}
	public HashMap<String,? extends Node> getContent() {
		return Content;
	}
	public void setContent(HashMap<String,? extends Node> content) {
		Content = content;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
