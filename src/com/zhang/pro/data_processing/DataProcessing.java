package com.zhang.pro.data_processing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import com.zhang.pro.data_processing.model.DataSet;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;

public class DataProcessing {
	
	public DataProcessing(){
		
	}
	public double pointToangle(GeoModel point2,GeoModel point1,GeoModel point3){
		double ab,ac,bc,angle,latitude;
		ab = geoDistance(point1,point2);
		bc = geoDistance(point2,point3);
		ac = geoDistance(point1,point3);
		latitude = point1.getLatitude()+(point3.getLongitude()-point1.getLongitude())/((point3.getLongitude()-point2.getLongitude())*(point1.getLatitude()-point3.getLatitude()));
		//�ж�����
		if(Math.abs(ab+bc-ac)<0.00000000000001)
			angle = 180;
		else
			{angle = Math.acos((ab*ab+bc*bc-ac*ac)/(2*ab*bc));
				angle = angle*(180/Math.PI);
			}
		angle = point2.getLatitude()>latitude? (float)angle-180:180-(float)angle;
		return angle;
	}
	public Vector <GeoModel> computeTurnAngle(Vector <GeoModel> geoInfos){
		int number = geoInfos.size();
		GeoModel point1,point2,point3;
		double ab,ac,bc,angle,latitude;//��λ��meter.
		for(int i = 1;i<number-1;i++){
			point1 = geoInfos.get(i-1);
			point2 = geoInfos.get(i);
			point3 = geoInfos.get(i+1);
			angle = pointToangle(point2,point1,point3);
			point2.setTransitionangle((float)angle);
		}
		return geoInfos;
	}
	public double geoDistance(GeoModel point1,GeoModel point2){
		double distance,A,B;
		double PI = 3.1415926;
		double Earth_Radius = 6378.137; //km
	    double radLat1 = (double)point1.getLatitude()*PI / 180.0;
	    double radLat2 = (double)point2.getLatitude()*PI / 180.0;
	    double a = radLat1 - radLat2;
	    double b = (double)point1.getLongitude()*PI/180.0 - (double)point2.getLongitude()*PI/180.0;
	    double s = Math.acos(Math.cos(radLat2)*Math.cos(radLat1)*Math.cos(b)+Math.sin(radLat2)*Math.sin(radLat1));
	    s = s*Earth_Radius;
	    s = 1000*s;
	    return s;//m is the unit of measure
	}
	/*
	 * ����ÿ���㴦��ת�ǣ��ж�����Ƕȵı仯����һ����ֵ������η��뵽һ���Ӷ��С�
	 */
	public void divideSegments(Vector <GeoModel> geoInfos,double area,PeoDataModel peodatamodel){
		int start=0,end=0,length,num;
		Vector trajects =new Vector <GeoModel[]> (); 
	//	peodatamodel.setTrajects(trajects);
		for(GeoModel obj:geoInfos){
			end++;
			if(Math.abs(obj.getTransitionangle())>area)	//ֹͣ��ǰɨ�裬�����ݷ��뵽trajectors�С�
				{length = end - start;
				GeoModel[] segments = new GeoModel[length];
				for(num=0;num<length;num++) segments[num] = geoInfos.get(start+num);
				trajects.addElement(segments);
				start = end;
				}
		}
		peodatamodel.getTrajects().addAll(trajects);
	//	peodatamodel.setTrajects(trajects);
	}
	/*
	 * floor ָ��ÿ���û��Ĺ켣�ļ�����filepath��ĵڼ���
	 * Presentfloor����һ��
	 */
	public void traverseAllPlt(DataSet dataset,PeoDataModel peodatamodel,String filePath,int floor,int Presentfloor)throws Exception{
		File file = new File(filePath);
		Presentfloor++;
		String peoid;
		if(file.exists())
		if(file.isDirectory())
		{
			for(File subfile:file.listFiles()){
				String name=subfile.getName();
				if(!name.endsWith("plt"))  //û�е�Ŀ��plt�ļ�
				{
					if(Presentfloor==floor){ //ָ���켣�ļ����ڵڼ��㣬ָ����Ǵ��plt�ļ�����һ��Ŀ¼
						if(!peodatamodel.getName().equals("")){
							//������������ǽ���һ���˵�������Ϣд�뵽�ڴ��С�
							dataset.getPeople().addElement((PeoDataModel)peodatamodel.clone());
							//����Ŀǰ��peodatamodel�������ӡ�
							peodatamodel.setName("");
							peodatamodel.setContent(new HashMap<String,Vector<GeoModel>>());
//							peodatamodel.setPltfiles(new Vector<String>());
						}
						peodatamodel.setName(name);
						traverseAllPlt(dataset,peodatamodel,filePath+"/"+name,floor,Presentfloor);
					}
					else
						traverseAllPlt(dataset,peodatamodel,filePath+"/"+name,floor,Presentfloor);
					}
				else{
//					peodatamodel.getPltfiles().addElement(filePath+"/"+name);
					peodatamodel.getContent().put(filePath+"/"+name,new Vector());
				//	System.out.println(filePath+"/"+name);
				}					
			}
		}
	}
	public void TrajectroyDbscan(Vector<GeoModel[]> trajects){
		
	}
	
}
