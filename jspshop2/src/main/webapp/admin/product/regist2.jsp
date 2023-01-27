<%@page import="com.jspshop.exception.PimgException"%>
<%@page import="com.jspshop.exception.ColorException"%>
<%@page import="com.jspshop.exception.PsizeException"%>
<%@page import="com.jspshop.repository.PimgDAO"%>
<%@page import="com.jspshop.repository.PsizeDAO"%>
<%@page import="com.jspshop.repository.ColorDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.jspshop.util.MessageObject"%>
<%@page import="com.jspshop.exception.ProductException"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.jspshop.mybatis.MybatisConfig"%>
<%@page import="com.jspshop.repository.ProductDAO"%>
<%@page import="com.jspshop.util.FileManager"%>
<%@page import="com.jspshop.domain.Color"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jspshop.domain.Pimg"%>
<%@page import="com.jspshop.domain.Psize"%>
<%@page import="com.jspshop.domain.Category"%>
<%@page import="com.jspshop.domain.Product"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page contentType="application;json=UTF-8"%>
<%!
	ProductDAO productDAO=new ProductDAO();
	PsizeDAO psizeDAO=new PsizeDAO();
	ColorDAO colorDAO=new ColorDAO();
	PimgDAO pimgDAO=new PimgDAO();
	
	MybatisConfig mybatisConfig=MybatisConfig.getInstance();
