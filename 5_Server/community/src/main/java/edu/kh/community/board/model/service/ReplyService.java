package edu.kh.community.board.model.service;

import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.community.board.model.dao.ReplyDAO;
import edu.kh.community.board.model.vo.Reply;



public class ReplyService {
	
	private ReplyDAO dao = new ReplyDAO();


	/** 댓글 목록 조회  Service
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int boardNo) throws Exception {
		
		Connection conn = getConnection(); // DBCP에서 컼넥션 얻어오기
		
		// 댓글 목록 조회 SQL 수행 후 결과 반환 받기
		List<Reply> rList = dao.selectReplyList(conn, boardNo);
		
		close(conn);
		
		return rList;
	}

}
