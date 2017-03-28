package com.zhang.pro.data_processing.model;

public class POI {
  private float latitude;
  private float longtitude;
  private String poitype;
  private static int id;
  //...
public float getLatitude() {
	return latitude;
}
public void setLatitude(float latitude) {
	this.latitude = latitude;
}
public float getLongtitude() {
	return longtitude;
}
public void setLongtitude(float longtitude) {
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
