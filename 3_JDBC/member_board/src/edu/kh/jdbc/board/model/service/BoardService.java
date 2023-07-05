package edu.kh.jdbc.board.model.service;

// import static : static 필드/ 메소드 호출시 클래스명 생략
import static edu.kh.jdbc.common.JDBCTemplate.*;
// * 기호 : 모두, 전부(ALL)

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.board.model.vo.Reply;
import edu.kh.jdbc.common.JDBCTemplate;

public class BoardService {

	private BoardDAO dao = new BoardDAO();
	
	/** 게시글 목록 조회 Service
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAll() throws Exception {
		
		// 1) Connection 생성
		Connection conn = getConnection();
		
		// 2) DAO 메소드(SELECT) 호출 후 결과 반환 받기
		List<Board> boardList = dao.selectAll(conn);
		
		// 3) Connection 반환
		close(conn);
		
		// 4) DAO 수행 결과를 View에 반환
		return boardList;
	}

	/** 게시글 삭제 조회
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectOne(int boardNo) throws Exception {
		
		// 1) Connection 생성
		Connection conn = getConnection();
		
		// 2) 특정 게시글 상세 조회 DAO 메소드(SELECT) 호출 후 결과 반환 받기
		Board board = dao.selectOne(conn, boardNo);
		
		if(board != null) { // 2)번 게시글 상세 조회 내용이 있을 경우에만
			
			// 3-1) 특정 게시글 댓글 목록 조회 DAO 메소드(SELECT) 호출 후 결과 반환 받기
			List<Reply> replyList = dao.selectReplyList(conn, boardNo);
			
			//Board 객체의 replyList 필드에 조회한 댓글 목록을 대입(세팅)
			board.setReplyList(replyList);
			
			// 3-2) 게시글 조회수 증가 DAO 메소드 (UPDATE) 호출 후 결과(성공한 행의 개수) 반환 받기
			int result = dao.increaseReadCount(conn,boardNo);
			// increase : 증가하다
			
			// 트랜잭션 제어 처리 + 조회수 동기화
			if(result>0) {
				commit(conn);
				
				// DB -> READ_COUNT 업데이트
				// -> 업데이트 전게 게시글 정보를 조회했음
				// -> 조회된 게시글 조회수가 DB 조회수보다 1 낮음
				// -> 조회된 게시글의 조회수를 +1 시켜서 DB와 동기화
				board.setReadCount(board.getReadCount()+1);
				
				
			}else {
				rollback(conn);
			}
			
		}
		
		
		// 4) Connection 반환
		close(conn);
		
		// 5) DAO 수행 결과 View로 반환
		
		return board; // 게시글 상세 조회 + 댓글 목록
	}

	/** 게시글 삭제 Service
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn, boardNo );
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		
		return result;
	}

	/**
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Board board) throws Exception{
		
		
		Connection conn = getConnection();
		
		int result = dao.updateBoard(conn, board);
		
		if(result >0) commit(conn);
		else		  rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 Service
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Reply reply) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertReply(conn, reply);
		
		
		if(result >0) commit(conn);
		else		  rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 수정 Service
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(Reply reply) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateReply(conn, reply);
		
		
		if(result >0) commit(conn);
		else		  rollback(conn);
		
		close(conn);
		
		return result;
		

	}

	/** 댓글 삭제 Service
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn, replyNo);
		
		
		if(result >0) commit(conn);
		else		  rollback(conn);
		
		close(conn);
		
		return result;
		
	
	}

	/** 게시글 작성 Service
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Board board) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.insertBoard(conn, board);
		
		if(result >0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 검색 Service
	 * @param menuNum
	 * @param keyword
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(int menuNum, String keyword)throws Exception  {
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.searchBoard(conn, menuNum, keyword);
		
		close(conn);
		
		
		return boardList;
	}	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
