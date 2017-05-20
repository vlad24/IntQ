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
@Table(name=NameOf.TABLE_LANG)
public class Lang {
	
	public static final String ANY = "*";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = NameOf.COLUMN_LANG_ID)
	long id;

	@Column(name=NameOf.COLUMN_LANG_ALIAS)
	String alias;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="lang")
	Set<Question> questions;
	
	
	
	public Lang() {
		this.id = -1;
		this.alias = NameOf.NOTHING;
	}
	
	public Lang(String alias) {
		this.alias = alias;
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

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return alias;
	}	
	
}
