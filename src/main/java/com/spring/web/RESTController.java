package com.spring.web;


import com.spring.dto.waitresDTO.ErrorDTO;
import com.spring.service.ErrorService;
import com.spring.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTController {

    private final OrderDetailService orderDetailService;

    private final ErrorService errorService;

    @Autowired
    public RESTController(OrderDetailService orderDetailService, ErrorService errorService) {
        this.orderDetailService = orderDetailService;
        this.errorService = errorService;
    }

    @RequestMapping(value = "waiter/order/{id}/menu", method = RequestMethod.POST)
    public String addToOrder(@PathVariable("id") long id,
                             @RequestParam("product") String product,
                             @RequestParam(value = "compliment", defaultValue = "") String compliment) {
        String prodName = orderDetailService.addOrderDetail(id, product, compliment);
        return prodName + " successfully added to order ";
    }

    @RequestMapping(value = "waiter/error/{id}", method = RequestMethod.POST)
    public void sendError(ErrorDTO error,
                          @PathVariable("id") Long id) {
        System.out.println(error.getMessage());
        errorService.saveError(error, id);
    }
}
