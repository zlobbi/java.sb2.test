package sb2.test.converter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sb2.test.converter.services.DayService;
import sb2.test.converter.services.ExchangeService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {
    private DayService dayService;
    private ExchangeService exchangeService;

    @GetMapping
    public String root(Model model, Principal principal) {
        model.addAttribute("day", dayService.getById(LocalDate.now()));
        model.addAttribute("user", principal.getName());
        model.addAttribute("exchangeHistory", exchangeService.getAllByUserName(principal.getName()));
        addRole(model);
        return "converter";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @PostMapping("/exchange")
    public String saveExchange(RedirectAttributes attributes, @RequestParam Map<String, String> params, Principal principal) {
        var sum = exchangeService.createAndSaveExchange(params, principal);
        attributes.addFlashAttribute("fromVal", Integer.parseInt(params.get("fromValute")));
        attributes.addFlashAttribute("toVal", Integer.parseInt(params.get("toValute")));
        attributes.addFlashAttribute("init", params.get("initSum"));
        attributes.addFlashAttribute("sum", sum);
        return "redirect:/";
    }

    @PostMapping("/filter")
    public String filterExchanges(RedirectAttributes attributes, @RequestParam Map<String, Object> params, Principal principal) {
        if (exchangeService.getFilteredExchanges(params, principal).size() != 0)
        attributes.addFlashAttribute("filtered", exchangeService.getFilteredExchanges(params, principal));
        return "redirect:/";
    }


    @GetMapping("/admin-page")
    public String adminPage(Model model) {
        model.addAttribute("currentUrl", dayService.getCBR_URL());
        addRole(model);
        return "admin-page";
    }

    @PostMapping("/change-link")
    public String changeLink(@RequestParam Map<String,
            String> params, RedirectAttributes attributes) {
        attributes.addFlashAttribute("success", "ok");
        dayService.changeCbrUrl(params);
        return "redirect:/";
    }

    public void addRole(Model model) {
        var roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_ADMIN"))
                model.addAttribute("admin", "admin");
        }
    }

}
