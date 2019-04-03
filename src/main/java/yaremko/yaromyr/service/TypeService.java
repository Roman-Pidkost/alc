package yaremko.yaromyr.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.request.TypeRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.PaginationResponse;
import yaremko.yaromyr.dto.response.TypeResponse;
import yaremko.yaromyr.entity.Type;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.repository.TypeRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public TypeResponse save(TypeRequest typeRequest){
        return new TypeResponse(typeRepository.save(typeRequestToType(null,typeRequest)));
    }

    public TypeResponse update(Long id, TypeRequest typeRequest) throws WrongInputData {
        Type type = findOne(id);
        return new TypeResponse(typeRepository.save(typeRequestToType(type,typeRequest)));
    }

    public DataResponse<TypeResponse> getAll(){
        return new DataResponse<TypeResponse>(typeRepository.findAll().stream().map(TypeResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<TypeResponse> getAllWithPagination(PaginationRequest paginationRequest){
        Page<Type> pageType = typeRepository.findAll(paginationRequest.getPageRequest());
        return new DataResponse<TypeResponse>(pageType.stream().map(TypeResponse::new).collect(Collectors.toList()),new PaginationResponse(pageType));
    }

    public DataResponse<TypeResponse> searchByName(String name){
        return new DataResponse<TypeResponse>(typeRepository.findByNameLike(name).stream().map(TypeResponse::new).collect(Collectors.toList()));
    }


    public Type findOne(Long id) throws WrongInputData {
        Optional<Type> typeOptional = typeRepository.findById(id);
        if(typeOptional.isPresent()){
            return typeOptional.get();
        }else{
            throw new WrongInputData("Type with id "+id+" not found");
        }
    }

    public Long delete(Long id) throws WrongInputData {
        typeRepository.delete(findOne(id));
        return id;
    }

    private Type typeRequestToType(Type type, TypeRequest typeRequest){
        if(type == null){
            log.info("Creating new type");
            type = new Type();
        }
        type.setName(typeRequest.getName());
        type.setMaturity(typeRequest.getMaturity());
        return type;
    }


}
