package eu.kerdev.testApp.domain.model;

import java.io.Serializable;

public abstract class BaseModel<ID> implements Serializable {

    private static final long serialVersionUID = 1L;
    public abstract ID getId();
    public abstract void setId(ID id);
}
