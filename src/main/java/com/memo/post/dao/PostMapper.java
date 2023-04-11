package com.memo.post.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
	
	//input:x	output:list
	public List<Map<String, Object>> selectPostList();
}
