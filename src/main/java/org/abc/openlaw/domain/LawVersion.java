package org.abc.openlaw.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by scamisay on 05/02/16.
 */
public class LawVersion {

    @Id
    private String id;

    private Date modificationDate;

    private String general;

    private List<Article> articles;

    public LawVersion(){
        modificationDate = new Date();
    }

    public LawVersion(String general) {
        this.general = general;
    }

    public LawVersion(String general, List<Article> modifiedArticles) {
        modificationDate = new Date();
        this.general = general;
        articles = modifiedArticles;
    }

    public LawVersion addArticle(Article anArticle){
        if(articles == null){
            articles = new ArrayList<Article>();
        }
        articles.add(anArticle);
        return this;
    }

    @Transient
    public boolean isNew() {
        return id == null;
    }

    public String getId() {
        return id;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public String getGeneral() {
        return general;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
