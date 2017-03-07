package com.zhang.pro.recommendation;

import java.util.HashMap;
import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.GeoModel;

public class Recommentdation {
	//�ļ������ȵ�����
	private HashMap<String,ActivityType> PeoActivity;
	public Recommentdation(){	}
	DataProcessing dataprocessing = new DataProcessing();
	
	/*
	 * @Description: This function find region in a plt file.
	 * @param name refers to the file's name; trajectores is the content of plt file.
	 * minTime: the disdinguish boundary of time interval.as same as minSpeed.yibuxingnong is the param to adjust speed,>=1;
	 * @return: return the two hotregions;
	 */
	public HotRegion findHotRegion(String name,Vector<GeoModel> trajectories,double minTime,double minSpeed,double yibuxingnong){
		Vector region1=new Vector <GeoModel>();
		Vector region2 = new Vector <GeoModel>();
		boolean clusterOpen = false;
		int ClusterId=0;
		Vector cluster = new Vector();
		HotRegion hotregion = new HotRegion();
		for(int i=0;i<trajectories.size()-1;i++){
			//����õ��ͣ��ʱ�䣬���ٶ�
			double time = (trajectories.get(i+1).getDatadistance()-trajectories.get(i).getDatadistance())*3600*24;
			double speed = dataprocessing.geoDistance(trajectories.get(i+1),trajectories.get(i))/time;
			//Ŀǰ��Ҫ��������ͨ���ٶ���ʱ�������������ȵ�����ʡ����һЩ����
			if(speed>minSpeed)
				if((time>minTime)&&(speed<minSpeed*yibuxingnong)) //belongs to region1
				{		
					cluster.addElement(trajectories.get(i));
					if(clusterOpen == false)
					clusterOpen = true;
				}
				else if(clusterOpen==true)
				{
					clusterOpen = false;
					cluster.addElement(trajectories.get(i+1));
					hotregion.getCluster().put(ClusterId,cluster);
					ClusterId++;
					cluster = new Vector(); 
				}			
			else if(speed<minSpeed) //belongs to region2
				{
					cluster.addElement(trajectories.get(i));
					if(clusterOpen == false)
						clusterOpen = true;
				}
				else if(clusterOpen == true)
				{
					clusterOpen = false;
					hotregion.getCluster().put(ClusterId, cluster);
					ClusterId++;
					cluster = new Vector();
				}	
		}
		return hotregion;
	}
	
	public void calculateInterestDegree(HashMap<String,ActivityType> PeoActivity,String startdata,String enddata){
		
	}
}
