package yaremko.yaromyr.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import yaremko.yaromyr.dto.request.CountryRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.CountryResponse;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.PaginationResponse;
import yaremko.yaromyr.entity.Country;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.repository.CountryRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;


    public CountryResponse save(CountryRequest countryRequest){
        return new CountryResponse(countryRepository.save(countryRequestToCountry(null,countryRequest)));
    }

    public CountryResponse update(Long id, CountryRequest countryRequest) throws WrongInputData {
        Country country = findOne(id);
        return new CountryResponse(countryRepository.save(countryRequestToCountry(country,countryRequest)));
    }

    public DataResponse<CountryResponse> getAll(){
        return new DataResponse<CountryResponse>(countryRepository.findAll().stream().map(CountryResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<CountryResponse> searchByName(String name){
        return new DataResponse<CountryResponse>(countryRepository.findByNameLike(name).stream().map(CountryResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<CountryResponse> getAllWithPagination(PaginationRequest paginationRequest){
        Page<Country> pageCountry = countryRepository.findAll(paginationRequest.getPageRequest());
        return new DataResponse<CountryResponse>(pageCountry.map(CountryResponse::new).getContent(),new PaginationResponse(pageCountry));
    }

    public Long delete(Long id) throws WrongInputData {
        countryRepository.delete(findOne(id));
        return id;
    }


    public Country findOne(Long id) throws WrongInputData {
        Optional<Country> country = countryRepository.findById(id);
        if(country.isPresent()){
            return country.get();
        }else{
            throw new WrongInputData("Country with "+id+" id not found");
        }
    }

    private Country countryRequestToCountry(Country country, CountryRequest countryRequest){
        if(country == null){
            country = new Country();
        }
        country.setName(countryRequest.getName());
        return country;
    }
}
