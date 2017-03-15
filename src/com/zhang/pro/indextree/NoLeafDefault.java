package com.zhang.pro.indextree;

import java.util.ArrayList;

import com.zhang.pro.data_processing.model.Segment;

public class NoLeafDefault extends NoLeafNode{
	NoLeafDefault ParentNode = null;
	ArrayList<Node> ChildIds= new ArrayList<Node> ();
	Segment Coreseg = new Segment(); 
	int Level;
	public NoLeafDefault(){	
//		this.ParentNode = null;
	}

	public ArrayList<Node> getChildIds() {
		return ChildIds;
	}
	public void setChildIds(ArrayList<Node> childIds) {
		ChildIds = childIds;
	}	
	public Segment getCoreIds() {
		return Coreseg;
	}
	public void setCoreIds(Segment Coreseg) {
		Coreseg = Coreseg;
	}

	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}	
}
