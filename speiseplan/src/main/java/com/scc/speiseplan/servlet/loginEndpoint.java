package com.scc.speiseplan.servlet;

import com.scc.speiseplan.data.MyDBHandler;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;


@WebServlet(name = "loginEndpoint")
public class loginEndpoint extends HttpServlet {


        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.valueOf(request.getParameter("userid"));
        String password = request.getParameter("password");
        String token = "-1";//request.getParameter("token");
        String userData;

                //  response.getWriter().println(userId+password+token)
        if (new MyDBHandler().isUser(userId,password,token)) {
                //setToken
                token = new BigInteger(130, new SecureRandom()).toString(32);
                new MyDBHandler().setToken(userId,token);
                userData = new ObjectMapper().writeValueAsString(new MyDBHandler().getUserData(userId));

        }else{
                userData = "{ \"UserId\":"+userId +", \"isAdmin\":\"-1\" }";
        }
        response.getWriter().write(userData);



    }
}
