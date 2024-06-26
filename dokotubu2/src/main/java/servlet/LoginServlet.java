package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import model.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//login.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//キャラクターエンコーディング
		request.setCharacterEncoding("UTF-8");
		
		//postリクエストからuseridとpassを取得
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		
		//Userインスタンスの作成
		User user = new User(userid, pass);
		
		//LoginLoginインスタンスの作成
		LoginLogic loginLogic = new LoginLogic()
		
		//LoginLogicのisLoginメソッドの実行しloginResultへ結果を格納
		boolean loginResult = loginLogic.isLogin(user);
		
		//isLoginの結果による分岐
		if (loginResult) {
			//trueの場合
			
			//セッションスコープにuseridを保存
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			
			//loginOK.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);
		}else {
			//falseの場合
			
			//LoginServletにリダイレクト
			response.sendRedirect("LoginServlet");
		}
		
		
		
	
		
	}

}
