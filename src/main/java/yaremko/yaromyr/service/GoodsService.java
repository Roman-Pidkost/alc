package yaremko.yaromyr.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import yaremko.yaromyr.dto.request.GoodsRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.request.SearchGoodsRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.GoodsResponse;
import yaremko.yaromyr.dto.response.PaginationResponse;
import yaremko.yaromyr.entity.Goods;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.repository.GoodsRepository;
import yaremko.yaromyr.specification.GoodsSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GoodsService {

    @Autowired
    private SubTypeService subTypeService;

    @Autowired
    private FirmService firmService;

    @Autowired
    private GoodsRepository goodsRepository;

    public DataResponse<GoodsResponse> getAll() {
        return new DataResponse<>(goodsRepository.findAll().stream().map(GoodsResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<GoodsResponse> getAll(PaginationRequest paginationRequest) {
        Page<Goods> page = goodsRepository.findAll(paginationRequest.getPageRequest());
        return new DataResponse<>(page.getContent().stream().map(GoodsResponse::new).collect(Collectors.toList()), new PaginationResponse(page));
    }

    public DataResponse<GoodsResponse> getAll(SearchGoodsRequest searchGoodsRequest) {
        PageRequest pageRequest = searchGoodsRequest.getPaginationRequest().getPageRequest();
        Page<Goods> page = goodsRepository.findAll(new GoodsSpecification(searchGoodsRequest), pageRequest);
        return new DataResponse<>(page.getContent().stream().map(GoodsResponse::new).collect(Collectors.toList()), new PaginationResponse(page));
    }

    public GoodsResponse save(GoodsRequest goodsRequest) throws WrongInputData, IOException {
        return new GoodsResponse(goodsRepository.save(goodsRequestToGoods(null, goodsRequest)));
    }

    public GoodsResponse update(Long id, GoodsRequest goodsRequest) throws WrongInputData, IOException {
        Goods goods = goodsRequestToGoods(findOne(id), goodsRequest);
        return new GoodsResponse(goodsRepository.save(goods));
    }

    public Long delete(Long id) throws WrongInputData {
        Goods goods = findOne(id);
        if (goods.getGoodsOrderList().size() > 0) {
            throw new WrongInputData("You can not delete goods with id " + id + " because there are orders with this goods");
        }
        goodsRepository.delete(goods);
        return id;
    }

    public Goods findOne(Long id) throws WrongInputData {
        return goodsRepository.findById(id).orElseThrow(() -> new WrongInputData("Goods with id " + id + " not found"));
    }

    private Goods goodsRequestToGoods(Goods goods, GoodsRequest goodsRequest) throws WrongInputData, IOException {
        if (goods == null) {
            goods = new Goods();
        }
        String pathToImage = saveImage(goodsRequest.getImage(), goods.getPathToImage());
        goods.setPathToImage(pathToImage);
        goods.setCapacity(goodsRequest.getCapacity());
        goods.setDescription(goodsRequest.getDescription());
        goods.setMaturity(goodsRequest.getMaturity());
        goods.setStrength(goodsRequest.getStrength());
        goods.setName(goodsRequest.getName());
        goods.setPrice(goodsRequest.getPrice());
        goods.setFirm(firmService.getOne(goodsRequest.getFirmId()));
        goods.setSubType(subTypeService.findOne(goodsRequest.getSubTypeId()));

        return goods;
    }

    private String saveImage(String imageString, String pathToOldImage) throws IOException {
        if (imageString == null || imageString.isEmpty()) {
            return pathToOldImage;
        }

        String[] split = imageString.split(",");

        String expansion = split[0].split("/")[1].split(";")[0];
        String imageName = String.format("%s.%s", UUID.randomUUID().toString(), expansion);
        Path destinationFile = Paths.get(System.getProperty("user.home"), imageName);

        byte[] decodedImg = Base64.getDecoder().decode(split[1].getBytes());
        Files.write(destinationFile, decodedImg);

        deleteFile(pathToOldImage);

        return destinationFile.toString();
    }

    private boolean deleteFile(String pathToFile) throws IOException {
        return pathToFile == null || pathToFile.isEmpty() || Files.deleteIfExists(Paths.get(pathToFile));
    }


}
