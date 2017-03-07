package com.zhang.pro.indextree;

import java.util.ArrayList;

public abstract class LeafNode extends Node{
	int id;
	int ParentId;
	private final int ChildIds[]=null;
	private final int CoreIds[]=null;
	private final float MaxDist=0; 
	int Level;
	int BelongsId;
	ArrayList<? extends Node>entries;
}
