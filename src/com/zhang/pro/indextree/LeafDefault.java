package com.zhang.pro.indextree;

import java.util.ArrayList;

import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.Segment;
/*
 * @ ParentNode ָ�����ڵ㣬Contentָ���ù켣�ΰ����Ĺ켣������ 
 */
public class LeafDefault extends LeafNode{
	NoLeafNode ParentNode = new NoLeafDefault();
	Segment Content = new Segment();

	public NoLeafNode getParent() {
		return ParentNode;
	}

	public void setParent(NoLeafNode parent) {
		ParentNode = parent;
	}

	public Segment getSegment() {
		return Content;
	}

	public void setSegment(Segment content) {
		Content = content;
	}
	
}
