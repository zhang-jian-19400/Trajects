package com.zhang.pro.indextree;

import java.util.ArrayList;

import com.zhang.pro.data_processing.model.GeoModel;

public class LeafDefault extends LeafNode{
	int id;
	ArrayList<GeoModel>points;

	public ArrayList<GeoModel> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<GeoModel> points) {
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
