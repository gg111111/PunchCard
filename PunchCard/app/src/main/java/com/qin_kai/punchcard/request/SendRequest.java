package com.qin_kai.punchcard.request;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qin_kai.punchcard.model.Card;
import com.qin_kai.punchcard.model.CardVO;
import com.qin_kai.punchcard.model.Category;
import com.qin_kai.punchcard.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class SendRequest {


    private final static OkHttpClient client = new OkHttpClient();
    private final static String path = "http://47.106.190.87:8081";
    private  final static ObjectMapper mapper = new ObjectMapper();
    public static Map<String, Object> login(User user) {
        String responseData = null;
        Map<String, Object> map= null;
        try {
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", user.getUsername())
                    .add("password",user.getPassword())
                    .build();


            Request request = new Request.Builder()
//                    .url("http://www.baidu.com")
                    .url(path + "/user/login")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            responseData = response.body().string();
            JsonNode rootNode = mapper.readTree(responseData);
            map = new HashMap<>();
            map.put("code", rootNode.path("code").asInt());
            map.put("msg", rootNode.path("msg").asText());
            User u = mapper.readValue(mapper.writeValueAsString(rootNode.path("data")), User.class);
            map.put("user", u);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Map register(User user) {
        String responseData = null;
        Map map = null;
        try {
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", user.getUsername())
                    .add("password",user.getPassword())
                    .build();

            Request request = new Request.Builder()
                    .url(path + "/user/register")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            responseData = response.body().string();

            map = mapper.readValue(responseData, Map.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Map<String, Object> saveCard(Card card) {
        String responseData = null;
        Map map = null;
        try {
//                        user_id, category_id, address, content, create_time
            RequestBody requestBody = new FormBody.Builder()
                    .add("userId", card.getUserId().toString())
                    .add("categoryId", card.getCategoryId().toString())
                    .add("address", card.getAddress())
                    .add("content", card.getContent())
                    .add("feel", card.getFeel())
                    .build();

            Request request = new Request.Builder()
                    .url(path + "/card/saveCard")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            responseData = response.body().string();
            map = mapper.readValue(responseData, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Map<String, Object> updateCard(Card card) {
        String responseData = null;
        Map map = null;
        try {
//                        user_id, category_id, address, content, create_time
            RequestBody requestBody = new FormBody.Builder()
                    .add("cardId", card.getCardId().toString())
                    .add("content", card.getContent())
                    .add("feel", card.getFeel())
                    .build();

            Request request = new Request.Builder()
                    .url(path + "/card/updateCard")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            responseData = response.body().string();
            map = mapper.readValue(responseData, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Map<String, Object> removeCard(Integer cardId) {
        String responseData = null;
        Map map = null;
        try {
            Request request = new Request.Builder()
                    .url(path + "/card/removeCard?cardId=" + cardId)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            responseData = response.body().string();
            System.out.println(responseData);
            map = mapper.readValue(responseData, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static List<CardVO> getCardList(Card card) {
        String responseData = null;
        List<CardVO> list = new ArrayList<>();
        try {
            Request request = new Request.Builder()
                    .url(path + "/card/cardList?userId=" + (card.getUserId() == null ? "" : card.getUserId()) + "&categoryId=" + (card.getCategoryId() == null ? "" : card.getCategoryId()))
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            responseData = response.body().string();

            JsonNode rootNode = mapper.readTree(responseData);
            Iterator<JsonNode> elements = rootNode.path("data").elements();
            list = new ArrayList<>();
            while (elements.hasNext()) {
                JsonNode node = elements.next();
                CardVO c = mapper.readValue(mapper.writeValueAsString(node), CardVO.class);
                list.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Category> getCategoryList() {
        String responseData = null;
        List<Category> list = null;
        try {
            Request request = new Request.Builder()
                    .url(path + "/category/categoryList")
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            responseData = response.body().string();

            JsonNode rootNode = mapper.readTree(responseData);
            Iterator<JsonNode> elements = rootNode.path("data").elements();
            list = new ArrayList<>();
            while (elements.hasNext()) {
                JsonNode node = elements.next();
                Category category = mapper.readValue(mapper.writeValueAsString(node), Category.class);
                list.add(category);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
