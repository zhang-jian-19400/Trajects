package com.zhang.pro.data_processing;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class DataProcessing {
	
	public DataProcessing(){
		
	}
	public Vector <GeoModel> computeTurnAngle(Vector <GeoModel> geoInfos){
		int number = geoInfos.size();
		GeoModel point1,point2,point3;
		double ab,ac,bc,angle,latitude;//单位是meter.
		for(int i = 1;i<number-1;i++){
			point1 = geoInfos.get(i-1);
			point2 = geoInfos.get(i);
			point3 = geoInfos.get(i+1);
			ab = geoDistance(point1,point2);
			bc = geoDistance(point2,point3);
			ac = geoDistance(point1,point3);
			latitude = point1.latitude+(point3.longitude-point1.longitude)/((point3.longitude-point2.longitude)*(point1.latitude-point3.latitude));
			//判断正负
			if(Math.abs(ab+bc-ac)<0.00000000000001)
				angle = 180;
			else
				{angle = Math.acos((ab*ab+bc*bc-ac*ac)/(2*ab*bc));
					angle = angle*(180/Math.PI);
				}
			point2.setTransitionangle( point2.latitude>latitude? (float)angle-180:180-(float)angle);
		}
		return geoInfos;
	}
	
	public double geoDistance(GeoModel point1,GeoModel point2){
		double distance,A,B;
		
		A = 111000*(point1.longitude-point2.longitude);
		B = 111000*(point1.latitude-point2.latitude);
		if(A<0) A=-A;
		if(B<0) B=-B;
		return Math.sqrt(A*A+B*B);
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
			if(Math.abs(obj.transitionangle)>area)	//停止向前扫描，将数据放入到trajectors中。
				{length = end - start;
				GeoModel[] segments = new GeoModel[length];
				for(num=0;num<length;num++) segments[num] = geoInfos.get(start+num);
				trajects.addElement(segments);
				start = end;
				}
			
		}
		peodatamodel.setTrajects(trajects);
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
				if(!name.endsWith("plt"))
				{
					if(Presentfloor==floor){
						if(!peodatamodel.getName().equals("")){
							dataset.getPeople().addElement((PeoDataModel)peodatamodel.clone());
							peodatamodel.setName("");
							peodatamodel.setPltfiles(new Vector<String>());
						}
						peodatamodel.setName(name);
						traverseAllPlt(dataset,peodatamodel,filePath+"/"+name,floor,Presentfloor);
					}
					else
						traverseAllPlt(dataset,peodatamodel,filePath+"/"+name,floor,Presentfloor);
					}
				else{
					peodatamodel.getPltfiles().addElement(filePath+"/"+name);
				//	System.out.println(filePath+"/"+name);
				}
					
			}
		}
	}
	public static void main(String args[]){
		DataSet dataset = new DataSet();
		FileProcess fileprocess = new FileProcess();
		DataProcessing dataprocessobj = new DataProcessing();
		PeoDataModel peodatamodel = new PeoDataModel();
		int n=0;
		String filename = "D:/学习资料/城市轨迹数据/Geolife Trajectories 1.3/Geolife Trajectories 1.3";
		try {

			
			dataprocessobj.traverseAllPlt(dataset,peodatamodel,filename,3,1);
			dataset.getPeople().addElement((PeoDataModel)peodatamodel.clone());
			for(PeoDataModel model:dataset.getPeople()){
				System.out.println(model.getName()+"**********");
				for(String str:model.getPltfiles())
					{
					Vector <GeoModel> obj=fileprocess.readGeoPlt(str);
					dataprocessobj.computeTurnAngle(obj);
					//得到每天的轨迹
					dataprocessobj.divideSegments(obj,45,model);
					}
			}
			/*for(int i=0;i<obj.size();i++)
				System.out.println(obj.get(i).getTransitionangle());*/
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
