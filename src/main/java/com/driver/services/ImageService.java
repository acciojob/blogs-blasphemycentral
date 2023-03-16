package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions)
    {
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();

        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id)
    {
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions)
    {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] a = screenDimensions.split("x");
        int dim = Integer.parseInt(a[0])*Integer.parseInt(a[1]);
        int d = Integer.parseInt(imageRepository2.findById(id).get().getDimensions());
        return dim/d*d;
    }
}
