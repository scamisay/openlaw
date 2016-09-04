package org.abc.openlaw.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by scamisay on 07/02/16.
 */
public class Law {

    @Id
    private String id;

    private String title;

    private LawType type;

    private String synthesis;

    private Integer number;

    private Date publicationDate;

    /**
     * se popula en la capa de servicio
     */
    @Transient
    private List<LawVersion> versions;

    /**
     * se usa para persistir en mongodb y poder implementar lazy loading
     */
    private List<String> lawVersionIds;

    protected Law(){}

    public Law(String title, LawType type, Integer number) {
        if(title == null || title.isEmpty() || type == null || number == null){
            throw new RuntimeException("all arguments are mandatory");
        }
        this.title = title;
        this.type = type;
        this.number = number;
    }

    private void lawVersionAssertion(LawVersion aLawVersion){
        if(aLawVersion == null){
            throw new RuntimeException("LawVersion is mandatory");
        }
    }

    public void addNewLawVersion(LawVersion aLawVersion){
        lawVersionAssertion(aLawVersion);

        if(versions == null){
            versions = new ArrayList<LawVersion>();
        }

        versions.add(aLawVersion);
    }


    /**
     * Debe llamarse desde el servicio una vez que las versiones esten persistidas
     */
    public void copyVersionIds(){
        if(lawVersionIds == null){
            lawVersionIds = new ArrayList<String>();
        }

        for(LawVersion aVersion : getVersions()){
            if(!aVersion.isNew()){
                lawVersionIds.add(aVersion.getId());
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LawVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<LawVersion> versions) {
        this.versions = versions;
    }

    public LawType getType() {
        return type;
    }

    public void setType(LawType type) {
        this.type = type;
    }

    public String getSynthesis() {
        return synthesis;
    }

    public void setSynthesis(String synthesis) {
        this.synthesis = synthesis;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<String> getLawVersionIds() {
        return lawVersionIds;
    }

    @Transient
    public LawVersion getLastVersion() {
        return getVersions().get(getVersions().size() - 1);
    }
}
