package com.zhang.pro.data_processing.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
	
	public void dataToJsonfile(PeoDataModel peodata){
		File file = new File("D:/学习资料/城市轨迹数据/Geolife Trajectories 1.3/Geolife Trajectories 1.3/Data/000/2.json");	
		JSONArray jsonarray = new JSONArray();
		for(String name:peodata.getContent().keySet())
		{
			StringBuffer strbuff = new StringBuffer();	
			JSONArray array = new JSONArray();
			for(GeoModel geo:peodata.getContent().get(name))
			{
				List<Float> list = new ArrayList<Float>();
				list.add(geo.getLongitude());
				list.add(geo.getLatitude());
				HashMap<String,List<Float>> map = new HashMap<String,List<Float>>();
				map.put("coord",list);
				JSONObject json = JSONObject.fromObject(map);
				array.add(json);
			}
			jsonarray.add(array);
			
		}
		try {
			FileWriter writer= new FileWriter(file);
			writer.write(jsonarray.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public void dataToJsonfiletest(ArrayList<GeoModel> peodata){
		File file = new File("D:/学习资料/城市轨迹数据/Geolife Trajectories 1.3/Geolife Trajectories 1.3/Data/000/3.json");	
		JSONArray jsonarray = new JSONArray();	
			StringBuffer strbuff = new StringBuffer();	
			JSONArray array = new JSONArray();
			for(GeoModel geo:peodata)
			{
				List<Float> list = new ArrayList<Float>();
				list.add(geo.getLongitude());
				list.add(geo.getLatitude());
				HashMap<String,List<Float>> map = new HashMap<String,List<Float>>();
				map.put("coord",list);
				JSONObject json = JSONObject.fromObject(map);
				array.add(json);
			}
			jsonarray.add(array);
			
		
		try {
			FileWriter writer= new FileWriter(file);
			writer.write(jsonarray.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
