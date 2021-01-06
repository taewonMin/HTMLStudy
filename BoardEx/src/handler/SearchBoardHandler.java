package handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.IBoardSerivce;
import vo.BoardVO;

public class SearchBoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/board/list.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(req.getParameter("search"));
		
		IBoardSerivce ibs = BoardServiceImpl.getInstance();
		
		List<BoardVO> list = ibs.searchBoard(bv);
		
		req.setAttribute("list", list);
		
		return VIEW_PAGE;
	}

}
