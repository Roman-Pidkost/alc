package yaremko.yaromyr.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import yaremko.yaromyr.dto.request.GoodsOrderRequest;
import yaremko.yaromyr.dto.request.OrderRequest;
import yaremko.yaromyr.dto.request.PaginationRequest;
import yaremko.yaromyr.dto.response.DataResponse;
import yaremko.yaromyr.dto.response.OrderResponse;
import yaremko.yaromyr.dto.response.PaginationResponse;
import yaremko.yaromyr.entity.GoodsOrder;
import yaremko.yaromyr.entity.Order;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.repository.GoodsOrderRepository;
import yaremko.yaromyr.repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

import static yaremko.yaromyr.entity.Order.Status.*;

@Service
public class OrderService {
    private static final String TIME_ZONE = "Europe/Kiev";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodsOrderRepository goodsOrderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    public DataResponse<OrderResponse> getAll() {
        return new DataResponse<>(orderRepository.findAll().stream().map(OrderResponse::new).collect(Collectors.toList()));
    }

    public DataResponse<OrderResponse> getAll(PaginationRequest paginationRequest) {
        Page<Order> page = orderRepository.findAll(paginationRequest.getPageRequest());
        return new DataResponse<>(page.getContent().stream().map(OrderResponse::new).collect(Collectors.toList()), new PaginationResponse(page));
    }

    public DataResponse<OrderResponse> getAllArchived(PaginationRequest paginationRequest) {
        return getAllByStatus(paginationRequest, STATUS_ARCHIVED);
    }

    public DataResponse<OrderResponse> getAllUnarchived(PaginationRequest paginationRequest) {
        return getAllByStatus(paginationRequest, STATUS_UNARCHIVED);
    }

    public DataResponse<OrderResponse> getAllByStatus(PaginationRequest paginationRequest, Order.Status status) {
        Page<Order> page = orderRepository.findAllByStatusEquals(status, paginationRequest.getPageRequest());
        return new DataResponse<>(page.getContent().stream().map(OrderResponse::new).collect(Collectors.toList()), new PaginationResponse(page));
    }

    public OrderResponse save(OrderRequest orderRequest) throws WrongInputData {
        return new OrderResponse(orderRequestToOrder(null, orderRequest));
    }

    public OrderResponse update(OrderRequest orderRequest, Long id) throws WrongInputData {
        return new OrderResponse(orderRequestToOrder(findOne(id), orderRequest));
    }

    public Long delete(Long id) throws WrongInputData {
        orderRepository.delete(findOne(id));
        return id;
    }

    public Order findOne(Long id) throws WrongInputData {
        return orderRepository.findById(id).orElseThrow(() -> new WrongInputData("Order with id " + id + " not found"));
    }

    private Order orderRequestToOrder(Order order, OrderRequest orderRequest) throws WrongInputData {
        if (order == null) {
            order = new Order();
        }

        order.setAddress(orderRequest.getAddress());
        order.setDateTime(LocalDateTime.now(ZoneId.of(TIME_ZONE)));
        order.setUser(userService.findOne(orderRequest.getUserRequest()));
        final Order orderWithId = orderRepository.save(order);
        order.setGoodsOrders(orderRequest.getGoods().stream().map(e -> goodsOrderRequestToGoodsOrder(e, orderWithId)).collect(Collectors.toList()));
        return orderRepository.saveAndFlush(orderWithId);
    }

    @SneakyThrows
    private GoodsOrder goodsOrderRequestToGoodsOrder(GoodsOrderRequest goodsOrderRequest, Order order) {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setCount(goodsOrderRequest.getCount());
        goodsOrder.setOrder(order);
        goodsOrder.setCount(goodsOrder.getCount());
        goodsOrder.setGoods(goodsService.findOne(goodsOrderRequest.getGoodsId()));
        return goodsOrderRepository.save(goodsOrder);
    }

}
