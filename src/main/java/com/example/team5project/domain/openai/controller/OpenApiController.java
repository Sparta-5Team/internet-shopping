package com.example.team5project.domain.openai.controller;

import com.example.team5project.domain.openai.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping("/collection-openapi")
    public String callOpenApi(@RequestParam(name = "page", required = true) int page,
                              @RequestParam(name = "size", required = true) int size) throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = "http://openAPI.seoul.go.kr:8088/4864566164616f6434357a774e5962/json/ServiceInternetShopInfo/"+page+"/"+size;
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine+"\n\r");
        }

        urlConnection.disconnect();

        openApiService.init(result.toString());

        return "Success";
    }
}
