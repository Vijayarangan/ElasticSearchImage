package com.elasticsearch.image.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.elasticsearch.image.model.Image;

import java.util.List;

public interface ImageRepository extends ElasticsearchRepository<Image, String> {

    Page<Image> findByCaption(String caption, Pageable pageable);

    List<Image> findByImageURL(String imageURL);

}