package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class LeaseContractPayItem implements Serializable {

    private Long formId;

    private Long spaceId;

    private Long itemId;

    private Long formulaId;

    private String spaceName;

    private String itemName;

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    private String formula;

    public LeaseContractPayItem() {
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getFormulaId() {
        return formulaId;
    }

    @Override
    public String toString() {
        return "LeaseContractPayItem{" +
                "formId=" + formId +
                ", spaceId=" + spaceId +
                ", itemId=" + itemId +
                ", formulaId=" + formulaId +
                ", spaceName='" + spaceName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", formula='" + formula + '\'' +
                '}';
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
    }
}
