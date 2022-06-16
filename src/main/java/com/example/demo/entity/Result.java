package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "results")
public class Result {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "analyze")
	private Integer analyze;

	@Column(name = "drive")
	private Integer drive;

	@Column(name = "volunteer")
	private Integer volunteer;

	@Column(name = "create")
	private Integer create;

	@Column(name = "type_id")
	private Integer typeId;

	@Column(name = "open")
	private Integer open;

	@Column(name = "created_date", insertable = false, updatable = false)
	private Timestamp createdDate;

	@Column(name = "updated_date", insertable = false)
	private Timestamp updatedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAnalyze() {
		return analyze;
	}

	public void setAnalyze(Integer analyze) {
		this.analyze = analyze;
	}

	public Integer getDrive() {
		return drive;
	}

	public void setDrive(Integer drive) {
		this.drive = drive;
	}

	public Integer getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Integer volunteer) {
		this.volunteer = volunteer;
	}

	public Integer getCreate() {
		return create;
	}

	public void setCreate(Integer create) {
		this.create = create;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}
