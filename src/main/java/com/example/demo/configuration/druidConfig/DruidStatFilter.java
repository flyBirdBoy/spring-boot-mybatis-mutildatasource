package com.example.demo.configuration.druidConfig;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 18:31 2018/5/3
 * @Modified By:
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "exclusions", value = "*.mp4,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {


}
