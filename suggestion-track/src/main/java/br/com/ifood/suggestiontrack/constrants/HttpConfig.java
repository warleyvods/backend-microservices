package br.com.ifood.suggestiontrack.constrants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpConfig {

    public static final String BASIC_AUTH = "Basic ";
    public static final String BEARER_AUTH = "Bearer ";
    public static final String TYPE = "grant_type";
    public static final String CLIENT = "client_credentials";

}
