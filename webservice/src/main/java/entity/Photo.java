package entity;

import javax.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idphoto;
    String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_idarticle")
    Article article_idarticle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Membre_idMembre")
    Membre membreIdmembre;

    public Photo() {
    }

    public Photo(String url, Article article_idarticle, Membre membreIdmembre) {
        this.url = url;
        this.article_idarticle = article_idarticle;
        this.membreIdmembre = membreIdmembre;
    }

    public int getIdphoto() {
        return idphoto;
    }

    public void setIdphoto(int idphoto) {
        this.idphoto = idphoto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article getArticle_idarticle() {
        return article_idarticle;
    }

    public void setArticle_idarticle(Article article_idarticle) {
        this.article_idarticle = article_idarticle;
    }

    public Membre getMembreIdmembre() {
        return membreIdmembre;
    }

    public void setMembreIdmembre(Membre membreIdmembre) {
        this.membreIdmembre = membreIdmembre;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "idphoto=" + idphoto +
                ", url='" + url + '\'' +
                '}';
    }
}
