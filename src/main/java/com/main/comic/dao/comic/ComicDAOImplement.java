package com.main.comic.dao.comic;

import com.main.comic.entity.Comic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComicDAOImplement implements ComicDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ComicDAOImplement(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Comic> getAllComics() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Comic> theQuery = currentSession.createQuery("from Comic", Comic.class);

        return theQuery.getResultList();
    }

    @Override
    public void saveComicData(Comic comicData) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(comicData);
    }

    @Override
    public Comic getComicById(int comicId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Comic.class, comicId);
    }

    @Override
    public void removeComicById(int comicId) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(currentSession.get(Comic.class, comicId));
    }

    @Override
    public boolean checkComicIsExist(String comicName, int comicId, int categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Comic> theQuery = currentSession.createQuery("from Comic where comicName = :comicName and id != :comicId and category.id = :categoryId", Comic.class);
        theQuery.setParameter("comicName", comicName);
        theQuery.setParameter("comicId", comicId);
        theQuery.setParameter("categoryId", categoryId);

        return !theQuery.getResultList().isEmpty();
    }
}
