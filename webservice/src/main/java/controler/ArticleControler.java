package controler;

import connection.HibernateUtil;
import entity.Article;
import entity.Photo;
import org.hibernate.Session;

import java.util.Date;
import java.util.Set;

public class ArticleControler {

    public Article getByID(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Article.class,id);
    }

    public Set<Photo> Delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Article article = session.get(Article.class,id);
        Set<Photo> set = article.getPhotos();
        session.delete(article);
        return set;
    }

    public Article Update(int id, Date publi, String content, String titre, Boolean featured){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Article article = session.get(Article.class,id);
        article.setPublication(publi);
        if(!content.isEmpty() && content!=null)
            article.setContent(content);
        if(!titre.isEmpty() && titre!=null)
            article.setTitre(titre);
        if(featured != article.getFeatured())
            article.setFeatured(featured);
        session.save(article);
        return article;
    }

    public Article insert(Date publi, String content, String titre, Boolean featured){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Date date = new Date();
        Article article = new Article(publi,content,titre,featured,date);
        session.save(article);
        return article;
    }
}
