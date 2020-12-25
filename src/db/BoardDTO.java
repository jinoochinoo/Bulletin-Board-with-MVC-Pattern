package db;

import java.sql.Date;

public class BoardDTO {

	private int bd_num;
	private String bd_id;
	private String bd_title;
	private String bd_content;
	private String bd_file;
	private int bd_re_ref;
	private int bd_re_lev;
	private int bd_re_seq;
	private int bd_cnt;
	private Date bd_date;
	
	
	public int getBd_num() {
		return bd_num;
	}
	public void setBd_num(int bd_num) {
		this.bd_num = bd_num;
	}
	public String getBd_id() {
		return bd_id;
	}
	public void setBd_id(String bd_id) {
		this.bd_id = bd_id;
	}
	public String getBd_title() {
		return bd_title;
	}
	public void setBd_title(String bd_title) {
		this.bd_title = bd_title;
	}
	public String getBd_content() {
		return bd_content;
	}
	public void setBd_content(String bd_content) {
		this.bd_content = bd_content;
	}
	public String getBd_file() {
		return bd_file;
	}
	public void setBd_file(String bd_file) {
		this.bd_file = bd_file;
	}
	public int getBd_re_ref() {
		return bd_re_ref;
	}
	public void setBd_re_ref(int bd_re_ref) {
		this.bd_re_ref = bd_re_ref;
	}
	public int getBd_re_lev() {
		return bd_re_lev;
	}
	public void setBd_re_lev(int bd_re_lev) {
		this.bd_re_lev = bd_re_lev;
	}
	public int getBd_re_seq() {
		return bd_re_seq;
	}
	public void setBd_re_seq(int bd_re_seq) {
		this.bd_re_seq = bd_re_seq;
	}
	public int getBd_cnt() {
		return bd_cnt;
	}
	public void setBd_cnt(int bd_cnt) {
		this.bd_cnt = bd_cnt;
	}
	public Date getBd_date() {
		return bd_date;
	}
	public void setBd_date(Date bd_date) {
		this.bd_date = bd_date;
	}
	
}
