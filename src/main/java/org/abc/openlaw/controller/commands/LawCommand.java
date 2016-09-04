package org.abc.openlaw.controller.commands;

import org.abc.openlaw.domain.Article;
import org.abc.openlaw.domain.Law;
import org.abc.openlaw.domain.LawType;
import org.abc.openlaw.domain.LawVersion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scamisay on 07/02/16.
 */
public class LawCommand {

    private String id;
    private String versionId;
    private String title;
    private String synthesis;
    private String type;
    private Integer number;

    private String general;
    private List<ArticleCommand> articles;

    public LawCommand() {}

    public LawCommand(Law law) {
        id = law.getId();
        versionId = law.getLastVersion().getId();
        title = law.getTitle();
        type = law.getType().toString();
        synthesis = law.getSynthesis();
        number = law.getNumber();

        consumeLawVersion(law.getLastVersion());
    }

    private void consumeLawVersion(LawVersion lawVersion) {
        general = lawVersion.getGeneral();
        articles = new ArrayList<ArticleCommand>();
        for(Article article : lawVersion.getArticles()){
            articles.add(new ArticleCommand(article));
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public List<ArticleCommand> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleCommand> articles) {
        this.articles = articles;
    }

    public String getSynthesis() {
        return synthesis;
    }

    public void setSynthesis(String synthesis) {
        this.synthesis = synthesis;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersionId() {
        return versionId;
    }

    public Law build(){
        Law aLaw = new Law(getTitle(), LawType.LEY, getNumber());
        LawVersion newLawVersion = buildLawVersion();
        aLaw.addNewLawVersion(newLawVersion);
        return aLaw;
    }

    private LawVersion buildLawVersion() {
        LawVersion lawVersion = new LawVersion(getGeneral());
        List<Article> articles = buildArticles();
        for(Article article : articles){
            lawVersion.addArticle(article);
        }
        return lawVersion;
    }

    private List<Article> buildArticles() {
        List<Article> articles = new ArrayList<Article>();
        for(ArticleCommand anArticleCommand : getArticles()){
            articles.add(anArticleCommand.build());
        }
        return articles;
    }

    public String getId() {
        return id;
    }
}
