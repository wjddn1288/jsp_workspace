package notice.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.noticeapp.domain.Notice;
import com.edu.noticeapp.repository.NoticeDAO;

public class WriteServlet extends HttpServlet {
	NoticeDAO noticeDAO; // JSP의 선언부가 됨
	
	public void init(ServletConfig config) throws ServletException {
		String data=config.getInitParameter("today");
		System.out.println("초기화시 넘겨받은 정보는"+data);
	}
	
	public WriteServlet() {
		noticeDAO = new NoticeDAO();
	}

	// 클라이언트가 post 방식으로 요청을 시도하면 작동할 메서드
	// 매개변수인 request는 사실 JSP 에서의 request 내장객체였다!!
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//jsp에서의 page 지식영역을 의미
		response.setContentType("text/html;charset=utf-8");
		
		// 파라미터 받기
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		// dto 채우기
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//응답 객체가 보유한 문자기반 출력스트림 얻어오기
		//이 스트림에 문자열을 누적시켜놓으면, 고양이가 최종적으로 응답시
		//클라이언트의 브라우저에 전송한다.
		PrintWriter out=response.getWriter();
		
		// dao 메서드 호출
		int result=noticeDAO.insert(notice);
		
		out.print("<script>");
		if(result>0) {
			out.print("alert('등록성공');");
			out.print("location.href='/notice/list.jsp';");
		}else {
			out.print("alert('등록실패');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
}









