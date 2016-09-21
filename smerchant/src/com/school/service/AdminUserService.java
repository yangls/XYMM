package com.school.service;

import com.school.bean.AdminUser;
import com.school.common.BaseDao;

public interface AdminUserService extends BaseDao<AdminUser> {

	/**
	 * 登陆验证
	 * @param user
	 * @param password
	 * @return
	 */
	AdminUser userLogin(String user, String password);

}
