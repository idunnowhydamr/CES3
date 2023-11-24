package co.edu.poli.ces3.universitas.servlet;

import co.edu.poli.ces3.universitas.controller.CtrUser;
import co.edu.poli.ces3.universitas.dto.DtoUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends MyServlet {
    private String message;

    private GsonBuilder gsonBuilder;

    private Gson gson;

    private ArrayList<DtoUser> users;

    CtrUser ctr = new CtrUser();

    public void init() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        users = new ArrayList<>();

        DtoUser user1 = new DtoUser();
        user1.id = 10;
        user1.setName("diego");
        user1.setMail("1213@sssj");

        users.add(user1);

        for (int i = 0; i < users.size(); i++)
        {
            System.out.println(users.get(i));
        }
        message = "I'm the best!!!";
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(req);
        DtoUser std = new DtoUser(
                body.get("mail").getAsString(),
                body.get("name").getAsString(),
                body.get("pass").getAsString()
        );

        DtoUser newUser = ctr.addUser(std);

        out.print(gson.toJson(newUser));
        out.flush();


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        String userIdParam = req.getParameter("id");

        if (userIdParam != null && !userIdParam.isEmpty()) {
            int userId = Integer.parseInt(userIdParam);
            DtoUser user = ctr.getUserById(userId);
            out.print(gson.toJson(user));
        } else {
            ArrayList<DtoUser> users = ctr.getAllUsers();
            out.print(gson.toJson(users));
        }

        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject body = gson.fromJson(stringBuilder.toString(), JsonObject.class);
        int userId = body.get("id").getAsInt();

        DtoUser updatedUser = new DtoUser(
                body.get("mail").getAsString(),
                body.get("name").getAsString(),
                body.get("pass").getAsString()
        );

        DtoUser result = ctr.updateUser(userId, updatedUser);

        out.print(gson.toJson(result));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");

        int userId = Integer.parseInt(req.getParameter("id"));

        ctr.deleteUser(userId);

        out.print(gson.toJson("Eliminado"));
        out.flush();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        switch (method){
            case "PATCH":
                this.doPatch(req, resp);
                break;
            default:
                super.service(req, resp);
        }

    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("************");
        System.out.println("Entro al metodo patch!!!");
        System.out.println("************");
    }

}