package com.shop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dto.DtoUtilMapper;
import com.shop.dto.ProductDto;
import com.shop.entity.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/newproduct", method=RequestMethod.GET)
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.getAll());
		return "views-admin-newproduct";
	}
	
	@RequestMapping(value="/saveproduct", method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute Product product, @RequestParam String categoryId, 
			@RequestParam MultipartFile pic, Model model) {
				
		try {
			productService.validation(product);
			productService.save(product, Integer.parseInt(categoryId), pic);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			model.addAttribute("categories", categoryService.getAll());
			return "views-admin-newproduct";
		}
		
		return "redirect:/adminpage";
	}
	
	@RequestMapping(value="/loadproducts", method=RequestMethod.POST)
	public @ResponseBody List<ProductDto> loadProducts(){
		return DtoUtilMapper.productsToProductsDTO(productService.getAll());
	}
	
	@RequestMapping(value="/productsincategory", method=RequestMethod.POST)
	public @ResponseBody List<ProductDto> productsInCategory(@RequestBody String index){
		return DtoUtilMapper.productsToProductsDTO(productService.getFromCategory(categoryService.getOne(Integer.parseInt(index))));
	}
	
	@RequestMapping(value="/livesearch", method=RequestMethod.POST)
	public @ResponseBody List<ProductDto> liveSearch(@RequestBody String search){
		return DtoUtilMapper.productsToProductsDTO(productService.liveSearch(search));
	}
	
	@RequestMapping(value="/rangeprice", method=RequestMethod.POST)
	public @ResponseBody List<ProductDto> rangePrice(@RequestBody String price){
		return DtoUtilMapper.productsToProductsDTO(productService.getByPrice(Integer.parseInt(price)));
	}
	
	@RequestMapping(value="/addtocart", method=RequestMethod.POST)
	public @ResponseBody List<ProductDto> addToCart(@RequestBody int index, Principal principal) {
		productService.addToCart(Integer.parseInt(principal.getName()), index);
		return DtoUtilMapper.productsToProductsDTO(productService.getAll());
	}
	
	@RequestMapping(value="/singleproduct_{id}", method=RequestMethod.GET)
	public String singleProduct(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.getWithCategory(id));
		return "views-product-singleproduct";
	}
	
	@RequestMapping(value="/singleaddtocart/{id}", method=RequestMethod.POST)
	public String singleAddToCart(@PathVariable int id, Principal principal) {
		productService.addToCart(Integer.parseInt(principal.getName()), id);
		return "redirect:/singleproduct_" + id;
	}
	
	@RequestMapping(value="/deleteproduct/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable String id) {
		productService.delete(Integer.parseInt(id));
		return "redirect:/";
	}
	
	@RequestMapping(value = "/changeimage/{id}", method = RequestMethod.POST)
    public String changeImage(@PathVariable int id, @RequestParam MultipartFile pic, Model model) {
		
		try {
			productService.changeImage(productService.getOne(id), pic);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return "redirect:/singleproduct_" + id;
		}

        return "redirect:/singleproduct_" + id;
    }
	
	@RequestMapping(value="/editproduct_{id}", method=RequestMethod.GET)
	public String editProduct(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.getOne(id));
		model.addAttribute("categories", categoryService.getAll());
		return "views-admin-editproduct";
	}
	
	@RequestMapping(value="/savechanges_{id}", method=RequestMethod.POST)
	public String saveChanges(@PathVariable int id, @ModelAttribute Product product,
			@RequestParam String categoryId, Model model) {
		productService.setCategory(product, Integer.parseInt(categoryId));
		productService.setSameImage(id, product);
		
		try {
			productService.validation(product);
			productService.update(product);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			model.addAttribute("categories", categoryService.getAll());
			return "views-admin-editproduct";
		}
		
		return "redirect:/singleproduct_" + id;
	}

}
