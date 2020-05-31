package com.accelsiors.test.dto;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raju Khunt RK
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskVO {

	private Integer id;
	private String date;
	private double duration;
	private String comment;

	private Integer activityId;
}
