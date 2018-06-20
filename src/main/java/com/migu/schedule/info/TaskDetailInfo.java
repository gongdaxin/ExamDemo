package com.migu.schedule.info;

public class TaskDetailInfo {
	
	private int taskId;
	private int consumption;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getConsumption() {
		return consumption;
	}
	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
	@Override
	public String toString() {
		return "TaskDetailInfo [taskId=" + taskId + ", consumption=" + consumption + "]";
	}
	
	

}
