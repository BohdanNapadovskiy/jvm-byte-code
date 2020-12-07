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

    @Test
    public void whenCreateNewUser() throws ServletException, IOException {
        CreateUserServlet controller = new CreateUserServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("testUser");
        when(req.getParameter("login")).thenReturn("testlogin");
        when(req.getParameter("email")).thenReturn("testEmail");
        when(req.getParameter("password")).thenReturn("12341234");
        controller.doPost(req, resp);

        assertThat(UserStore.getInstance().selectUser("testEmail").getLogin(), is("testlogin"));
    }


}