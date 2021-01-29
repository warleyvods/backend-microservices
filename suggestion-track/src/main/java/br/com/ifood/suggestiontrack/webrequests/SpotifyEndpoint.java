package br.com.ifood.suggestiontrack.webrequests;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "spotify", url = "")
public class SpotifyEndpoint {
}
