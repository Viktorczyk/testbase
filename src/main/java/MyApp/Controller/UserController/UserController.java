package MyApp.Controller.UserController;

import MyApp.Model.User.Users;
import MyApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    //https://www.jackrutorial.com/2018/04/spring-boot-user-registration-login.html

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value={"/","/loginek"},method = RequestMethod.GET   )
    public ModelAndView login()
    {ModelAndView model = new ModelAndView();
        model.setViewName("pages/loginek");
        return model;
    }

    @RequestMapping(value="/signup",method = RequestMethod.GET   )
    public ModelAndView signup(){
        ModelAndView model = new ModelAndView();
        Users user = new Users();
        model.addObject("user", user);
        model.setViewName("pages/signup");

        return model;

    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid Users user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Users userExists = userRepository.findByLogin(user.getLogin());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userRepository.save(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new Users());
            model.setViewName("user/signup");
        }

        return model;
    }

    @RequestMapping(value="/all",method = RequestMethod.GET   )
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByLogin(auth.getName());

        model.addObject("userName", user.getLogin());
        model.setViewName("pages/all");

        return model;
    }

    @RequestMapping(value="/access_denied",method = RequestMethod.GET   )
    public ModelAndView accesDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("pages/access_denied");

        return model;
    }

}

