package com.rzh.iot.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;
import java.util.List;

@CrossOrigin
public class PayItem implements Serializable {

    private Long itemId;

    private String itemName;

    private Double price;

    private String remark;

    private List<PayItemFormula> formulas;

    public PayItem() {
    }

    @Override
    public String toString() {
        return "PayItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", remark='" + remark + '\'' +
                ", formulas=" + formulas +
                '}';
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<PayItemFormula> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<PayItemFormula> formulas) {
        this.formulas = formulas;
    }

    public String getRemark() {
        return remark;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
