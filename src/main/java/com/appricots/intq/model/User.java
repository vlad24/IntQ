package com.appricots.intq.model;

import javax.persistence.*;

import com.appricots.intq.NameOf;

import java.util.Set;


@Entity
@Table(name = NameOf.TABLE_USER)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = NameOf.COLUMN_USER_ID)
    Long id;

    @Column(name = NameOf.COLUMN_USER_NAME)
    String name;
    @Column(name = NameOf.COLUMN_USER_LAST_NAME)
    String lastName;
    @Column(name = NameOf.COLUMN_USER_EMAIL)
    String email;
    @Column(name = NameOf.COLUMN_USER_AGE)
    int age;
    @Column(name = NameOf.COLUMN_USER_ACTIVENESS)
    long activeness;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = NameOf.COLUMN_USESSION_ID)
    UserSession session;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = NameOf.COLUMN_USER_CREDS_ID)
    UserCreds creds;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = NameOf.COLUMN_USER_AUTHORITY)
    Set<UserAuthority> authorities;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public long getActiveness() {
        return activeness;
    }


    public void setActiveness(long activeness) {
        this.activeness = activeness;
    }


    public UserSession getSession() {
        return session;
    }


    public void setSession(UserSession session) {
        this.session = session;
    }


    public UserCreds getCreds() {
        return creds;
    }


    public void setCreds(UserCreds creds) {
        this.creds = creds;
    }


    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }


    public void setAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }


    public User() {
    }


    public User(String name, String lastName, String email, int age, long activeness, UserSession session, UserCreds creds, Set<UserAuthority> authorities) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.activeness = activeness;
        this.session = session;
        this.creds = creds;
        this.authorities = authorities;
    }
}