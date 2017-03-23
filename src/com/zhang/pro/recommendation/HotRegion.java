package com.zhang.pro.recommendation;

import java.util.HashMap;
import java.util.Vector;

public class HotRegion {
	private String filename;
	private HashMap<Integer,Vector> Cluster = new HashMap<Integer,Vector>();
	
	public HotRegion(){
		this.Cluster = new HashMap();
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public HashMap<Integer, Vector> getCluster() {
		return Cluster;
	}

	public void setCluster(HashMap<Integer, Vector> cluster) {
		Cluster = cluster;
	}
	
}
