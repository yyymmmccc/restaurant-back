package com.example.food.dto.request.planner;

import com.example.food.domain.Planner;
import com.example.food.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlannerRequestDto {

    private String plannerTitle;
    private Date startDate;
    private Date endDate;

    public Planner toEntity(User user){
        return Planner.builder()
                .plannerTitle(this.plannerTitle)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .user(user)
                .build();
    }

}
