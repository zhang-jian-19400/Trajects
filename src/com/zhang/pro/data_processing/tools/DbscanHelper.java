package com.zhang.pro.data_processing.tools;

import java.util.ArrayList;

import com.zhang.pro.data_processing.model.Segment;
import com.zhang.pro.indextree.IndexNode;
import com.zhang.pro.indextree.LeafDefault;
import com.zhang.pro.indextree.Node;

public class DbscanHelper {
	private IndexNode indexnode;
	
	public DbscanHelper(IndexNode indexnode){this.indexnode = indexnode;}
	public IndexNode ITC_ITcluster(){
		IndexNode indexnode = new IndexNode();
		if(indexnode.getLevel()==0)
		{ArrayList<Node> leafnodes = indexnode.getContent();
		/*
		 * ����ÿ���Σ�������һ����Ҷ�ӽڵ㣬�����ݾ��ൽ�ýڵ���
		 * �ӵڶ��������ڵ����������ЩԪ�ء������������������ɾ����
		 */
			for(int i=0;i<indexnode.getNumber();i++)
			{
				Segment segment= leafnodes.get(i).getSegment();
			}			
		}
		return indexnode;
	}
}
