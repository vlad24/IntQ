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

	public static final Lang ANY = new Lang("*");

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = NameOf.COLUMN_LANG_ID)
	long id;

	@Column(name=NameOf.COLUMN_LANG_ALIAS)
	String alias;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="lang")
	Set<Question> questions;
	
	@Column(name=NameOf.COLUMN_LANG_METAFLAG, nullable=false)
	private boolean isMeta;
	
	
	
	public Lang() {
		this.id = -1;
		this.alias = NameOf.NOTHING;
		this.isMeta = false;
	}
	
	public Lang(String alias) {
		this();
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
		Lang other = (Lang) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}	
	
}
