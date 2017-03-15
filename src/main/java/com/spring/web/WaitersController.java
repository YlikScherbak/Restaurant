package com.spring.web;

import com.spring.dto.waitresDTO.ErrorDTO;
import com.spring.model.User;
import com.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/waiter")
public class WaitersController {

    private final TableService tableService;

    private final OrderService orderService;

    private final MenuService menuService;

    private final DiscountService discountService;

    private final OrderDetailService orderDetailService;


    @Autowired
    public WaitersController(TableService tableService, OrderService orderService, MenuService menuService,
                             DiscountService discountService, OrderDetailService orderDetailService) {
        this.tableService = tableService;
        this.orderService = orderService;
        this.menuService = menuService;
        this.discountService = discountService;
        this.orderDetailService = orderDetailService;
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public String tables(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", user.getUsername());
        model.addAttribute("floors", tableService.getAllTables());
        return "waiter/tables";
    }


    @RequestMapping(value = "/table/new", method = RequestMethod.GET)
    public String createOrder(@RequestParam("table") int table) {
        long id = orderService.createOrder(table);
        return "redirect:/waiter/order/" + id;
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public String getOrderDetails(@PathVariable("id") long id,
                                  Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("discounts", discountService.getAllDiscount());
        model.addAttribute("waiter", user.getUsername());
        model.addAttribute("orderNumber", id);
        model.addAttribute("order", orderService.getOrder(id));
        model.addAttribute("error", new ErrorDTO());
        return "waiter/order";
    }

    @RequestMapping(value = "/order/{id}/close", method = RequestMethod.GET)
    public String closeOrder(@PathVariable("id") long id,
                             Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ' ' HH:mm:ss");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("order", orderService.getCheckInfo(id));
        model.addAttribute("details", orderDetailService.getOrderDetails(id));
        model.addAttribute("closedDate", dateFormat.format(new Date()));
        model.addAttribute("waiter", user.getUsername());
        orderService.closeOrder(id);
        return "waiter/order_check";
    }

    @RequestMapping(value = "/myorders", method = RequestMethod.GET)
    public String getAllActiveOrders(Model model) {
        model.addAttribute("orders", orderService.getWaiterActiveOrders());
        return "waiter/waiter_orders";
    }

    @RequestMapping(value = "/order/{id}/menu", method = RequestMethod.GET)
    public String getMenu(@PathVariable("id") long id,
                          @RequestParam(value = "compliment", defaultValue = "") String compliment,
                          Model model) {
        if (compliment.equals("true")) {
            model.addAttribute("compliment", compliment);
        }
        model.addAttribute("Mcategory", menuService.getMenu());
        model.addAttribute("order", id);
        return "waiter/menu";
    }

    @RequestMapping(value = "/order/{id}/setdiscount/{discount}", method = RequestMethod.GET)
    public String setDiscount(@PathVariable("id") Long id,
                              @PathVariable("discount") String discount) {
        discountService.setDiscountToOrder(id, discount);
        return "redirect:/waiter/order/{id}";
    }

    @RequestMapping(value = "/order/{id}/discount/disable", method = RequestMethod.GET)
    public String disableDiscount(@PathVariable("id") Long id) {
        discountService.disableDiscount(id);
        return "redirect:/waiter/order/{id}";
    }

}
