package com.accelsiors.test;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.accelsiors.test.model.Activity;
import com.accelsiors.test.model.Task;

/**
 * Test service class
 * 
 * @author ZoltanS
 *
 */
@Service
public class TestService {

	private static Map<Integer, Activity> _activities;
	private static Map<Integer, Task> _tasks = fillTasks();

	public Collection<Activity> findAllActivities() {
		return _activities.values();
	}

	public Collection<Task> findAllTasks() {
		return _tasks.values();
	}

	public void saveTask(Task task) throws Exception {
		if (task == null)
			throw new Exception("Task reference is null");

		boolean newTask = false;
		Task tsk = _tasks.get(task.getId());
		if (tsk == null) {
			if (task.getId() == null) // add new task
				newTask = true;
			else
				throw new Exception("Invalid task");
		}
		if (task.getActivity() == null || _activities.get(task.getActivity().getId()) == null)
			throw new Exception("Invalid activity for task.");

		if (newTask) {
			task.setId(_tasks.size() + 1);
			_tasks.put(task.getId(), task);
		} else {
			tsk.setDate(task.getDate());
			tsk.setDuration(task.getDuration());
			tsk.setActivity(task.getActivity());
			tsk.setComment(task.getComment());
		}
	}

	private static Map<Integer, Task> fillTasks() {

		_activities = Collections.synchronizedMap(new LinkedHashMap<Integer, Activity>());
		Map<Integer, Task> tasks = Collections.synchronizedMap(new LinkedHashMap<Integer, Task>());

		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(0);
		cal.set(Calendar.YEAR, 2019);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 10);

		Activity activity = new Activity(1, "Driving");
		_activities.put(1, activity);
		tasks.put(1, new Task(1, cal.getTime(), 1.5, activity, "There was a traffic jam"));

		cal.set(Calendar.DAY_OF_MONTH, 10);

		activity = new Activity(2, "Fishing");
		_activities.put(2, activity);
		tasks.put(2, new Task(2, cal.getTime(), 5.5, activity, "It was exciting"));

		cal.set(Calendar.DAY_OF_MONTH, 12);

		activity = new Activity(3, "Shopping");
		_activities.put(3, activity);
		tasks.put(3, new Task(3, cal.getTime(), 3, activity, "It was boring"));

		return tasks;
	}

}
