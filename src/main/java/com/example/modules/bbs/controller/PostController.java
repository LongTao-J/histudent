package com.example.modules.bbs.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.modules.bbs.entity.dto.IssuePostDTO;
import com.example.modules.bbs.entity.dto.PostDTO;
import com.example.modules.bbs.entity.po.Post;
import com.example.modules.bbs.entity.vo.PostVO;
import com.example.modules.bbs.repository.PostLikeRepository;
import com.example.modules.bbs.repository.PostRepository;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/post")
public class PostController {
    @Autowired
    PostRepository postRepositoryImpl;
    @Autowired
    PostLikeRepository postLikeRepositoryImpl;

    @PutMapping("/put/upload-file")
    @CrossOrigin
    public R<Object> uploadImg(@RequestBody String url){
        try{
            // 通过Redis获取UserId;
            String userId = "1";    // 暂用此替代
            postRepositoryImpl.uploadReleasePostFile(userId, url);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/issue-post")
    @CrossOrigin
    public R<Object> issuePost(@RequestBody IssuePostDTO issuePostDTO){
        try{
            // 通过Redis获取UserId;
            String userId = "1";    // 暂用此替代
            Post post = new Post();
            post.setTitle(issuePostDTO.getTitle());
            post.setContent(issuePostDTO.getContent());
            post.setUserId(userId);
            List<String> list = postRepositoryImpl.getReleasePostFileListCache(userId);
            PostDTO dto = new PostDTO();
            dto.setPost(post);
            dto.setImages(list);
            postRepositoryImpl.issuePost(dto);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/un-issue-post")
    @CrossOrigin
    public R<Object> unissuePost(){
        try{
            // 通过Redis获取UserId;
            String userId = "1";    // 暂用此替代
            postRepositoryImpl.unissuePost(userId);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/all-rec")
    @CrossOrigin
    public R<Object> getListRec(){
        try{
            // 通过Redis获取UserId;
            String userId = "1";    // 暂用此替代
            // 获取推荐Post列表
            List<Post> posts = postRepositoryImpl.getRecPostList();
            // 生成推荐VO视图模型
            List<PostVO> list = new ArrayList<>();
            for(Post post : posts){
                // 数据注入Post
                PostVO temp = new PostVO();
                temp.setPost(post);
                // 判断当前登录用户是否点赞帖子
                Integer like = postLikeRepositoryImpl.isLike(userId, post.getId());
                // null: 从未点赞过, 1: 点赞状态, 0: 点赞过又取消状态
                if(like == null || like == 0) temp.setUserIsLike(false);
                else temp.setUserIsLike(true);
                // 数据注入图片列表
                List<String> images = postRepositoryImpl.getFileListByPostId(post.getId());
                temp.setImages(images);
                // 将结果插入到返回列表中
                list.add(temp);
            }
            return R.success(list);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/rec/{post-id}")
    @CrossOrigin
    public R<Object> setRec(@PathVariable("post-id") String postId) {
        try{
            postRepositoryImpl.recPost(postId);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/un-rec/{post-id}")
    @CrossOrigin
    public R<Object> unRec(@PathVariable("post-id") String postId) {
        try{
            postRepositoryImpl.unRecPost(postId);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }
}
