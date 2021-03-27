package com.meiken.framework;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.meiken.controller.IndexController;
import com.meiken.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @Author glf
 * @Date 2021/1/17
 */
@WebServlet(urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String,GetPatcher> getMappings = new HashMap<>();

    private Map<String,PostPatcher> postMappings = new HashMap<>();

    private ViewEngine viewEngine;

    //TODO：可以指定 package 并自动扫描
    private List<Class<?>> controllers = Lists.newArrayList(IndexController.class, UserController.class);

    private static final Set<Class<?>> supportGetParameterTypes  = Sets.newHashSet(int.class, long.class, boolean.class,String.class, HttpServletRequest.class, HttpServletResponse.class, HttpSession.class);
    private static final Set<Class<?>> supportPostParameterTypes = Sets.newHashSet(HttpServletRequest.class, HttpServletResponse.class, HttpSession.class);

    @Override
    public void init() throws ServletException {
        logger.info("init myservlet{}...",getClass().getSimpleName());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 依此处理每个 Controller
        for(Class<?> controllerClass : controllers){
            try{
               Object controllerInstance = controllerClass.getConstructor().newInstance();
               //依次处理每个Method
                for(Method method : controllerClass.getMethods()){
                    //处理 @Get
                    if(method.getAnnotation(GetMapping.class) != null){
                        if(method.getReturnType() != ModelAndView.class && method.getReturnType() != void.class){
                            throw new UnsupportedOperationException(
                                    "Unsupported return type: " + method.getReturnType() + " fro method: " + method);
                        }

                        for(Class<?> parameterClass : method.getParameterTypes()){
                            if(!supportGetParameterTypes.contains(parameterClass)){
                                throw new UnsupportedOperationException("Unsupported parameter type: " + parameterClass + " for method: " + method);
                            }
                        }

                        Parameter[] parameters = method.getParameters();
                        String[] parameternames = new String[parameters.length];
                        for (int i = 0; i < parameters.length; i++) {
                            parameternames[i] = parameters[i].getName();
                        }
                        String path = method.getAnnotation(GetMapping.class).value();

                        logger.info("Found Get: {} -> {}", path,method);

                        this.getMappings.put(path,new GetPatcher(controllerInstance,method,parameternames,method.getParameterTypes()));
                    }
                    //处理 Post
                    else if(method.getAnnotation(PostMapping.class) != null){
                        if(method.getReturnType() != ModelAndView.class && method.getReturnType() != void.class){
                            throw new UnsupportedOperationException("Unsupported return type: " + method.getReturnType() + " for method: " + method);
                        }
                        Class<?> requestBodyClass = null;
                        for(Class<?> parameterClass : method.getParameterTypes()){
                            if(!supportPostParameterTypes.contains(parameterClass)){
                                if(requestBodyClass ==  null){
                                    requestBodyClass = parameterClass;
                                }else{
                                    throw new UnsupportedOperationException("Unsupported duplicate request body type: " + parameterClass + " fro method: " + method);
                                }
                            }
                        }

                        String path = method.getAnnotation(PostMapping.class).value();
                        logger.info("Found POST: {} => {}",path,method);
                        this.postMappings.put(path,new PostPatcher(controllerInstance,method,method.getParameterTypes(),objectMapper));
                    }
                }
            }catch (Exception e){
                logger.info("error");
                throw new ServletException(e);
            }
        }
        this.viewEngine = new ViewEngine(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp,this.getMappings);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp,this.postMappings);
    }

    private void process(HttpServletRequest request, HttpServletResponse response, Map<String, ? extends AbstractPatcher> dispatcherMap) throws ServletException,IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI().substring(request.getContextPath().length());
        AbstractPatcher dispatcher = dispatcherMap.get(path);
        if(dispatcher == null){
            response.sendError(404);
            return;
        }
        ModelAndView mv = null;
        try{
            mv = dispatcher.invoke(request,response);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        if(mv == null){
            return;
        }
        if(mv.view.startsWith("redirect:")){
            response.sendRedirect(mv.view.substring(9));
            return;
        }
        PrintWriter writer = response.getWriter();
        this.viewEngine.render(mv,writer);
        writer.flush();

    }
}
