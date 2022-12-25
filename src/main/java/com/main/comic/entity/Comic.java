package com.main.comic.entity;

import com.main.comic.service.category.CategoryService;
import com.main.comic.validator.CategorySelectConstraint;
import org.hibernate.service.spi.InjectService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "comics")
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "comic_name")
    @NotBlank(message = "Comic Name can not be null")
    private String comicName;
    @Column(name = "comic_content")
    @NotBlank(message = "Comic Content can not be null")
    private String comicContent;
    @Column(name = "description")
    @NotBlank(message = "Description can not be null")
    private String description;
    @Column(name = "image_cover")
    @NotBlank(message = "Image Cover can not be null")
    private String imageCover;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    @CategorySelectConstraint
    private String categorySelected;

    public Comic() {
    }

    public Comic(int id, String comicName, String comicContent, String description, String imageCover, Date createdAt, Date updatedAt, Category category, String categorySelected) {
        this.id = id;
        this.comicName = comicName;
        this.comicContent = comicContent;
        this.description = description;
        this.imageCover = imageCover;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.categorySelected = categorySelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getComicContent() {
        return comicContent;
    }

    public void setComicContent(String comicContent) {
        this.comicContent = comicContent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(String categorySelected) {
        this.categorySelected = categorySelected;
    }


    @Override
    public String toString() {
        return "Comic{" + "id=" + id + ", comicName='" + comicName + '\'' + ", comicContent='" + comicContent + '\'' + ", description='" + description + '\'' + ", imageCover='" + imageCover + '\'' + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", category=" + category + '}';
    }
}
