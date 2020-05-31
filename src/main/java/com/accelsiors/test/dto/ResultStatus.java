package com.accelsiors.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raju Khunt RK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultStatus {

	private Integer statusCode = 0;
	private String message;
	private String status = "Fail";

}
