package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.Conversation;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IConversationService {
    @WebMethod
    boolean put(Integer conversationId, String conversationName, Boolean removed, Boolean personal);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    Conversation get(int id);

    @WebMethod
    Conversation[] getAll();
}
