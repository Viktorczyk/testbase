package MyApp.Controller;

import MyApp.Model.Headers;
import MyApp.Model.Items;
import MyApp.Model.Location;
import MyApp.Model.ModelDto.PosDto;
import MyApp.Model.Position;
import MyApp.Repository.ItemRepository;
import MyApp.Repository.LocationReporsitory;
import MyApp.Repository.PosRepository;
import MyApp.Service.ServiceHeaders;
import lombok.Data;
import org.hibernate.jpa.criteria.expression.function.CurrentTimeFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@org.springframework.stereotype.Controller
@RequestMapping("/all")
class Controller {

    @Autowired
    private ServiceHeaders serviceHeaders;

    @Autowired
    private PosRepository posRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LocationReporsitory locationReporsitory;

    @GetMapping("/headers")
    public String addHeader(Model model){
        model.addAttribute("addHeader", new Headers());
        List<Headers> allHeaders =  serviceHeaders.getAllHeaders();
        model.addAttribute("addHeaders", allHeaders);
        return "pages/headers";
    }

    @PostMapping(value="/headers")
    public String saveHeader(@ModelAttribute("addHeader")
                                       Headers newHeaders
    ){
        Headers header = new Headers();
        header.setNumber(newHeaders.getNumber());
        header.setTypHeader(newHeaders.getTypHeader());
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
        List<Items> allItem =itemRepository.findAll();
        model.addAttribute("allItem", allItem);
        List<Location> allLocation = locationReporsitory.findAll();
        model.addAttribute("allLocation",allLocation);

        return "pages/allPosition";
    }


    @PostMapping(value="/position")
    public String savePosition(@ModelAttribute("addHeader") PosDto posToAdd){
        Position newPos = new Position();
        newPos.setHeaders(serviceHeaders.getHeadersById(posToAdd.getHeaders()));
        newPos.setItems(itemRepository.findOne(posToAdd.getItem()));
        newPos.setLocation(locationReporsitory.findOne(posToAdd.getLocation()));
        newPos.setDescription(posToAdd.getDescription());
        newPos.setQuantity(posToAdd.getQuantity());
        serviceHeaders.addPosition(newPos);
        return  "redirect:/all/position";
    }

    @GetMapping("/items")
    public String allItems(Model model){
        model.addAttribute("addItem", new Items());
        List<Items> allItems =  itemRepository.findAll();
        model.addAttribute("allItem", allItems);
        return "pages/items";
    }


    @PostMapping("/items")
    public String addItems(@ModelAttribute("addItem") Items newItem){
        Items addItem = new Items();

        addItem.setName(newItem.getName());
        addItem.setNumber(newItem.getNumber());
        addItem.setDescription(newItem.getDescription());
        addItem.setJm(newItem.getJm());
        addItem.setWeight(newItem.getWeight());

        itemRepository.save(addItem);
        return "redirect:/all/items";

    }


    @GetMapping("/location")
    public String allLocation(Model model){
        model.addAttribute("allLoc", new Location());
        List<Location> allLocation =  locationReporsitory.findAll();
        model.addAttribute("allLocs", allLocation);
        return "pages/location";
    }



}
