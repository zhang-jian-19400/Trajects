package com.zhang.pro.recommendation;

import java.util.ArrayList;
import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.DataSet;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.POI;
import com.zhang.pro.data_processing.model.PeoDataModel;
import com.zhang.pro.data_processing.model.PoiTable;
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
			/*
			 * Ѱ�ҵ���һ���˵Ĺ켣����,�����Ļ�б��������������ͨ��POI����
			 */
			ArrayList<GeoModel> segments= new ArrayList<GeoModel>();
			PoiTable poitable = new PoiTable(); //���ڴ洢��ǰ�������ݵĻ���Ϳ�
//			PeopleActivity peopleactivity =recommander.findPeoActivity(dataset.getPeople().get(0));
			for(int k=0;k<dataset.getPeople().size();k++){
				PeopleActivity peopleactivity =recommander.findPeoActivity(dataset.getPeople().get(k));
				int activitiesnumber = peopleactivity.getActivitiesnumber();
			for(ActivityType activity:peopleactivity.getActivities()){
				activity.setDegreeInterest((float)(activity.getVisitTime())/activitiesnumber);
				System.out.println(activity.getTimeLength()*3600*24);
				int position = activity.getHR().get(0).size()/2;
				//�˴��Ĵ���ʽҲ�ǽ���ȡ�û��ĳ������ĵ�һ���㡣/////
				GeoModel point = (GeoModel)activity.getHR().get(0).get(position);				
				//�õ���Ȥ��ID�ţ�
				int result = poitable.getPoiInfo(point);
				activity.setOid(result);
				//Done
				
			/*	for(Vector<GeoModel> segment:activity.getHR()){	
					int size = segment.size();
					if (size ==1) System.out.println(segment.get(0).getDatadistance());
					for(GeoModel point:segment){
					segments.add(point);
				}
			}*/				
			}System.out.println("**************************************");
			}
			//fileprocess.dataToJsonfiletest(segments);	
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	
}
