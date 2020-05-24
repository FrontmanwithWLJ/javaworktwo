package com.sl.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 防止非法操作
 */
public class LoginFilter implements Filter {
    private final String loginPath = "/user/login.jsp";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String currentURL = request.getRequestURI();
        String ctxPath = request.getContextPath();

        String targetURL = currentURL.substring(ctxPath.length());
        HttpSession session = request.getSession(false);
        System.out.println(targetURL);
        //not login page
        if (!loginPath.equals(targetURL) && (session == null || session.getAttribute("msg") == null)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("refresh","3;"+ctxPath+loginPath);
            response.getWriter()
                    .print("您还未登陆，三秒钟后跳转至登录页面,或者 <a href='/web_war_exploded/user/login.jsp'>点我跳转</a>");
            //response.sendRedirect(loginPath);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}