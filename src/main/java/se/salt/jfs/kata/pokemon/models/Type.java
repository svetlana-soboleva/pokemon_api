package se.salt.jfs.kata.pokemon.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {

    @JsonProperty("slot")
    private int slot;

    @JsonProperty("type")
    private TypeDetail typeDetail;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public TypeDetail getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(TypeDetail typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public String toString() {
        return "Type {" +
                "slot: " + slot +
                ", typeDetail: " + typeDetail +
                '}';
    }
}
