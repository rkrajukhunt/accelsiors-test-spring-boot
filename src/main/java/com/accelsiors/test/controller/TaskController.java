package com.accelsiors.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accelsiors.test.dto.ResultStatus;
import com.accelsiors.test.dto.TaskVO;
import com.accelsiors.test.model.Activity;
import com.accelsiors.test.model.Task;
import com.accelsiors.test.service.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Raju Khunt RK
 *
 */

@Slf4j
@Controller
@RequestMapping("/")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/task-view")
	public String getTasks(Model model) {
		List<Task> tasks = taskService.getTasks();
		model.addAttribute("tasks", tasks);
		return "task-view";
	}

	@RequestMapping(value= {"task-edit" },method = RequestMethod.GET)
	public String getActivites(Model model, @RequestParam(name="id",required = false) Optional<Integer> taskId) {
		try {
				List<Activity> activities = taskService.getActivitirs();
			 	model.addAttribute("activities", activities);

				TaskVO task = null;
				model.addAttribute("taskRoute","/save-task");
			if(taskId.isPresent() && taskId.get() > 0 ) {
				model.addAttribute("taskRoute","/update-task");
				task = taskService.getTaskById(taskId.get());
			}
			model.addAttribute("task", task != null ? task : new TaskVO());
			model.addAttribute("errorMessage","");

		} catch (Exception e) {
			// TODO: handle exception
			log.debug("getting error");
		}
		return "task-edit";
	}

	@PostMapping("/save-task")
	public String saveTask( TaskVO taskVO,Model model) {
		try {
			 ResultStatus saveTask = taskService.saveTask(taskVO);
			 if(saveTask.getStatusCode() == 1) {
				 return "redirect:/task-view";
			 }
			 List<Activity> activities = taskService.getActivitirs();
			 	model.addAttribute("activities", activities);
			
				model.addAttribute("taskRoute","/save-task");
			
				model.addAttribute("task", taskVO);
				model.addAttribute("errorMessage", saveTask.getMessage());
		}catch (Exception e) {
			log.debug("getting error saveTask ");

		}
		return "task-edit";
	}

	@PostMapping("/update-task")
	public String updateTask(TaskVO taskVO,Model model) {
		log.info("payload :- {}", taskVO.toString());
		try {
			 ResultStatus editTask = taskService.editTask(taskVO);
			 if(editTask.getStatusCode() == 1) {
				 return "redirect:/task-view";
			 }
			 List<Activity> activities = taskService.getActivitirs();
			 	model.addAttribute("activities", activities);
		
			 	model.addAttribute("taskRoute","/update-task");
		
			model.addAttribute("task", taskVO);
			model.addAttribute("errorMessage", editTask.getMessage());
		}catch (Exception e) {
			log.debug("getting error updateTask ");
		}
		return "task-edit";
	}
}