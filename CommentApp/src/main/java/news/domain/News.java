package news.domain;

import java.util.List;

import lombok.Data;

@Data
public class News {
	private int news_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
//	Comments // 자식한건
	//하나의 뉴스 기사는 여려명의 자식글을 보유할수 있다!
	//마이바티스에선 컬렉션이라 한다. 컬렉션으로 처리 가능
	private List<Comments> commentsList;
}