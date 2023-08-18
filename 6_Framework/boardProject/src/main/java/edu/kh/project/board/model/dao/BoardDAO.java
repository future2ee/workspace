package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession sqlSession;

	/** 게시판 종류 목록 조회
	 * @return boardTypeList
	 */
	public List<Map<String, Object>> selectBoardTypeList() {
		
		return sqlSession.selectList("boardMapper.selectBoardTypeList");
	}
}
