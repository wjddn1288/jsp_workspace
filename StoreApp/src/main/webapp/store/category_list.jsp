<%@page import="store.domain.Category"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="store.repository.CategroyDAO"%>
<%@page import="java.util.List"%>
<%!
	CategroyDAO categoryDAO= new CategroyDAO();
%>
<%
//비동기로 요청을 시도하는 클라이언트를 위해, html문서 전체를 
//응답할 필요없고, 클라이언트가 우너하는 데이터만 골리서 보내주면 된다.
//왜? 클라이언트는 화면 전체를 원하지 않는다..
		List<Category> categoryList=categoryDAO.selectAll();
		//out.print(categoryList);
		
		//쌩고생 스타일
		//개발자가 json 문자열을 직접처리하는 방법
		
		//java는 json표기를 이해할 수 없으므로, 문자열 취급하여 전송해보자
		StringBuilder sb= new StringBuilder();
		sb.append("[");
		for(int i=0; i<categoryList.size();i++){
			Category category= categoryList.get(i);
			
			sb.append("{");
			sb.append("\"category_idx\":"+category.getCategory_idx()+",");
			sb.append("\"category_name\":\""+category.getCategory_name()+"\"");
			if(i< categoryList.size()-1){
				sb.append("},"); //사이즈-1보다 작을때만
			}else{
				sb.append("}");
			}
		}
		sb.append("]");
		out.print(sb.toString());
%>
