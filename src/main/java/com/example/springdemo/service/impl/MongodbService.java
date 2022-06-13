package com.example.springdemo.service.impl;

import com.example.springdemo.bean.User;
import com.example.springdemo.utils.BaseResult;
import com.example.springdemo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongodbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "user";

    public void insert(String name){
        User objectToSave = new User(null,name, DateUtils.getCurrentDate("1") ,"M","chain");
        mongoTemplate.insert(objectToSave,COLLECTION_NAME);
        String objectId = objectToSave.getId(); // 获取到ObjectId值了
        System.out.println(objectId);
    }

    public void insert(){
        User objectToSave = new User(null,"liming", DateUtils.getCurrentDate("1") ,"M","chain");
        mongoTemplate.insert(objectToSave,COLLECTION_NAME);
        String objectId = objectToSave.getId(); // 获取到ObjectId值了
        System.out.println(objectId);
    }

    public void insertMore(){
        List<User> userLst = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
         User objectToSave = new User(null,"liming"+i, DateUtils.getCurrentDate("1") ,"M","chain");
         userLst.add(objectToSave);
        }
        mongoTemplate.insert(userLst,COLLECTION_NAME);
    }

    public void remove(String id){
        Query query = Query.query(Criteria.where("id").is(id));  //  Criteria.where("id").in(ids)  in删多个
        mongoTemplate.remove(query, User.class);
    }

    public void removeMore(String[] ids){
        Query query = Query.query(Criteria.where("id").in(ids));  //  Criteria.where("id").in(ids)  in删多个
        mongoTemplate.remove(query, User.class);
    }

    public List<User> findById(String[] ids){
        Query query = Query.query(Criteria.where("id").in(ids));  //  Criteria.where("id").in(ids)  in删多个
        List<User> result = mongoTemplate.find(query, User.class);
        return result;
    }
    public List<User> findAll(){
        List<User> result = mongoTemplate.findAll(User.class);
        return result;
    }
    public List<User>  findByName(String name){
        Criteria criteria = Criteria.where("name").is(name);
        Query query = new Query(criteria);
        //查询条件  指定返回数据类型
        List< User> result = mongoTemplate.find(query, User.class);
        return result;
    }

    public BaseResult update(String[] ids){
        BaseResult result = new BaseResult();
        try{
            for (int i = 0; i < ids.length; i++) {
                String  date = DateUtils.getCurrentDate("1");
                String  name = "xiaohong" + DateUtils.getCurrentDate("2");
                User objectToSave = new User(ids[i] ,name , date,"M","chain");
                mongoTemplate.save(objectToSave,COLLECTION_NAME);
            }
            result.setSuccess("更新成功");
        }catch (Exception e){
            System.out.println(e.getMessage());
            result.setError("更新失败");
        }
        return result;
    }

}
