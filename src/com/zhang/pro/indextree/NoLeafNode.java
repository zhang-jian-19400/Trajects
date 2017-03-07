package com.zhang.pro.indextree;

public abstract class NoLeafNode extends Node{
	int id;
	int ParentId;
	int ChildIds[];
	int CoreIds[];
	float MaxDist; 
	int Level; 
}
