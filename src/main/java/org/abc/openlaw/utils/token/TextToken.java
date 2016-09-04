package org.abc.openlaw.utils.token;

/**
 * Created by scamisay on 28/02/16.
 */
public class TextToken extends Token {
    public TextToken(String aLine) {
        content = aLine.trim();
    }
}
