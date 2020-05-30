package com.accelsiors.test.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Task entities
 * @author ZoltanS
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date date;
	private double duration;
	private String comment;
	
	private Activity activity;
	
	public Task( Integer id, Date date, double duration, Activity activity, String comment ) {
		this.id = id;
		this.date = date;
		this.duration = duration;
		this.activity = activity;
		this.comment = comment;
	}	
}
