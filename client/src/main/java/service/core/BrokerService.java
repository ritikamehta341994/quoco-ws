package service.core;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface BrokerService {
    @WebMethod
    LinkedList<Quotation> getQuotations(ClientInfo info) ;
}