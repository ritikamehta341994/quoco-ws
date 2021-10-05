package service.core;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class Broker {
    static HashSet<String> url = new HashSet<>();

    @WebMethod
    public LinkedList<Quotation> getQuotations(ClientInfo info) {
        LinkedList<Quotation> result = new LinkedList<>();
        try {
            //Thread.sleep(6000);
            for(String wsdlUrl : url){
                //System.out.println("URL : "+wsdlUrl);
                URL finalURL = new
                        URL(wsdlUrl);
                QName serviceName =
                        new QName("http://core.service/", "QuoterService");
                Service service = Service.create(finalURL, serviceName);
                QName portName = new QName("http://core.service/", "QuoterPort");

                QuoterService qService =
                        service.getPort(portName, QuoterService.class);
                Quotation finalQuotation = qService.generateQuotation(info);
                result.add(finalQuotation);

            }
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args){
        try {
            Endpoint endpoint = Endpoint.create(new Broker());
            HttpServer server = HttpServer.create(new InetSocketAddress(9000), 5);
            server.setExecutor(Executors.newFixedThreadPool(5));
            HttpContext context = server.createContext("/broker");
            endpoint.publish(context);
            server.start();

            JmDNS jmDNS = JmDNS.create(InetAddress.getLocalHost());
            jmDNS.addServiceListener("_http._tcp.local.",new WSDLServiceListener());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class WSDLServiceListener implements ServiceListener {
        @Override
        public void serviceAdded(ServiceEvent serviceEvent) {

        }

        @Override
        public void serviceRemoved(ServiceEvent serviceEvent) {

        }

        @Override
        public void serviceResolved(ServiceEvent serviceEvent) {
            String path = serviceEvent.getInfo().getURLs()[0];
            if (path != null && path.contains("?wsdl")) url.add(path);
        }
    }
}
