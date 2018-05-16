package eu.kerdev.testApp.domain.model.app;

import eu.kerdev.testApp.domain.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "it_system", schema = "app")
public class ItSystem extends BaseModel<Long> {

    private Long id;
    private String name;
    private String description;
    private String techs;

    @Override
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "it_table_seq")
    @SequenceGenerator(name = "it_table_seq", schema = "app")
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 120)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "techs")
    public String getTechs() {
        return techs;
    }

    public void setTechs(String techs) {
        this.techs = techs;
    }
}
