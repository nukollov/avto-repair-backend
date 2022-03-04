package ukolov.controller;

import ukolov.form.ServiceForm;
import ukolov.form.UpdateServiceForm;
import ukolov.entity.ServiceEntity;
import ukolov.service.AvtoRepairService;
import ukolov.service.ServiceService;
import ukolov.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

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
     *
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
    public RedirectView deleteService(@PathVariable Long id, Model model) {
        serviceService.deleteById(id);
        return new RedirectView("/");
    }

    /**
     * метод загрузки страницы редактирования услуги по id
     * @param id - id редактируемой услуги
     */
    @GetMapping("/editService/id={id}")
    public String loadEditService(@PathVariable Long id, Model model) {
        model.addAttribute("updateServiceForm", new UpdateServiceForm());
        model.addAttribute("service", serviceService.findById(id));
        return "editService";
    }

    /**
     * метод редактирования услуши по id
     * @param updateServiceForm - форма с новыми данными
     * @param id - id редактируемой услуги
     * @return - редирект на главную страницу
     */
    @PostMapping("/editService/{id}")
    public RedirectView addExcursion(@ModelAttribute UpdateServiceForm updateServiceForm, @PathVariable Long id) {
        serviceService.update(id, updateServiceForm);
        return new RedirectView("/");
    }
}