%>
<%
	int maxSize=5*(1024*1024); //5MB
	String path="/data/"; 
	// 선언부에서는 서비스메서드 영역이 아니라서 내장객체를 쓸 수 없다.
	// 멤버변수라서 메모리에 올릴수 없다.

	//설정정보를 가진 객체 //어디에 지정할지 크기를 결정할수 있다!!
	DiskFileItemFactory factory=new DiskFileItemFactory(); //새로 만들기

	//스클립트 서비스매서드처리 영역 
	path=application.getRealPath(path); //실제 경로로 대체
	
	System.out.println(path);
	//파일 업로드 요청이 있으므로, 업로드 라이브러리를 활요해야 한다!
	factory.setSizeThreshold(maxSize);
	factory.setRepository(new File(path)); //임시디렉토리 및 파일 저장 위치
	
	//실제 업로드 처리 담당
	ServletFileUpload upload=new ServletFileUpload(factory);
	
	//클라이언트의 요청분석 후 결과 반환
	List<FileItem> itemList=upload.parseRequest(request);
	
	//클라이언트의 파라미터의 종류와 이름에 따라 적잘한 DTO에 담자
	Product product= new Product();
	List<Psize> psizeList=new ArrayList<Psize>();
	List<Color> colorList=new ArrayList<Color>();;
	List<Pimg> pimgList=new ArrayList<Pimg>();;
	
	//Product 안에 리스트넣어두기 
	product.setPsizeList(psizeList);
	product.setColorList(colorList);
	product.setPimgList(pimgList);
	
	
	for(FileItem item : itemList){ //파라미터 수 만큼 반복처리
		//이미지까지 포함한 포문
        if(item.isFormField()){ //일반 텍스트 파라미터라면
            if(item.getFieldName().equals("category_idx")){ //html의 파라미터명이 카테고리인지
                //Product가 category_idx라는 int형으로 보유하지 않고
                //아예 부모를 DTO형태로 보유하기 때문에
                Category category = new Category();
                category.setCategory_idx(Integer.parseInt(item.getString()));
                product.setCategory(category); //Product에 대입

            }else if(item.getFieldName().equals("product_name")){ //상품명인지
                product.setProduct_name(item.getString("utf-8"));

            }else if(item.getFieldName().equals("brand")){ //브랜드 인지
                product.setBrand(item.getString("utf-8"));
            
            }else if(item.getFieldName().equals("price")){ //가격인지
                product.setPrice(Integer.parseInt(item.getString()));
            
            }else if(item.getFieldName().equals("discount")){ //할인가인지
                product.setDiscount(Integer.parseInt(item.getString()));
            
            }else if(item.getFieldName().equals("size[]")){ //사이즈인지
                System.out.println(item.getString("utf-8"));
            
            	//넘어온 데이터가 배열인 경우 쉼표로 구분되어 날아왔다!!
            	//따라서 쉼표를 구분으로 다시 분리시켜 배열로 만들자
            	String[] psize=item.getString("utf-8").split(",");
            	for(int i=0; i<psize.length; i++ ){
            		//String size : psize
            		Psize psizeobj = new Psize(); //empty DTO
            		psizeobj.setProduct(product); //어떤 상품에 소속된 사이즈인지 (핵심)
            		psizeobj.setPsize_name(psize[i]); //XL,L,M
            		
            		psizeList.add(psizeobj); //저 위에 리스트에 추가
            	}
            	
            }else if(item.getFieldName().equals("color[]")){ //색상인지
            	String[] color=item.getString("utf-8").split(",");
            	System.out.println("색상 : " +item.getString("utf-8"));
				for(int i=0;i<color.length;i++){
					Color colorObj = new Color();//empty
					colorObj.setProduct(product);//어떤 상품에 소속된 색상인지
					colorObj.setColor_name(color[i]); 
					
					colorList.add(colorObj);//DTO를 저 위에 리스트에 추가
				}				
				
            }else if(item.getFieldName().equals("detail")){ //상세내용인지
				product.setDetail(item.getString("utf-8"));
            }
        }else{ //파일 업로드 라면..
			long time=System.currentTimeMillis();//파일 이름 예정
			String ext=FileManager.getExt(item.getName());
			
			//조합된 파일명
			String filename=time+"."+ext;//또 써먹을 일이 있을까봐 뺌
			
			item.write(new File(path+filename)); //디스크에 내려쓰기 일어남
			//파일에 대한 정보를 DTO에 담아서, Pimg에 넣으면 됨 
			//Pimg는 product에 이미들어가있음
			Pimg pimg=new Pimg(); //empty
			pimg.setProduct(product); //어떤 상품에  소속된 이미지인지..
			pimg.setFilename(filename);
			
			pimgList.add(pimg);
        }
	}
	
	SqlSession sqlSession= mybatisConfig.getSqlSession();
	
	//얻어진 SqlSession을 해당 DAO에게 전달..
	
	//상품등록 트랜잭션은 총 4개의 DML로 이루어져있다
	
	MessageObject messageObject=new MessageObject();
	
	try{
		// 세부업무1 : Product 테이블에 넣기
		//try,catch 안잡으면 개발자 손해
		productDAO.setSqlSession(sqlSession);//주입
		productDAO.insert(product);
	
		//세부업무2: Psize 테이블에 넣기
		psizeDAO.setSqlSession(sqlSession); //주입
		//유저가 체크한 사이즈 수만큼...
		for(Psize psize : product.getPsizeList() ){ 
			psizeDAO.insert(psize);
		}				
		
		//세부업무3: Color 테이블에 넣기
		colorDAO.setSqlSession(sqlSession); //주입
		//유저가 체크한 사이즈 수만큼... (반환형 : 얼만큼)
		for(Color color : product.getColorList() ){
			colorDAO.insert(color);
		}
		
		//세부업무4: Pimg 테이블에 넣기
		pimgDAO.setSqlSession(sqlSession);//주입
		for(Pimg pimg : product.getPimgList()){
			pimgDAO.insert(pimg);
		}
		
		sqlSession.commit();
		messageObject.setCode(1);
		messageObject.setMsg("상품등록 완료");
	}catch(ProductException e){
		sqlSession.rollback();		
		messageObject.setCode(0);
		messageObject.setMsg(e.getMessage());
	}catch(PsizeException e){
		sqlSession.rollback();		
		messageObject.setCode(0);
		messageObject.setMsg(e.getMessage());
	}catch(ColorException e){
		sqlSession.rollback();
		messageObject.setCode(0);
		messageObject.setMsg(e.getMessage());
	}catch(PimgException e){
		sqlSession.rollback();		
		messageObject.setCode(0);
		messageObject.setMsg(e.getMessage());
	}finally{
		mybatisConfig.release(sqlSession);
	}
	
	//응답정보 보내기(응답정보를 json으로 변환)
	Gson gson=new Gson();
	String json=gson.toJson(messageObject);
	
	out.print(json);
	
%>











