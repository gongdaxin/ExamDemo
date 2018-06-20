package com.migu.schedule.sort;

import java.util.Comparator;

import com.migu.schedule.info.TaskDetailInfo;

public class ConsumptionSort  implements Comparator {

	public int compare(Object o1, Object o2) {
		TaskDetailInfo d1 =(TaskDetailInfo) o1;
		TaskDetailInfo d2 =(TaskDetailInfo) o2;
		if (d1.getConsumption()>d2.getConsumption()) {
			return 1;
		}
		return -1;
	}

}
