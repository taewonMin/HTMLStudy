package controller;

import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import handler.NullHandler;

public class WebController extends HttpServlet {
	
	// 매핑정보 저장
	private Map<String, CommandHandler> cmdHandlerMap = new HashMap<String, CommandHandler>();
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		// 설정파일을 읽어서 대응되는 핸들러객체를 생성하여 맵에 등록하기
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr;
		try {
			fr = new FileReader(configFileRealPath);
			
			handlerProp.load(fr);
		}catch(IOException e) {
			throw new ServletException(e);
		}
		
		for(Object key : handlerProp.keySet()) {
			String reqUrl = (String) key;
			
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(reqUrl));
				CommandHandler handlerInsatance = (CommandHandler) klass.newInstance();
				cmdHandlerMap.put(reqUrl, handlerInsatance);
			}catch(Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			
		}
		Set<Map.Entry<String, CommandHandler>> entrySet = cmdHandlerMap.entrySet();
		for(Map.Entry<String, CommandHandler> entry : entrySet) {
			System.out.println(entry.getKey() + " => " + entry.getValue());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 URL가져오기
		String reqURI = req.getRequestURI();
		
		// ContextPath 부분을 제거한 URL 가져오기
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(req.getContextPath().length()); 
		}
		
		System.out.println("reqURI : " + reqURI);
		System.out.println("cmdHandlerMap : " + cmdHandlerMap);
		
		CommandHandler handler = cmdHandlerMap.get(reqURI);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = "";	// 뷰화면 정보
		try {
			viewPage = handler.process(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
//		System.out.println("viewPage : " + URLDecoder.decode(viewPage, "utf-8"));
		System.out.println("viewPage : " + viewPage);
		
		// VIEW 화면 처리
		if(viewPage != null) {	// 뷰페이지가 존재하면
			if(handler.isRedirect(req)) {
				resp.sendRedirect(viewPage);
			}else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
		
	}
}
