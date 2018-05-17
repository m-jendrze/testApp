package eu.kerdev.testApp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Option {
    private String displayText;
    private String value;

    public Option() {}

    public Option(String displayText, String value) {
        this.displayText = displayText;
        this.value = value;
    }

    @JsonProperty("DisplayText")
    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
