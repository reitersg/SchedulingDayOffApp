package org.cubrc.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by reitersg on 7/19/2016.
 */
public class RestClient {

        private String server = "http://localhost:3030/prototype/query";
        private RestTemplate rest;
        public HttpHeaders headers;
        private HttpStatus status;

        public RestClient() {
            this.rest = new RestTemplate();
            this.headers = new HttpHeaders();
            headers.add("Content-Type", "application/sparql-query");
            headers.add("Accept", "*/*");
        }

        public String get(String uri) {
            HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
            ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
            this.setStatus(responseEntity.getStatusCode());
            return responseEntity.getBody();
        }

        public String post(String uri, String json) {
            HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
            ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, String.class);
            this.setStatus(responseEntity.getStatusCode());
            return responseEntity.getBody();
        }
        public void setServerToUpdate(){
            this.server = "http://localhost:3030/prototype/update";
            headers.remove("Content-Type");
            headers.add("Content-Type", "application/sparql-update");

        }
        public void setServerToQuery(){
            this.server = "http://localhost:3030/prototype/query";
            headers.remove("Content-Type");
            headers.add("Content-Type", "application/sparql-query");
        }

        public HttpStatus getStatus() {
            return status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }

}
