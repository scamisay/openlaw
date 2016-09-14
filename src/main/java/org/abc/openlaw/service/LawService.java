package org.abc.openlaw.service;

import org.abc.openlaw.domain.Law;
import org.abc.openlaw.domain.LawVersion;

import java.util.List;
import java.util.Map;

/**
 * Created by scamisay on 04/09/16.
 */
public interface LawService {

    Law saveLaw(Law law);

    List<Law> findAllLaws();

    Law findLawById(String lawId);

    LawVersion editArticle(Law law, Integer articleNumber, String articleValue);

    LawVersion editArticles(Law law, Map<Integer, String> mapArticlesNumberValues);

    Law deleteArticles(Law law, Map<Integer, String> mapArticlesNumberValues);

    Law addArticles(Law law, Map<Integer, String> mapArticlesNumberValues);

    Law cloneLaw(Law law);

    Law mergeLaws(Law law1, Law law2);

}
