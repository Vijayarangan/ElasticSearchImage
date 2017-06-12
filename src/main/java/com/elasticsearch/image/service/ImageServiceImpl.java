package com.elasticsearch.image.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elasticsearch.image.model.Image;
import com.elasticsearch.image.repository.ImageRepository;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public void setIMageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public void delete(Image image) {
        imageRepository.delete(image);
    }

    public Image findOne(String id) {
        return imageRepository.findOne(id);
    }

    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    public Page<Image> findByCaption(String caption, PageRequest pageRequest) {
        return imageRepository.findByCaption(caption, pageRequest);
    }

    public List<Image> findByImageURL(String imageURL) {
        return imageRepository.findByImageURL(imageURL);
    }

}