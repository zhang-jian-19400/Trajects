package com.zhang.pro.indextree;

public class NoLeafDefault extends NoLeafNode{
	int id;
	int ParentId;
	int ChildIds[];
	int CoreIds[];
	float MaxDist; 
	int Level;
	public NoLeafDefault(){	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	public int[] getChildIds() {
		return ChildIds;
	}
	public void setChildIds(int[] childIds) {
		ChildIds = childIds;
	}
	public int[] getCoreIds() {
		return CoreIds;
	}
	public void setCoreIds(int[] coreIds) {
		CoreIds = coreIds;
	}
	public float getMaxDist() {
		return MaxDist;
	}
	public void setMaxDist(float maxDist) {
		MaxDist = maxDist;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}	
}
