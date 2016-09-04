package org.abc.openlaw.domain;

/**
 * Created by scamisay on 09/02/16.
 */
public enum LawType {
    DECRETO("Decreto"),
    LEY("Ley"),
    RESOLUCION("Resolución");

    private final String name;

    private LawType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
