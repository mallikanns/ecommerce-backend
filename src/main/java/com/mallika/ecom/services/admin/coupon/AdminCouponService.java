package com.mallika.ecom.services.admin.coupon;

import com.mallika.ecom.entity.Coupon;

import java.util.List;

public interface AdminCouponService {
    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();
}
