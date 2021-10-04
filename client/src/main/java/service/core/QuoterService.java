package service.core;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface QuoterService {
    @WebMethod
    Quotation generateQuotation(ClientInfo info);
}