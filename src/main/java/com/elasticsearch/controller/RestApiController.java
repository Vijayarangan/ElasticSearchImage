package com.elasticsearch.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elasticsearch.image.model.Image;
import com.elasticsearch.image.service.ImageService;
import com.elasticsearch.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ImageService imageService; 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/image/{searchText}", method = RequestMethod.GET)
	public ResponseEntity<List<Image>> getImages(@PathVariable("searchText") String searchText) {
		logger.info("Fetching Images with searchText {}", searchText);
		Page<Image> byCaption = imageService.findByCaption(searchText, new PageRequest(0, 10));
		logger.info("No.Of Images with searchText {}", byCaption.getTotalElements());
		if (byCaption.getContent().size()<1) {
			logger.error("No Images found with searchText {}", searchText);
			return new ResponseEntity(new CustomErrorType("Images with Caption " + searchText 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		List<Image> imageList=byCaption.getContent();
		return new ResponseEntity<List<Image>>(imageList, HttpStatus.OK);
	}



}