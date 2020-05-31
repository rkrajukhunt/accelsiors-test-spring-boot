package com.accelsiors.test.dto;

import groovy.transform.builder.Builder;
import lombok.Data;

/**
 * @author Raju Khunt RK
 *
 */

@Builder
@Data
public class ResultStatus {

	private Integer statusCode = 0;
	private String message;
	private String status = "Fail";

}
