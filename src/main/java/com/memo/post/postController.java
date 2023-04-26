package com.memo.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.post.BO.PostBO;
import com.memo.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class postController {
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/post_list_view")
	public String postListView(
			@RequestParam(value = "prevId", required=false) Integer prevIdParam,
			@RequestParam(value = "nextId", required=false) Integer nextIdParam,
			Model model,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			// 비로그인이면 로그인 페이지로 이동
			return "redirect:/user/sign_in_view";
		}
		int prevId = 0;
		int nextId = 0;
		List<Post> postList = postBO.getPostListByUserId(userId, prevIdParam, nextIdParam);
		if (postList.isEmpty() == false) {	//postList가 비어있을 때 에러 방지 List는 null경우가 없다 비어있을떄도 [] 그래서 메소드를 사용함
			// 리스트가 비어 있지 않으면 처리
			prevId = postList.get(0).getId();
			nextId = postList.get(postList.size() - 1).getId(); //.size() 함수로 크기구한 후 -1 하면 마지막 리스트 번호
		
			// 이전 방향의 끝인가?
			// prevId와 post 테이블의 가장 큰 id와 같따면 이전 페이지 없음
			if (postBO.isPrevLastPage(userId, prevId)) {
				prevId=0;
			}
			
			
			// 다음 방향의 끝인가?
			// nextId와 post 테이블의 가장 작은 id와 같다면 다음 페이지 없음
			if (postBO.isNextLastPage(userId, nextId)) {
				nextId = 0;
			}
		
		}
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		
		model.addAttribute("view", "post/postList");
		model.addAttribute("postList", postList);
		
		
		return "template/layout";
	}
	
	/**
	 * 글쓰기 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/post_create_view")
	public String postCreateView(Model model) {
		model.addAttribute("view","post/postCreate");
		
		return "template/layout";
	}
	@GetMapping("/post_detail_view")
	public String postDetailView(
			@RequestParam("postId") int postId,
			HttpSession session,
			Model model) {
		
		// db select by postId, (userId)
		int userId = (int)session.getAttribute("userId");
		Post post = postBO.getPostByPostIdUserId(postId, userId);
		

		model.addAttribute("post", post);
		model.addAttribute("view", "post/postDetail");
		
		return "template/layout";
	}
}
