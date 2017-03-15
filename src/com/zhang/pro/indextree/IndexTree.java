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
		��0�������ڵ���Ҫ��ָ��Ҷ�ӽڵ㣬
	 */
	public void createLeaf(DataSet dataset){
		IndexNode node = new IndexNode();		
		this.index.add(node);
		//�����еĹ켣���ն�Ϊ��λ���뵽Ҷ�ӽڵ��С�
			for(PeoDataModel people : dataset.getPeople()){
				if(people.getContent()!=null||people.getContent().size()!=0)
				for(String name:people.getContent().keySet()){
					Vector<GeoModel> trajectory = people.getContent().get(name);
					if(trajectory.size()!=0||trajectory!=null){
						ArrayList<Segment> segments = dataprocesser.divideSegments(name, trajectory, 45);					
						for(Segment segment : segments){	
							LeafDefault leafdefault = new LeafDefault();
							leafdefault.setSegment(segment);
							node.getContent().add(leafdefault); //������ֵΪnull�ǿ��еģ�ֻҪ��ȥ�������ǵ���Ӧ�Ķ���ռ伴�ɡ�
							node.setNumber(node.getContent().size()+1);
						}
					}
				}
			}
			node.setLevel(0);
	}
}
