package org.abc.openlaw.utils.token;

/**
 * Created by scamisay on 28/02/16.
 */
public class ArticleToken extends Token {

    public ArticleToken(String tag) {
        content = readTagContent(tag);
    }
}
