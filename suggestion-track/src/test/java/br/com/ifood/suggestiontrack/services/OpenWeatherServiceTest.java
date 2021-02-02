package br.com.ifood.suggestiontrack.services;

import br.com.ifood.suggestiontrack.SuggestionTrackApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuggestionTrackApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpenWeatherServiceTest {

    @Autowired
    OpenWeatherService openWeatherService;

    @Test
    public void test() {

    }


//    @Test
//    public void getTemperatureByCity() {
//        Float goiania = openWeatherService.getTemperatureByCity("goiania");
//        System.out.println(goiania);
//    }

//    @Test
//    public void getTemperatureByCityFeign() {
//        Float goiania = openWeatherService.getTemperatureFeign("goiania");
//        System.out.println(goiania);
//    }

}
