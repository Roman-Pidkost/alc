package yaremko.yaromyr.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import yaremko.yaromyr.dto.request.FirmRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.FirmResponse;
import yaremko.yaromyr.dto.response.PaginationResponse;
import yaremko.yaromyr.entity.Firm;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.repository.FirmRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FirmService {


    @Autowired
    private FirmRepository firmRepository;

    @Autowired
    private CountryService countryService;



    public FirmResponse save(FirmRequest firmRequest) throws WrongInputData {
        return new FirmResponse(firmRepository.save(firmRequestToFirm(null, firmRequest)));
    }

    public FirmResponse update(Long id, FirmRequest firmRequest) throws WrongInputData {
        Firm firm = getOne(id);
        return new FirmResponse(firmRequestToFirm(firm,firmRequest));
    }

    public DataResponse<FirmResponse> getAll(){
        return new DataResponse<FirmResponse>(firmRepository.findAll().stream().map(FirmResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<FirmResponse> getAllWithPagination(PaginationRequest paginationRequest){
        Page<Firm> firmPage = firmRepository.findAll(paginationRequest.getPageRequest());
        return new DataResponse<FirmResponse>(firmPage.map(FirmResponse::new).getContent(),new PaginationResponse(firmPage));
    }

    public DataResponse<FirmResponse> getByCountryId(Long countryId){
        return new DataResponse<FirmResponse>(firmRepository.findByCountryId(countryId).stream().map(FirmResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<FirmResponse> searchByName(String name){
        return new DataResponse<FirmResponse>(firmRepository.findByNameLike(name).stream().map(FirmResponse::new).collect(Collectors.toList()));
    }

    public Long delete(Long id) throws WrongInputData {
        firmRepository.delete(getOne(id));
        return id;
    }

    public Firm getOne(Long id) throws WrongInputData {
        Optional<Firm> optional = firmRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new WrongInputData("Firm with "+id+" id not found");
        }
    }

    private Firm firmRequestToFirm(Firm firm, FirmRequest firmRequest) throws WrongInputData {
        if(firm == null){
            firm = new Firm();
        }
        firm.setName(firmRequest.getName());
        firm.setCountry(countryService.findOne(firmRequest.getCountryId()));
        return firm;
    }

}
