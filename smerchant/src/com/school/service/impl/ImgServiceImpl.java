package com.school.service.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.school.bean.account.ImgPath;
import com.school.common.BaseDaoImpl;
import com.school.service.ImgService;

@Service
public class ImgServiceImpl extends BaseDaoImpl<ImgPath> implements ImgService{

	public int deleteByNewNmae(String name) {
		
		Query query=em.createQuery("delete ImgPath where newName=:newName");
		query.setParameter("newName", name);
		int count=query.executeUpdate();
		return count;
	}

	public String findByNewNmae(String name) {
		Query query=em.createQuery("from ImgPath where newName=:newName");
		query.setParameter("newName", name);
		List<ImgPath> list=query.getResultList();
		if(list.size()>0){
			return list.get(0).getPathName();
		}
		return null;
	}

}
