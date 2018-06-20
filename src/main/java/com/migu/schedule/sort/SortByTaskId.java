package com.migu.schedule.sort;

import java.util.Comparator;

import com.migu.schedule.info.TaskInfo;

public class SortByTaskId implements Comparator {

	public int compare(Object o1, Object o2) {
		TaskInfo task1 = (TaskInfo) o1;
		TaskInfo task2 = (TaskInfo) o1;
		if (task1.getTaskId()>task2.getTaskId()) {
			return -1;
		}
		return 1;
	}

}
