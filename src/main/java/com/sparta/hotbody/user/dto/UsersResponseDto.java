package com.sparta.hotbody.user.dto;

import com.sparta.hotbody.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;


@Getter
public class UsersResponseDto {
  private Long id;
  private String nickname;
  private int gender;
  private int age;
  private String region;
  private String email;

  public UsersResponseDto(){}

  public UsersResponseDto(User user){
    this.id = user.getId();
    this.nickname = user.getNickname();
    this.gender = user.getGender();
    this.age = user.getAge();
    this.region = user.getRegion();
    this.email = user.getEmail();
  }

  @Builder
  public UsersResponseDto(Long id, String nickname, int gender, int age, String region, String email){
    this.id = id;
    this.nickname = nickname;
    this.gender = gender;
    this.age = age;
    this.region = region;
    this.email = email;
  }

  public Page<UsersResponseDto> toDtoPage(Page<User> userPage) {
    Page<UsersResponseDto> usersResponseDtoPage = userPage
        .map(m -> UsersResponseDto.builder()
            .id(m.getId())
            .nickname(m.getNickname())
            .gender(m.getGender())
            .age(m.getAge())
            .region(m.getRegion())
            .build());
    return usersResponseDtoPage;
  };
}
