package com.woowacourse.moragora.presentation.auth;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenCookieProvider {

    private static final String REFRESH_TOKEN = "refreshToken";

    private final long validityInMilliseconds;

    public RefreshTokenCookieProvider(
            @Value("${security.refresh.token.expire-length}") final long validityInMilliseconds) {
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public ResponseCookie create(final String refreshToken) {
        return createTokenCookieBuilder(refreshToken)
                .maxAge(Duration.ofMillis(validityInMilliseconds))
                .build();
    }

    private ResponseCookieBuilder createTokenCookieBuilder(final String value) {
        return ResponseCookie.from(REFRESH_TOKEN, value)
                .httpOnly(true)
                .secure(true)
                .path("/token/refresh")
                .sameSite(SameSite.NONE.attributeValue());
    }
}
