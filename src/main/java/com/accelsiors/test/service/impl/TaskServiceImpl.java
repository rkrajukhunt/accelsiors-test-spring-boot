package com.accelsiors.test.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accelsiors.test.dto.ResultStatus;
import com.accelsiors.test.dto.TaskVO;
import com.accelsiors.test.model.Activity;
import com.accelsiors.test.model.Task;
import com.accelsiors.test.repository.ActivityRepository;
import com.accelsiors.test.repository.TaskRepository;
import com.accelsiors.test.service.TaskService;
import com.accelsiors.util.IConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Raju Khunt RK
 *
 */

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Activity> getActivitirs() {
		log.info("Enter into the getActivitirs ");
		try {
			return activityRepository.findAllByOrderByNameAsc();
		} catch (Exception e) {
			log.debug("Exception raised at getActivitirs in  TaskServiceImpl class", e);
			return null;
		}
	}

	@Override
	public List<Task> getTasks() {
		log.info("Enter into the getTasks ");

		try {
			return taskRepository.findAllByOrderByDateDesc();
		} catch (Exception e) {
			log.debug("Exception raised at getTasks in  TaskServiceImpl class", e);
			return null;
		}

	}

	public TaskVO getTaskById(Integer id) {
		log.info("Enter into the getTasks ");

		try {
			Task task = taskRepository.findById(id).get();
			if (task != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(IConstant.taskDateFormate);

				TaskVO vo = new TaskVO();
				vo.setActivityId(task.getActivity().getId());
				vo.setComment(task.getComment());
				vo.setDate(sdf.format(task.getDate()));
				vo.setId(task.getId());
				vo.setDuration(task.getDuration());
				return vo;
			}

		} catch (Exception e) {
			log.debug("Exception raised at getTaskById in  TaskServiceImpl class", e);

		}
		return null;
	}

	@Override
	public ResultStatus saveTask(TaskVO taskVO) {
		log.info("Enter into the saveTask ");

		ResultStatus status = new ResultStatus();
		try {
			// validate here
			SimpleDateFormat sdf = new SimpleDateFormat(IConstant.taskDateFormate);

			Optional<Task> optTask = taskRepository.findByActivityIdAndDate(taskVO.getActivityId(),
					sdf.parse(taskVO.getDate()));

			Optional<Activity> otpActivity;

			if (!optTask.isEmpty()) {
				status.setMessage("Activity already exist on this date.");
				return status;
			} else {
				otpActivity = activityRepository.findById(taskVO.getActivityId());
			}

			Task task = new Task();

			task.setComment(taskVO.getComment());
			task.setDuration(taskVO.getDuration());
			task.setDate(sdf.parse(taskVO.getDate()));
			task.setActivity(otpActivity.get());
			taskRepository.save(task);
			status.setMessage("Task Saved Successful...");
			status.setStatusCode(1);
			status.setStatus("success");
		} catch (Exception e) {
			log.debug("Exception raised at saveTask in  TaskServiceImpl class", e);
			status.setMessage("Task Saving Failed...");
		}
		return status;

	}

	@Override
	public ResultStatus editTask(TaskVO taskVO) {
		log.info("Enter into the editTask ");

		ResultStatus status = new ResultStatus();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(IConstant.taskDateFormate);

			Optional<Task> optTask = taskRepository.findByActivityIdAndDateAndIdNot(taskVO.getActivityId(),
					sdf.parse(taskVO.getDate()), taskVO.getId());

			Optional<Activity> otpActivity;

			if (!optTask.isEmpty()) {
				status.setMessage("Activity already exist on this date.");
				return status;
			} else {
				otpActivity = activityRepository.findById(taskVO.getActivityId());
			}
			Optional<Task> existingTask = taskRepository.findById(taskVO.getId());
			Task task = existingTask.get();

			task.setComment(taskVO.getComment());
			task.setDuration(taskVO.getDuration());
			task.setDate(sdf.parse(taskVO.getDate()));
			task.setActivity(otpActivity.get());
			taskRepository.save(task);
			status.setMessage("Task Updated Successful...");
			status.setStatusCode(1);
			status.setStatus("success");
		} catch (Exception e) {
			log.debug("Exception raised at editTask in  TaskServiceImpl class", e);
			e.printStackTrace();
			status.setMessage("Task Updation Failed...");
		}
		return status;

	}
}
