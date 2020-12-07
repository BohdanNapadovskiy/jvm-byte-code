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

public class EditRoleServletsTest {

    @Test
    public void whenEditRoleUserThan() throws ServletException, IOException {
        EditRoleServlets controller = new EditRoleServlets();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("email")).thenReturn("loginemail");
        when(req.getParameter("role")).thenReturn("userRoleTest");
        controller.doPost(req, resp);

        assertThat(UserStore.getInstance().selectUser("loginemail").getRole().getRoleName(), is("userRoleTest"));
    }


}