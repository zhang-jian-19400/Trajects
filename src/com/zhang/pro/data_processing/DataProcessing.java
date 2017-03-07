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
		//判断正负
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
		double ab,ac,bc,angle,latitude;//单位是meter.
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
	 * 遍历每个点处的转角，判断如果角度的变化超过一个阈值，则将这段放入到一个子段中。
	 */
	public void divideSegments(Vector <GeoModel> geoInfos,double area,PeoDataModel peodatamodel){
		int start=0,end=0,length,num;
		Vector trajects =new Vector <GeoModel[]> (); 
	//	peodatamodel.setTrajects(trajects);
		for(GeoModel obj:geoInfos){
			end++;
			if(Math.abs(obj.getTransitionangle())>area)	//停止向前扫描，将数据放入到trajectors中。
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
	 * floor 指明每个用户的轨迹文件夹在filepath后的第几层
	 * Presentfloor在上一层
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
				if(!name.endsWith("plt"))  //没有到目标plt文件
				{
					if(Presentfloor==floor){ //指明轨迹文件夹在第几层，指向的是存放plt文件的上一层目录
						if(!peodatamodel.getName().equals("")){
							//下面这条语句是将上一个人的总体信息写入到内存中。
							dataset.getPeople().addElement((PeoDataModel)peodatamodel.clone());
							//更新目前的peodatamodel这个大盒子。
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
