package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.Post;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IPostService {
    @WebMethod
    boolean put(Integer postId, String postContent, Integer views,
                Integer group, Integer author, Long postDate);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    Post get(int id);

    @WebMethod
    Post[] getAll();
}
