package com.zhang.pro.recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;

public class ActivityType {
	private int Oid;
	private ArrayList<Vector> HR;
	private float TimeLength=0; //时间总长度
	private int Count = 0;
	private String LastTime = "";
	private double threshold = 0;
	DataProcessing dataprocessing = new DataProcessing();
	public ActivityType(HotRegion HRmodel){
		HashMap <Integer,Vector> map = HRmodel.getCluster();
		for(Integer id: map.keySet()){
			Vector <GeoModel> segments = map.get(id);
			
			
		}
	}
	// 先查看HR中有无，无则创建新的节点。
	public boolean MergeHR(ArrayList<Vector> HR,Vector<GeoModel> traject){
		boolean flag = false;
		GeoModel trajectm,traject1m ;
		
		for(Vector<GeoModel> traject1:HR){
			if(CompareHR(traject1,traject,threshold)){ 
				this.setCount(Count++);
				trajectm = traject.get(traject.size()/2);
				traject1m = traject1.get(traject1.size()/2);
				if (trajectm.getDatadistance()-(traject1m.getDatadistance())>0) this.setLastTime(trajectm.getTime());				
				flag = true;}
			else //创建新的节点
			{}
		}
		return flag;
	}
	/*
	 * if the Euclidean that is between two traject2 is lower than threshold,It's accepted.
	 */
	public boolean CompareHR(Vector<GeoModel> trajects1,Vector<GeoModel> trajects2,double threshold){
		int site1 = trajects1.size()/2;
		int site2 = trajects2.size()/2;
		boolean flag = dataprocessing.geoDistance(trajects1.get(site1), trajects2.get(site2))>threshold?false:true;
		return flag;
	}
	
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public int getOid() {
		return Oid;
	}
	public void setOid(int oid) {
		Oid = oid;
	}
	public ArrayList<Vector> getHR() {
		return HR;
	}
	public void setHR(ArrayList<Vector> hR) {
		HR = hR;
	}
	public float getTimeLength() {
		return TimeLength;
	}
	public void setTimeLength(float timeLength) {
		TimeLength = timeLength;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public String getLastTime() {
		return LastTime;
	}
	public void setLastTime(String lastTime) {
		LastTime = lastTime;
	}
}
