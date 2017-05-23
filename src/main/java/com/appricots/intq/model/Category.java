package com.appricots.intq.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.appricots.intq.NameOf;

@Entity
@Table(name = NameOf.TABLE_CAT)
public class Category {
	
	public static final String ANY_ALIAS = "*";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = NameOf.COLUMN_CAT_ID)
	Long id;
	@Column(name=NameOf.COLUMN_CAT_ALIAS)
	String alias;
	@Column(name = NameOf.COLUMN_CAT_METAFLAG)
	boolean isMeta;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="categories")
	Set<Question> questions;
	
	
	public Category() {
		this.alias = NameOf.NOTHING;
		this.questions = null;
		isMeta = false;
	}
	
	public Category(String alias) {
		super();
		this.alias = alias;
		isMeta = false;
	}
	
	public Category(String alias, Set<Question> question, boolean isMeta) {
		super();
		this.alias = alias;
		this.questions = question;
		this.isMeta = isMeta;
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
	
	
	
	public boolean isMeta() {
		return isMeta;
	}

	public void setMeta(boolean isMeta) {
		this.isMeta = isMeta;
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
		Category other = (Category) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}

	
	
}
