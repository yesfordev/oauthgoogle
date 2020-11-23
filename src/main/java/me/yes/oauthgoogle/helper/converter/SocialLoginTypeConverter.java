package me.yes.oauthgoogle.helper.converter;

import me.yes.oauthgoogle.helper.constants.SocialLoginType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType> {
    @Override
    public SocialLoginType convert(String s) {
        return SocialLoginType.valueOf(s.toUpperCase()); // 대문자 값을 소문자로 mapping하기 위해
    }
}
