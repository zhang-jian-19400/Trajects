package com.zhang.pro.data_processing.model;

import java.util.ArrayList;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.tools.Constant;

public class PoiTable {
	private static int poid=0;
	private static ArrayList<POI> poitable = new ArrayList<POI>();
	private final float threshold =20;
	DataProcessing dataprocessing = new DataProcessing();
	
	public void addPoi(POI e){
		e.setId(poid++);
		e.setPoitype("");
		poitable.add(e);	
	}
	
	public int getPoiInfo(GeoModel point){
		double Latitude = point.getLatitude();
		double Longtitude = point.getLongitude();
		double mindistance=Constant.inf;
		POI flagPoi=new POI();
		for(POI poi:poitable){
			GeoModel poipoint = new GeoModel();
			poipoint.setLatitude(poi.getLatitude());
			poipoint.setLongitude(poi.getLongtitude());
		double distance=dataprocessing.geoDistance(point, poipoint);
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
				flagPoi = e; //将数据转移到flagPOI中		
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
