package handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.IBoardSerivce;

public class DeleteBoardHandler implements CommandHandler {
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		IBoardSerivce ibs = BoardServiceImpl.getInstance();
		int cnt = ibs.deleteBoard(Integer.parseInt(req.getParameter("boardNo")));
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		String resultURI = req.getContextPath() + "/board/list.do?msg=" + URLEncoder.encode(msg, "utf-8");
		return resultURI;
	}

}
