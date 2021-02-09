package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class PayItemFormula implements Serializable {

    private Long formulaId;

    private String formulaName;

    private String formula;

    private Long itemId;

    public PayItemFormula() {
    }

    public Long getFormulaId() {
        return formulaId;
    }

    @Override
    public String toString() {
        return "PayItemFormula{" +
                "formulaId=" + formulaId +
                ", formulaName='" + formulaName + '\'' +
                ", formula='" + formula + '\'' +
                ", itemId=" + itemId +
                '}';
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }


}
