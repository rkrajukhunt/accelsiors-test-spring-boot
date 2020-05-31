package com.accelsiors.test.service;

import java.util.List;

import com.accelsiors.test.dto.ResultStatus;
import com.accelsiors.test.dto.TaskVO;
import com.accelsiors.test.model.Activity;
import com.accelsiors.test.model.Task;

/**
 * @author Raju Khunt RK
 *
 */
public interface TaskService {

	public List<Activity> getActivitirs();

	public List<Task> getTasks();

	public ResultStatus saveTask(TaskVO taskVO);

	public ResultStatus editTask(TaskVO taskVO);
	public TaskVO getTaskById(Integer id);

}
