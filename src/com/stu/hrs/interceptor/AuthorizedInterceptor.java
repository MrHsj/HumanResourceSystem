package com.stu.hrs.interceptor;

import com.stu.hrs.domain.User;
import com.stu.hrs.util.HrsConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-04 9:27
 */
public class AuthorizedInterceptor implements HandlerInterceptor {
    /** 定义不需要拦截的请求 */
    private static final String[] IGNORE_URI = {"/loginForm", "/login", "/404.html"};

    /**
     * 该方法需要preHandle方法的返回值为true时才会执行，在整个请求完成之后执行，主要作用时用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 该方法在处理器进行处理之后，也就是在Controller的方法调用之后执行，需要在preHandle方法的返回值为true才会执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * preHandle方法进行处理器拦截用的，该方法在Controller处理之前进行调用，当preHandle的返回值为false的时候整个请求就结束了
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 默认用户没有登录
        boolean flag = false;
        // 获得请求的ServletPath
        String servletPath = request.getServletPath();
        // 判断请求是否需要拦截
        for (String s: IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }
        //拦截请求
        if (!flag) {
            // 获取session中的用户
            User user = (User) request.getSession().getAttribute(HrsConstants.USER_SESSION);
            // 判断用户是否已经登录
            if (user == null) {
                // 如果用户没有登录，跳转到登录页面
                request.setAttribute("message", "请先登录再访问网站");
                request.getRequestDispatcher(HrsConstants.LOGIN).forward(request, response);
                return flag;
            } else {
                flag = true;
            }
        }
        return flag;
    }
}
