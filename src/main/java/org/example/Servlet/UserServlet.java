package org.example.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Model.User;
import org.example.Service.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    UserService service=new UserService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        Integer id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String mobile =request.getParameter("mobile");


        if(id==null || name== null || email == null || mobile ==null)
        {

        }
        User user=new User(id,name,email,mobile);

        User createduser=service.createuser(user);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        Integer id=Integer.parseInt(request.getParameter("id"));
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response)
    {
        
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
    {

    }
}
