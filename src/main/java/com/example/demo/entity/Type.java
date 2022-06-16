package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class Type {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "type_name")
	private String typeName;

	@Column(name = "desire")
	private String desire;

	@Column(name = "type_key")
	private String typeKey;

	@Column(name = "firstMeeting")
	private String firstMeeting;

	@Column(name = "happy_word")
	private String happyWord;

	@Column(name = "taste")
	private String taste;

	@Column(name = "stress")
	private String stress;

	@Column(name = "strengths")
	private String strengths;

	@Column(name = "task")
	private String task;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDesire() {
		return desire;
	}

	public void setDesire(String desire) {
		this.desire = desire;
	}

	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public String getFirstMeeting() {
		return firstMeeting;
	}

	public void setFirstMeeting(String firstMeeting) {
		this.firstMeeting = firstMeeting;
	}

	public String getHappyWord() {
		return happyWord;
	}

	public void setHappyWord(String happyWord) {
		this.happyWord = happyWord;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getStress() {
		return stress;
	}

	public void setStress(String stress) {
		this.stress = stress;
	}

	public String getStrengths() {
		return strengths;
	}

	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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
