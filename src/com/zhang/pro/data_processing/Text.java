package com.zhang.pro.data_processing;

import java.util.Vector;

import com.zhang.pro.data_processing.model.DataSet;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;
import com.zhang.pro.data_processing.tools.FileProcess;

public class Text {
	public static void main(String args[]){
		DataSet dataset = new DataSet();
		FileProcess fileprocess = new FileProcess();
		DataProcessing dataprocessobj = new DataProcessing();
		PeoDataModel peodatamodel = new PeoDataModel();
		int n=0;
		String filename = "D:/ѧϰ����/���й켣����/Geolife Trajectories 1.3/Geolife Trajectories 1.3";
		try {

			//���ļ����У����ڸø��˵��ļ����֣�����ŵ���������
			dataprocessobj.traverseAllPlt(dataset,peodatamodel,filename,3,1);
			dataset.getPeople().addElement((PeoDataModel)peodatamodel.clone());
			int i=0,j=0;
			//������Щ�˶�����������ļ�����ȡplt�ļ��е����ݣ���ŵ����˵Ĺ켣�������У����ս���洢��dataset�С�
			for(PeoDataModel model:dataset.getPeople()){
				i++;//Ŀǰֻ�Ե�һ���˵Ĺ켣���з���
				if(i<=1){
					System.out.println(model.getName());
				//�������ÿһ��plt�ļ�
					for(String str:model.getContent().keySet())
//					for(String str:model.getPltfiles())
						{
						Vector <GeoModel> obj=fileprocess.readGeoPlt(str);
						model.getContent().replace(str, null, obj);
						dataprocessobj.computeTurnAngle(obj);
						//�õ�ÿ��Ĺ켣
//						dataprocessobj.divideSegments(obj,60,model);
						}
					
//					System.out.println(model.getTrajects().size());
					System.out.println(model.getContent().size());
				}
				}
			/*for(int i=0;i<obj.size();i++)
				System.out.println(obj.get(i).getTransitionangle());*/
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
