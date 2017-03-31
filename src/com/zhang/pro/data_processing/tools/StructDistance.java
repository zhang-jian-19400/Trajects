package com.zhang.pro.data_processing.tools;

import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.GeoModel;
// new StructDistance(GeoModel[] segment1,GeoModel[] segment2).getStructDistance()
/*
 * 此部分的四个距离均根据论文改写。
 */
public class StructDistance {
	DataProcessing process = new DataProcessing();
	private Vector<GeoModel> segment1;
	private Vector<GeoModel>  segment2;
	
	public void setSegment(Vector<GeoModel>  segment1,Vector<GeoModel>  segment2) {
		this.segment1 = segment1;
		this.segment2 = segment2;
	}
	class group<T>{
			T o1;
			T o2;
			T o3;
			public group(T o1,T o2,T o3){
				this.o1 = o1;
				this.o2 = o2;
				this.o3 = o3;
			}
			public group(T o1,T o2){
				this(o1,o2,null);
				
			}
			public group(){
				
			}
		}
	public double calculateAngleDistance(){
		int size = segment1.size()<segment2.size()?segment1.size():segment2.size();
		double sum=0;
		for(int i=0;i<size;i++){
			double angle1 = segment1.get(i).getTransitionangle();
			double angle2 = segment2.get(i).getTransitionangle();
			 sum +=(angle1-angle2)/(Math.abs(angle2)+Math.abs(angle1));
		}
		return sum/(size);
	}
	/*
	 * 已知两个轨迹各个点坐标，求出最长最短距离
	 * 先找出segment1到segment2中距离最小的点。然后在这些点中找到最长的距离。
	 */
	public double calculateLocDistance(){
		double min = Constant.inf;
		double max = Constant.min;
		double distance;
		for(int i=0;i<segment1.size();i++)
		{	
			min = Constant.inf;
			for(int j=0;j<segment2.size();j++){
				distance = process.geoDistance(segment1.get(i),segment2.get(j));
				min = min<distance?min:distance;
			}
			max = max > min ? max : min;
		}
		return max;
	}
	/*
	 * 已知两个轨迹段的起点，终点。得到两个方向角
	 * min(Li,Lj)*sin(x)  0=<x<=90
	 * min(Li,Lj)
	 */
	public double calculateDirDistance(){
		double dirdistance;
		GeoModel seg1Start = segment1.get(0);
		GeoModel seg1End = segment1.get(segment1.size()-1);
		GeoModel seg2Start = segment2.get(0);
		GeoModel seg2End = segment2.get(segment2.size()-1);
		GeoModel Point = new GeoModel();
		//计算转角,将两条轨迹移动至同一个起点上。以seg2为参照
		Double y = seg2Start.getLatitude()-seg1Start.getLatitude();
		Double x = seg2Start.getLongitude()-seg1Start.getLongitude();
		Point.setLatitude(seg1End.getLatitude()+y);
		Point.setLongitude(seg1End.getLongitude()+x);		
		double angle = process.pointToangle(seg2Start, Point, seg2End);
		double segment1Length = process.geoDistance(seg1Start, seg1End);
		double segment2Length = process.geoDistance(seg2Start, seg2End);
		double length = segment1Length < segment2Length ? segment1Length : segment2Length;
		if(angle>=0&&angle<90)
			dirdistance = Math.sin(angle)*length;
		else
			dirdistance = length;
		return dirdistance;
	}
	/*
	 * 分别求出max average min of speed.then sumition of them.
	 */
	public double calculateSpeedByPoint(GeoModel point1,GeoModel point2){
		double distance = process.geoDistance(point1, point2);
		double time = point2.getDatadistance()-point1.getDatadistance();
		return (distance/time)*3600*24;// m/s
	}
	public group getThreeSpeed(Vector<GeoModel> segment)
	{
		double speedaverage = calculateSpeedByPoint(segment.get(0),segment.get(segment.size()-1));
		double minspeed = Constant.inf;
		double maxspeed = Constant.min;
		for(int i=0;i<segment.size()-1;i++)
		{
			double speed = calculateSpeedByPoint(segment.get(i),segment.get(i+1));
			minspeed = minspeed<speed?minspeed:speed;
			maxspeed = maxspeed>speed?maxspeed:speed;
		}
		return new group(minspeed,speedaverage,maxspeed);
	}
	/*
	 * According the definition of speeddifference
	 */
	public double calculateSpeedDistance(){
		if((segment1.size()>=3)&&segment2.size()>=3){
		group<Double>g1 = getThreeSpeed(segment1);
		group<Double> g2 = getThreeSpeed(segment2);
		double SpeedDifference = (Math.abs(g1.o1-g2.o1)+Math.abs(g1.o2-g2.o2)+Math.abs(g1.o3-g2.o3));
		return SpeedDifference/3;	
		}
		else
			return Constant.inf;
	}
	
	public double getStructDistance(double... W){
		if(W.length==4)
		return calculateAngleDistance()*W[0]+
				calculateSpeedDistance()*W[1]+
				calculateDirDistance()*W[2]+
				calculateLocDistance()*W[3];
		else return Constant.min;
	}
	
	 
}
