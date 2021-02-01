package br.com.ifood.suggestiontrack.constrants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpConstants {

    public static final String BASIC_AUTHORIZATION = "Basic ";
    public static final String BEARER_AUTHORIZATION = "Bearer ";

    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_CREDENTIALS = "client_credentials";

}
