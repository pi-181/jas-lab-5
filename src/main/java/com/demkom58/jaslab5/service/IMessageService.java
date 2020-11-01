package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.Message;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IMessageService {
    @WebMethod
    boolean put(Integer messageId, Integer sender, String textMessage,
                Boolean removed, Integer conversation);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    Message get(int id);

    @WebMethod
    Message[] getAll();
}
