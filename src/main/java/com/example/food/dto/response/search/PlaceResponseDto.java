package com.example.food.dto.response.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponseDto {

    private Response response;

    @Getter
    @Setter
    public static class Response{
        private Body body;
    }

    @Getter
    @Setter
    public static class Body {
        private Items items;
        private int totalCount;
    }

    @Getter
    @Setter
    public static class Items {
        private List<Item> item;
    }

    @Getter
    @Setter
    public static class Item {
        private String addr1;
        private String areacode; // 제주도 지역번호 : 39
        private String contenttypeid;  // 12 : 관광지, 32 : 숙박, 39: 음식점
        private String firstimage;
        private String mapx;
        private String mapy;
        private String tel;
        private String title;
    }

}
