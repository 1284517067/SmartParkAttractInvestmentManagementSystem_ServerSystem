package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.PayItem;
import com.rzh.iot.service.LeaseContractPayItemService;
import com.rzh.iot.service.PayItemService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class PayItemController {

    @Autowired
    PayItemService payItemService;
    @Autowired
    LeaseContractPayItemService leaseContractPayItemService;

    @RequestMapping(value = "/getPayItemTableData")
    @JwtToken
    @ResponseBody
    public String getPayItemTableData(@RequestParam String currentPage, @RequestParam String limit){
        return payItemService.getPayItemTableData(Integer.parseInt(currentPage),Integer.parseInt(limit)).toJSONString();
    }

    @RequestMapping(value = "/updatePayItem")
    @JwtToken
    @ResponseBody
    public String updatePayItem(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        PayItem payItem = object.getObject("payItem",PayItem.class);
        return payItemService.updatePayItem(payItem).toJSONString();
    }

    @RequestMapping(value = "/getPayItemDetail")
    @JwtToken
    @ResponseBody
    public String getPayItemDetail(@RequestParam String itemId){
        return payItemService.getPayItemDetail(Long.parseLong(itemId)).toJSONString();
    }

    @RequestMapping(value = "/deletePayItem")
    @JwtToken
    @ResponseBody
    public String deletePayItem(@RequestParam String itemId){
        return payItemService.deletePayItem(Long.parseLong(itemId)).toJSONString();
    }

    @RequestMapping(value = "/searchPayItemByKey")
    @JwtToken
    @ResponseBody
    public String searchPayItemByKey(@RequestParam String searchKey){
        return payItemService.searchPayItemByKey(searchKey).toJSONString();
    }

    @RequestMapping(value = "/getPayItemList")
    @JwtToken
    @ResponseBody
    public String getPayItemList(){
        return payItemService.getPayItemList().toJSONString();
    }

    @RequestMapping(value = "/getPayItemFormulasByItemId")
    @JwtToken
    @ResponseBody
    public String getPayItemFormulasByItemId(@RequestParam String itemId){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("formulas",payItemService.getPayItemFormulasByItemId(Long.parseLong(itemId)));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getLeaseContractPayItemData")
    @JwtToken
    @ResponseBody
    public String getLeaseContractPayItemData(@RequestParam String formId){
        return leaseContractPayItemService.getLeaseContractPayItemData(Long.parseLong(formId)).toJSONString();
    }

}
