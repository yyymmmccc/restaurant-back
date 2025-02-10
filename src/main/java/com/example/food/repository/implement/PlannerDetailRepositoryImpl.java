package com.example.food.repository.implement;

import com.example.food.domain.PlannerDetail;
import com.example.food.repository.PlannerDetailCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlannerDetailRepositoryImpl implements PlannerDetailCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void batchInsertPlannerDetail(List<PlannerDetail> plannerDetails) {

        String sql = "INSERT INTO planner_detail(day_number, place_id, planner_id) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            // BatchPreparedStatementSetter 실행 -> BatchSize 만큼 setValues()를 반복 ->
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                PlannerDetail detail = plannerDetails.get(i);
                ps.setInt(1, detail.getDayNumber());
                ps.setInt(2, detail.getPlace().getPlaceId());
                ps.setInt(3, detail.getPlanner().getPlannerId());
                // SQL 쿼리 VALUES (?, ?, ?) 에 값을 바인딩함
            }

            @Override
            public int getBatchSize() {
                return plannerDetails.size();
            }
        });
    }
}
