package rin.copmani.RikiAndMortiAPIUrlDownloader.service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rin.copmani.RikiAndMortiAPIUrlDownloader.configuration.UrlConfiguration;
import rin.copmani.RikiAndMortiAPIUrlDownloader.models.Characters;
import rin.copmani.RikiAndMortiAPIUrlDownloader.models.Result;
import rin.copmani.RikiAndMortiAPIUrlDownloader.repository.RepositoryApi;
import java.util.List;

@Service
@Data
public class ServiceApi {
    // Подключаем конфигурацию
    @Autowired
    private UrlConfiguration config;
    // Подключаем репозиторий
    @Autowired
    private RepositoryApi repositoryApi;
    /*Подключаем RestTemplate - клиент, предоставляемый Spring, который используется для вызова HTTP-URL и получения ответа в виде JSON-строки*/
    @Autowired
    private RestTemplate restTemplate;
    /*Подключаем HttpHeader - это представление набора HTTP-заголовков. HttpHeaders не создаётся непосредственно, а возвращается из HttpRequest или HttpResponse*/
    @Autowired
    private HttpHeaders httpHeaders;



    /*Метод получения всех данных о характерахю На вход принимает строку "next" - переход на новый раздел данных,"prev" - переход на предыдущий раздел,
    иные аргументы приведут к загрузке первой страницы */
    public Characters getAllCharacters(String upper) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        //Загружаемый url
        String url = config.getUrl();
        // Проверка на загрузку следующих данных
        if(upper.equals("next") && repositoryApi.getCharacters() != null) {
            url = repositoryApi.getCharacters().getInfo().getNext();
        };
        // Проверка на загрузку предыдущих данных
        if(upper.equals("prev") && repositoryApi.getCharacters() != null) {
            url = repositoryApi.getCharacters().getInfo().getPrev();
        };
        //Загрузка данных с указанного URL
        ResponseEntity<Characters> response = restTemplate.exchange(url, HttpMethod.GET,entity,Characters.class);
        // Сохранение загруженных данных в репозиторий
        repositoryApi.setCharacters(response.getBody());
        return response.getBody();
    }

    public Result getResultById(Integer id){
        Result result = repositoryApi.getResultById(id);
        if(result!=null) return result; else return null;
    }

}
