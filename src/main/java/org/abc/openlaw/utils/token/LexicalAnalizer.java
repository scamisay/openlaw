package org.abc.openlaw.utils.token;

import org.abc.openlaw.utils.exception.TokenizingException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by scamisay on 28/02/16.
 */
public class LexicalAnalizer {

    private static final String ARTICLE_PATTERN = "Article\\s*(\\s\\d)";
    private static final String ITEM_PATTERN = "Item\\s*(\\s\\d)";
    private static final String ANNEXED_PATTERN = "Annexed";


    public List<Token> tokenize(String aString) throws TokenizingException {
        return Arrays.asList(aString.split("\n"))
                                .stream()
                                .map(e->analizeLine(e))
                                .collect(Collectors.toList());
    }

    private Token analizeLine(String aLine) {
        if(aLine.matches(taggedElement(ARTICLE_PATTERN))){
            return new ArticleToken(aLine);
        }else if(aLine.matches(taggedElement(ITEM_PATTERN))){
            return new ItemToken(aLine);
        }else if(aLine.matches(taggedElement(ANNEXED_PATTERN))){
            return new AnnexedToken(aLine);
        }else{
            return new TextToken(aLine);
        }
    }

    private String taggedElement(String tagContent){
        return "(?i:\\s*\\[\\s*"+tagContent+"\\s*\\])";
    }
}
