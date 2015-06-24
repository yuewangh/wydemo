package com.lqbw.model;


public class BaseModel implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4994848560358696240L;
			// 查询数据库获取总个数
			private int totalCount;
			// 总的页数
			private int pageCount;
			// 每页的条数
			private int pageSize = 10;
			// 当前页数
			private int pageIndex = 1;
			// 开始位置
			private int startPos;
			// 结束位置
			private int endPos;
			
			public void calculatePage() {
				// 获取总页数
				if (totalCount % pageSize == 0) {
					pageCount = totalCount / pageSize;
				} else {
					pageCount = totalCount / pageSize + 1;
				}
				// 计算开始记录以及结束记录
				// pageindex == 1 则 startPos = 0；endPos = 3
				// pageindex == 1  则 startPos = 3；endPos = 6
				startPos = (pageIndex-1) * pageSize;
				endPos = pageIndex * pageSize;
			}
			
			public int getTotalCount() {
				return totalCount;
			}
			public void setTotalCount(int totalCount) {
				this.totalCount = totalCount;
			}
			public int getPageCount() {
				return pageCount;
			}
			public void setPageCount(int pageCount) {
				this.pageCount = pageCount;
			}
			public int getPageSize() {
				return pageSize;
			}
			public void setPageSize(int pageSize) {
				this.pageSize = pageSize;
			}
			public int getPageIndex() {
				return pageIndex;
			}
			public void setPageIndex(int pageIndex) {
				this.pageIndex = pageIndex;
			}
			public int getStartPos() {
				return startPos;
			}
			public void setStartPos(int startPos) {
				this.startPos = startPos;
			}
			public int getEndPos() {
				return endPos;
			}
			public void setEndPos(int endPos) {
				this.endPos = endPos;
			}
			
			
	}
