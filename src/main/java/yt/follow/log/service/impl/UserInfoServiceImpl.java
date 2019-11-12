package yt.follow.log.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yt.follow.log.dao.UserInfoMapper;
import yt.follow.log.model.UserInfoAO;
import yt.follow.log.model.UserInfoDO;
import yt.follow.log.model.UserInfoVO;
import yt.follow.log.service.IUserInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yunteng
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public List<UserInfoVO> getAllUserInfo(UserInfoAO userInfoAO) {

		List<UserInfoDO> list = userInfoMapper.getAllUserInfo(userInfoAO.getId());
		if(CollectionUtils.isEmpty(list)) {
			return new ArrayList<>();
		}
		Map<Integer,Integer> map = new HashMap<>();
		List<UserInfoVO> retList = new ArrayList<>();
		list.forEach(userInfoDO -> {
			UserInfoVO userInfoVO = new UserInfoVO();
			BeanUtils.copyProperties(userInfoDO,userInfoVO);
			retList.add(userInfoVO);
		});
		log.info("getAllUserInfo==service==list:{}",JSON.toJSON(retList));
		return retList;
	}
}
