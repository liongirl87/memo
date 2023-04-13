package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.common.EncryptUtils;
import com.memo.user.BO.UserBO;
import com.memo.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	/**
	 * 아이디 중복확인 API
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId")String loginId) {
		
		Map<String, Object> result = new HashMap<>();
		// select
		User user = userBO.getUserByLoginId(loginId);
		
		if (user != null) {
			result.put("code", 1);
			result.put("result", true);
		} else {
			
			result.put("code", 400);
			result.put("result", false);
		}
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email){
		
		// 비밀번호 해싱 - md5
		// aaaa => adldlkfkfkakak
		// aaaa => adldlkfkfkakak
		Map<String, Object> result = new HashMap<>();
		String hashedPassword = EncryptUtils.md5(password);
		// db insert
		
		
		if (userBO.addUser(loginId, hashedPassword, name, email) > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 400);
			result.put("errorMessage", "가입실패");
		}
		
		return result;
	}
}
