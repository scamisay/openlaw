package org.abc.openlaw.utils.token;

/**
 * Created by scamisay on 01/03/16.
 */
public class ItemToken extends Token {

    public ItemToken(String tag) {
        content = readTagContent(tag);
    }
}
