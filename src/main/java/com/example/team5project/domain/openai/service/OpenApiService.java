package com.example.team5project.domain.openai.service;

import com.example.team5project.domain.openai.entity.OpenApi;
import com.example.team5project.domain.openai.repository.OpenApiRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenApiService {

    private final OpenApiRepository openApiRepository;

    public void init(String jsonData) { // result.toString에서 jsonData를 JSONParser를 통해 분해하는 과정
        try {
            // JSON 객체 생성
            JSONObject Jobj;

            // JSON 파싱 객체를 생성
            JSONParser jsonParser = new JSONParser();
            // 파싱할 STRING을 JSON 객체로 파서를 통해 저장
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonData);
            // 데이터 분해 단계

            // response를 받아옴
            JSONObject parseResponse = (JSONObject) jsonObj.get("ServiceInternetShopInfo");
            if (parseResponse == null) {
                throw new IllegalAccessException("reponse is null");
            }
            // parseResponse에는 response 내부의 데이터들이 들어있음

//            // body를 받아옴
//            JSONObject parseBody = (JSONObject) jsonObj.get("row");
//            // parseBody에는 body 내부의 데이터들이 들어있음
//            if (parseBody == null) {
//                throw new IllegalAccessException("body is null");
//            }

            // items 안쪽의 데이터는 [] 즉 배열의 형태이기에 제이슨 배열로 받아온다.
            JSONArray array = (JSONArray) parseResponse.get("row");
            if (array == null) {
                throw new IllegalAccessException("items is null");
            }

            for (int i = 0; i < array.size(); i++) {
                Jobj = (JSONObject) array.get(i);

                OpenApi store = OpenApi.builder()
                        .company(Jobj.get("COMPANY").toString())
                        .shopname(Jobj.get("SHOP_NAME").toString())
                        .email(Jobj.get("EMAIL").toString())
                        .statin(Jobj.get("STAT_NM").toString())
                        .build();


                openApiRepository.save(store);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
