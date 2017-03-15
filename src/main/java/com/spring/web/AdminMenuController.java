package com.spring.web;

import com.spring.exception.DAOException;
import com.spring.model.MenuCategory;
import com.spring.model.Product;
import com.spring.model.Subcategory;
import com.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/menu")
public class AdminMenuController {


    private final SubCategoryService subCategoryService;

    private final ProductService productService;

    private final MenuCategoryService menuCategoryService;


    @Autowired
    public AdminMenuController(SubCategoryService subCategoryService,
                               ProductService productService, MenuCategoryService menuCategoryService) {
        this.subCategoryService = subCategoryService;
        this.productService = productService;
        this.menuCategoryService = menuCategoryService;
    }

    //Добавление в меню основные категории
    @RequestMapping(value = "/add_main", method = RequestMethod.GET)
    public String addToMenu(Model model) {
        model.addAttribute("menuCategory", new MenuCategory());
        return "admin/menu/add_main";
    }

    @RequestMapping(value = "/add_main", method = RequestMethod.POST)
    public String addToMenuPost(@Valid MenuCategory menuCategory, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/menu/add_main";
        }
        try {
            menuCategoryService.addMainCategory(menuCategory);
        } catch (DAOException e) {
            model.addAttribute("error", e);
            return "admin/menu/add_main";
        }

