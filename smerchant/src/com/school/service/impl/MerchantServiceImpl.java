package com.school.service.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.school.bean.AdminUser;
import com.school.bean.merchant.MerChantInfo;
import com.school.common.BaseDaoImpl;
import com.school.service.MerchantService;

@Service
public class MerchantServiceImpl extends BaseDaoImpl<MerChantInfo> implements MerchantService{

	public MerChantInfo findByphone(String phone) {
		
		Query query=em.createQuery("from MerChantInfo t where t.phone=:phone");
		
		query.setParameter("phone", phone);
		List<MerChantInfo> list =query.getResultList();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public MerChantInfo login(String phone, String password) {
		Query query=em.createQuery("from MerChantInfo t where t.phone=:phone and t.passWord=:password");
		
		query.setParameter("phone", phone);
		query.setParameter("password",password);
		List<MerChantInfo> list =query.getResultList();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
