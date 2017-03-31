package com.zhang.pro.recommendation;

import java.util.Vector;

public class PeopleActivity {
	private String name;
	private Vector <ActivityType> activities;
	private int activitiesnumber=0;
	private double activitytime = 0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<ActivityType> getActivities() {
		return activities;
	}
	public void setActivities(Vector<ActivityType> activities) {
		this.activities = activities;
	}
	public int getActivitiesnumber() {
		return activitiesnumber;
	}
	public void addActivitiesnumber(int activitiesnumber) {
		this.activitiesnumber += activitiesnumber;
	} 
	public double getActivitytime(){
		return this.activitytime;
	}
	public void addActivitytime(double activitiestime){
		this.activitytime+=activitiestime;
	}
}
