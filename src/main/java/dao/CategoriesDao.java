package dao;

import model.Categories;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoriesDao {
    @Autowired
    EntityManager entityManager;

    public List<Categories> getListCategories(){
        String queryStr = "SELECT c FROM Categories c";
        TypedQuery<Categories> query = entityManager.createQuery(queryStr, Categories.class);
        return query.getResultList();
    }
}
