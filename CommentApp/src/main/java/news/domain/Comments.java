package news.domain;

import lombok.Data;

@Data
public class Comments {
	private int comments_idx;
	private String msg;
	private String author;
	private String regdate;
	private News news;
}



