package MyApp.Controller.RestController;

import MyApp.Model.Headers;
import MyApp.Model.ModelDto.PosDto;
import MyApp.Model.Position;
import MyApp.Service.ServiceHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    private ServiceHeaders serviceHeaders;

    //WYSWIETLANIE WSZYSTKICH NAGLOWKOW
    @GetMapping(value ="/headers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Headers> positonHeaders(){
        return serviceHeaders.getAllHeaders();
    }

    //WYSWIETLANIE KONKRETNEGO NAGLOWKA
    @GetMapping(value ="/headers/{headerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Headers getHeader(@PathVariable("headerId") Integer headerId){

        return serviceHeaders.getHeadersById(headerId);

    }


    //WYSWIETLANIE WSZYSTKICH POZYCJI
    @GetMapping(value ="/position", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Position> positionList(){
        return serviceHeaders.getAllPosition();
    }

    //WYSWIETLANIE KONKRETNEJ POZYCJI
    @GetMapping(value ="/position/{idPos}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Position getPos(@PathVariable("idPos") Integer idPos){
        return serviceHeaders.getPosId(idPos);
    }

    @PostMapping(value="/pos")
    public Position savePosition(@RequestBody Position newPos) {

        newPos.setId(0);

        serviceHeaders.addPosition(newPos);
        return  newPos;
    }


}
