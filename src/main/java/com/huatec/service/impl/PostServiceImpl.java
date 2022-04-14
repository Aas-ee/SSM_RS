package com.huatec.service.impl;


import com.huatec.dao.PositionDao;
import com.huatec.dao.PostDao;
import com.huatec.domain.Post;
import com.huatec.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service("PostService")
public class PostServiceImpl implements PostService{

    @Autowired
    private PostDao postDao;


    @Override
    public List<Post> findPosts() {
        return postDao.findPosts();
    }

//    @Override
//    public Integer getCount(Map<String, Object> map) {
//
//        return postDao.getCount(map);
//    }
@Override
    public Integer addPost(Post post) {

        return postDao.addPost(post);
    }
@Override
    public Integer updatePost(Post post) {

        return postDao.updatePost(post);
    }
@Override
    public Integer deletePost(Integer id) {

        return postDao.deletePost(id);
    }
@Override
    public Post getPostById(Integer id) {

        return postDao.getPostById(id);
    }

}

