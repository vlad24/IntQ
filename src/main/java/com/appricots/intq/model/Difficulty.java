package com.appricots.intq.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.appricots.intq.NameOf;

@Entity
@Table(name=NameOf.TABLE_DIFFICULTY)
public class Difficulty {
	public static final String ANY = "*";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=NameOf.COLUMN_DIFF_ID)
	long id;
	
	@Column(name=NameOf.COLUMN_DIFF_ALIAS, nullable=false, unique=true)
	String alias;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy = "difficulty")
	Set<Question> questions;
	
	
	
	public Difficulty() {
		this.id = -1;
		this.alias = NameOf.NOTHING;
		this.questions = null;
	}
	
	public Difficulty(String alias) {
		super();
		this.alias = alias;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return alias;
	}

}
