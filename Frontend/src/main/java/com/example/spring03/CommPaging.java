package com.example.spring03;


public class CommPaging {

	private int totalPage;
	private int startPage;
	private int endPage;
	private int startRow;
	private int endRow;
	private int nowPage;
	private int prev10;
	private int next10;
	boolean last10 = false;
	private String resultString;
	
	public CommPaging(PageDto page, int recordSize, int blockSize, String method) {
		//nowPage 현재페이지
		//totalCount 총 결과값 갯수
		//recordSize 한페이지에 보여줄 row갯수
		//blockSize [1][2] <--이거 갯수
		
		// 총블럭 수 구하기
		this.totalPage = (int)Math.ceil((double)page.getTotalCount() / (double)recordSize);
		
		// 현재페이지는 마지막 페이지보다 작아야 한다.
		if(this.totalPage < page.getNowPage()) {
			this.nowPage = this.totalPage;
		} else {
			this.nowPage = page.getNowPage();
		}
		
		if(this.totalPage == 0) {
			this.nowPage = 1;
		}
		
		//결과값을 위한 범위값 구하기
		this.startRow = (int) (this.nowPage - 1) * recordSize + 1;
		this.endRow = (int) this.startRow + recordSize - 1;
		
		//[1][2] <-- 이거 시작과 끝 구하기
		this.startPage = (int)((this.nowPage -1) / blockSize) * blockSize + 1;
		this.endPage = (int)this.startPage + blockSize - 1;

		if(page.getNowPage() < 11) {
			prev10 = 1;
		} else {
			prev10 = (this.nowPage - 1) / 10 * 10 - 9;	
		}
		
		next10 = this.endPage + 1;
		//마지막 페이지 번호 보정
		if(this.endPage > this.totalPage) {
			this.endPage = totalPage;
			next10 -= 10;
			last10 = true;
		}
		
		StringBuilder result = new StringBuilder();
		
		result.append("<form id='form' method='post' enctype='application/x-www-form-urlencoded'>");
		result.append("<input type='hidden' name='nowPage' id='now_page' value='" + nowPage  + "' />");
		result.append("<ul class='paging_list'>");
		//이전버튼 생성
		if(nowPage > 1) {
			if(nowPage > 10) {
				result.append("<li class='prev pointer'><a href='javascript:" + method + "(");
				result.append(prev10);
				result.append(")'><i class='fa fa-angle-double-left'></i></a></li>");
			} else {
				result.append("<li class='prev pointer'><a href='javascript:" + method +"(");
				result.append("1");
				result.append(")'><i class='fa fa-angle-double-left'></i></a></li>");
			}
			
			result.append("<li class='prev pointer'><a href='javascript:" + method + "(");
			result.append(nowPage-1);
			result.append(")'><i class='fa fa-angle-left'></i></a></li>");
			
		} else {

			result.append("<li class='prev pointer'><a href='javascript:" + method +"(");		
			result.append("1");
			result.append(")'><i class='fa fa-angle-double-left'></i></a></li>");
		
			result.append("<li class='prev pointer'><a href='javascript:;'>");	
			result.append("<i class='fa fa-angle-left'></i></a></li>");
			
		}
		if(endPage == 0 || endPage == 1) {
			result.append("<li class='active pointer'><a href='javascript:void(0);'>1</a></li>");
		} else {
			for(int i=startPage; i<=endPage; i++) {
				if(nowPage == i) {
					result.append("<li class='active pointer' id='pgn_"+i+"'><a href='javascript:" + method + "(");
					result.append(i);
					result.append(")' title='" + i + "'> ");
					result.append(i);
					result.append(" </a></li>");
				} else {
					result.append("<li class='pointer' id='pgn_"+i+"'><a href='javascript:" + method + "(");
					result.append(i);
					result.append(")' title='" + i + "'> ");
					result.append(i);
					result.append(" </a></li>");
				}
			}
		}
		
		if(nowPage < totalPage) {
			result.append("<li class='next pointer'><a href='javascript:" + method + "(");
			result.append(nowPage + 1);
			result.append(")'><i class='fa fa-angle-right'></i></a></li>");
			
			if(last10) {			
				result.append("<li class='next pointer'><a href='javascript:" + method + "(");
				result.append(totalPage);
				result.append(")'><i class='fa fa-angle-double-right'></i></a></li>");
			} else {
				result.append("<li class='next pointer'><a href='javascript:" + method + "(");
				result.append(next10);
				result.append(")'><i class='fa fa-angle-double-right'></i></a></li>");
			}	
		} else {
			result.append("<li class='next pointer'><a href='javascript:;'>");
			result.append("<i class='fa fa-angle-right'></i></a></li>");
			
			//끝 페이지
			result.append("<li class='next pointer'><a href='javascript:;'>");
			result.append("<i class='fa fa-angle-double-right'></i></a></li>");
		}
		
		result.append("</ul>");
		result.append("</form>");
		resultString = result.toString();
		
	}

	public String resultString() {
		return resultString;
	}
	
	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
}
