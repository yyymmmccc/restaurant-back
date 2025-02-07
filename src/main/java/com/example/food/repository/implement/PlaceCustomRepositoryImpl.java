package com.example.food.repository.implement;

import com.example.food.domain.Place;
import com.example.food.repository.PlaceCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import org.springframework.data.web.PageableDefault;
import java.util.List;

import static com.example.food.domain.QPlace.place;

@Repository
@AllArgsConstructor
@Slf4j
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Place> findByPlace(String query, int categoryCode, Pageable pageable) {

        List<Place> content = jpaQueryFactory
                .selectFrom(place)
                .where(containQuery(query))
                .where(equalCategoryCode(categoryCode))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(place.count())
                .from(place)
                .where(containQuery(query))
                .where(equalCategoryCode(categoryCode));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression containQuery(String query){
        if(!query.isEmpty()){
            return place.placeName.contains(query);
        }

        return null;
    }

    private BooleanExpression equalCategoryCode(int categoryCode){
        if(categoryCode != 0){
            return place.categoryCode.eq(categoryCode);
        }

        return null;
    }
}
