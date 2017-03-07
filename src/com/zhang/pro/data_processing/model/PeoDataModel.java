package com.zhang.pro.data_processing.model;

import java.util.HashMap;
import java.util.Vector;

public class PeoDataModel implements Cloneable{
	private String name;
	private boolean sex;
	private int age;
	private String job;
	private HashMap<String,Vector<GeoModel>> Content; //pltfilename,trajects
	private Vector<String> pltfiles;
	private Vector <GeoModel[]> trajects ;
	public PeoDataModel(){
		this.name = "";	
		this.pltfiles = new Vector <String>();
		this.trajects = new Vector <GeoModel[]> ();
		this.Content = new HashMap();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Vector<GeoModel[]> getTrajects() {
		return trajects;
	}
	public void setTrajects(Vector<GeoModel[]> trajects) {
		this.trajects = trajects;
	}
	public Vector<String> getPltfiles() {
		return pltfiles;
	}
	public void setPltfiles(Vector<String> pltfiles) {
		this.pltfiles = pltfiles;
	}
	
	public HashMap<String, Vector<GeoModel>> getContent() {
		return Content;
	}
	public void setContent(HashMap<String, Vector<GeoModel>> content) {
		Content = content;
	}
	public Object clone() throws CloneNotSupportedException{
		PeoDataModel model = (PeoDataModel)super.clone();
		model.pltfiles = (Vector<String>)this.pltfiles.clone();
		model.trajects = (Vector <GeoModel[]>)this.trajects.clone();
		model.Content = (HashMap)this.Content.clone();
		return model;
	}
	
}
