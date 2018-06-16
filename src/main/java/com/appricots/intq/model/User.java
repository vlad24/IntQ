package com.appricots.intq.model;

import com.appricots.intq.NameOf;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = NameOf.Table.USER)
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = NameOf.Column.USER_ID)
    Long id;
    @Column(name = NameOf.Column.USER_FIRST_NAME)
    String firstName;
    @Column(name = NameOf.Column.USER_LAST_NAME)
    String lastName;
    @Column(name = NameOf.Column.USER_EMAIL)
    String email;
    @Column(name = NameOf.Column.USER_AGE)
    int age;
    @Column(name = NameOf.Column.USER_ACTIVENESS)
    long activeness;
    @Column(name = NameOf.Column.USER_IS_BLOCKED)
    Boolean isBlocked;
    @Column(name = NameOf.Column.USER_IS_DELETED)
    Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = NameOf.Column.USESSION_ID)
    UserSession session;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = NameOf.Column.USER_CREDS_ID)
    UserCredentials credentials;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = NameOf.Table.LINK_USER_AUTHORITY, joinColumns =
    @JoinColumn(name = NameOf.Column.USER_ID), inverseJoinColumns = @JoinColumn(name = NameOf.Column.AUTHORITY_ID))
    Set<UserAuthority> authorities;


}