package com.rzh.iot.dao;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.LeaseContractPayItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LeaseContractPayItemDao {

    @Select("select * from lease_contract_pay_item where form_id = #{formId}")
    List<LeaseContractPayItem> getLeaseContractPayItemsByFormId(Long formId);

    @Insert("<script>" +
            "insert into lease_contract_pay_item " +
            "(form_id, space_id, item_id, formula_id) " +
            "values " +
            "<foreach collection='payItems' item='item' index='index' separator=','> " +
            "(#{item.formId}, #{item.spaceId}, #{item.itemId}, #{item.formulaId})" +
            "</foreach>" +
            "</script>")
    int createLeaseContractPayItems(@Param(value = "payItems") List<LeaseContractPayItem> payItems);

    @Delete("delete from lease_contract_pay_item where form_id = #{formId}")
    int deleteLeaseContractPayItemsByFormId(Long formId);

    @Select("select a.* ,b.space_name, c.item_name, d.formula from lease_contract_pay_item a inner join space_data b , pay_item c, pay_item_formula d" +
            " where a.form_id = #{formId} and a.space_id = b.space_id and a.item_id = c.item_id and a.formula_id = d.formula_id")
    List<LeaseContractPayItem> getLeaseContractPayItemData(Long formId);


}
