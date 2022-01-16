package service;

import dao.CategoriesDao;
import model.Categories;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoriesService {
    @Autowired
    CategoriesDao categoriesDao;

    public List<Categories> getAllCategories(){
        return categoriesDao.getListCategories();
    }
}
