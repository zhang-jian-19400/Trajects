package com.zhang.pro.data_processing.model;

public class POI {
  private double latitude;
  private double longtitude;
  private String poitype;
  private static int id;
  //...
public double getLatitude() {
	return latitude;
}
public void setLatitude(double latitude) {
	this.latitude = latitude;
}
public double getLongtitude() {
	return longtitude;
}
public void setLongtitude(double longtitude) {
	this.longtitude = longtitude;
}
public String getPoitype() {
	return poitype;
}
public void setPoitype(String poitype) {
	this.poitype = poitype;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
  
}
