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
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=NameOf.COLUMN_DIFF_ID)
	long id;
	
	@Column(name=NameOf.COLUMN_DIFF_ALIAS, nullable=false, unique=true)
	String alias;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy = "difficulty")
	Set<Question> questions;
	
	@Column(name=NameOf.COLUMN_DIFF_METAFLAG, nullable=false)
	private boolean isMeta;
	
	
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

	public boolean isMeta() {
		return isMeta;
	}

	public void setMeta(boolean isMeta) {
		this.isMeta = isMeta;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return alias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Difficulty other = (Difficulty) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}

}
