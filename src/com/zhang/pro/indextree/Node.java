package com.zhang.pro.indextree;

import java.util.ArrayList;

import com.zhang.pro.data_processing.model.Segment;

public abstract class Node {  
	public Segment getSegment(){
		return new Segment();
	}
	public ArrayList<Node> getContent() {
		return new ArrayList();
	}
}
