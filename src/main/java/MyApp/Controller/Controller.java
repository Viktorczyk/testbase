package MyApp.Controller;

import MyApp.Model.Headers;
import MyApp.Model.ModelDto.HeadersDto;
import MyApp.Model.ModelDto.PosDto;
import MyApp.Model.Position;
import MyApp.Model.TypeHeader;
import MyApp.Repository.PosRepository;
import MyApp.Service.ServiceHeaders;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@org.springframework.stereotype.Controller
@RequestMapping("/all")
class Controller {

    @Autowired
    private ServiceHeaders serviceHeaders;

    @Autowired
    private PosRepository posRepository;

    @GetMapping("/headers")
    public String addHeader(Model model){
        model.addAttribute("addHeader", new Headers());
        List<Headers> allHeaders =  serviceHeaders.getAllHeaders();
        model.addAttribute("addHeaders", allHeaders);
        return "pages/headers";
    }

    @PostMapping(value="/headers")
    public String saveHeader(@ModelAttribute("addHeader")
                                     HeadersDto newHeaders
    ){
        Headers header = new Headers();
        header.setTypHeader(TypeHeader.chooseTypeDoc(newHeaders.getTypHeader()));
        header.setNumber(newHeaders.getNumber());
        header.setDescription(newHeaders.getDescription());
        serviceHeaders.addHeader(header);
        return  "redirect:/all/headers";
    }

    @GetMapping("/position")
    public String addPosition(Model model){

        model.addAttribute("addHeader", new PosDto());
        List<Headers> allHeaders =  serviceHeaders.getAllHeaders();
        model.addAttribute("allHeaders", allHeaders);
        List<Position> positionList = serviceHeaders.getAllPosition();
        model.addAttribute("posList", positionList);
        return "pages/allPosition";
    }


    @PostMapping(value="/position")
    public String savePosition(@ModelAttribute("addHeader") PosDto posToAdd){
        Position newPos = new Position();
        newPos.setHeaders(serviceHeaders.getHeadersById(posToAdd.getHeaders()));
        newPos.setDescription(posToAdd.getDescription());
        newPos.setIndeks(posToAdd.getIndeks());
        newPos.setQuantity(posToAdd.getQuantity());
        serviceHeaders.addPosition(newPos);
        return  "redirect:/all/position";
    }



}
