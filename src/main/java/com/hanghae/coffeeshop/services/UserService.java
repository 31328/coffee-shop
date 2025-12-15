package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.domain.pointTransaction.PointTransaction;
import com.hanghae.coffeeshop.domain.pointTransaction.PointTransactionRepository;
import com.hanghae.coffeeshop.domain.user.User;
import com.hanghae.coffeeshop.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PointTransactionRepository pointTransactionRepository;

    public UserService(UserRepository userRepository, PointTransactionRepository pointTransactionRepository) {
        this.userRepository = userRepository;
        this.pointTransactionRepository = pointTransactionRepository;
    }


    public void addPoint(String userId, Integer point) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setPoint(user.getPoint() + point);
            userRepository.save(user);
            PointTransaction pointTransaction = new PointTransaction();
            pointTransaction.setUserSeq(user.getSeq());
            pointTransaction.setType(PointTransaction.TransactionType.INCOME);
            pointTransaction.setPoint(point);
            pointTransactionRepository.save(pointTransaction);
        }
    }
}
