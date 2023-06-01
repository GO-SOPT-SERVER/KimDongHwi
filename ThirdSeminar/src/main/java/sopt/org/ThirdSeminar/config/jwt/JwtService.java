package sopt.org.ThirdSeminar.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sopt.org.ThirdSeminar.config.redis.TokenDto;
import sopt.org.ThirdSeminar.exception.ErrorStatus;
import sopt.org.ThirdSeminar.exception.model.ExpiredAccessRxception;
import sopt.org.ThirdSeminar.exception.model.NotFoundException;
import sopt.org.ThirdSeminar.exception.model.UnauthorizedException;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final Long REFRESH_TOKEN_EXPIRE_TIME = 120 * 60 * 1000L;

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostConstruct
    protected void init() { //1. 시작할 때 jwt.secret값을 불러와 저장
        jwtSecret = Base64.getEncoder()
                .encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
//    public String generateRefreshToken(String userId) {
//        RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(), userId);
//        refreshTokenRepository.save(refreshToken);
//    }

    // JWT 토큰 발급 (2. login 할 시 String 형태로 토큰 반환)
    public String issuedToken(String userId) {
        final Date now = new Date();

        // 클레임 생성 (주체가 무엇인지를 표현하는 이름과 값의 쌍) [등록된 클레임 (서비스와 무관하게 토큰에 대한 정보들을 담기 위해 이미 존재하는 클레임]
        final Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(now) //발행시간
                .setExpiration(new Date(now.getTime() + 60 * 1000L)); //유효기간

        //private claim 등록 (유저 id 등록) [비공개 클레임 (클라이언트와 서버간 합의하에 사용)]
        claims.put("userId", userId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE , Header.JWT_TYPE) //헤더
                .setClaims(claims) //payload
                .signWith(getSigningKey()) //signature
                .compact(); //압축 및 서명
    }

    private String issuedRefreshToken(String userId) {
        final Date now = new Date();

        // 클레임 생성 (주체가 무엇인지를 표현하는 이름과 값의 쌍) [등록된 클레임 (서비스와 무관하게 토큰에 대한 정보들을 담기 위해 이미 존재하는 클레임]
        final Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(now) //발행시간
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME)); //유효기간

        //private claim 등록 (유저 id 등록) [비공개 클레임 (클라이언트와 서버간 합의하에 사용)]
        claims.put("userId", userId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //헤더
                .setClaims(claims) //payload
                .signWith(getSigningKey()) //signature
                .compact();//압축 및 서명
    }

    // JWT 토큰 발급 (2. login 할 시 String 형태로 토큰 반환)
    public TokenDto signIn(String userId) {
        String refreshToken = issuedRefreshToken(userId);
        String accessToken = issuedToken(userId);

        TokenDto tokenDto = new TokenDto(accessToken, refreshToken);

        redisTemplate.opsForValue().set( //redis에 저장 - 만료시간 설정을 통해 자동 삭제 처리
                userId, //키값
                refreshToken,
                REFRESH_TOKEN_EXPIRE_TIME,
                TimeUnit.MILLISECONDS
        );

        return tokenDto;
    }

    private Key getSigningKey() { //서명할때 사용하는 비밀키 생성
        final byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8); //jwtSecret의 값을 바이트 단위로 가져와
        return Keys.hmacShaKeyFor(keyBytes); //SHA 기반으로 secret key 생성 (유저가 String or Byte형태로 가지고 있던 비밀키를 Secret key 객체로 변환)
    }

    // JWT 토큰 검증 (userId Resolver에서 사용)
    public boolean verifyToken(String accessToken, String refreshToken) throws ExpiredAccessRxception{
        try {
            final Claims claims = getBody(accessToken);
            return true;
        } catch (RuntimeException e) {
            if (e instanceof ExpiredJwtException) { //access토큰이 만료되었을 때 예외처리하여 새로 생성하게끔 함
                throw new ExpiredAccessRxception();
            }
            return false;
        }
    }

    public boolean verifyRefreshToken(String refreshToken) {
        try {
            final Claims claims = getBody(refreshToken);
            return true;
        } catch (RuntimeException e) {
            if (e instanceof ExpiredJwtException) { //access토큰이 만료되었을 때 작동하는 것
                throw new UnauthorizedException(ErrorStatus.TOKEN_TIME_EXPIRED_EXCEPTION, ErrorStatus.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());
            }
            return false;
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder() //JwtParseBuilder 인스턴스 생성
                .setSigningKey(getSigningKey()) //서명 검증을 위한 secret key 지정 (내가 가지고 있는 secret key) (개인키 공개키 방식에선 공개키가 할당)
                .build() //스레드에 안전한 JwtParser를 리턴하기 위해 호출
                .parseClaimsJws(token) // ClaimesJws는 body에 claims가 포함되어 있는 jwt(토큰 자체에 정보들을 저장하고 있는 웹토큰)다
                // 받아온 토큰으로 원본 Jws 반환 (claim) (Json web signature = 인증정보를 서버의 private key로 서명한 것을 토큰화 한것)
                .getBody(); //인스턴스의 claim or string 반환
    }

    // JWT 토큰 내용 확인
    public String getJwtContents(String token) {
        final Claims claims = getBody(token); //클레임을 가져와
        return (String) claims.get("userId"); //유저 아이디 반환
    }

    public TokenDto regenerateToken(String token) {
            // Refresh Token 검증
            if (!verifyRefreshToken(token)) {
                throw new NotFoundException(ErrorStatus.NOT_FOUND_USER_EXCEPTION, ErrorStatus.NOT_FOUND_USER_EXCEPTION.getMessage());
            }

            // Access Token 에서 User id를 가져온다.
            String userId = getJwtContents(token);

            // Redis에서 저장된 Refresh Token 값을 가져온다.
//            String refreshToken = redisTemplate.opsForValue().get(userId);
//            if(!refreshToken.equals(token)) {
//                throw new BadRequestException(ErrorStatus.VALIDATION_EXCEPTION, ErrorStatus.VALIDATION_EXCEPTION.getMessage());
//            }
            // 토큰 재발행
//            String new_refresh_token = issuedRefreshToken(refreshToken);
            TokenDto tokenDto = new TokenDto(
                    issuedToken(userId),
                    token
            );
            // RefreshToken Redis에 업데이트
            redisTemplate.opsForValue().set(
                    userId,
                    token,
                    REFRESH_TOKEN_EXPIRE_TIME,
                    TimeUnit.MILLISECONDS
            );

            return tokenDto;
    }
}
