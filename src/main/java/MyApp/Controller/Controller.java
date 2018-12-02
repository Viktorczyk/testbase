package MyApp.Controller;

import MyApp.Model.*;
import MyApp.Model.ModelDto.HeadersDto;
import MyApp.Model.ModelDto.LocationDto;
import MyApp.Model.ModelDto.PosDto;
import MyApp.Repository.*;
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
    private HeaderRepository headerRepository;

    @Autowired
    private PosRepository posRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

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
        header.setNumber(newHeaders.getNumber());
        header.setTypHeader(TypeHeader.chooseTypeDoc(newHeaders.getTypHeader()));
        header.setDescription(newHeaders.getDescription());
        serviceHeaders.addHeader(header);
        return  "redirect:/all/headers";
    }

    @RequestMapping(value="/deleteheader/{headId}", method = RequestMethod.POST)
    public String delHeader(@PathVariable ("headId") Integer headId ){
        headerRepository.delete(headId);
        return "redirect:/all/headers";
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
        List<Location> allLocation = locationRepository.findAll();
        model.addAttribute("allLocation",allLocation);

        return "pages/allPosition";
    }


    @PostMapping(value="/position")
    public String savePosition(@ModelAttribute("addHeader") PosDto posToAdd){
        Position newPos = new Position();
        newPos.setHeaders(serviceHeaders.getHeadersById(posToAdd.getHeaders()));
        newPos.setItems(itemRepository.findOne(posToAdd.getItem()));
        newPos.setLocation(locationRepository.findOne(posToAdd.getLocation()));
        newPos.setDescription(posToAdd.getDescription());
        newPos.setQuantity(posToAdd.getQuantity());
        serviceHeaders.addPosition(newPos);
        return  "redirect:/all/position";
    }

    @RequestMapping(value="/deletepos/{posId}", method = RequestMethod.POST)
    public String delPos(@PathVariable ("posId") Integer posId ){
        posRepository.delete(posId);
        return "redirect:/all/position";
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

    @RequestMapping(value="/deleteitem/{itemId}", method = RequestMethod.POST)
    public String delItems(@PathVariable ("itemId") Integer itemId ){
        itemRepository.delete(itemId);
        return "redirect:/all/items";
    }


    @GetMapping("/location")
    public String allLocation(Model model){
        model.addAttribute("addLoc", new Location());
        List<Location> allLocation =  locationRepository.findAll();
        model.addAttribute("allLocs", allLocation);
        List<Warehouse> allWare = warehouseRepository.findAll();
        model.addAttribute("allWar", allWare);
        return "pages/location";
    }


    @PostMapping("/location")
    public String addLocal(@ModelAttribute("addLoc") LocationDto addLocation){

       Location newLocaltion = new Location();

       newLocaltion.setDescription(addLocation.getDescription());
       newLocaltion.setLocation(addLocation.getLocation());
       newLocaltion.setShelf(addLocation.getShelf());

       newLocaltion.setPosition(posRepository.findOne(addLocation.getPosition()));
       newLocaltion.setWarehouse(warehouseRepository.findOne(addLocation.getWarehause()));

       locationRepository.save(newLocaltion);
       return "redirect:/all/location";

    }

    @RequestMapping(value="/delete/{locId}", method = RequestMethod.POST)
    public String delLocation (@PathVariable ("locId") Integer locId ){
    locationRepository.delete(locId);
    return "redirect:/all/location";
    }


    //POBIERAMY ITEM ZGODNY Z ID_CLIENTA
    @GetMapping(value="/findWareHouse/{wareId}")
    public List<Location> findWareHouse(@PathVariable("wareId") Integer wareId){
        return locationRepository.findByWarehouse(wareId);
    }

}
