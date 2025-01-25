//package com.csk.worklangcorrector.controller;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class RouterConfig {
//    public void logRequest(ServerRequest request) {
//        System.out.println("Request received: " + request.methodName() + " " + request.path());
//    }
//
//    // 공통 헤더 제공
//    public Map<String, String> getDefaultHeaders() {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        return headers;
//    }
//}
