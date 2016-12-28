package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.CategoryDto;
import com.shop.dto.DtoUtilMapper;
import com.shop.entity.Category;
import com.shop.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/loadcategories", method = RequestMethod.POST)
    public List<CategoryDto> loadCategories() {
        return DtoUtilMapper.categoriesToCategoriesDTO(categoryService.getAll());
    }
	
	@RequestMapping(value = "/searchcategory", method = RequestMethod.POST)
    public List<CategoryDto> searchCategory(@RequestBody String name) {
        return DtoUtilMapper.categoriesToCategoriesDTO(categoryService.getByName(name));
    }
	
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public List<CategoryDto> saveCountry(@RequestBody Category category) {
		categoryService.save(category);
        return DtoUtilMapper.categoriesToCategoriesDTO(categoryService.getAll());
    }

    @RequestMapping(value = "/deletecategory", method = RequestMethod.DELETE)
    public List<CategoryDto> deleteCategory(@RequestBody String index) {
    	categoryService.delete(Integer.parseInt(index));
        return DtoUtilMapper.categoriesToCategoriesDTO(categoryService.getAll());
    }

}
