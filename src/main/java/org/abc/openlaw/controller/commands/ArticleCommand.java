package org.abc.openlaw.controller.commands;

import org.abc.openlaw.domain.Article;

/**
 * Created by scamisay on 07/02/16.
 */
public class ArticleCommand {
    private String general;

    public ArticleCommand() {}

    public ArticleCommand(Article article) {
        if(article != null){
            general = article.getGeneral();
        }else{
            general = "";
        }
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public Article build() {
        Article anArticle = new Article(general);
        return anArticle;
    }
}
