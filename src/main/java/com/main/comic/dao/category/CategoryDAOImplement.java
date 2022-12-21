package com.main.comic.dao.category;

import com.main.comic.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImplement implements CategoryDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImplement(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getAllCategories() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Category> theQuery = currentSession.createQuery("from Category", Category.class);

        return theQuery.getResultList();
    }

    @Override
    public void saveCategoryData(Category categoryData) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(categoryData);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Category.class, categoryId);
    }

    @Override
    public void removeCategoryById(int categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(currentSession.get(Category.class, categoryId));
    }

    @Override
    public boolean checkCategoryIsExist(String categoryName, int categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Category> theQuery = currentSession.createQuery("from Category where categoryName like :categoryName and id not like :categoryId", Category.class);
        theQuery.setParameter("categoryName", categoryName);
        theQuery.setParameter("categoryId", categoryId);

        return !theQuery.getResultList().isEmpty();
    }
}
