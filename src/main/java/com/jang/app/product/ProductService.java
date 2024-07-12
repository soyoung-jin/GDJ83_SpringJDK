package com.jang.app.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jang.app.util.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	public Map<String, Object> getList(Long page, String kind, String search) throws Exception {
		System.out.println("======= Service ========");
		
		// 페이지가 안넘어 오거나 1보다 작으면 페이지 1번 으로 고정
		if(page == null) {
			page = 1L;
		}
		
		if(page < 1) {
			page = 1L;
		}
		
		if(search == null) {
			search = "";
		}
		
		// page가   1이면  2     3
		// 첫번째 숫자 1    6     11
		// 두번째 숫자 5    10     15
		
		//한페이지에 5개씩 할때
//		long perPage =5L;
//		long startRow = (page-1) * perPage+1;
//		long lastRow = page * perPage;
		
		// page가   1이면  2     3
		// 첫번째 숫자 1    11    21
		// 두번째 숫자 10   20    30
		
		
		//한페이지에 10개씩 할때
		long perPage = 10L; // 한페이지에 몇개 화면에 나타낼 것인지 
		long startRow = (page - 1) * perPage + 1; // 처음 시작하는 row
		long lastRow = page * perPage; // 마지막 row
		
			
		List<Long> ar = new ArrayList<Long>();
		ar.add(startRow);
		ar.add(lastRow);
		
		Pager pager = new Pager();
		pager.setStartRow(startRow);
		pager.setLastRow(lastRow);
		pager.setKind(kind);
		pager.setSearch(search);
		
		// 1. 총 갯수로 총페이지수 구하기
		long totalCount = productDAO.getTotalCount(pager);
		long totalPage = totalCount/perPage;
		System.out.println("======TotalPage =======");
		 
		
		if(totalCount % perPage != 0) {
			totalPage++;
			System.out.println(totalPage);
		}
		
		// 2.총 페이지수로 총블럭수 구하기
		long perBlock = 5L; //한 페이지에 블럭을(1~5번/ 6~10번/ 11~15번) 몇개씩 보여줄 페이지 번호의 개수
		long totalBlock = 0;  //총 블럭의 수
		
		
		totalBlock = totalPage / perBlock; // 102/5 -> 20.4
		
	
		if(totalPage % perBlock !=0) {
			totalBlock++;
			System.out.println("==== TotalBlock ====");
			System.out.println(totalBlock);
		}
		
		// 3.현재 페이지 번호로 현재 블럭 번호를 구하기
		// page  1 2 3 4 5 6 78910 11
		// 블럭번호 1 1 1 1 1 2 2...2  3
		
		long curBlock =0;
		curBlock = page/perBlock;
		
		if(page % perBlock != 0) {
			curBlock++;
			System.out.println("=== curBlock =====");
			System.out.println(curBlock);
		}
		
//		4.현재 블럭 번호로 시작번호와 끝 번호 구하기		
		// curBlock(현재블록)  1  2  3  4  5  6~
		// start            1  1  1  1  1  6~    
		// last             5  5  5  5  5  10~   
		long startNum = (curBlock-1)*perBlock+1;
		long lastNum = curBlock * perBlock;
		 
		//5. 이전블록과 다음 블럭 유무 판단
		boolean pre = true; // true면 이전블럭이 존재, false면 이전블럭이 x
		boolean next = true; // true면 다음블럭이 존재, false면 다음블럭이 x
		
		if(curBlock == 1) {
			pre = false;
		}
		
		if(curBlock == totalBlock) {
			next = false;
			
			lastNum = totalPage;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", productDAO.getList(pager));
		map.put("totalPage" , totalPage);
		map.put("startNum", startNum);
		map.put("lastNum", lastNum);
		map.put("pre", pre);
		map.put("next", next);
		map.put("kind", kind);
		map.put("search", search);
		
		return map;

	}
	
	public ProductDTO detail(ProductDTO product_id) throws Exception {
		System.out.println("detail 서비스");
	  	return  productDAO.detail(product_id);
	}
	
	public int add(ProductDTO productDTO) throws Exception {
		return productDAO.add(productDTO);
	}
	
	public int delete(ProductDTO productDTO) throws Exception {
		return productDAO.delete(productDTO);
	}
	
	public int update(ProductDTO productDTO) throws Exception {
		return productDAO.update(productDTO);
	}

	
}
