package com.blazenn.ecommerce.service;

import com.blazenn.ecommerce.domain.Order;
import com.blazenn.ecommerce.domain.User;
import com.blazenn.ecommerce.repository.OrderRepository;
import com.blazenn.ecommerce.service.dto.OrderDTO;
import com.blazenn.ecommerce.service.dto.UserDTO;
import com.blazenn.ecommerce.service.mapper.OrderMapper;
import com.blazenn.ecommerce.service.mapper.UserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Order}.
 */
@Service
@Transactional
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final UserMapper userMapper;

    private final UserService userService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserService userService, UserMapper userMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    /**
     * Save a order.
     *
     * @param orderDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderDTO save(OrderDTO orderDTO) {
        UserDTO user = userService.getUserWithAuthorities()
        .map(UserDTO::new)
        .orElseThrow(() -> new AccountResourceException("User could not be found"));
        log.debug("Dataa of userDTO in orders: {}", user);
        User userEntity = userMapper.userDTOToUser(user);
        log.debug("Request to save Order : {}", orderDTO);
        Order order = orderMapper.toEntity(orderDTO);
        order.setUser(userEntity);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    /**
     * Get all the orders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Orders");
        return orderRepository.findAll(pageable)
            .map(orderMapper::toDto);
    }


    /**
     * Get one order by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderDTO> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id)
            .map(orderMapper::toDto);
    }

    /**
     * Delete the order by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }
}
