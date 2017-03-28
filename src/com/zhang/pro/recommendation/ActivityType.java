package com.zhang.pro.recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;

public class ActivityType {
	private static int Oid;
	private Vector<Vector> HR;
	private float TimeLength=0; //时间总长度
	private int Count = 0;
	private float LastTime = 0;
	private double threshold = 0;
	DataProcessing dataprocessing = new DataProcessing();
	public ActivityType(HotRegion HRmodel){
		HashMap <Integer,Vector> map = HRmodel.getCluster();
		for(Integer id: map.keySet()){
			Vector <GeoModel> segments = map.get(id);		
		}
	}
	public ActivityType(){}
	// 先查看HR中有无，无则创建新的节点。
	public boolean MergeHR(ArrayList<Vector> HR,Vector<GeoModel> traject){
		boolean flag = false;
		GeoModel trajectm,traject1m ;		
		for(Vector<GeoModel> traject1:HR){
			if(CompareHR(traject1,traject,threshold)){ 
				this.setCount(Count++);
				trajectm = traject.get(traject.size()/2);
				traject1m = traject1.get(traject1.size()/2);
				if (trajectm.getDatadistance()-(traject1m.getDatadistance())>0) this.setLastTime(trajectm.getDatadistance());				
				flag = true;
				} 
			}	
		if(flag==false)//创建新的节点
				HR.add(traject);				
		return flag;
	}
	/*
	 * if the Euclidean that is between two trajects is lower than threshold,It's accepted.
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
	public Vector<Vector> getHR() {
		return HR;
	}
	public void setHR(Vector<Vector> hR) {
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
	public float getLastTime() {
		return LastTime;
	}
	public void setLastTime(float lastTime) {
		LastTime = lastTime;
	}
}
