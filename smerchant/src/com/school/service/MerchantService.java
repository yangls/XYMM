package com.school.service;

import com.school.bean.merchant.MerChantInfo;
import com.school.common.BaseDao;

public interface MerchantService extends BaseDao<MerChantInfo>{

	MerChantInfo findByphone(String phone);

	MerChantInfo login(String phone, String password);

}
