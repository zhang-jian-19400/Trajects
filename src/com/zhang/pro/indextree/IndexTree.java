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
		��0�������ڵ���Ҫ��ָ��Ҷ�ӽڵ㣬
	 */
	public void createLeaf(){
		IndexNode node = this.index.get(0);
		HashMap<String,? extends Node> Content = new HashMap<String,LeafDefault>();
		// ��ȡ�ļ��õ��켣�Σ�������hash����.
		
		node.setContent(Content);
	}
}
