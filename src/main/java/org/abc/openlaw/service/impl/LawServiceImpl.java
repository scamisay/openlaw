package org.abc.openlaw.service.impl;

import org.abc.openlaw.domain.Article;
import org.abc.openlaw.domain.Law;
import org.abc.openlaw.domain.LawVersion;
import org.abc.openlaw.domain.ModificationPair;
import org.abc.openlaw.repository.LawRepository;
import org.abc.openlaw.repository.LawVersionRepository;
import org.abc.openlaw.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by scamisay on 05/02/16.
 */
@Service
public class LawServiceImpl implements LawService {

    @Autowired
    private LawVersionRepository lawVersionRepository;

    @Autowired
    private LawRepository lawRepository;

    public Law saveLaw(Law law){
        for(LawVersion aVersion : law.getVersions()){
            if(aVersion.isNew()){
                lawVersionRepository.save(aVersion);
            }
        }

        //armo un arreglo con las referencia a las versiones
        law.copyVersionIds();

        return lawRepository.save(law);
    }

    public List<LawVersion> findAllVersions(){
        return lawVersionRepository.findAll();
    }

    public LawVersion saveVersion(LawVersion aLaw){
        return lawVersionRepository.save(aLaw);
    }

    public List<Law> findAllLaws() {
        return lawRepository.findAll();
    }

    public Law findLawById(String lawId) {
        Law law = lawRepository.findOne(lawId);
        for(String versionId : law.getLawVersionIds()){
            law.addNewLawVersion(lawVersionRepository.findOne(versionId));
        }
        return law;
    }

    public LawVersion editArticle(Law law, Integer articleNumber, String articleValue) {
        LawVersion previousLawVersion = law.getLastVersion();

        //normalizo el texto que puede venir con retorno de carro
        String persistedArticle = previousLawVersion.getArticles()
                .get(articleNumber-1).getGeneral()
                .replaceAll("\r","");
        /**
         * si no hubo cambios => no se genera ninguna version nueva
         */
        if(persistedArticle.equals(articleValue)){
            return null;
        }

        /**
         * guardando nueva version en forma de listas con elementos nulos
         */
        Integer size = law.getLastVersion().getArticles().size();
        Article[] articles = new Article[size];
        articles[articleNumber-1] = new Article(articleValue);
        List<Article> modifiedArticles = Arrays.asList(articles);
        LawVersion modifiedLawVersion = new LawVersion(previousLawVersion.getGeneral(), modifiedArticles);

        law.addNewLawVersion(modifiedLawVersion);

        //TODO: persistir ley modificada
     //   saveLaw(law);

        return modifiedLawVersion;
    }

    /**
     *
     * @param law
     * @param mapArticlesNumberValues ej: {(1, "soy el contenido del articulo 1"), (5, "soy el contenido del articulo 5")}
     * @return
     */
    @Override
    public LawVersion editArticles(Law law, Map<Integer, String> mapArticlesNumberValues) {
        /**
         * creo lawversion para despues persistirla
         */
        LawVersion modifiedLawVersion = new LawVersion(
            law.getLastVersion().getGeneral(),
            mapArticlesNumberValues.entrySet().stream()
                    .map(Map.Entry::getValue)
                    .map(value -> new Article(value))
                    .collect(Collectors.toList())
            );
        law.addNewLawVersion(modifiedLawVersion);

        /**
         * armo el history y se lo paso a la law
         */
        mapArticlesNumberValues.forEach(
            (articleIndex,articleValue) ->
                law.addElementToArticleModificationHistory(articleIndex, law.createModificationPair(articleIndex))

        );
        //todo guardar aca law

        return modifiedLawVersion;
    }

    @Override
    public Law deleteArticles(Law law, Map<Integer, String> mapArticlesNumberValues) {
        return null;
    }

    @Override
    public Law addArticles(Law law, Map<Integer, String> mapArticlesNumberValues) {
        return null;
    }

    @Override
    public Law cloneLaw(Law law) {
        return null;
    }

    @Override
    public Law mergeLaws(Law law1, Law law2) {
        return null;
    }
}
