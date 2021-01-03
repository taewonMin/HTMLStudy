package handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.IBoardSerivce;
import vo.BoardVO;

public class UpdateBoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/board/update.jsp";
	
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
			bv.setBoardNo(Integer.parseInt(req.getParameter("boardNo")));
			bv.setBoardTitle(req.getParameter("boardTitle"));
			bv.setBoardContent(req.getParameter("boardContent"));
			
			IBoardSerivce ibs = BoardServiceImpl.getInstance();
			int chk = ibs.updateBoard(bv);
		
			String msg = "";
			if(chk > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			String resultURI = req.getContextPath() + "/board/select.do?boardNo=" + req.getParameter("boardNo") + "&msg=" + URLEncoder.encode(msg, "utf-8");
			return resultURI;
		}
		return null;
	}
	
}
