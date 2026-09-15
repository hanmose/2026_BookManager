package kr.ac.kopo.mose.bookmarket.repository;

import kr.ac.kopo.mose.bookmarket.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
}
