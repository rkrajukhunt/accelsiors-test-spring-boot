package com.accelsiors.test.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Task entities
 * 
 * @author ZoltanS
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	private double duration;
	private String comment;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Activity.class)
	@Type(type = "uuid-char")
	private Activity activity;

	public Task(Integer id, Date date, double duration, Activity activity, String comment) {
		this.id = id;
		this.date = date;
		this.duration = duration;
		this.activity = activity;
		this.comment = comment;
	}

}
