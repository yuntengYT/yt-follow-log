package yt.follow.log.service;


import yt.follow.log.model.UserInfoAO;
import yt.follow.log.model.UserInfoVO;

import java.util.List;

/**
 * @author yunteng
 */
public interface IUserInfoService {

	List<UserInfoVO> getAllUserInfo(UserInfoAO userInfoAO);
}
