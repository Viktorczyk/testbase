package MyApp.Model.ModelDto;

import lombok.Data;

@Data
public class LocationDto {

    private String location;
    private String description;
    private Integer shelf;

    //wybor magazynu
    private Integer warehause;
    private Integer position;

}
