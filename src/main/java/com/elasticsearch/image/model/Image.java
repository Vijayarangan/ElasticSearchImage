package com.elasticsearch.image.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "imageindex", type = "images")
public class Image {

    @Id
    private String id;
    private String imageURL;
    private String caption;
    private int height;
    private int width;

    public Image() {
    }

    public Image(String id, String imageURL, String caption, int height,int width) {
        this.id = id;
        this.imageURL = imageURL;
        this.caption = caption;
        this.height = height;
        this.width=width;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", caption='" + caption + '\'' +
                ", height='" + height + '\'' +
                ", width='" + width + '\'' +
                '}';
    }
}
