package notice;

import java.io.Serializable;

public class NoticeDTO implements Serializable {
	private int notiNo;
	private String notiTitle;
	private String notiContent;
	private String notiDate;
	private int empNo;
	
	public int getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(int notiNo) {
		this.notiNo = notiNo;
	}

	public String getNotiTitle() {
		return notiTitle;
	}

	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
	}

	public String getNotiContent() {
		return notiContent;
	}

	public void setNotiContent(String notiContent) {
		this.notiContent = notiContent;
	}

	public String getNotiDate() {
		return notiDate;
	}

	public void setNotiDate(String notiDate) {
		this.notiDate = notiDate;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	@Override
	public String toString() {
		return "NoticeDTO [notiNo=" + notiNo + ", notiTitle=" + notiTitle + ", notiContent=" + notiContent
				+ ", notiDate=" + notiDate + ", empNo=" + empNo + "]";
	}

	public NoticeDTO(int notiNo, String notiTitle, String notiContent, String notiDate, int empNo) {
		super();
		this.notiNo = notiNo;
		this.notiTitle = notiTitle;
		this.notiContent = notiContent;
		this.notiDate = notiDate;
		this.empNo = empNo;
	}

	public NoticeDTO() {
		super();

	}

	
}