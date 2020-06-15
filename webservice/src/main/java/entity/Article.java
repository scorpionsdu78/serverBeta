package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idarticle;
    Date publication;
    String content;
    String titre;
    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    Boolean featured;
    Date creation;

    @OneToMany(mappedBy = "article_idarticle", fetch = FetchType.EAGER)
    private Set<Photo> photos;

    public Article() {
    }

    public Article(Date publication, String content, String titre, Boolean featured, Date creation) {
        this.publication = publication;
        this.content = content;
        this.titre = titre;
        this.featured = featured;
        this.creation = creation;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean feature) {
        this.featured = feature;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Override
    public String toString() {
        return "Article{" +
                "idarticle=" + idarticle +
                ", publication=" + publication +
                ", content='" + content + '\'' +
                ", titre='" + titre + '\'' +
                ", feature=" + featured +
                ", creation=" + creation;
    }
}
