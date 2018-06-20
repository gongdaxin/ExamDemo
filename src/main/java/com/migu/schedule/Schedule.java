package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskDetailInfo;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.sort.SortByTaskId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

/*
*类名和方法不能修改
 */
public class Schedule {
	
	/**
	 * 服务节点集合,key是节点id
	 */
	private static HashMap<Integer,List<TaskDetailInfo>> nodeMap = new HashMap<Integer,List<TaskDetailInfo>>();
	
	/**
	 * 挂起任务
	 */
//	private  static ArrayList<TaskDetailInfo> detailList = new ArrayList<TaskDetailInfo>();
	private static Queue<TaskDetailInfo> detailQueue = new LinkedList<TaskDetailInfo>();
	
	/**
	 * 进行中任务
	 */
	private static Queue<TaskDetailInfo> taskQueue = new LinkedList<TaskDetailInfo>();
	
	/**
	 * 运行节点
	 * 
	 */
	private static HashMap<Integer,List<TaskDetailInfo>> taskNode = new HashMap<Integer,List<TaskDetailInfo>>();
	
    public int init() {
    	nodeMap.clear();
    	detailQueue.clear();
    	taskQueue.clear();
    	taskNode.clear();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        
    	if (nodeId<=0) {
			return ReturnCodeKeys.E004;
		}
    	if (nodeMap.get(nodeId)!=null) {
			return ReturnCodeKeys.E005;
		}
    	
    	List<TaskDetailInfo> taskDetailList = new ArrayList<TaskDetailInfo>();
    	nodeMap.put(nodeId, taskDetailList);
    	
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        
    	if (nodeId<=0) {
			return ReturnCodeKeys.E004;
		}
    	
    	List<TaskDetailInfo> taskDetailList = nodeMap.get(nodeId);
    	
    	if (taskDetailList==null) {
			return ReturnCodeKeys.E007;
		}
    	
    	if (taskNode.get(nodeId)!=null) {
			for (TaskDetailInfo taskDetailInfo : taskDetailList) {
				taskQueue.offer(taskDetailInfo);
			}
		}
    	nodeMap.remove(nodeId);
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
		if (taskId<=0) {
			return ReturnCodeKeys.E009;
		}
		Iterator<TaskDetailInfo> it = detailQueue.iterator();
		while (it.hasNext()) {
			if (taskId == it.next().getTaskId()) {
				it.remove();
				return ReturnCodeKeys.E010;
			}
		}
		
		TaskDetailInfo detail = new TaskDetailInfo();
		detail.setConsumption(consumption);
		detail.setTaskId(taskId);
		detailQueue.offer(detail);
		
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        
    	if (taskId<=0) {
    		return ReturnCodeKeys.E009;
		}
    	Iterator<TaskDetailInfo> it = detailQueue.iterator();
    	
    	while (it.hasNext()) {
			if (taskId == it.next().getTaskId()) {
				it.remove();
				return ReturnCodeKeys.E011;
			}
		}
    	
        return ReturnCodeKeys.E012;
    }


    public int scheduleTask(int threshold) {
    	if (threshold<=0) {
			return ReturnCodeKeys.E002;
		}
    	boolean flag = false;
    	
    	
        return ReturnCodeKeys.E013;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
    	
    	if (tasks==null) {
			tasks =new ArrayList<TaskInfo>();
		}
    	
    	for (Entry<Integer, List<TaskDetailInfo>> entry : nodeMap.entrySet()) {
    		int nodeId = entry.getKey();
    		 List<TaskDetailInfo> details = entry.getValue();
    		 for (TaskDetailInfo info : details) {
    			 TaskInfo task = new TaskInfo();
    			 task.setNodeId(nodeId);
    			 task.setTaskId(info.getTaskId());
			}
    	}
    	
    	
    	Iterator<TaskDetailInfo> it = detailQueue.iterator();
		while (it.hasNext()) {
			int taskId = it.next().getTaskId();
			TaskInfo task = new TaskInfo();
			task.setNodeId(-1);
   			task.setTaskId(taskId);
			
		}
    	
    	if (tasks==null||tasks.size()==0) {
			return ReturnCodeKeys.E016;
		}

		Collections.sort(tasks, new SortByTaskId());
    	
        return ReturnCodeKeys.E015;
    }

}
