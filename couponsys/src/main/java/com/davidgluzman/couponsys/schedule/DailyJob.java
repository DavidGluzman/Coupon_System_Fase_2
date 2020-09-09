package com.davidgluzman.couponsys.schedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.davidgluzman.couponsys.beans.Coupon;

import com.davidgluzman.couponsys.service.services.CouponService;

@Component
public class DailyJob implements Runnable{

	private boolean quit = false;

    @Autowired
    private CouponService couponService;

public DailyJob() {
    super();
}



@Scheduled(fixedDelay = 1000*10)
@Override
public void run() {
    while (!quit) {
    	System.out.println("DailyJob woke up...");
        List<Coupon> coupons = couponService.getAllCoupons();
        for (Coupon c : coupons) {
            if (c.getEndDate().before(new Date(System.currentTimeMillis()))) {
                couponService.deleteCoupon(c.getId());
                System.out.println("DailyJob deleted coupon #"+c.getId());
            }
        }
        try {

        	System.out.println("DailyJob going to sleep...");
            Thread.sleep(1000*10);
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
    }
}

public void stopJob() {
    quit = true;
}
}
