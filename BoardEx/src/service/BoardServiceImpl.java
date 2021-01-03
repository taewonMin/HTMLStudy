package service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import dao.BoardDaoImpl;
import util.SqlMapClientFactory;
import vo.BoardVO;

public class BoardServiceImpl implements IBoardSerivce {
	private static BoardServiceImpl boardService;
	private BoardDaoImpl boardDao;
	private SqlMapClient smc;
	
	private BoardServiceImpl() {
		smc = SqlMapClientFactory.getInstance();
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			smc.startTransaction();
			
			cnt = boardDao.insertBoard(smc, bv);
			
			smc.commitTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				smc.endTransaction();
			} catch (SQLException e) {
				throw new RuntimeException("글 삽입 예외 발생", e);
			}
		}
		return cnt;
	}

	@Override
	public List<BoardVO> displayAll() {
		List<BoardVO> boardList = Collections.emptyList();
		
		try {
			boardList = boardDao.displayAll(smc);
		}catch(SQLException e) {
			throw new RuntimeException("전체 글 조회 예외 발생", e);
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(String search) {
		List<BoardVO> boardList = Collections.emptyList();
		try {
			boardList = boardDao.searchBoard(smc, search);	
		} catch(SQLException e) {
			throw new RuntimeException("글 검색 예외 발생", e);
		}
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = boardDao.updateBoard(smc, bv);
		}catch(SQLException e) {
			throw new RuntimeException("글 수정 예외 발생", e);
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = boardDao.deleteBoard(smc, boardNo);
		} catch(SQLException e) {
			throw new RuntimeException("글 삭제 예외 발생", e);
		}
		return cnt;
	}
}
