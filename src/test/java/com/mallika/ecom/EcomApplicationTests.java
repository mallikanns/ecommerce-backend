package com.mallika.ecom;

import com.mallika.ecom.dto.CartItemsDto;
import com.mallika.ecom.entity.CartItems;
import com.mallika.ecom.entity.Product;
import com.mallika.ecom.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EcomApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		List<CartItems> cartItemsList = new ArrayList<>();
		CartItems cartItems = new CartItems();
		CartItems cartItems2 = new CartItems();
		CartItems cartItems3 = new CartItems();
		cartItems2.setQuantity(5L);
		cartItems3.setQuantity(6L);
		cartItems.setProduct(new Product());
		cartItems2.setProduct(new Product());
		cartItems3.setProduct(new Product());
		cartItems.setUser(new User());
		cartItems2.setUser(new User());
		cartItems3.setUser(new User());
		cartItemsList.add(cartItems);
		cartItemsList.add(cartItems2);
		cartItemsList.add(cartItems3);
		Optional<List<CartItems>> test = Optional.of(cartItemsList);
		Optional<List<CartItemsDto>> res = test.map(cartList -> {
			return cartList.stream().map(cart -> cart.getCartDto()).collect(Collectors.toList());
		});
		if (res.isPresent()){
			System.out.println(res.get());
		}

	}

}
