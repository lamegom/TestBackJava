package teste.springboot.springboot;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/api/*")
public class AllowAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("in AllowAccessFilter.doFilter");
        HttpServletRequest request = (HttpServletRequest)sRequest;
        HttpServletResponse response = (HttpServletResponse)sResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); 
        chain.doFilter(request, response);
    }

}
