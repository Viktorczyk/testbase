package MyApp.Controller.UserController;

import MyApp.Model.User.Roles;
import MyApp.Repository.RolesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationEvent;
import java.util.List;

@Data
@Controller
@RequestMapping("/all")
public class RoleController {

    public static final String REDIRECT_ALL_ROLES = "redirect:/all/roles";

    @Autowired
    private RolesRepository rolesRepository;


    @GetMapping("/roles")
    public String allRoles(Model model){
        model.addAttribute("newRole", new Roles());
        List<Roles> rolesList = rolesRepository.findAll();
        model.addAttribute("listRoles", rolesList);
        return "pages/roles";
    }


    @PostMapping("/roles")
    public String addRoles(@ModelAttribute ("addRole")
                           Roles addRoles){
        Roles newRole = new Roles();
        newRole.setRoles(addRoles.getRoles());

        rolesRepository.save(newRole);

        return REDIRECT_ALL_ROLES;

    }

    @RequestMapping(value="/deleterole/{roleId}", method = RequestMethod.POST)
    public String delRole(@PathVariable("roleId") Integer roleId){
        rolesRepository.delete(roleId);
        return REDIRECT_ALL_ROLES;
    }
}
