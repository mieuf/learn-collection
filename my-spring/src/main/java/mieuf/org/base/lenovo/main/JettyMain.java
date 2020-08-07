package mieuf.org.base.lenovo.main;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JettyMain extends AbstractHandler {

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");

        response.setStatus(HttpServletResponse.SC_OK);

        baseRequest.setHandled(true);

        response.getWriter().println("<h1>HelloWorld</h1>");

    }



    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        server.setHandler(new JettyMain());

        server.start();

        server.join();

    }
}
