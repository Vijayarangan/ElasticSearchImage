package com.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.elasticsearch.image.model.Image;
import com.elasticsearch.image.service.ImageService;

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
		imageService.save(new Image("1001", "Blue Shirt","http://www.merchmethod.com/prod_images/xlarge/AJ019.jpg", 10,10));
		Page<Image> images = imageService.findByCaption("blue", new PageRequest(0, 10));
		images.forEach(x -> System.out.println(x));
	}
}