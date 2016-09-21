package com.school.service;

import com.school.bean.account.ImgPath;
import com.school.common.BaseDao;

public interface ImgService extends BaseDao<ImgPath>{

	int deleteByNewNmae(String name);

	String findByNewNmae(String name);

}
