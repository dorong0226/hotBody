package com.sparta.hotbody.post.service;

import com.sparta.hotbody.post.entity.Post;
import com.sparta.hotbody.post.entity.PostLike;
import com.sparta.hotbody.post.repository.PostLikeRepository;
import com.sparta.hotbody.post.repository.PostRepository;
import com.sparta.hotbody.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

  private final PostRepository postRepository;

  private final PostLikeRepository postLikeRepository;

  // 6. 게시글 좋아요 추가
  @Transactional
  public void okLikes(Long postId, User user) {
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("게시글 존재 유무 확인")
    );
    if (postLikeRepository.existsByPostIdAndUserId(postId, user.getId())) {
      throw new IllegalArgumentException("이미 좋아요 버튼을 눌렀습니다.");
    }
    PostLike postLike = new PostLike(post, user);
    postLikeRepository.countAllByPostId(postId);
    postLikeRepository.save(postLike);
  }

  // 7. 게시글 좋아요 취소
  @Transactional
  public void cancelLikes(Long postId, User user) {
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다.")
    );
    if (!postLikeRepository.existsByPostIdAndUserId(postId, user.getId())) {
      throw new IllegalArgumentException("이미 좋아요가 취소되었습니다.");
    }
    postLikeRepository.deleteByPostIdAndUserId(postId, user.getId());
  }
}
