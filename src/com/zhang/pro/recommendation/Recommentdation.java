package com.zhang.pro.recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;
import com.zhang.pro.data_processing.tools.Constant;

public class Recommentdation {
	//文件名，热点区域
	private HashMap<String,ArrayList<ActivityType>> PeoActivity = new HashMap();
	private double minTime=180;
	private double minSpeed = 3;
	private double yibuxingnong = 1.5;
	private double threshold = 10;
	public Recommentdation(){	}
	DataProcessing dataprocessing = new DataProcessing();
	
	/*
	 * @Description: This function find region in a plt file.
	 * @param name refers to the file's name; trajectores is the content of plt file.
	 * minTime: the disdinguish boundary of time interval.as same as minSpeed.yibuxingnong is the param to adjust speed,>=1;
	 * @return: return the two hotregions;
	 */
	public HotRegion findHotRegion(Vector<GeoModel> trajectories,double minTime,double minSpeed,double yibuxingnong){
		Vector region1=new Vector <GeoModel>();
		Vector region2 = new Vector <GeoModel>();
		boolean clusterOpen = false;
		int ClusterId=0;
		Vector cluster = new Vector();
		HotRegion hotregion = new HotRegion();
		for(int i=0;i<trajectories.size()-1;i++){
			//求出该点的停留时间，与速度
			double time = (trajectories.get(i+1).getDatadistance()-trajectories.get(i).getDatadistance())*3600*24;
			double speed = dataprocessing.geoDistance(trajectories.get(i+1),trajectories.get(i))/time;
			//目前主要的做法是通过速度与时间来划分两种热点区域省略了一些步骤
			if(speed>minSpeed)
				if((time>minTime)&&(speed<minSpeed*yibuxingnong)) //belongs to region1 环绕模式
				{		
					cluster.addElement(trajectories.get(i));
					if(clusterOpen == false)
					clusterOpen = true;
				}
				else if(clusterOpen==true)
				{
					clusterOpen = false;
					cluster.addElement(trajectories.get(i+1));
					hotregion.getCluster().put(ClusterId,cluster);
					ClusterId++;
					cluster = new Vector(); 
				}			
			else if(speed<minSpeed) //belongs to region2 停留模式
				{
					cluster.addElement(trajectories.get(i));
					if(clusterOpen == false)
						clusterOpen = true;
				}
				else if(clusterOpen == true)
				{
					clusterOpen = false;
					hotregion.getCluster().put(ClusterId, cluster);
					ClusterId++;
					cluster = new Vector();
				}	
		}
		return hotregion;
	}
	public PeopleActivity findPeoActivity(PeoDataModel peomodel){
		Vector<ActivityType> activities = new Vector<ActivityType>();
		int i=0;
		//访问人的所有轨迹数据文件 哈希表的添加不是按照的顺序
		for(String key:peomodel.getContent().keySet()){
			i++;
			HotRegion area = findHotRegion(peomodel.getContent().get(key),minTime,minSpeed,yibuxingnong);
			if (area.getCluster().size()>=1)
				mergePeoRegion(activities,area);
		}
		PeopleActivity peoactivity = new PeopleActivity();
		peoactivity.setActivities(activities);
	return peoactivity;
	}
	/*
	 * @param activities represents the set of HotRegion belongs to Someone,
	 * area is the hotregion in a trajectory of someone.
	 * activities->First HR is compared with area.if the result of it is suitable,
	 * then merge area to activity
	 */
	public void mergePeoRegion(Vector<ActivityType> activities,HotRegion area){
		double mindistance = Constant.inf;
		int position = -1;
		for(Integer Cluster :area.getCluster().keySet()){
			position = -1;
			mindistance = Constant.inf;
			Vector<GeoModel> hrarea1 = area.getCluster().get(Cluster);
		for(int i=0;i<activities.size();i++){
			Vector<GeoModel> hrarea2 = activities.get(i).getHR().get(0);//get the first HR of current activity
			double distance = dataprocessing.geoDistance(hrarea1.get(hrarea1.size()/2),hrarea2.get(hrarea2.size()));
			if(distance<threshold){ //如果两者距离比阈值小，那么再选择出距离最短的那个。
				if(distance<mindistance){
					 mindistance = distance;
					 position = i;
					}
			}
			}
			if(position<0){
				Vector vector = new Vector();
				vector.add(hrarea1);
				ActivityType activity = new ActivityType();				
				activity.setCount(0);
				activity.setHR(vector);
				activity.setLastTime(hrarea1.get(hrarea1.size()-1).getDatadistance());
				activity.setOid(activities.size());//从0编号开始				
				activity.setThreshold(threshold);
				activity.setTimeLength(0);
				activities.add(activity);
			}
			else{
				activities.get(position).getHR().add(hrarea1);
			}
		}
	}
	/*
	 * @ just consider the distance of two areas.
	 */
	public boolean compareRegion(Vector<GeoModel> area1,Vector<GeoModel> area2,double threshold){
		if(dataprocessing.geoDistance(area1.get(area1.size()/2),area2.get(area2.size()))<threshold)
			return true;
		return false;
	}
	public void calculateInterestDegree(HashMap<String,ActivityType[]> PeoActivity,String startdata,String enddata){
		
	}
	public HashMap<String, ArrayList<ActivityType>> getPeoActivity() {
		return PeoActivity;
	}
	public void setPeoActivity(HashMap<String, ArrayList<ActivityType>> peoActivity) {
		PeoActivity = peoActivity;
	}
	public double getMinTime() {
		return minTime;
	}
	public void setMinTime(double minTime) {
		this.minTime = minTime;
	}
	public double getMinSpeed() {
		return minSpeed;
	}
	public void setMinSpeed(double minSpeed) {
		this.minSpeed = minSpeed;
	}
	public double getYibuxingnong() {
		return yibuxingnong;
	}
	public void setYibuxingnong(double yibuxingnong) {
		this.yibuxingnong = yibuxingnong;
	}
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public DataProcessing getDataprocessing() {
		return dataprocessing;
	}
	public void setDataprocessing(DataProcessing dataprocessing) {
		this.dataprocessing = dataprocessing;
	}
	
}
