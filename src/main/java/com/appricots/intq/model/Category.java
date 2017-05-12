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
	
	public static final String ANY = "*";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = NameOf.COLUMN_CAT_ID)
	long id;
	@Column(name=NameOf.COLUMN_CAT_ALIAS)
	String alias;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="categories")
	Set<Question> question;
	
	
	public Category() {
		this.id = -1;
		this.alias = NameOf.NOTHING;
	}
	
	public Category(long id, String alias, Set<Question> question) {
		super();
		this.id = id;
		this.alias = alias;
		this.question = question;
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
