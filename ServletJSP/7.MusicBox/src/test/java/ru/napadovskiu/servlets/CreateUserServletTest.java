package ru.napadovskiu.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;


import ru.napadovskiu.store.UserStore;

import static org.junit.Assert.*;

public class CreateUserServletTest {


    private final UserStore usersStore = UserStore.INSTANCE;

    @Test
    public void whenCreateNewUser() throws ServletException, IOException {
        CreateUserServlet controller = new CreateUserServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("testUser");
        when(req.getParameter("login")).thenReturn("testlogin");
        when(req.getParameter("password")).thenReturn("12341234");
        when(req.getParameter("address")).thenReturn("testAddress");
        when(req.getParameter("role")).thenReturn("USER");
        String[] musicType = {"RAP","ROCK"};
        when(req.getParameterValues("music")).thenReturn(musicType);


        controller.doPost(req, resp);

        assertThat(usersStore.selectUser("testlogin","testUser").getLogin(), is("testlogin"));
    }




}