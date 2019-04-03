package yaremko.yaromyr.controller;

import yaremko.yaromyr.dto.request.GoodsRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.exception.WrongInputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yaremko.yaromyr.dto.request.SearchGoodsRequest;
import yaremko.yaromyr.dto.response.GoodsResponse;
import yaremko.yaromyr.service.GoodsService;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public DataResponse<GoodsResponse> getAll() {
        return goodsService.getAll();
    }

    @PostMapping("/search")
    public DataResponse<GoodsResponse> getAll(@RequestBody SearchGoodsRequest searchGoodsRequest) {
        return goodsService.getAll(searchGoodsRequest);
    }

    @PostMapping("/page")
    public DataResponse<GoodsResponse> getAll(@RequestBody PaginationRequest paginationRequest) {
        return goodsService.getAll(paginationRequest);
    }

    @PostMapping
    public GoodsResponse save(@RequestBody GoodsRequest goodsRequest) throws WrongInputData, IOException {
        return goodsService.save(goodsRequest);
    }

    @PutMapping
    public GoodsResponse update(@RequestParam Long id, @RequestBody GoodsRequest goodsRequest) throws WrongInputData, IOException {
        return goodsService.update(id, goodsRequest);
    }

    @DeleteMapping
    public Long delete(@RequestParam Long id) throws WrongInputData {
        return goodsService.delete(id);
    }
}
