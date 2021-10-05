package service.core;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface QuoterService {
    @WebMethod
    Quotation generateQuotation(ClientInfo info);
}