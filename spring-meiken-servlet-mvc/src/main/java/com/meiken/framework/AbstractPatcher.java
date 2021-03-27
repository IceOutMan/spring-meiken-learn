package com.meiken.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author glf
 * @Date 2021/1/17
 */
public abstract class AbstractPatcher {
    public abstract ModelAndView invoke(HttpServletRequest request, HttpServletResponse response) throws IOException, ReflectiveOperationException;
}
