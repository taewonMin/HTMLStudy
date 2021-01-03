package handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.IBoardSerivce;
import vo.BoardVO;

public class InsertBoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/board/insert.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else if(req.getMethod().equals("POST")) {
			
			BoardVO bv = new BoardVO();
			bv.setBoardWriter(req.getParameter("boardWriter"));
			bv.setBoardTitle(req.getParameter("boardTitle"));
			bv.setBoardContent(req.getParameter("boardContent"));
			
			IBoardSerivce ibs = BoardServiceImpl.getInstance();
			int chk = ibs.insertBoard(bv);
			
			String msg = "";
			if(chk > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			
			String resultURI = req.getContextPath() + "/board/list.do?msg=" + URLEncoder.encode(msg, "utf-8");
			return resultURI;
		}
		return null;
	}

}
