package sopt.org.ThirdSeminar.config.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sopt.org.ThirdSeminar.config.jwt.JwtService;
import sopt.org.ThirdSeminar.config.redis.TokenDto;
import sopt.org.ThirdSeminar.exception.model.ExpiredAccessRxception;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component //@UserId 어노테이션을 쓰면 해당 작업이 수행됨
public class UserIdResolver implements HandlerMethodArgumentResolver { //컨트롤러 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 값을 바인딩 해주는 인터페이스
   //ex) @RequestBody
    private final JwtService jwtService;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {  //어떤 파라미터에 대해 작동할건지 정의하는 곳
        return parameter.hasParameterAnnotation(UserId.class) && Long.class.equals(parameter.getParameterType());
        //파라미터 어노테이션이 userId이고 파라미터 타입이 Long이면 True
    }

    @Override // 파라미터에 대한 실질적인 로직을 처리하는 곳
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest(); //리퀘스트 가져옴
        String accessToken = request.getHeader("Authorization"); //헤더에 있는 인증 토큰 가져옴
        String refreshToken = request.getHeader("Refresh"); //헤더에 있는 인증 토큰 가져옴
        try {
            // 토큰 검증
            if (!jwtService.verifyToken(accessToken, refreshToken)) { //token에 클레임이 있는지 (access token 유효성 검사)
                throw new RuntimeException(String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(), parameter.getMethod()));
            }
        } catch (ExpiredAccessRxception e) {
            TokenDto tokenDto = jwtService.regenerateToken(refreshToken);
            accessToken = tokenDto.getAccessToken();
            refreshToken = tokenDto.getRefreshToken();
        }

        // 유저 아이디 반환
        final String tokenContents = jwtService.getJwtContents(accessToken); //token -> userId
        try {
            return Long.parseLong(tokenContents);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(), parameter.getMethod()));
        }
    }
}
