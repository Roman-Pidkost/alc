package yaremko.yaromyr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.request.TypeRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.TypeResponse;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.service.TypeService;

@RestController
@CrossOrigin
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping
    public TypeResponse save(@RequestBody TypeRequest typeRequest){
        return typeService.save(typeRequest);
    }

    @PutMapping
    public TypeResponse update(@RequestParam Long id, @RequestBody TypeRequest typeRequest) throws WrongInputData {
        return typeService.update(id, typeRequest);
    }

    @GetMapping("/all")
    public DataResponse<TypeResponse> getAll(){
        return typeService.getAll();
    }

    @GetMapping("/search")
    public DataResponse<TypeResponse> getAll(@RequestParam String name){
        return typeService.searchByName(name);
    }

    @PostMapping("/page")
    public DataResponse<TypeResponse> getAllOnPages(@RequestBody PaginationRequest paginationRequest){
        return typeService.getAllWithPagination(paginationRequest);
    }

    @DeleteMapping
    public Long delete(@RequestParam Long id) throws WrongInputData {
        return typeService.delete(id);
    }


}
