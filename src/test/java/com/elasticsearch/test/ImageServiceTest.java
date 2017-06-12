package com.elasticsearch.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.elasticsearch.Application;
import com.elasticsearch.image.model.Image;
import com.elasticsearch.image.service.ImageService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Image.class);
        esTemplate.createIndex(Image.class);
        esTemplate.putMapping(Image.class);
        esTemplate.refresh(Image.class);
    }

    @Test
    public void testSave() {

        Image image = new Image("1001", "http://hi", "Blue Pant", "23","10");
        Image testImage = imageService.save(image);

        assertNotNull(testImage.getId());
        assertEquals(testImage.getCaption(), image.getCaption());
        assertEquals(testImage.getImageURL(), image.getImageURL());
        assertEquals(testImage.getHeight(), image.getHeight());

    }

    @Test
    public void testFindOne() {

    	Image image = new Image("1002", "http://hello", "Blue Shirt", "13","11");
        imageService.save(image);

        Image testImage = imageService.findOne(image.getId());

        assertNotNull(testImage.getId());
        assertEquals(testImage.getCaption(), image.getCaption());
        assertEquals(testImage.getImageURL(), image.getImageURL());
        assertEquals(testImage.getHeight(), image.getHeight());

    }

    @Test
    public void testFindByImageURL() {

    	Image image = new Image("1002", "hello123", "Blue Shirt", "13","11");
        imageService.save(image);

        List<Image> byURL = imageService.findByImageURL(image.getImageURL());
        assertThat(byURL.size(), is(1));
    }

    @Test
    public void testFindByAuthor() {

        List<Image> imageList = new ArrayList<>();

        imageList.add(new Image("10010","http://123","Blue Shirt","23","10"));
        imageList.add(new Image("10020", "http://103", "Blue Pant", "13","10"));
        imageList.add(new Image("10030", "http://193", "Blue Shoes", "21","10"));
        imageList.add(new Image("10070", "http://183", "Blue Socks", "01","10"));
        imageList.add(new Image("10080", "http://163", "Red Rose", "25","10"));

        for (Image image : imageList) {
            imageService.save(image);
        }

        Page<Image> byCaption = imageService.findByCaption("Blue", new PageRequest(0, 10));
        assertThat(byCaption.getTotalElements(), is(4L));

        Page<Image> byCaption2 = imageService.findByCaption("Red", new PageRequest(0, 10));
        assertThat(byCaption2.getTotalElements(), is(1L));

    }

    @Test
    public void testDelete() {

        Image image = new Image("10011", "http://go.go", "Yello Tie", "23","17");
        imageService.save(image);
        imageService.delete(image);
        Image testImage = imageService.findOne(image.getId());
        assertNull(testImage);
    }

}
