package MyApp.Service;

import MyApp.Model.Headers;
import MyApp.Model.Position;
import MyApp.Repository.HeaderRepository;
import MyApp.Repository.PosRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Data
@Service
public class ServiceHeaders {

    @Autowired
    private final HeaderRepository headerRepository;

    @Autowired
    private final PosRepository posRepository;


    public List<Headers> getAllHeaders(){
        return headerRepository.findAll();
    }

    public List<Position> getAllPosition(){
        return posRepository.findAll();
    }

    public Position getPosId(Integer idPos){return posRepository.findOne(idPos);}

    public Position getPos(Integer idPos){return posRepository.getPosByLp(idPos);}

    public Position addPosition(Position newPos) {
        return posRepository.save(newPos);
    }

    public Headers getHeadersById(Integer headerId) {
        return headerRepository.findOne(headerId);
    }



    public Headers addHeader(Headers header) {
        return headerRepository.save(header);
    }


    public Position savePosition(Position newPosition) {
        return posRepository.save(newPosition);
    }

    public Headers saveHeaders(Headers headers) {
        return headerRepository.save(headers);
    }

    public void deletePos(Integer posId) {
        posRepository.delete(posId);
    }


}
