package com.zhang.pro.data_processing.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.zhang.pro.data_processing.model.GeoModel;
/*
 * 默认文件plt格式为：
 * 已经是：按照时间排序，正式数据从第7行开始。
 */
public class FileProcess {
	String FileName;
	public FileProcess(){
		
	}
	/*
	 * @param filename文件名
	 * @see 依次通过 File,FileReader,BufferedReader,StringBuffer
	 * 
	 */
	public Vector <GeoModel> readGeoPlt(String filename) throws IOException{
		File file = new File(filename);
		Vector <GeoModel> geoInfos = new Vector <GeoModel>();
		if(!file.exists()||file.isDirectory())
			throw new FileNotFoundException();
		FileReader filereader = new FileReader(file);
		BufferedReader br = new BufferedReader(filereader);
		String temp = null;
		StringBuffer sb = new StringBuffer();
		//由于该plt文件特有的特点，需要从第7行开始读入数据
		int linenum;
		for(linenum=0;linenum<7;linenum++){temp =br.readLine();}		
		while(temp!=null){
			//do somgting 
			String tempinfo[] = new String[7];
			tempinfo = temp.split(",");
			GeoModel geomodel = new GeoModel();
			geomodel.setLatitude(Float.parseFloat(tempinfo[0]));
			geomodel.setLongitude(Float.parseFloat(tempinfo[1]));
			geomodel.setType(Integer.parseInt(tempinfo[2])); 
			geomodel.setAltitude(Float.parseFloat(tempinfo[3]));
			geomodel.setDatadistance(Float.parseFloat(tempinfo[4]));
			geomodel.setData(tempinfo[5]);
			geomodel.setTime(tempinfo[6]);
			geoInfos.addElement(geomodel);
			temp = br.readLine();
		}
		return geoInfos;	
	}
	
	/*
	 *  A:21.17156982421875 B:
	 */
	
	/*
	 * 读取文件夹内容，获取每个人的轨迹数据读入数据从Geolife Trajectories 1.3文件夹开始。
	 * Geolife Trajectories 1.3\Data\000\Trajectory，读到每个plt文件。
	 */
}
