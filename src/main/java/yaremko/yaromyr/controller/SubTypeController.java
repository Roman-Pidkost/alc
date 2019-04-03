package yaremko.yaromyr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.request.SubTypeRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.SubTypeResponse;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.service.SubTypeService;

@RestController
@CrossOrigin
@RequestMapping("/subType")
public class SubTypeController {

    @Autowired
    private SubTypeService subTypeService;

    @GetMapping
    public DataResponse<SubTypeResponse> getAll(){
        return subTypeService.getAll();
    }

    @GetMapping("/search")
    public DataResponse<SubTypeResponse> getAll(@RequestParam String name){
        return subTypeService.searchByName(name);
    }

    @PostMapping("/page")
    public DataResponse<SubTypeResponse> getAllWithPagination(@RequestBody PaginationRequest paginationRequest){
        return subTypeService.getAll(paginationRequest);
    }

    @GetMapping("/by/idType")
    public DataResponse<SubTypeResponse> getAllByTypeID(@RequestParam Long idType){
        return subTypeService.getByTypeId(idType);
    }

    @PostMapping("/by/idType/page")
    public DataResponse<SubTypeResponse> getAllByTypeIDWithPagination(@RequestParam Long idType,@RequestBody PaginationRequest paginationRequest){
        return subTypeService.getByTypeId(idType,paginationRequest);
    }

    @PostMapping
    public SubTypeResponse save(@RequestBody SubTypeRequest subTypeRequest) throws WrongInputData {
        return subTypeService.save(subTypeRequest);
    }

    @PutMapping
    public SubTypeResponse update(@RequestParam Long id,@RequestBody SubTypeRequest subTypeRequest) throws WrongInputData {
        return subTypeService.update(id,subTypeRequest);
    }

    @DeleteMapping
    public Long delete(@RequestParam Long id) throws WrongInputData {
        return subTypeService.delete(id);
    }


}
