package me.yes.oauthgoogle.service;

import lombok.RequiredArgsConstructor;
import me.yes.oauthgoogle.helper.constants.SocialLoginType;
import me.yes.oauthgoogle.service.social.AppleOauth;
import me.yes.oauthgoogle.service.social.GoogleOauth;
import me.yes.oauthgoogle.service.social.SocialOauth;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;

    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try {
            //구글 로그인 요청시, 구글 웹페이지에서 사용자의 브라우저 세션에 구글 계정이 로그인되어 있는가 및 구글 로그인을 할 수 있는 페이지로 이동
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.requestAccessToken(code);
    }

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
}
