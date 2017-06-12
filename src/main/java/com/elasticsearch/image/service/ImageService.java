package com.elasticsearch.image.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.elasticsearch.image.model.Image;

import java.util.List;

public interface ImageService {

    Image save(Image image);

    void delete(Image image);

    Image findOne(String id);

    Iterable<Image> findAll();

    Page<Image> findByCaption(String caption, PageRequest pageRequest);

    List<Image> findByImageURL(String ImageURL);

}