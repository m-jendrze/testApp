package eu.kerdev.testApp.model.dto;

/**
 * DTO used to expose ItSystem data by controllers
 * @see eu.kerdev.testApp.model.entities.app.ItSystem
 * @author Michal Jendrzejek
 */
public class ItSystemDto {
    private Long id;
    private String name;
    private String description;
    private String techs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechs() {
        return techs;
    }

    public void setTechs(String techs) {
        this.techs = techs;
    }
}
