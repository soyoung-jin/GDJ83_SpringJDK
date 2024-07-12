<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<h1>금융상품 리스트</h1>
	<div class="row col-md-4 ms-5 mt-5">
		<!-- 검색어 입력 폼 -->	
		<form action="./list" method ="get" class="row row-cols-lg-auto g-3 align-items-center">
				 
			<div class="col-12">
			    <label class="visually-hidden" for="inlineFormSelectPref">Preference</label>
			    <select name="kind" class="form-select" id="inlineFormSelectPref">
			      <option value="k1">상품명</option>
			      <option value="k2">상품내용</option>
		    	</select>
		  </div>

		
		  <div class="col-12">
		    <label class="visually-hidden" for="inlineFormInputGroupUsername">Username</label>
		    <div class="input-group">		 
		      <input type="text" name="search" class="form-control" id="inlineFormInputGroupUsername" placeholder="Username">
		    </div>
		  </div>
		

		
		
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary">Submit</button>
		  </div>
		</form>
	
	
	
	
	
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>상품명</th>
					<th>이자율</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${map.list}" var="map">
					<tr>
						<td>${map.product_id}</td>
						<td><a href="detail?product_id=${map.product_id}">${map.product_type}</a></td>
						<td>${map.product_rate}</td>
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</div>
		<nav aria-label="Page navigation example">
	  <ul class="pagination">
	  
	  

	  
		<li class="page-item ${map.pre?'':'disabled'}">
		  <a class="page-link" href="./list?page=${map.startNum-1}&kind=${map.kind}&search=${map.search}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>  
	    </li>
	
	    <!--  for(int i=0; i<=10; i=i+2-->
	    <c:forEach begin="${map.startNum}" end="${map.lastNum}" step="1" var="i">
	    	<li class="page-item"><a class="page-link" href="list?page=${i}&kind=${map.kind}&search=${map.search}">${i}</a></li>    
	    </c:forEach>
	
		<li class="page-item ${map.next?'':'disabled'}">
			<a class="page-link" href="./list?page=${map.lastNum+1}&kind=${map.kind}&search=${map.search}" aria-label="Next">
	        	<span aria-hidden="true">&raquo;</span>
	     	</a>
	    </li>
	  </ul>
	</nav>
	<div>
		<a href="add" class="btn btn-primary" role="button">상품등록</a>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>