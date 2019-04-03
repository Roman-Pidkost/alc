package yaremko.yaromyr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yaremko.yaromyr.dto.request.CountryRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.CountryResponse;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.service.CountryService;

@RestController
@CrossOrigin
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public DataResponse<CountryResponse> getAll(){
        return countryService.getAll();
    }

    @GetMapping("/search")
    public DataResponse<CountryResponse> search(@RequestParam String name){
        return countryService.searchByName(name);
    }

    @PostMapping("/page")
    public  DataResponse<CountryResponse> getAllWithPagination(@RequestBody PaginationRequest paginationRequest){
        return countryService.getAllWithPagination(paginationRequest);
    }

    @PostMapping
    public CountryResponse save(@RequestBody CountryRequest countryRequest){
        return countryService.save(countryRequest);
    }

    @PutMapping
    public CountryResponse update(@RequestParam Long id, @RequestBody CountryRequest countryRequest) throws WrongInputData {
        return countryService.update(id,countryRequest);
    }

    @DeleteMapping
    public Long delete(@RequestParam Long id) throws WrongInputData {
        return countryService.delete(id);
    }
}
