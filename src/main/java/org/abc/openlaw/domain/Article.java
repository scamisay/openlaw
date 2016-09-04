package org.abc.openlaw.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scamisay on 05/02/16.
 */
public class Article {

    private String general;

    private List<Item> items;

    public Article(String general) {
        this.general = general;
    }

    public Article addItem(Item anItem){
        if(items == null){
            items = new ArrayList<Item>();
        }
        items.add(anItem);
        return this;
    }

    public String getGeneral() {
        return general;
    }
}
