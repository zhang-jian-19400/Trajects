package com.zhang.pro.recommendation;

import java.util.Vector;

import com.zhang.pro.data_processing.DataProcessing;
import com.zhang.pro.data_processing.model.DataSet;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;
import com.zhang.pro.data_processing.tools.FileProcess;
import com.zhang.pro.indextree.IndexTree;

public class Test {
	/*
	 * 获取轨迹数据，将数据传入到findactivity的中。
	 */
	public static void main(String args[]){
		DataSet dataset = new DataSet();
		FileProcess fileprocess = new FileProcess();
		DataProcessing dataprocessobj = new DataProcessing();
		PeoDataModel peodatamodel = new PeoDataModel();
		int n=0;
		String filename = "D:/学习资料/城市轨迹数据/Geolife Trajectories 1.3/Geolife Trajectories 1.3";
		try {

			//将文件夹中，属于该个人的文件名字，都存放到各个人下
			 dataprocessobj.traverseAllPlt(peodatamodel,filename,3,1);
			 dataset =dataprocessobj.getDataset();
			dataset.getPeople().addElement((PeoDataModel)peodatamodel.clone());
			int i=0,j=0;Vector <GeoModel> obj1 = new Vector();
			//遍历这些人对象，逐个处理文件，提取plt文件中的数据，存放到个人的轨迹段链表中，最终结果存储在dataset中。
			for(PeoDataModel model:dataset.getPeople()){
				i++;//目前只对第一个人的轨迹进行分析
				if(i<=10){
					System.out.println(model.getName());
				//逐个处理每一个plt文件
					for(String str:model.getContent().keySet())
						{
						Vector <GeoModel> obj=fileprocess.readGeoPlt(str);
						obj1 = obj;
						//用新的更新好的<string，trajectory>更新已有的内容,中间的为对象实例，而非空
						model.getContent().replace(str,new Vector<GeoModel>(), obj);
						dataprocessobj.computeTurnAngle(obj);
						}					
					System.out.println(model.getContent().size());
				}
				}
			Recommentdation recommander = new Recommentdation();
			//寻找到第一个人的轨迹数据
			PeopleActivity peopleactivity =recommander.findPeoActivity(dataset.getPeople().get(0));
			System.out.println(peopleactivity.getActivities().size());
			System.out.println();
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	
}
