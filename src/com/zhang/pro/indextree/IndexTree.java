package com.zhang.pro.indextree;

import java.util.ArrayList;
import java.util.HashMap;

public class IndexTree {
	
	ArrayList<IndexNode> index;
	
	public IndexTree(){}
	public IndexTree(IndexNode node){
		this.index.add(node);
	}
	public ArrayList<IndexNode> getIndex() {
		return index;
	}
	public void setIndex(ArrayList<IndexNode> index) {
		this.index = index;
	}
	
	public void CreateCluster(){
		
	}
	public void addIndexNode (IndexNode node)
	{
		this.index.add(node);
	}
	/*
	 * 
		第0个索引节点主要是指向叶子节点，
	 */
	public void createLeaf(){
		IndexNode node = this.index.get(0);
		HashMap<String,? extends Node> Content = new HashMap<String,LeafDefault>();
		// 读取文件得到轨迹段，并存入hash表中.
		
		node.setContent(Content);
	}
}
