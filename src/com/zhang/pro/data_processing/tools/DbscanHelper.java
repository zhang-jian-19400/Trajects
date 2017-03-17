package com.zhang.pro.data_processing.tools;

import java.util.ArrayList;
import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.Segment;
import com.zhang.pro.indextree.IndexNode;
import com.zhang.pro.indextree.IndexTree;
import com.zhang.pro.indextree.LeafDefault;
import com.zhang.pro.indextree.NoLeafDefault;
import com.zhang.pro.indextree.Node;

public class DbscanHelper {
	DataProcessing process = new DataProcessing();
	private StructDistance structdistance = new StructDistance();
	public DbscanHelper(){}
	
	/*algorithm
	 * 在第一层中进行聚类操作中：遍历每个元素，便以当前元素为核段创建一个类的非叶子节点。
	 * 内层循环是遍历核段与其他边的距离，如果距离小于距离阈值，那么该段加入到核段的类中。
	 * 完成内层循环后，如果该核段类包含的边数少于规定的阈值，那么这个核段就被标注为噪声段。添加到indexnode.noise中
	 * 如果多于该阈值的话，则将数据添加到indexnode.content中。
	 */
	/*
	 * @param indextree 表示索引树  floor表示处理第几层，kese表示距离阈值，yibusinong表示数量阈值。
	 * @return 该层的索引节点
	 */
	public void ITC_ITcluster(IndexTree indextree,int floor,float kese,int yibusinong){
		IndexNode indexnode = (IndexNode)indextree.getIndex().get(floor);		
		IndexNode NIndexnode = new IndexNode(kese,yibusinong);
		indextree.addIndexNode(NIndexnode);
		if(indexnode.getLevel()==0)
		{
			ArrayList<Node> leafnodes = indexnode.getContent();
		/*
		 * 第一层循环，遍历每个段，并建立一个非叶子节点，将数据聚类到该节点中
		 * 第二个循环，从第二个索引节点出发访问这些元素。如果不符合条件，就删除。
		 */
			for(int i=0;i<indexnode.getNumber();i++)
			{
				NoLeafDefault noleaf =  new NoLeafDefault();
				LeafDefault leaf = (LeafDefault)indexnode.getContent().get(i); //父类可以强制转化为子类
				noleaf.setLevel(floor+1);
				noleaf.setCoreseg(leaf.getSegment());
				Segment segment1 = indexnode.getContent().get(i).getSegment();
				noleaf.getChildIds().add(leaf);
				for(int j =0;j<indexnode.getNumber();j++)
				{
					Segment segment2 = indexnode.getContent().get(j).getSegment();
					String segment1name = segment1.getParent_Trajectory();
					String segment2name = segment2.getParent_Trajectory();
					Vector<Vector<GeoModel>> segment = process.getSegmentsByName(segment1name, segment2name);					
					structdistance.setSegment(segment.get(0), segment.get(1));
					double distance = structdistance.getStructDistance(0.25,0.25,0.25,0.25);
					if (distance<NIndexnode.getKese()){
						noleaf.setCoreseg(segment1);
						noleaf.getChildIds().add(indexnode.getContent().get(j));
					}
				}
				if(noleaf.getChildIds().size()>=NIndexnode.getYibusinong())
				  NIndexnode.getContent().add(noleaf);
			}
		}
		return ;
	}
}
