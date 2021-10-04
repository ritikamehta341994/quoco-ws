package service.core;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.concurrent.Executors;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Broker {
    @WebMethod
    public LinkedList<Quotation> getQuotations(ClientInfo info) {
            String host = "localhost";

        return new LinkedList<>();
    }

    public static void main(String[] args) {
        try{
            Endpoint endpoint = Endpoint.create(new Broker());
            HttpServer server = HttpServer.create(new InetSocketAddress(9000), 5);
            server.setExecutor(Executors.newFixedThreadPool(5));
            HttpContext context = server.createContext("/broker");
            endpoint.publish(context);
            server.start();

        } catch (Exception e) {
            e.printStackTrace();
    }
    }

}
