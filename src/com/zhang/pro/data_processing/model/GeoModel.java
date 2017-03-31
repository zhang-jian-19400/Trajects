package com.zhang.pro.data_processing.model;

public class GeoModel implements Cloneable{
	double latitude;//γ��
	double longitude;//����
	float altitude;//����
	int type;//��ʱ��ʵ������
	double datadistance;//���� 12/30/1899������
	String data;//����
	String time;//ʱ��
	float transitionangle;//ת��
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
