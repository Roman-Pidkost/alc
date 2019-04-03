package yaremko.yaromyr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yaremko.yaromyr.dto.request.OrderRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.OrderResponse;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public DataResponse<OrderResponse> getAll() {
        return orderService.getAll();
    }

    @PostMapping("/page")
    public DataResponse<OrderResponse> getAll(@RequestBody PaginationRequest paginationRequest) {
        return orderService.getAll(paginationRequest);
    }

    @PostMapping("/archived")
    public DataResponse<OrderResponse> getAllArchived(@RequestBody PaginationRequest paginationRequest) {
        return orderService.getAllArchived(paginationRequest);
    }

    @PostMapping("/unarchived")
    public DataResponse<OrderResponse> getAllUnarchived(@RequestBody PaginationRequest paginationRequest) {
        return orderService.getAllUnarchived(paginationRequest);
    }

    @PostMapping
    public OrderResponse save(@RequestBody OrderRequest orderRequest) throws WrongInputData {
        return orderService.save(orderRequest);
    }

    @PutMapping
    public OrderResponse update(@RequestParam Long id, @RequestBody OrderRequest orderRequest) throws WrongInputData {
        return orderService.update(orderRequest, id);
    }

    @DeleteMapping
    public Long delete(@RequestParam Long id) throws WrongInputData {
        return orderService.delete(id);
    }

}
