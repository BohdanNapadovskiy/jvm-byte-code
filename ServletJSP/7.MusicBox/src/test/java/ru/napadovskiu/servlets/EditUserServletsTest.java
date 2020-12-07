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

    private final UserStore usersStore = UserStore.INSTANCE;

    @Test
    public void whenCreateNewUser() throws ServletException, IOException {
        EditUserServlets controller = new EditUserServlets();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("userId")).thenReturn("1");

        when(req.getParameter("name")).thenReturn("testName");


        controller.doPost(req, resp);

        assertThat(usersStore.getById(1).getLogin(), is("testlogin"));
    }



}