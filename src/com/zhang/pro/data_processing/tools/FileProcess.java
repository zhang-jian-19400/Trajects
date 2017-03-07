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
 * Ĭ���ļ�plt��ʽΪ��
 * �Ѿ��ǣ�����ʱ��������ʽ���ݴӵ�7�п�ʼ��
 */
public class FileProcess {
	String FileName;
	public FileProcess(){
		
	}
	/*
	 * @param filename�ļ���
	 * @see ����ͨ�� File,FileReader,BufferedReader,StringBuffer
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
		//���ڸ�plt�ļ����е��ص㣬��Ҫ�ӵ�7�п�ʼ��������
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
	 * ��ȡ�ļ������ݣ���ȡÿ���˵Ĺ켣���ݶ������ݴ�Geolife Trajectories 1.3�ļ��п�ʼ��
	 * Geolife Trajectories 1.3\Data\000\Trajectory������ÿ��plt�ļ���
	 */
}
