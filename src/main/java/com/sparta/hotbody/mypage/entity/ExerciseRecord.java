package com.sparta.hotbody.mypage.entity;

import com.sparta.hotbody.common.TimeStamp;
import com.sparta.hotbody.mypage.dto.ExerciseRecordRequestDto;
import com.sparta.hotbody.mypage.service.CalorieCalculator;
import com.sparta.hotbody.mypage.service.CalorieCalculator.ExerciseType;
import com.sparta.hotbody.user.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "record")
@Getter
@NoArgsConstructor
public class ExerciseRecord extends TimeStamp {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  User user;

  @Column
  private String exercise; // 운동 명
  @Column
  private int time; // 운동 시간(분)
  @Column
  private int reps; // 반복 횟수

  @Column
  private double calories; // 소모 열량

  @Column
  private LocalDateTime date; // 운동 일시



  public ExerciseRecord(User user, ExerciseRecordRequestDto exerciseRecordRequestDto) {
    CalorieCalculator calorieCalculator = new CalorieCalculator();
    this.user = user;
    this.exercise = exerciseRecordRequestDto.getExercise();
    this.time = exerciseRecordRequestDto.getTime();
    this.reps = exerciseRecordRequestDto.getReps();
    this.calories = calorieCalculator.calculateCaloriesBurned(user.getWeight(), time,
        ExerciseType.valueOf(exercise), reps);
  }

  public void update(ExerciseRecordRequestDto exerciseRecordRequestDto){
    this.exercise = exerciseRecordRequestDto.getExercise();
    this.time = exerciseRecordRequestDto.getTime();
    this.reps = exerciseRecordRequestDto.getReps();
  }

}
