package com.zhang.pro.data_processing;

import java.util.Vector;

public class DataSet {
	private Vector <PeoDataModel> People;
	public DataSet(){
		this.People = new Vector <PeoDataModel>();
	}
	public Vector<PeoDataModel> getPeople() {
		return People;
	}

	public void setPeople(Vector<PeoDataModel> people) {
		People = people;
	}
	
}
