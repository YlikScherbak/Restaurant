package com.spring.web;

import com.spring.dto.adminDTO.OrdersDTO;
import com.spring.service.OrderDetailService;
import com.spring.util.Pagination;
import com.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/orders")
public class AdminOrdersController {

    private static final int ITEMS_PER_PAGE = 15;

    private final OrderService orderService;

    private final OrderDetailService orderDetailService;

    @Autowired
    public AdminOrdersController(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;

        this.orderDetailService = orderDetailService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showAllOrders(Model model,
                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                @RequestParam(value = "active", required = false, defaultValue = "false") Boolean active) {
        List<OrdersDTO> list;
        long totalCount = orderService.count();
        Pagination pagination = new Pagination();
        pagination.setCount(totalCount);
        pagination.setResultsPerPage(ITEMS_PER_PAGE);
        pagination.setCurrentPage(page);
        list = orderService.getOrders(active, (pagination.getCurrentPage() - 1) * ITEMS_PER_PAGE, pagination.getResultsPerPage());
        model.addAttribute("pagination", pagination);
        model.addAttribute("orders", list);
        return "/admin/order/orders";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editOrder(Model model,
                            @PathVariable("id") Long id) {
        model.addAttribute("order", orderService.getOrder(id));
        model.addAttribute("id", id);
        return "/admin/order/edit_order";
    }

    @RequestMapping(value = "/delete/{id}/{product}", method = RequestMethod.GET)
    public String deleteFromOrder(Model model,
                                  @PathVariable("id") Long id,
                                  @PathVariable("product") String prodName) {
        orderDetailService.deleteOrderDetail(id, prodName);
        return "redirect:/admin/orders/edit/" + id;
    }

    @RequestMapping(value = "/audition/{id}", method = RequestMethod.GET)
    public String getAuditionInfo(Model model, @PathVariable("id") Long id) {
        model.addAttribute("audit", orderDetailService.getAudition(id));

        return "/admin/order/order_audit";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchOrder(@RequestParam Long id,  Model model){
        model.addAttribute("orders", orderService.searchOrder(id));
        return "/admin/order/orders";
    }
}
