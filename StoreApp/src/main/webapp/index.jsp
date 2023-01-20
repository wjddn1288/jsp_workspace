<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
	#map{
		width:100%;
		height:100%;
	}
</style>
<script type="text/javascript">
let map;

function createOption(categoryList){
	
	let op="<option value='0'>선택하세요</option>";
	
	for(let i=0; i<categoryList.length;i++){
		let category=categoryList[i]; //
		op+="<option value='"+category.category_idx+"'>"+category.category_name+"</option>";
	}
	
	$("#category_idx").html(op); //innerHTML
}

//맛집의 카테고리 가져오기(비동기 방식으로 데이터만 백그라운드로 가져오기)
	function getCategoryList() {
		let xhttp = new XMLHttpRequest();//ajax의 비동기 통신 객체
		xhttp.open("GET","/store/category_list.jsp");
		xhttp.onreadystatechange=function(){
			if(this.readyState==4 && this.status==200){
				
				//문자열에 불과한 데이터를, 파싱하여 실제 JSON내장 객체로 변환!!
				console.log(this.responseText);
				let categoryList=JSON.parse(this.responseText);
				console.log(categoryList);
				console.log("카테고리 수는", categoryList.length);
				
				//옵션태그에 반영하기
				createOption(categoryList);
			}
		}
		
		xhttp.send(); // 호스팅 환경인 크롬브라우저 요청 출발!!
	}
	
	function loadMap() {
		var mapProp = {
			center : new google.maps.LatLng(37.5587939,126.9396418),
			zoom : 15,
		};
		map = new google.maps.Map(document.getElementById("map"), mapProp);
		
		getStoreList(map); //맵 가져오기
	}
	
//마커 생성하기
function createMarker(map, storeList){
	for(let i=0; i<storeList.length; i++){ //맛집 수만큼 마커를 생성하자!! json 배열...
		let store = storeList[i]; //맛집 하나 추출 
		
		var mapProp= {
			center:new google.maps.LatLng(store.lati , store.longi),
			zoom:15,
		};	
		
		var marker = new google.maps.Marker({position: mapProp.center});
		marker.setMap(map);
	}
}

//모든 맛집 리스트 가져오기 위한 비동기 요청
function getStoreList(map){
	let xhttp = new XMLHttpRequest();
	xhttp.open("get", "/store/store_list.jsp");
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			//서버에서 가져온 json 목록을 이용하여 지도에 마커표시!!
			console.log(this.responseText);
			let jsonList=JSON.parse(this.responseText);
			
			createMarker(map, jsonList);
			
			createTableList(jsonList);//테이블 처리~
		}
	};
	xhttp.send();
}

//post 전송+비동기
//text : content-type : application/x-www-form-urlencoded
//binary : content-type : multipart/form-data
function regist(){
	let xhttp = new XMLHttpRequest();
	xhttp.open("post","/store/regist.jsp");
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	let data="lati="+$("input[name='lati']").val();
	data+="&longi="+$("input[name='longi']").val();
	data+="&category_idx="+$("#category_idx").val();
	data+="&store_name="+$("input[name='store_name']").val();
	
	//서버와의 통신 상태 체크
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			console.log("등록결과는",this.responseText);
			//모든 상점 가져오기
			getStoreList(map); //목록 가져오기 비동기 요청!!
		}
	};
	xhttp.send(data);
}

	$(function() {
		$("#bt_regist").click(function() {
			regist();
		});
		//온로드할때
		getCategoryList(); // 카테고리 리스트  
		getStoreList(); //맛집 리스트
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row mt-2">
			<div class="col-md-2 border" >
				<div class="form-group mt-2">
					<input class="form-control" placeholder="위도" name="lati">
				<div class="form-group mt-2">
					<input class="form-control" placeholder="경도" name="longi">
				</div>
				</div>
				<div class="form-group mt-2">
					<select class="form-control" id="category_idx"></select> 
				</div>
				<div class="form-group mt-2">
					<input class="form-control" placeholder="상호명" name="store_name">
				</div>
				<div class="form-group mt-2">
					<button id="bt_regist" type="button" class="form-control">맛집등록</button>
				</div>
				<!-- 맛집목록 -->
				<div style="overflow:scroll">
					<table class="table">
					    <thead class="thead-dark">
					      <tr>
					        <th>상호명</th>
					      </tr>
					    </thead>
					    <tbody>
					      <tr>
					        <td>John</td>
					      </tr>
					    </tbody>
					  </table>
				  </div>
			</div>
			<div class="col-md-10 border">
				<div id="map"></div>
			</div>
		</div>
	</div>
</body>
</html>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAWKAbQopG5gQ3ZZy2MYsjls4sD67R5EDk&callback=loadMap"></script>