package com.appricots.intq.model;

import com.appricots.intq.NameOf;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = NameOf.TABLE_AUTHORITY)
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = NameOf.COLUMN_USER_AUTHORITY_ID)
    private String id;

    @Column(name = NameOf.COLUMN_USER_AUTHORITY_NAME)
    private String name;

    @Column(name = NameOf.COLUMN_USER_AUTHORITY_COMMENT)
    private String comment;

    @ManyToMany
    @JoinTable(name=NameOf.TABLE_USER_AUTHORITY_REL, joinColumns=
        @JoinColumn(name=NameOf.COLUMN_USER_AUTHORITY_ID), inverseJoinColumns=@JoinColumn(name=NameOf.COLUMN_USER_ID))
    private Set<User> users;


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


    public Set<User> getUsers() {
        return users;
    }


    public void setUsers(Set<User> users) {
        this.users = users;
    }
}