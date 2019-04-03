package yaremko.yaromyr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yaremko.yaromyr.dto.request.FirmRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.FirmResponse;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.service.FirmService;

@RestController
@CrossOrigin
@RequestMapping("/firm")
public class FirmController {

    @Autowired
    private FirmService firmService;

    @GetMapping
    public DataResponse<FirmResponse> getAll(){
        return firmService.getAll();
    }

    @GetMapping("/search")
    public DataResponse<FirmResponse> search(@RequestParam String name){
        return firmService.searchByName(name);
    }

    @GetMapping("/by/country")
    public DataResponse<FirmResponse> getByCountry(@RequestParam Long countryId ){
        return firmService.getByCountryId(countryId);
    }

    @PostMapping("/page")
    public DataResponse<FirmResponse> getAllWithPagination(@RequestBody PaginationRequest paginationRequest){
        return firmService.getAllWithPagination(paginationRequest);
    }

    @PostMapping
    public FirmResponse save(@RequestBody FirmRequest firmRequest) throws WrongInputData {
        return firmService.save(firmRequest);
    }

    @PutMapping
    public FirmResponse update(@RequestParam Long id ,@RequestBody FirmRequest firmRequest) throws WrongInputData {
        return firmService.update(id, firmRequest);
    }

    @DeleteMapping
    public Long delete(@RequestParam Long id) throws WrongInputData {
        return firmService.delete(id);
    }

}
