package MyApp.Controller;


import MyApp.Model.Warehouse;
import MyApp.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/all")
public class WareHouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping("/warehouse")
    public String getAllWareHouse(Model model){
        model.addAttribute("addWare", new Warehouse());
        List<Warehouse> allWare = warehouseRepository.findAll();
        model.addAttribute("allWare", allWare);
        return "pages/warehouse";
    }

    @PostMapping("/warehouse")
    public String addWare(@ModelAttribute("addWare") Warehouse addWare){
        Warehouse newWare = new Warehouse();

        newWare.setDescription(addWare.getDescription());
        newWare.setName(addWare.getName());
        newWare.setSymbol(addWare.getSymbol());

        warehouseRepository.save(newWare);
        return "redirect:/all/warehouse";
    }

    @RequestMapping(value = "/wardelete/{warId}", method = RequestMethod.POST)
    public String delWare (@PathVariable ("warId") Integer warId){
        warehouseRepository.delete(warId);
        return "redirect:/all/warehouse";
    }

}
