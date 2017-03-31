package com.zhang.pro.data_processing.model;

public class GeoModel implements Cloneable{
	double latitude;//纬度
	double longitude;//经度
	float altitude;//海拔
	int type;//暂时无实际意义
	double datadistance;//距离 12/30/1899的天数
	String data;//日期
	String time;//时间
	float transitionangle;//转角
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public float getAltitude() {
		return altitude;
	}
	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getDatadistance() {
		return datadistance;
	}
	public void setDatadistance(double datadistance) {
		this.datadistance = datadistance;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public float getTransitionangle() {
		return transitionangle;
	}
	public void setTransitionangle(float transitionangle) {
		this.transitionangle = transitionangle;
	}
	public Object clone() throws CloneNotSupportedException{
		GeoModel model = (GeoModel)super.clone();
		return model;
	}
	}
