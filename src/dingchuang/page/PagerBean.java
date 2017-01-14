package dingchuang.page;

import java.util.List;

public class PagerBean {
	public int countSize =0;// 总条数
	public int pageSize = 10;// 每页显示条数
	public int totalPage = 0;// 总页数
	public int curPage = 1;// 当前所在页
	private String hql = null;//hql语句
	public List<?> list = null;// 每页中数据
	public int firstPage = 1;// 第一页
	public int lastPage = 1;// 最后一页
	private String href;
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	public int getCountSize() {
		return countSize;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
		this.totalPage = this.countSize%this.pageSize==0?this.countSize/this.pageSize:this.countSize/this.pageSize+1;
		this.firstPage = this.totalPage>=1?1:0;
		this.lastPage = this.totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.totalPage = this.countSize%this.pageSize==0?this.countSize/this.pageSize:this.countSize/this.pageSize+1;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
