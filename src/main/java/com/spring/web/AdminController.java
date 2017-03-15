package com.spring.web;

import com.spring.dto.adminDTO.WorkShiftDTO;
import com.spring.exception.DAOException;
import com.spring.exception.InsufficientPermissionsException;
import com.spring.model.User;
import com.spring.service.*;
import com.spring.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private static final int ITEMS_PER_PAGE = 15;

    private final UserService userService;

    private final ErrorService errorService;

    private final WorkShiftService workShiftService;

    private final GeneralReportService generalReportService;

    private final WaiterReportService waiterReportService;


    @Autowired
    public AdminController(UserService userService, ErrorService errorService, WorkShiftService workShiftService,
                           GeneralReportService generalReportService, WaiterReportService waiterReportService) {
        this.workShiftService = workShiftService;
        this.userService = userService;
        this.errorService = errorService;
        this.generalReportService = generalReportService;
        this.waiterReportService = waiterReportService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String adminFrontPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", user.getUsername());
        model.addAttribute("activeError", errorService.checkActiveErrors());
        model.addAttribute("workShift", workShiftService.getActiveWorkShift());
        return "admin/main";
    }

    //Управление официантами
    @RequestMapping(value = "/waiters", method = RequestMethod.GET)
    public String waiters(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/waiters";
    }

    @RequestMapping(value = "/waiters/edit/{id}", method = RequestMethod.GET)
    public String editWaiter(@PathVariable(value = "id") Long id,
                             Model model) {
        model.addAttribute("id", id);
        model.addAttribute("waiter", userService.findWaiterById(id));
        return "admin/edit_waiter";
    }

    @RequestMapping(value = "/waiters/edit/{id}", method = RequestMethod.POST)
    public String editWaiterPost(@Valid User user, BindingResult bindingResult,
                                 @PathVariable("id") Long id,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("waiter", user);
            return "/admin/edit_waiter";
        }
        userService.editWaiter(user);
        return "redirect:/admin/waiters";
    }

    @RequestMapping(value = "/waiters/disable", method = RequestMethod.GET)
    public String enableDisableWaiter(@RequestParam(value = "id", defaultValue = "0") Long id) {
        userService.enableDisableWaiter(id);
        return "redirect:/admin/waiters";
    }

    @RequestMapping(value = "/registry", method = RequestMethod.GET)
    public String registryGet(Model model) {
        model.addAttribute(new User());
        return "admin/waiter_registry";
    }

    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public String registryPost(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/waiter_registry";
        }
        try {
            userService.saveUser(user);
        } catch (DAOException e) {
            model.addAttribute("error", e);
            return "/admin/waiter_registry";
        }
        return "redirect:/admin/waiters";
    }

    //Сообщения от официантов
    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public String showErrors(Model model) {
        model.addAttribute("errors", errorService.getErrors());
        return "admin/waiter_messages";
    }

    @RequestMapping(value = "/errors/delete", method = RequestMethod.GET)
    public String deleteErrors(@RequestParam("id") Long id) {
        errorService.deleteError(id);
        return "redirect:/admin/errors";
    }

    //Робочие смены
    @RequestMapping(value = "/shift/create", method = RequestMethod.GET)
    public String openWorkShift() {
        workShiftService.openNewWorkShift();
        return "redirect:/admin/main";
    }

    @RequestMapping(value = "/shift/close", method = RequestMethod.GET)
    public String closeWorkShift(Model model) {
        Long id;
        try {
            id = workShiftService.closeWorkShift();
        } catch (InsufficientPermissionsException e) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("name", user.getUsername());
            model.addAttribute("error", e);
            return "/admin/main";
        }
        Long reportId = generalReportService.createGeneralReport(id);
        return "redirect:/admin/report/" + reportId;
    }

    @RequestMapping(value = "/shifts/all")
    public String showAllShifts(Model model,
                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        List<WorkShiftDTO> list;
        long totalCount = workShiftService.countWorkShift();
        Pagination pagination = new Pagination();
        pagination.setCount(totalCount);
        pagination.setResultsPerPage(ITEMS_PER_PAGE);
        pagination.setCurrentPage(page);
        list = workShiftService.getShifts((pagination.getCurrentPage() - 1) * ITEMS_PER_PAGE, pagination.getResultsPerPage());
        model.addAttribute("pagination", pagination);
        model.addAttribute("shifts", list);
        return "admin/shifts";
    }

    @RequestMapping(value = "/shifts/search", method = RequestMethod.POST)
    public String searchReport(@RequestParam Long id,  Model model){
        model.addAttribute("shifts", workShiftService.getWorkShift(id));
        return "admin/shifts";
    }

    //Отчёти
    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public String showReport(Model model, @PathVariable("id") Long id) {
        model.addAttribute("generalReport", generalReportService.getGenReport(id));
        model.addAttribute("waiterReport", waiterReportService.getWaiterReports(id));
        return "admin/report";
    }




}
