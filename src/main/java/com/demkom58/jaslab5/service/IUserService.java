package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.User;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IUserService extends SimpleService<User> {
}
