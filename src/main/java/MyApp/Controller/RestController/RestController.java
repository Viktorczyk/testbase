package MyApp.Controller.RestController;

import MyApp.Model.Headers;
import MyApp.Model.ModelDto.PosDto;
import MyApp.Model.Position;
import MyApp.Repository.HeaderRepository;
import MyApp.Repository.PosRepository;
import MyApp.Service.ServiceHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    private ServiceHeaders serviceHeaders;

    @Autowired
    private HeaderRepository headerRepository;

    @Autowired
    private PosRepository posRepository;

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
    public Position savePosition(@RequestBody PosDto newPos) {

        //WYSZUKUJEMY NAGLOWEK DLA POZYCJI
        Headers headers = serviceHeaders.getHeadersById(newPos.getHeaders());


        //TWORZYMY NOWA POZYCJE WRAZ Z DANYMI
        Position newPosition = new Position();

        newPosition.setDescription(newPos.getDescription());
        newPosition.setQuantity(newPos.getQuantity());
        newPosition.setIndeks(newPos.getIndeks());
        newPosition.setHeaders(headers);            //USTAWIAMY NAGLOWEK DLA POZYCJI

        //ZAPIS POZYCJI I NAGLOWKA
        serviceHeaders.addPosition(newPosition);
        serviceHeaders.addHeader(headers);
        return  newPosition;
    }



    @PutMapping(value="/put/{posId}")
    public @ResponseBody Position putPosition(@RequestBody PosDto newPos, @PathVariable("posId")Integer posId) {



        //WYSZUKUJEMY NAGLOWEK DLA POZYCJI
        Headers headers = serviceHeaders.getHeadersById(newPos.getHeaders());         //POBIERAMY ID HEADERS

        //TWORZYMY NOWA POZYCJE WRAZ Z DANYMI
        Position newPosition = new Position();
        newPosition.setId(posId);                                                       //USTAWIAMY ID POZYCJI
        //TWORZYMY NOWA POZYCJE WRAZ Z DANYMI
        newPosition.setDescription(newPos.getDescription());
        newPosition.setQuantity(newPos.getQuantity());
        newPosition.setIndeks(newPos.getIndeks());
        //newPosition.setDataModified(newPos.getDataModified());            //JEST TEMPORARY TIMESTAMP A DATA JEST NULL-OWA  TRZA TRIGGER NA BAZIE WALNAC :P
        newPosition.setLp(newPos.getLp());
        newPosition.setHeaders(headers);            //USTAWIAMY NAGLOWEK DLA POZYCJI


        //ZAPIS POZYCJI I NAGLOWKA
        serviceHeaders.savePosition(newPosition);
        serviceHeaders.saveHeaders(headers);
        return  newPosition;
    }







    @DeleteMapping("/pos/{posId}")
    public String deleteFuel(@PathVariable ("posId") Integer posId){

        serviceHeaders.deletePos(posId);

        return "Delete position id - " + posId ;
    }



}
