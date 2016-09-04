package org.abc.openlaw.utils.token;

/**
 * Created by scamisay on 28/02/16.
 */
public abstract class Token {

    protected String content;

    protected String getContent() {
        return content;
    }

    protected String readTagContent(String tag){
        return tag.split("\\[")[1].split("\\]")[0];
    }
}
