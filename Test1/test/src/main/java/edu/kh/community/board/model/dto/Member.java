package edu.kh.community.board.model.dto;

public class Member {
	
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberNickname;
	private String memberRel;
	private String memberAddress;
	private String profileImage;
	private String enrollDate;
	private String secessionFlag;
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberRel() {
		return memberRel;
	}
	public void setMemberRel(String memberRel) {
		this.memberRel = memberRel;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getSecessionFlag() {
		return secessionFlag;
	}
	public void setSecessionFlag(String secessionFlag) {
		this.secessionFlag = secessionFlag;
	}

}
