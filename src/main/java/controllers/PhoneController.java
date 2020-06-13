package controllers;

import model.SmartPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.phone.SmartPhoneService;
import service.phone.exception.NotFoundException;

@Controller
@RequestMapping("/phones")
public class PhoneController {
    private final SmartPhoneService smartPhoneService;

    @Autowired
    public PhoneController(SmartPhoneService smartPhoneService) {
        this.smartPhoneService = smartPhoneService;
    }


    @GetMapping
    public ModelAndView showPhoneList(@PageableDefault(size = 5, sort = "id") Pageable pageable) throws NotFoundException {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<SmartPhone> phonePage = smartPhoneService.findAll(pageable);
        modelAndView.addObject("phonePage", phonePage);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("phone", new SmartPhone());
        modelAndView.addObject("title", "Thêm mới");
        return modelAndView;
    }
}
