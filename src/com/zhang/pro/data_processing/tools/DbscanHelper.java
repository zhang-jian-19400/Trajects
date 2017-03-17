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
	 * �ڵ�һ���н��о�������У�����ÿ��Ԫ�أ����Ե�ǰԪ��Ϊ�˶δ���һ����ķ�Ҷ�ӽڵ㡣
	 * �ڲ�ѭ���Ǳ����˶��������ߵľ��룬�������С�ھ�����ֵ����ô�öμ��뵽�˶ε����С�
	 * ����ڲ�ѭ��������ú˶�������ı������ڹ涨����ֵ����ô����˶ξͱ���עΪ�����Ρ���ӵ�indexnode.noise��
	 * ������ڸ���ֵ�Ļ�����������ӵ�indexnode.content�С�
	 */
	/*
	 * @param indextree ��ʾ������  floor��ʾ����ڼ��㣬kese��ʾ������ֵ��yibusinong��ʾ������ֵ��
	 * @return �ò�������ڵ�
	 */
	public void ITC_ITcluster(IndexTree indextree,int floor,float kese,int yibusinong){
		IndexNode indexnode = (IndexNode)indextree.getIndex().get(floor);		
		IndexNode NIndexnode = new IndexNode(kese,yibusinong);
		indextree.addIndexNode(NIndexnode);
		if(indexnode.getLevel()==0)
		{
			ArrayList<Node> leafnodes = indexnode.getContent();
		/*
		 * ��һ��ѭ��������ÿ���Σ�������һ����Ҷ�ӽڵ㣬�����ݾ��ൽ�ýڵ���
		 * �ڶ���ѭ�����ӵڶ��������ڵ����������ЩԪ�ء������������������ɾ����
		 */
			for(int i=0;i<indexnode.getNumber();i++)
			{
				NoLeafDefault noleaf =  new NoLeafDefault();
				LeafDefault leaf = (LeafDefault)indexnode.getContent().get(i); //�������ǿ��ת��Ϊ����
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
