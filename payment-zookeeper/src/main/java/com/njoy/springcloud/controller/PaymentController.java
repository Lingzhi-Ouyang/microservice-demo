package com.njoy.springcloud.controller;


import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ouyanglingzhi
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private MongoTemplate mongoTemplate;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "payment/zk")
    public String payment(){
        return "Zookeeper with port: " + serverPort + "\t" + UUID.randomUUID().toString();
    }


    @PostMapping(value = "/payment/create")
    public ApiResponse<Payment> create(@RequestBody Payment payment){
        log.info("create payment : " + payment);
        Payment saveResult = this.mongoTemplate.save(payment, "collectionName1");
        log.info("save payment result : " + saveResult);
        Payment insertResult = this.mongoTemplate.insert(payment, "collectionName2");
        log.info("insert payment result : " + insertResult);
        return new ApiResponse(200, "create success at port " + serverPort, saveResult);
    }


    @GetMapping(value = "/payment/get/{id}")
    public ApiResponse<Payment> queryById(@PathVariable("id") Long id){
        log.info("query payment : " + id);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Map result = this.mongoTemplate.findOne(query, Map.class,"collectionName1");
        log.info("query payment result : " + result);
        return new ApiResponse(200, "query success at port " + serverPort, result);
    }

    @PostMapping("/mongoSave")
    public ApiResponse mongoSave(){
        Map<String, Object> map = new HashMap<>();
        map.put("_id", "10086");
        map.put("name", "mike");
        map.put("age", "26");
        map.put("wechat", "mk");
        Map saveResult = this.mongoTemplate.save(map, "collectionName1");
        log.info("save payment result : " + saveResult);
        Map insertResult = this.mongoTemplate.insert(map, "collectionName2");
        log.info("insert payment result : " + insertResult);
        return new ApiResponse(200, "新增成功", insertResult);
    }

    /**
     * @description:MongoDB根据条件删除数据
     * @param
     * @return com.github.collection.common.util.Response
     * @author songfayuan
     * @date 2019/4/10 20:03
     */
    @DeleteMapping("/mongoRemove")
    public ApiResponse mongoRemove(){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in("10086"));
        DeleteResult deleteResult = this.mongoTemplate.remove(query, "collectionName1");
        return new ApiResponse(200, "删除成功", deleteResult.toString());
    }

    /**
     * @description:MongoDB根据条件更新数据
     * @param
     * @return com.github.collection.common.util.Response
     * @author songfayuan
     * @date 2019/4/10 20:24
     */
    @PostMapping("/mongoUpdate")
    public ApiResponse mongoUpdate(){
        List<String> list = new ArrayList<>();
        list.add("10086");
        list.add("10000");
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(list));
        Update update = new Update();
        update.set("age", "120");
        update.set("updateTime", new Date());
        UpdateResult updateResult = this.mongoTemplate.updateMulti(query, update,"collectionName1");
        return new ApiResponse(200, "更新成功", updateResult);
    }

    /**
     * @description:MongoDB获取数据库中的所有文档集合
     * @param
     * @return com.github.collection.common.util.Response
     * @author songfayuan
     * @date 2019/4/10 20:33
     */
    @GetMapping("/mongoGetCollectionNames")
    public ApiResponse mongoGetCollectionNames(){
        Set<String> set = this.mongoTemplate.getCollectionNames();
        return new ApiResponse(200, "获取集合名", set);
    }

    /**
     * @description:MongoDB根据条件查询一条数据
     * @param
     * @return com.github.collection.common.util.Response
     * @author songfayuan
     * @date 2019/4/10 20:46
     */
    @GetMapping("/mongoFindOne")
    public ApiResponse mongoFindOne() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("10086"));
        Map result = this.mongoTemplate.findOne(query, Map.class,"collectionName1");
        return new ApiResponse(200, "查询成功", result);
    }

    /**
     * @description:MongoDB根据条件查询列表数据
     * @param
     * @return com.github.collection.common.util.Response
     * @author songfayuan
     * @date 2019/4/10 20:53
     */
    @GetMapping("/mongoFindList")
    public ApiResponse mongoFindList() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("mike"));
        List resultList = this.mongoTemplate.find(query, Map.class,"collectionName1");
        return new ApiResponse(200, "查询成功",  resultList);
    }



}
