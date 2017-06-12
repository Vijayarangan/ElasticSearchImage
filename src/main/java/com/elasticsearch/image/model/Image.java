package com.elasticsearch.image.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "imageindex", type = "images")
public class Image {

    @Id
    private String id;

   // private String title;
    private String imageURL;

  
	//private String author;
    private String caption;

    //private String releaseDate;
    private String height;
    
    private String width;

    public Image() {
    }

    public Image(String id, String imageURL, String caption, String height,String width) {
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

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
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
