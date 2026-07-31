package org.example.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Model.User;
import org.example.Service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    UserService userService=new UserService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String mobile =request.getParameter("mobile");


        if(id==null || name== null || email == null || mobile ==null)
        {
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write("{ \n"+
                    " \"message\": \"Some fielda are missing\n"  +"}");
        }
        User user=new User(id,name,email,mobile);

        User createduser=userService.createuser(user);

        response.setStatus(201);
        response.setContentType("application/json");
        response.getWriter().write("{ \n"+
                " \"message\": \"User added Successfully\n"  +"}");

    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        String idParam = request.getParameter("id");

        if(idParam == null) {
            List<User> users = userService.getAllUsers();
            response.setStatus(200);
            response.setContentType("application/json");
            response.getWriter().write(usersToJson(users));
            return;
        }
        Integer id = Integer.parseInt(idParam);

        User userResp = userService.getUserById(id);

        if(userResp == null) {
            response.setStatus(404);
            response.setContentType("application/json");
        }

        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(userToJson(userResp));

    }
    @Override
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        if (name == null || email == null || mobile == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Some fields are missing\"}");
            return;
        }

        User user = new User(id, name, email, mobile);

        User updatedUser = userService.updateUser(id, user);

        if (updatedUser == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"User not found\"}");
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"User updated successfully\"}");
    }

    @Override
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Id is required\"}");
            return;
        }

        Integer id = Integer.parseInt(idParam);

        boolean deleted = userService.deleteUser(id);

        if (!deleted) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"User not found\"}");
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"User deleted successfully\"}");
    }

    private String userToJson(User userRes)
    {
        return "{\n" +
                "    \"id\":"+userRes.getId()+",\n" +
                "    \"name\":"+userRes.getName()+",\n" +
                "    \"email\":"+userRes.getEmail()+",\n" +
                "    \"mobile\":"+userRes.getMobile()+"\n" +
                "}";
    }

    private String usersToJson(List<User> users)
    {
        StringBuilder builder=new StringBuilder();
        builder.append("[");

        for(int i=0;i<users.size();i++)
        {
            builder.append(userToJson(users.get(i)));

            if(i<users.size()-1){
                builder.append(",");
            }

        }
        builder.append("]");
        return builder.toString();
    }
}
