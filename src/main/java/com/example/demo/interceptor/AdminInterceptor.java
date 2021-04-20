package com.example.demo.interceptor;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class AdminInterceptor implements  HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("执行了TestInterceptor的preHandle方法");
        System.out.println("已经进入登录拦截器");
        String url = request.getRequestURI();
        System.out.println("url------:" + url);
        try {
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)

            System.out.println("进入拦截器"+request.getRequestURI());
            if (request.getSession().getAttribute("sId")!=null&&request.getRequestURI().contains("student")){
                return true;
            }
            if( request.getSession().getAttribute("tId")!=null&&request.getRequestURI().contains("teacher")){
                return true;
            }
            if (request.getSession().getAttribute("sId")==null&&request.getRequestURI().contains("student")){
                System.out.println("进入user...");
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }
            if (request.getSession().getAttribute("tId")==null&&request.getRequestURI().contains("teacher")){
               System.out.println("进入back...");
               response.sendRedirect(request.getContextPath()+"/login");
               return false;
           }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
                      //如果设置为true时，请求将会继续执行后面的操作
    }
 
    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }
 
    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }
    
}