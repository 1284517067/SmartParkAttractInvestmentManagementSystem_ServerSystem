package com.rzh.iot.dao;

import com.rzh.iot.model.PayItemFormula;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PayItemFormulaDao {

    @Select("select * from pay_item_formula where item_id = #{itemId}")
    List<PayItemFormula> getPayItemFormulasByItemId(Long itemId);

    @Insert("<script>" +
            "insert into pay_item_formula (formula_name, item_id, formula) values " +
            "<foreach collection='formulas' item='item' index='index' separator=','>" +
            "(#{item.formulaName}, #{item.itemId}, #{item.formula})" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "formulaId" , keyColumn = "formula_id")
    int createPayItemFormulas(@Param(value = "formulas") List<PayItemFormula> formulas);

    @Delete("delete from pay_item_formula where item_id = #{itemId}")
    int deleteFormulasByItemId(Long itemId);

    @Select("select count(*) from pay_item_formula where item_id = #{itemId}")
    int isRoomsExist(Long itemId);

    @Select("select * from pay_item_formula where item_id = #{itemId}")
    List<PayItemFormula> getFormulasByItemId(Long itemId);
}
