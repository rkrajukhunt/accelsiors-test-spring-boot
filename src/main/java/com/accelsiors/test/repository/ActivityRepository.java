package com.accelsiors.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accelsiors.test.model.Activity;

/**
 * @author Raju Khunt RK
 *
 */
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	List<Activity> findAllByOrderByNameAsc();

}
