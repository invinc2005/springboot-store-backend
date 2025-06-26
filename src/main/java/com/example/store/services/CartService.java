package com.example.store.services;

import com.example.store.dtos.CartDto;
import com.example.store.dtos.CartItemDto;
import com.example.store.entities.Cart;
import com.example.store.exceptions.CartNotFoundExceptoin;
import com.example.store.exceptions.ProductNotFoundException;
import com.example.store.mappers.CartMapper;
import com.example.store.repositories.CartRepository;
import com.example.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.catalog.CatalogException;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private ProductRepository productRepository;

    public CartDto createCart() {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartItemDto addToCart(UUID cartId, Long productId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundExceptoin();
        }

        var product  = productRepository.findById(productId).orElse(null);
        if(product == null){
            throw new RuntimeException();
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);
        return cartMapper.toDto(cartItem);
    }
    public CartDto getCart(UUID cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundExceptoin();
        }

        return cartMapper.toDto(cart);
    }

    public CartItemDto updateItem(UUID cartId,Long productId,Integer quantity){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundExceptoin();
        }

        var cartItem = cart.getItem(productId);
        if (cartItem == null) {
            throw new ProductNotFoundException();
        }

        cartItem.setQuantity(quantity);
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public void removeItem(UUID cartId, Long productId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundExceptoin();
        }

        cart.removeItem(productId);

        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundExceptoin();
        }

        cart.clear();

        cartRepository.save(cart);
    }
}

