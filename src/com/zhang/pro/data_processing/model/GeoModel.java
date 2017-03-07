package com.zhang.pro.data_processing.model;

public class GeoModel implements Cloneable{
	float latitude;//γ��
	float longitude;//����
	float altitude;//����
	int type;//��ʱ��ʵ������
	float datadistance;//���� 12/30/1899������
	String data;//����
	String time;//ʱ��
	float transitionangle;//ת��
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
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
	public float getDatadistance() {
		return datadistance;
	}
	public void setDatadistance(float datadistance) {
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
