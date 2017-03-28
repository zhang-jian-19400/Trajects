package com.zhang.pro.data_processing.model;

import java.util.ArrayList;

import com.zhang.pro.data_processing.tools.Constant;

public class PoiTable {
	private static int poid=0;
	private static ArrayList<POI> poitable = new ArrayList<POI>();
	private final float threshold =2;
	public void addPoi(POI e){
		e.setId(poid++);
		e.setPoitype("");
		poitable.add(e);	
	}
	
	public int getPoiInfo(float Latitude,float Longtitude){
		double mindistance=Constant.inf;
		POI flagPoi=new POI();
		for(POI poi:poitable){
		double distance=Math.sqrt(Math.pow(poi.getLatitude()-Latitude,2)+Math.pow(poi.getLongtitude()-Longtitude, 2));
			if(distance<threshold)
				if(distance<mindistance)
					{mindistance = distance; flagPoi = poi;}	
				}
			if(mindistance==Constant.inf)
			{
				POI e = new POI();
				e.setLatitude(Latitude);
				e.setLongtitude(Longtitude);
				addPoi(e);
				flagPoi = e; //������ת�Ƶ�flagPOI��		
			}
			return flagPoi.getId();
		
		
	}
	
	public ArrayList<POI> getPoitable() {
		return poitable;
	}

	public void setPoitable(ArrayList<POI> poitable) {
		this.poitable = poitable;
	}
	
}
