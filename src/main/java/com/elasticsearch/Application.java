package com.elasticsearch;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;


import com.elasticsearch.image.model.Image;
import com.elasticsearch.image.service.ImageService;

import java.util.Map;

@SpringBootApplication(scanBasePackages={"com.elasticsearch"})
public class Application implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private ImageService imageService;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        printElasticSearchInfo();

        imageService.save(new Image("1001", "http://hi", "blue shirt", "12","20"));
        imageService.save(new Image("1002", "http://hello", "blue socks", "13","40"));
        imageService.save(new Image("1003", "http://how", "Red pant", "21","11"));
        

        //fuzzy search?
        Page<Image> images = imageService.findByCaption("blue", new PageRequest(0, 10));

        images.forEach(x -> System.out.println(x));


    }

    //useful for debug
    private void printElasticSearchInfo() {

        System.out.println("--ElasticSearch-->");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("<--ElasticSearch--");
    }

}