package com.memo.post.model;

public class PostPagingDTO {
	
	// 총 포스팅 수
	private int totalPosts;
	
	// 총 페이지 (버튼)숫자
	private int totalPages;
	
	// 시작 페이지
	private int startPage;
	
	// 끝 페이지
	private int endPage;
	
	// 이전 페이지, 다음페이지 버튼 유무
	private boolean prevBtn, nextBtn;
	
	// 현재 페이지
	private int nowPageNum;
	
	// 한 페이지에 포스팅(게시글) 갯수 
	private static final int postsPerPage = 12;
	
	// 한 번에 노출되는 페이징(버튼) 갯수
	private static final int displayPageNum = 10;
	
	// MYSQL 에 넘겨줄 limit에서 사용할 스킵(skip)개수
	private int mysqlSkip;
	
	
	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public static int getDisplaypagenum() {
		return displayPageNum;
	}

	public static int getPostsperpage() {
		return postsPerPage;
	}

	public int getTotalPosts() {
		return totalPosts;
	}

	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrevBtn() {
		return prevBtn;
	}

	public void setPrevBtn(boolean prevBtn) {
		this.prevBtn = prevBtn;
	}

	public boolean isNextBtn() {
		return nextBtn;
	}

	public void setNextBtn(boolean nextBtn) {
		this.nextBtn = nextBtn;
	}

	public int getnowPageNum() {
		return nowPageNum;
	}

	public void setnowPageNum(int nowPageNum) {
		this.nowPageNum = nowPageNum;
	}

	public int getMysqlSkip() {
		return mysqlSkip;
	}

	public void setMysqlSkip(int mysqlSkip) {
		this.mysqlSkip = mysqlSkip;
	}
	
	public PostPagingDTO(int nowPageNum, int totalPosts) {
		
		this.totalPosts = totalPosts;
		this.nowPageNum = nowPageNum;
		
		this.mysqlSkip = (nowPageNum - 1)* postsPerPage;
		this.totalPages = ((totalPosts - 1)/ postsPerPage)+1;
		this.startPage = ((nowPageNum - 1) / displayPageNum) *displayPageNum +1;
		this.endPage = (((nowPageNum - 1) / displayPageNum) + 1) *displayPageNum;
		
		// tatalPages 가 endPage 보다 작은 경우 endPage값을 조정
		if (totalPages < endPage) {
			this.endPage = totalPages;
		}
		// 시작페이지 값이 1보다 큰경우 true
		this.prevBtn = this.startPage > 1;
		
		// 마지막 페이지값이 전체 총 페이지값보다 작은경우 true
		this.nextBtn = this.endPage < this.totalPages;
	}
	
}
