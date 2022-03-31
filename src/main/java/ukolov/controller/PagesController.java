package ukolov.controller;

import org.springframework.web.bind.annotation.*;
import ukolov.form.ServiceForm;
import ukolov.entity.ServiceEntity;
import ukolov.service.AvtoRepairService;
import ukolov.service.ServiceService;
import ukolov.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Контроллер, отслеживающий все запросы с клиента
 * */
@Controller
public class PagesController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    AvtoRepairService avtoRepairService;

    @Autowired
    TypeServiceService typeServiceService;

    /**
     * метод загрузки главной страницы - страницы услуг
     */
    @GetMapping("/")
    public String loadallServicesAdmin(Model model) {
        model.addAttribute("services", serviceService.findAll());
        return "allServicesAdmin";
    }

    /**
     * метод загрузки страницы добавления услуг
     */
    @GetMapping("/addService")
    public String loadAddService(Model model) {
        model.addAttribute("autoRepairs", avtoRepairService.findAll());
        model.addAttribute("typeServices", typeServiceService.findAll());
        model.addAttribute("serviceForm", new ServiceForm());
        return "addService";
    }

    /**
     * метод добавления новой услуги
     */
    @PostMapping("/addNewService")
    public RedirectView addExcursion(@ModelAttribute ServiceForm serviceForm) {
        serviceService.save(serviceForm);
        return new RedirectView("/");
    }

    /**
     * метод показа подробной информации услуги по ее id
     * @param id - id, выбранной услуги
     * @return - страница со всей информацией об услуге
     */
    @GetMapping("/service/id={id}")
    public String loadService(@PathVariable Long id, Model model) {
        ServiceEntity service = serviceService.findById(id);
        model.addAttribute("service", service);
        return "service";
    }

    /**
     * метод удаления услуги по ее id
     * @param id - id, удаляемой услуги
     * @return - редирект на главную страницу
     */
    @GetMapping("/deleteService/id={id}")
    public RedirectView deleteService(@PathVariable Long id) {
        serviceService.deleteById(id);
        return new RedirectView("/");
    }

    /**
     * метод загрузки страницы редактирования услуги по id
     * @param id - id редактируемой услуги
     */
    @GetMapping("/editService/id={id}")
    public String loadEditService(@PathVariable Long id, Model model) {
        model.addAttribute("updateServiceForm", ServiceForm.builder().id(id).build());
        return "editService";
    }

    /**
     * метод редактирования услуши по id
     * @param serviceForm - форма с новыми данными
     * @param id - id редактируемой услуги
     * @return - редирект на главную страницу
     */
    @PostMapping("/editService/{id}")
    public RedirectView updateExcursion(@ModelAttribute ServiceForm serviceForm, @PathVariable Long id) {
        serviceForm.setId(id);
        serviceService.update(serviceForm);
        return new RedirectView("/");
    }
}
