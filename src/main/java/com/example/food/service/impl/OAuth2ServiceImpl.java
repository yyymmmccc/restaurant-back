package com.example.food.service.impl;

import com.example.food.domain.User;
import com.example.food.oauth2.CustomOAuth2User;
import com.example.food.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2ServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request){

        OAuth2User oAuth2User = super.loadUser(request);
        // 외부 서비스로부터 사용자의 정보를 가져와 OAuth2User 객체로 반환

        String provider = request.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 시 키(PK)가 되는 값
        // 카카오 : id, 네이버 : response
        String userNameAttributeName = request.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes(); // 소셜 로그인에서 API가 제공하는 userInfo의 JSON값

        String userId = extractUserId(attributes, provider);  // kakao, naver 각각 사용자 정보 id 추출하기

        User user = registerUser(userId, provider);

        return new CustomOAuth2User(oAuth2User.getAuthorities(), attributes, userNameAttributeName, user);
    }

    public String extractUserId(Map<String, Object> attributes, String provider){

        if(provider.equals("kakao")){
            String userId = String.valueOf(attributes.get("id"));

            return userId;
        }

        else if(provider.equals("naver")){
            Map<Object, String> map = (Map<Object, String>) attributes.get("response");
            String userId = map.get("id");

            return userId;
        }

        return null;
    }

    public User registerUser(String userId, String provider){

        User user = userRepository.findById(userId).orElse(null);

        if(user == null){

            return userRepository.save(User.builder()
                    .userId(userId)
                    .password(String.valueOf(UUID.randomUUID()))
                    .nickname(userId)
                    .provider(provider)
                    .build());
        }

        return user;
    }
}
