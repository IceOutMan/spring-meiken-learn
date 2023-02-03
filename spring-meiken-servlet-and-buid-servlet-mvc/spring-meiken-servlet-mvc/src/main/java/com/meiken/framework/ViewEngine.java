package com.meiken.framework;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author glf
 * @Date 2021/1/17
 */
public class ViewEngine {
    private final PebbleEngine engine;

    public ViewEngine(ServletContext servletContext) {
        //定义一个ServletLoader用于加载模版
        ServletLoader servletLoader = new ServletLoader(servletContext);
        // 模版编码
        servletLoader.setCharset("UTF-8");
        // 模版前缀，这里默认模版必须放在 /WEB-INF/templates 目录
        servletLoader.setPrefix("/WEB-INF/templates");
        //模版后缀
        servletLoader.setSuffix("");
        //创建 Pebble 实例
        this.engine = new PebbleEngine.Builder()
                .autoEscaping(true)//默认打开HTML字符转义，防止XSS攻击
                .cacheActive(false)//禁止缓存使得每次修改模版都可以立刻看到结果
                .loader(servletLoader).build();

    }

    public void render(ModelAndView mv, Writer writer) throws IOException {
        // 查找模版
        PebbleTemplate template = this.engine.getTemplate(mv.view);
        //渲染
        template.evaluate(writer,mv.model);
    }
}
