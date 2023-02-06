package com.mvc3.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.mvc3.controller.Controller;
import com.mvc3.model.board.BoardDAO;
import com.mvc3.mybatis.MybatisConfig;

//목록요청을 처리하는 하위 컨트롤러
public class ListController implements Controller{
    
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
    BoardDAO boardDAO = new BoardDAO();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
    	//목록가져오기
        SqlSession sqlSession = mybatisConfig.getSqlSession();
        boardDAO.setSqlSession(sqlSession); //주입
        //3단계 : 일 시키기
        List boardList=boardDAO.selectAll();
        
        //4단계 : application(서버가동 되는 동안),session(세션 범위), request(요청종료)
        request.setAttribute("boardList", boardList);
        mybatisConfig.release(sqlSession);
    }

	@Override
	public String getViewName() {
		return "/board/view/list"; //검색어만 가지고 있으면 된다!!
	}

	public boolean isForward() {
		return true;
	}

    
}