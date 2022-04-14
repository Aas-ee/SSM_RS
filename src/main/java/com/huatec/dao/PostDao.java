package com.huatec.dao;



import java.util.List;
import java.util.Map;

import com.huatec.domain.Post;
import org.springframework.stereotype.Repository;



@Repository
public interface PostDao {

    public List<Post> findPosts();


    public Integer addPost(Post post);


    public Integer updatePost(Post post);


    public Integer deletePost(Integer id);


    public Post getPostById(Integer id);
}