package com.zhang.pro.indextree;

import java.util.ArrayList;
import java.util.HashMap;

public class IndexNode extends Node{

	
	int number;
	float kese; //距离阈值
	int yibusinong; //数量阈值
	int level;
	ArrayList<Node> Content = new ArrayList<Node>();
	ArrayList<Node> noiseNodes = new ArrayList<Node>();
	public IndexNode(float kese,int yibusinong){
		this.kese = kese;
		this.yibusinong = yibusinong;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
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
	
	public ArrayList<Node> getContent() {
		return Content;
	}
	public void setContent(ArrayList<Node> content) {
		Content = content;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public ArrayList<Node> getNoiseNodes() {
		return noiseNodes;
	}
	public void setNoiseNodes(ArrayList<Node> noiseNodes) {
		this.noiseNodes = noiseNodes;
	}
}
