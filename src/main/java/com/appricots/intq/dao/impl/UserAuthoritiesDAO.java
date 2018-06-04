package com.appricots.intq.dao.impl;

import com.appricots.intq.model.User;
import com.appricots.intq.model.UserAuthority;
import com.appricots.intq.model.UserCreds;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class UserAuthoritiesDAO extends DAO<UserAuthority, Long>{

}
