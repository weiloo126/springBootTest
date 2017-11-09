package com.example.springBootTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring boot 推荐使用Thymeleaf来代替jsp，Thymeleaf是一款用于渲染XML/XHTML/HTML5内容的模板引擎。类似JSP，Velocity，FreeMaker等，它也可以轻易的与Spring MVC等Web框架进行集成作为Web应用的模板引擎。
 * 与其它模板引擎相比，Thymeleaf最大的特点是能够直接在浏览器中打开并正确显示模板页面，而不需要启动整个Web应用。
 * Thymeleaf是与众不同的，因为它使用了自然的模板技术。这意味着Thymeleaf的模板语法并不会破坏文档的结构，模板依旧是有效的XML文档。模板还可以用作工作原型，Thymeleaf会在运行期替换掉静态值。Velocity与FreeMarker则是连续的文本处理器。
 *   
 * @date 2017年11月8日
 */
@Controller
public class ViewTemplateController {

    @RequestMapping("/thymeleaf")
    public String thymeleafView(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "thymeleaf view");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";  
    }

    @RequestMapping("/freemarker")
    public String freemarkerView(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "freemarker view");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";  
    }

}
