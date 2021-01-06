package handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.IBoardSerivce;
import vo.BoardVO;

public class SelectBoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/board/select.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(Integer.parseInt(req.getParameter("boardNo")));
		
		IBoardSerivce ibs = BoardServiceImpl.getInstance();
		
		List<BoardVO> list = (List<BoardVO>) ibs.searchBoard(bv);
		
		req.setAttribute("boardVO", list.get(0));
		
		return VIEW_PAGE;
	}

}
