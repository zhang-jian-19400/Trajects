package com.zhang.pro.recommendation;

import java.util.Vector;

public class PeopleActivity {
	private String name;
	private Vector <ActivityType> activities;
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
	
}
