package com.zhang.pro.recommendation;

import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.DataSet;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;
import com.zhang.pro.data_processing.tools.FileProcess;
import com.zhang.pro.indextree.IndexTree;

public class Test {
	/*
	 * ��ȡ�켣���ݣ������ݴ��뵽findactivity���С�
	 */
	public static void main(String args[]){
		DataSet dataset = new DataSet();
		FileProcess fileprocess = new FileProcess();
		DataProcessing dataprocessobj = new DataProcessing();
		PeoDataModel peodatamodel = new PeoDataModel();
		int n=0;
		String filename = "D:/ѧϰ����/���й켣����/Geolife Trajectories 1.3/Geolife Trajectories 1.3";
		try {

			//���ļ����У����ڸø��˵��ļ����֣�����ŵ���������
			 dataprocessobj.traverseAllPlt(peodatamodel,filename,3,1);
			 dataset =dataprocessobj.getDataset();
			dataset.getPeople().addElement((PeoDataModel)peodatamodel.clone());
			int i=0,j=0;Vector <GeoModel> obj1 = new Vector();
			//������Щ�˶�����������ļ�����ȡplt�ļ��е����ݣ���ŵ����˵Ĺ켣�������У����ս���洢��dataset�С�
			for(PeoDataModel model:dataset.getPeople()){
				i++;//Ŀǰֻ�Ե�һ���˵Ĺ켣���з���
				if(i<=10){
					System.out.println(model.getName());
				//�������ÿһ��plt�ļ�
					for(String str:model.getContent().keySet())
						{
						Vector <GeoModel> obj=fileprocess.readGeoPlt(str);
						obj1 = obj;
						//���µĸ��ºõ�<string��trajectory>�������е�����,�м��Ϊ����ʵ�������ǿ�
						model.getContent().replace(str,new Vector<GeoModel>(), obj);
						dataprocessobj.computeTurnAngle(obj);
						}					
					System.out.println(model.getContent().size());
				}
				}
			Recommentdation recommander = new Recommentdation();
			//Ѱ�ҵ���һ���˵Ĺ켣����
			PeopleActivity peopleactivity =recommander.findPeoActivity(dataset.getPeople().get(0));
			System.out.println(peopleactivity.getActivities().size());
			System.out.println();
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	
}
