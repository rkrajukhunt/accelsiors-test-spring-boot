package com.accelsiors.test.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accelsiors.test.model.Task;

/**
 * @author Raju Khunt RK
 *
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findAllByOrderByDateDesc();

	Optional<Task> findByActivityIdAndDate(Integer activityId, Date date);

	Optional<Task> findByActivityIdAndDateAndIdNot(Integer activityId, Date date, Integer id);

}
