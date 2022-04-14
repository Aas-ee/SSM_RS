package com.huatec.service;


import com.huatec.domain.Post;

import java.util.List;
import java.util.Map;



public interface PostService {


    public List<Post>findPosts();


//    public Integer getCount(Map<String, Object> map);


    public Integer addPost(Post post);


    public Integer updatePost(Post post);


    public Integer deletePost(Integer id);


    public Post getPostById(Integer id);
}