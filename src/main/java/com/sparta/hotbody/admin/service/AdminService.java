package com.sparta.hotbody.admin.service;

import com.sparta.hotbody.common.page.PageDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {

  ResponseEntity permitTrainer(Long userId);

  ResponseEntity getRegistrations(PageDto pageDto);

  ResponseEntity refuseTrainer(Long userId);

  ResponseEntity updatePost(Long postId); // TODO: PostRequestDto 추가

  ResponseEntity deletePost(Long postId);

  ResponseEntity updateComment(Long commentId가); // TODO: CommentRequestDto 추가

  ResponseEntity deleteComment(Long commentId);

  ResponseEntity getUserList(PageDto pageDto);

  ResponseEntity getUser(Long userId);

  ResponseEntity getTrainerList(PageDto pageDto);

  ResponseEntity getTrainer(Long trainerId);

  ResponseEntity updateUserInfo(Long userId); // TODO: UserRequestDto 추가

}
