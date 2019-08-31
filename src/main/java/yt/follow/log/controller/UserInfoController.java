package yt.follow.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yt.follow.log.model.UserInfoAO;
import yt.follow.log.model.UserInfoVO;
import yt.follow.log.service.IUserInfoService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author yunteng
 */
@RestController
@Slf4j
public class UserInfoController {

	@Autowired
	private IUserInfoService userInfoService;
	@Resource(name = "followLogThreadPool")
	private ExecutorService executorService;

	@GetMapping("get-all-user-info")
	public List<UserInfoVO> getAllUserInfo(UserInfoAO userInfoAO){
		executorService.submit(() -> userInfoService.getAllUserInfo(userInfoAO));
		executorService.submit(() -> userInfoService.getAllUserInfo(userInfoAO));
		executorService.submit(() -> userInfoService.getAllUserInfo(userInfoAO));

		log.info("getAllUserInfo====userId:{}",userInfoAO.getId());
		return new ArrayList<>();
	}
}
