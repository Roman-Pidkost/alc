package yaremko.yaromyr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yaremko.yaromyr.dto.request.CityRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.CityResponse;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.service.CityService;

@RestController
@CrossOrigin
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public DataResponse<CityResponse> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/search")
    public DataResponse<CityResponse> getAll(@RequestParam String name) {
        return cityService.searchByName(name);
    }

    @PostMapping("/page")
    public DataResponse<CityResponse> getAllWithPagination(@RequestBody PaginationRequest paginationRequest) {
        return cityService.getAll(paginationRequest);
    }

    @GetMapping("/by/idCountry")
    public DataResponse<CityResponse> getAllByCountryID(@RequestParam Long idCountry) {
        return cityService.getByCountryId(idCountry);
    }

    @PostMapping("by/idCountry/page")
    public DataResponse<CityResponse> getAllByCountryIDWithPagination(@RequestParam Long idCountry, @RequestBody PaginationRequest paginationRequest) {
        return cityService.getByCountryId(idCountry, paginationRequest);
    }

    @PostMapping
    public CityResponse save(@RequestBody CityRequest cityRequest) throws WrongInputData {
        return cityService.save(cityRequest);
    }

    @PutMapping
    public CityResponse update(@RequestParam Long id, @RequestBody CityRequest cityRequest) throws WrongInputData {
        return cityService.update(id, cityRequest);
    }

    @DeleteMapping
    public Long delete(@RequestParam Long id) throws WrongInputData {
        return cityService.delete(id);
    }
}
