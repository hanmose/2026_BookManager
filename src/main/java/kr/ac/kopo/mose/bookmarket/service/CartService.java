package kr.ac.kopo.mose.bookmarket.service;

import kr.ac.kopo.mose.bookmarket.domain.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
}
