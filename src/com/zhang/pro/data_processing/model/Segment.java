package com.zhang.pro.data_processing.model;

public class Segment {
	String Parent_Trajectory;
	int startPosition;
	int offset;
	boolean Clusterflag;//表示是否被聚类
	public int getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getParent_Trajectory() {
		return Parent_Trajectory;
	}
	public void setParent_Trajectory(String parent_Trajectory) {
		Parent_Trajectory = parent_Trajectory;
	}
	public boolean isClusterflag() {
		return Clusterflag;
	}
	public void setClusterflag(boolean clusterflag) {
		Clusterflag = clusterflag;
	}
	
}
