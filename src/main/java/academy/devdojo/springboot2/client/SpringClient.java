package academy.devdojo.springboot2.client;

import academy.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity(
                "http://localhost:8081/animes/{id}", Anime.class, 2);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8081/animes/2", Anime.class);
        log.info(object);

        Anime[] animes  = new RestTemplate().getForObject("http://localhost:8081/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8081/animes/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
        log.info(exchange.getBody());

       /* Anime kingdom = Anime.builder().name("kingdom").build();
        Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8081/animes/", kingdom, Anime.class);
        log.info("Saved anime", kingdomSaved);*/

        Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
        ResponseEntity<Anime> samuraiSaved = new RestTemplate().exchange("http://localhost:8081/animes/",
                HttpMethod.POST,
                new HttpEntity<>(samuraiChamploo), Anime.class);
        log.info("Saved anime {}", samuraiSaved);
    }
}
