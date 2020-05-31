/**
 * @author Raju Khunt RK
 * 
 */
package com.accelsiors.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raju Khunt RK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskVO {

	private Integer id;
	private String date;
	private double duration;
	private String comment;

	private Integer activityId;
}
