package com.zhang.pro.indextree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.DataSet;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;
import com.zhang.pro.data_processing.model.Segment;

public class IndexTree {
	DataProcessing dataprocesser = new DataProcessing();
	ArrayList<Node> index = new ArrayList<Node>();
	ArrayList<NoLeafNode> noleafnodes;
	ArrayList<LeafNode> leafnodes;
	
	public IndexTree(){}

	public ArrayList<? extends Node> getIndex() {
		return index;
	}
	public void CreateCluster(){
		
	}
	public void addIndexNode (Node node)
	{
		this.index.add(node);
	}
	/*
	 * 
		第0个索引节点主要是指向叶子节点，
	 */
	public void createLeaf(DataSet dataset){
		IndexNode node = new IndexNode();		
		this.index.add(node);
		//将所有的轨迹按照段为单位放入到叶子节点中。
			for(PeoDataModel people : dataset.getPeople()){
				if(people.getContent()!=null||people.getContent().size()!=0)
				for(String name:people.getContent().keySet()){
					Vector<GeoModel> trajectory = people.getContent().get(name);
					if(trajectory.size()!=0||trajectory!=null){
						ArrayList<Segment> segments = dataprocesser.divideSegments(name, trajectory, 45);					
						for(Segment segment : segments){	
							LeafDefault leafdefault = new LeafDefault();
							leafdefault.setSegment(segment);
							node.getContent().add(leafdefault); //给对象赋值为null是可行的，只要不去访问他们的相应的对象空间即可。
							node.setNumber(node.getContent().size()+1);
						}
					}
				}
			}
			node.setLevel(0);
	}
}