        return "redirect:/admin/menu/add_main";
    }

    //Добавление в меню под категории
    @RequestMapping(value = "/add_subcategory", method = RequestMethod.GET)
    public String addSubcategory(Model model) {
        model.addAttribute("mainCategory", menuCategoryService.getAllMenuCategory());
        model.addAttribute(new Subcategory());
        return "admin/menu/add_subcategory";
    }

    @RequestMapping(value = "/add_subcategory", method = RequestMethod.POST)
    public String addSubcategoryPost(@Valid Subcategory subcategory,
                                     BindingResult bindingResult,
                                     @RequestParam("mainCategory") Long mainCategory,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mainCategory", menuCategoryService.getAllMenuCategory());
            return "admin/menu/add_subcategory";
        }
        try {
            subCategoryService.addSubcategory(subcategory, mainCategory);
        } catch (DAOException e) {
            model.addAttribute("error", e);
            model.addAttribute("mainCategory", menuCategoryService.getAllMenuCategory());
            return "admin/menu/add_subcategory";
        }
        redirectAttributes.addFlashAttribute("mainCategory", menuCategoryService.getAllMenuCategory());

        return "redirect:/admin/menu/add_subcategory";
    }

    //Добавление в меню продукты
    @RequestMapping(value = "/add_product", method = RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("subcategory", subCategoryService.getAllSubcategory());
        return "admin/menu/add_product";
    }

    @RequestMapping(value = "/add_product", method = RequestMethod.POST)
    public String addProductPost(@Valid Product product, BindingResult bindingResult,
                                 @RequestParam("subcategory") Long subcategory,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subcategory", subCategoryService.getAllSubcategory());
            return "admin/menu/add_product";
        }
        try {
            productService.addProduct(product, subcategory);
        } catch (DAOException e) {
            model.addAttribute("error", e);
            model.addAttribute("subcategory", subCategoryService.getAllSubcategory());
            return "admin/menu/add_product";
        }

        redirectAttributes.addFlashAttribute("subcategory", subCategoryService.getAllSubcategory());
        return "redirect:/admin/menu/add_product";
    }

    //Редактирование и удаление основных категорий
    @RequestMapping(value = "/main_category", method = RequestMethod.GET)
    public String getAllMenuCategory(Model model) {
        model.addAttribute("mainCat", menuCategoryService.getAllMenuCategory());
        return "admin/menu/main_category";
    }

    @RequestMapping(value = "/all_main", method = RequestMethod.GET)
    public String showAllMainCategory(Model model) {
        model.addAttribute("mainCategory", menuCategoryService.getAllMenuCategory());
        return "admin/menu/all_main";
    }

    @RequestMapping(value = "/edit/main/{id}", method = RequestMethod.GET)
    public String editMainCategory(@PathVariable("id") Long id,
                                   Model model) {
        model.addAttribute("menuCategory", menuCategoryService.getById(id));
        return "admin/menu/edit_main";
    }

    @RequestMapping(value = "/edit/main/{id}", method = RequestMethod.POST)
    public String editMainCategoryPost(@Valid MenuCategory menuCategory, BindingResult bindingResult,
                                       @PathVariable("id") Long id,
                                       Model model) {
        model.addAttribute("id", id);
        if (bindingResult.hasErrors()) {
            return "admin/menu/edit_main";
        }
        try {
            menuCategoryService.edit(menuCategory, id);
        } catch (DAOException e) {
            model.addAttribute("error", e);
            return "admin/menu/edit_main";
        }

        return "redirect:/admin/menu/all_main";
    }

    @RequestMapping(value = "/delete/main/{id}", method = RequestMethod.GET)
    public String deleteMainCategory(@PathVariable("id") Long id) {
        menuCategoryService.delete(id);
        return "redirect:/admin/menu/all_main";
    }

    //Редактирование и удаление под категорий
    @RequestMapping(value = "/all_subcategory", method = RequestMethod.GET)
    public String showAllSubcategory(Model model) {
        model.addAttribute("subcategory", subCategoryService.getAllSubcategory());
        return "admin/menu/all_subcategory";
    }

    @RequestMapping(value = "/edit/subcategory/{id}", method = RequestMethod.GET)
    public String editSubcategory(@PathVariable("id") Long id,
                                  Model model) {
        model.addAttribute("mainCategory", menuCategoryService.getAllMenuCategory());
        model.addAttribute("subcategory", subCategoryService.getById(id));
        return "admin/menu/edit_subcategory";
    }

    @RequestMapping(value = "/edit/subcategory/{id}", method = RequestMethod.POST)
    public String editSubcategoryPost(@Valid Subcategory subcategory, BindingResult bindingResult,
                                      @PathVariable("id") Long id,
                                      @RequestParam("mainCategory") Long mainCategory,
                                      Model model) {
        model.addAttribute("id", id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("mainCategory", menuCategoryService.getAllMenuCategory());
            return "admin/menu/edit_subcategory";
        }
        try {
            subCategoryService.edit(subcategory, id, mainCategory);
        } catch (DAOException e) {
            model.addAttribute("mainCategory", menuCategoryService.getAllMenuCategory());
            model.addAttribute("error", e);
            return "admin/menu/edit_subcategory";
        }

        return "redirect:/admin/menu/all_subcategory";
    }

    @RequestMapping(value = "/delete/subcategory/{id}", method = RequestMethod.GET)
    public String deleteSubcategory(@PathVariable("id") Long id) {
        subCategoryService.delete(id);
        return "redirect:/admin/menu/all_subcategory";
    }

    //Редактирование и удаление продуктов
    @RequestMapping(value = "/all_product", method = RequestMethod.GET)
    public String showAllProduct(Model model) {
        model.addAttribute("productDTO", productService.getAllProduct());
        return "admin/menu/all_product";
    }

    @RequestMapping(value = "/edit/product/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("id") Long id,
                              Model model) {
        model.addAttribute("subcategory", subCategoryService.getAllSubcategory());
        model.addAttribute("product", productService.getById(id));
        return "admin/menu/edit_product";
    }

    @RequestMapping(value = "/edit/product/{id}", method = RequestMethod.POST)
    public String editProductPost(@Valid Product product, BindingResult bindingResult,
                                  @RequestParam("subcategory") Long subcategory,
                                  @PathVariable("id") Long id,
                                  Model model) {
        model.addAttribute("id", id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("subcategory", subCategoryService.getAllSubcategory());
            return "admin/menu/edit_product";
        }
        try {
            productService.edit(product, id, subcategory);
        } catch (DAOException e) {
            model.addAttribute("error", e);
            model.addAttribute("subcategory", subCategoryService.getAllSubcategory());
            return "admin/menu/edit_product";
        }

        return "redirect:/admin/menu/all_product";
    }

    @RequestMapping(value = "/delete/product/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/admin/menu/all_product";
    }
}
