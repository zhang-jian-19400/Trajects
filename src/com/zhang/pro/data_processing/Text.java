package com.zhang.pro.data_processing;

import java.util.Vector;

import com.zhang.pro.data_processing.model.DataSet;
import com.zhang.pro.data_processing.model.GeoModel;
import com.zhang.pro.data_processing.model.PeoDataModel;
import com.zhang.pro.data_processing.tools.FileProcess;

public class Text {
	public static void main(String args[]){
		DataSet dataset = new DataSet();
		FileProcess fileprocess = new FileProcess();
		DataProcessing dataprocessobj = new DataProcessing();
		PeoDataModel peodatamodel = new PeoDataModel();
		int n=0;
		String filename = "D:/学习资料/城市轨迹数据/Geolife Trajectories 1.3/Geolife Trajectories 1.3";
		try {

			//将文件夹中，属于该个人的文件名字，都存放到各个人下
			dataprocessobj.traverseAllPlt(dataset,peodatamodel,filename,3,1);
			dataset.getPeople().addElement((PeoDataModel)peodatamodel.clone());
			int i=0,j=0;
			//遍历这些人对象，逐个处理文件，提取plt文件中的数据，存放到个人的轨迹段链表中，最终结果存储在dataset中。
			for(PeoDataModel model:dataset.getPeople()){
				i++;//目前只对第一个人的轨迹进行分析
				if(i<=1){
					System.out.println(model.getName());
				//逐个处理每一个plt文件
					for(String str:model.getContent().keySet())
//					for(String str:model.getPltfiles())
						{
						Vector <GeoModel> obj=fileprocess.readGeoPlt(str);
						model.getContent().replace(str, null, obj);
						dataprocessobj.computeTurnAngle(obj);
						//得到每天的轨迹
//						dataprocessobj.divideSegments(obj,60,model);
						}
					
//					System.out.println(model.getTrajects().size());
					System.out.println(model.getContent().size());
				}
				}
			/*for(int i=0;i<obj.size();i++)
				System.out.println(obj.get(i).getTransitionangle());*/
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
