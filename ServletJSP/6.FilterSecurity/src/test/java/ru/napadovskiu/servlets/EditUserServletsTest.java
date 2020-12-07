package ru.napadovskiu.servlets;

import org.junit.Test;
import ru.napadovskiu.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditUserServletsTest {

    @Test
    public void whenCreateNewUser() throws ServletException, IOException {
        EditUserServlets controller = new EditUserServlets();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("oldEmail")).thenReturn("wewe");
        when(req.getParameter("oldName")).thenReturn("wewew");
        when(req.getParameter("oldLogin")).thenReturn("wewew");

        when(req.getParameter("email")).thenReturn("testEmail");
        when(req.getParameter("name")).thenReturn("testName");
        when(req.getParameter("login")).thenReturn("testLogin");
        when(req.getParameter("password")).thenReturn("12341234");


        controller.doPost(req, resp);

        assertThat(UserStore.getInstance().selectUser("testEmail").getLogin(), is("testlogin"));
    }


}