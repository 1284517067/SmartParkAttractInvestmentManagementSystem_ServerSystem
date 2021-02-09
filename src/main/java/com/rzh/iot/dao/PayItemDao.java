package com.rzh.iot.dao;

import com.rzh.iot.model.PayItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PayItemDao {

    @Select("select * from pay_item order by item_id desc limit #{currentPage}, #{limit}")
    List<PayItem> getPayItemTableData(Integer currentPage,Integer limit);

    @Select("select count(item_id) from pay_item")
    int getSizeOfPayItemTable();

    @Select("select * from pay_item where item_id = #{itemId}")
    PayItem getPayItemDetail(Long itemId);

    @Insert("insert into pay_item (item_name, remark, price) values (#{itemName}, #{remark}, #{price})")
    @Options(useGeneratedKeys = true, keyColumn = "item_id", keyProperty = "itemId")
    int createPayItem(PayItem payItem);

    @Update("update pay_item set item_name = #{itemName}, remark = #{remark}, price = #{price} where item_id = #{itemId}")
    int updatePayItem(PayItem payItem);

    @Select("select count(*) from pay_item where item_name = #{itemName}")
    int isNameExist(String itemName);

    @Select("select count(*) from pay_item where item_id = #{itemId}")
    int isPayItemExist(Long itemId);

    @Delete("delete from pay_item where item_id = #{itemId}")
    int deletePayItem(Long itemId);

    @Select("select * from pay_item where (item_name like '%${value}%') or (remark like '%${value}%') or (price like '%${value}%')")
    List<PayItem> getPayItemByKey(String value);

    @Select("select count(item_id) from pay_item where (item_name like '%${value}%') or (remark like '%${value}%') or (price like '%${value}%')")
    int getSizeOfPayItemByKey(String value);

    @Select("select * from pay_item")
    List<PayItem> getPayItemList();

}
