package yaremko.yaromyr.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import yaremko.yaromyr.dto.request.CityRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.CityResponse;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.PaginationResponse;
import yaremko.yaromyr.entity.City;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.repository.CityRepository;

import java.util.stream.Collectors;

@Service
@Log4j2
public class CityService {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityRepository cityRepository;

    public CityResponse save(CityRequest cityRequest) throws WrongInputData {
        return new CityResponse(cityRepository.save(cityRequestToCity(null, cityRequest)));
    }

    public CityResponse update(Long id, CityRequest cityRequest) throws WrongInputData {
        return new CityResponse(cityRequestToCity(findOne(id), cityRequest));
    }

    public DataResponse<CityResponse> getAll() {
        return new DataResponse<CityResponse>(cityRepository.findAll().stream().map(CityResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<CityResponse> getAll(PaginationRequest paginationRequest) {
        Page<City> page = cityRepository.findAll(paginationRequest.getPageRequest());
        return new DataResponse<CityResponse>(
                page.stream().map(CityResponse::new).collect(Collectors.toList()),
                new PaginationResponse(page)
        );
    }

    public DataResponse<CityResponse> getByCountryId(Long countryId) {
        return new DataResponse<CityResponse>(cityRepository.findByCountryId(countryId).stream().map(CityResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<CityResponse> getByCountryId(Long countryId, PaginationRequest paginationRequest){
        Page<City> page = cityRepository.findByCountryId(countryId, paginationRequest.getPageRequest());
        return new DataResponse<CityResponse>(page.stream().map(CityResponse::new).collect(Collectors.toList()), new PaginationResponse(page));
    }

    public DataResponse<CityResponse> searchByName(String name){
        return new DataResponse<CityResponse>(cityRepository.findByNameLike(name).stream().map(CityResponse::new).collect(Collectors.toList()));
    }

    public Long delete(Long id) throws WrongInputData {
        City city = findOne(id);
        if (city.getUserList().size() > 0) {
            throw new WrongInputData("You can not delete city with id " + id + " because there are users with this city");
        }
        cityRepository.delete(city);
        return id;
    }

    public City findOne(Long id) throws WrongInputData {
        return  cityRepository.findById(id).orElseThrow(() -> new WrongInputData("City with id " + id + " not found"));
    }

    private City cityRequestToCity(City city, CityRequest cityRequest) throws WrongInputData {
        if (city == null) {
            city = new City();
        }
        city.setName(cityRequest.getName());
        city.setCountry(countryService.findOne(cityRequest.getCountryId()));

        return city;
    }
}
