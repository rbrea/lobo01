package com.icetea.manager.pagodiario.dao.pagination;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;
import com.icetea.manager.pagodiario.dao.BasicDao;

public class PaginationContainer extends BasicOutputDto {
	private static final long serialVersionUID = 1L;

	private int activePage = 0;
	private int itemsPerPage = BasicDao.PAGE_SIZE_DEFAULT;
	private int totalPages = 0;
	private int totalRows = 0;
	
	private List<?> list = Lists.newArrayList();
	
	public PaginationContainer() {
		super();
	}
	
	public PaginationContainer(List<?> list) {
		this();
		this.list = list;
	}

	public int getActivePage() {
		return activePage;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

}
