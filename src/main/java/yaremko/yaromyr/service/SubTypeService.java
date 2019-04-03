package yaremko.yaromyr.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.request.SubTypeRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.PaginationResponse;
import yaremko.yaromyr.dto.response.SubTypeResponse;
import yaremko.yaromyr.entity.SubType;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.repository.SubTypeRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SubTypeService {

    @Autowired
    private TypeService typeService;

    @Autowired
    private SubTypeRepository subTypeRepository;


    @Transactional
    public SubTypeResponse save(SubTypeRequest subTypeRequest) throws WrongInputData {
        return new SubTypeResponse(subTypeRepository.save(subTypeRequestToSubType(null,subTypeRequest)));

    }

    @Transactional
    public  SubTypeResponse update(Long id, SubTypeRequest subTypeRequest) throws WrongInputData {
        SubType subType = findOne(id);
        return new SubTypeResponse(subTypeRepository.save(subTypeRequestToSubType(subType,subTypeRequest)));
    }

    @Transactional
    public DataResponse<SubTypeResponse> getAll(){
        return new DataResponse<SubTypeResponse>(subTypeRepository.findAll().stream().map(SubTypeResponse::new).collect(Collectors.toList()));
    }

    @Transactional
    public DataResponse<SubTypeResponse> getAll(PaginationRequest paginationRequest){
        Page<SubType> page = subTypeRepository.findAll(paginationRequest.getPageRequest());
        return new DataResponse<SubTypeResponse>(page.stream().map(SubTypeResponse::new).collect(Collectors.toList()),new PaginationResponse(page));
    }


    @Transactional
    public DataResponse<SubTypeResponse> getByTypeId(Long typeId){
        return new DataResponse<SubTypeResponse>(subTypeRepository.findByTypeId(typeId).stream().map(SubTypeResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<SubTypeResponse> searchByName(String name){
        return new DataResponse<SubTypeResponse>(subTypeRepository.findByNameLike(name).stream().map(SubTypeResponse::new).collect(Collectors.toList()));
    }

    @Transactional
    public DataResponse<SubTypeResponse> getByTypeId(Long typeId, PaginationRequest paginationRequest){
        Page<SubType> subTypePage = subTypeRepository.findByTypeId(typeId, paginationRequest.getPageRequest());
        return new DataResponse<SubTypeResponse>(subTypePage.stream().map(SubTypeResponse::new).collect(Collectors.toList()), new PaginationResponse(subTypePage));
    }

     @Transactional
     public Long delete(Long id) throws WrongInputData {
         SubType subType = findOne(id);
         if(subType.getGoods().size()>0){
             throw new WrongInputData("You can not delete subType with id "+id+" because there are goods with this subtype");
         }
         subTypeRepository.delete(subType);
         return id;
     }



    public SubType findOne(Long id) throws WrongInputData {
        Optional<SubType> subTypeOptional = subTypeRepository.findById(id);
        if(subTypeOptional.isPresent()){
            return subTypeOptional.get();
        }else{
            throw new WrongInputData("SubType with id "+id+" not found");
        }
    }

    private SubType subTypeRequestToSubType(SubType subType ,SubTypeRequest subTypeRequest) throws WrongInputData {
        if(subType == null){
            subType = new SubType();
        }
        subType.setName(subTypeRequest.getName());
        subType.setType(typeService.findOne(subTypeRequest.getTypeId()));
        return subType;

    }

}
