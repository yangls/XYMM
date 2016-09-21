package com.school.service.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.school.bean.AdminUser;
import com.school.common.BaseDaoImpl;
import com.school.service.AdminUserService;

@Service
public class AdminUserServiceImpl extends BaseDaoImpl<AdminUser> implements AdminUserService{

	public AdminUser userLogin(String user, String password) {
		Query query=em.createQuery("from AdminUser t where t.userName=:userName and t.passWord=:passWord");
		
		query.setParameter("userName", user);
		query.setParameter("passWord", password);
		List<AdminUser> list =query.getResultList();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	

}
