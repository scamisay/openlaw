package org.abc.openlaw.domain;

/**
 * Created by scamisay on 04/09/16.
 */
public class ModificationPair {

    private int lawVersionIndex;
    private int articleIndex;

    public ModificationPair(int lawVersionIndex, int articleIndex) {
        this.lawVersionIndex = lawVersionIndex;
        this.articleIndex = articleIndex;
    }

    public int getLawVersionIndex() {
        return lawVersionIndex;
    }

    public int getArticleIndex() {
        return articleIndex;
    }
}
