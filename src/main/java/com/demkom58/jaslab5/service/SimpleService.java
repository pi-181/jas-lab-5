package com.demkom58.jaslab5.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SimpleService <T> {
    @WebMethod
    public boolean add(T p);

    @WebMethod
    public boolean delete(int id);

    @WebMethod
    public T get(int id);

    @WebMethod
    public T[] getAll();
}
