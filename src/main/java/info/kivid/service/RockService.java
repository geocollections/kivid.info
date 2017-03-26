package info.kivid.service;

import info.kivid.model.RockApiResult;
import org.springframework.stereotype.Service;

@Service
public class RockService {

    //TODO get results from API
    public RockApiResult getRock(String id) {

        RockApiResult rockApiResult = new RockApiResult();
        rockApiResult.setId(id);
        rockApiResult.setName("rõngaspaas");
        rockApiResult.setDescription("Karplubjakivi koosneb valdavalt mereloomade tervikuna säilinud kodadest, sarnanedes tekke, koostise ja ka kasutusviiside poolest purdlubjakivile. Karplubjbakivi kõige iseloomulikumaks näiteks Eestis on Borealis-lubjakivi, mis koosneb brahhiopoodi Borealis borealis'e kodadest. See eripärane settekiht moodustus Siluri ajastu alguses ligikaudu 440 miljoni aasta eest, kui Eesti ala kattis troopiline madalmeri. Piiratud alal Kesk-Eestis kuhjusid käsijalgsete kojad kuni 13 m paksuse kihina. Borealis-lubjakivi on väga puhas karbonaatkivim, milles saviaines jm lisandid praktiliselt puuduvad.");
        return rockApiResult;
    }
}
