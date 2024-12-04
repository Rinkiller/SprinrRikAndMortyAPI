package rin.copmani.RikiAndMortiAPIUrlDownloader.repository;


import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;
import rin.copmani.RikiAndMortiAPIUrlDownloader.models.Characters;
import rin.copmani.RikiAndMortiAPIUrlDownloader.models.Result;

import java.util.List;
import java.util.Optional;


@Repository
@Data
@Log
public class RepositoryApi {
    //Параметры загруженных на данный момент характеров(данных)
    private Characters characters;

    public RepositoryApi() {
        this.characters = new Characters();
    }

    /*Поиск характера по его id*/
    public Result getResultById(Integer id){
        log.info("id res = " + id);
        List<Result> rl = characters.getResults();
        log.info("List = " + rl);
        Optional<Result> r = rl.stream().filter(res->res.getId()==id).findFirst();//characters.getResults().stream().findFirst().filter(result -> result.getId() == id);
        log.info("Result finded = " + r);
        if(r.isPresent())return  r.get(); else return null;
    }
}
