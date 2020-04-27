package com.leyou.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FilterZuul extends ZuulFilter {
    //过滤类型
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;//转发之前进行过滤
    }

    //通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;//4
    }

    //是否进行过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //处理业务逻辑
    @Override
    public Object run() throws ZuulException {
        //http://localhost:10010/api/user/1?access-token=1897（转发）
        //http://localhost:10010/api/user/2(不转发)
        //获取当前上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request请求
        HttpServletRequest request=currentContext.getRequest();

        String parameter = request.getParameter("access-token");//1897
        if(StringUtils.isBlank(parameter)){
            //不进行转发
            currentContext.setSendZuulResponse(false);
            //401
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());


        }
        return null;
    }
}
