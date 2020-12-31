package cmnt;

import java.sql.Date;

public class CmntDTO {

	private int cmnt_num; // 댓글 번호
	private int cmnt_bd; // 게시글 번호
	private String cmnt_id; // 댓글 작성자
	private Date cmnt_date; // 댓글 작성일
	private int cmnt_parent; // 부모글
	private String cmnt_content; // 댓글 내용
	private int cmnt_level; // 댓글 답변글 길이
	
	public int getCmnt_num() {
		return cmnt_num;
	}
	public void setCmnt_num(int cmnt_num) {
		this.cmnt_num = cmnt_num;
	}
	public int getCmnt_bd() {
		return cmnt_bd;
	}
	public void setCmnt_bd(int cmnt_bd) {
		this.cmnt_bd = cmnt_bd;
	}
	public String getCmnt_id() {
		return cmnt_id;
	}
	public void setCmnt_id(String cmnt_id) {
		this.cmnt_id = cmnt_id;
	}
	public Date getCmnt_date() {
		return cmnt_date;
	}
	public void setCmnt_date(Date cmnt_date) {
		this.cmnt_date = cmnt_date;
	}
	public int getCmnt_parent() {
		return cmnt_parent;
	}
	public void setCmnt_parent(int cmnt_parent) {
		this.cmnt_parent = cmnt_parent;
	}
	public String getCmnt_content() {
		return cmnt_content;
	}
	public void setCmnt_content(String cmnt_content) {
		this.cmnt_content = cmnt_content;
	}
	public int getCmnt_level() {
		return cmnt_level;
	}
	public void setCmnt_level(int cmnt_level) {
		this.cmnt_level = cmnt_level;
	}
	
}
