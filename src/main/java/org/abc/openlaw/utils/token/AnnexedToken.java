package org.abc.openlaw.utils.token;

/**
 * Created by scamisay on 01/03/16.
 */
public class AnnexedToken extends Token {
    public AnnexedToken(String tag) {
        content = readTagContent(tag);
    }
}
