package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.PointTransactionDto;
import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.entity.PointTransactionEntity;
import com.hanghae.coffeeshop.entity.UserEntity;
import com.hanghae.coffeeshop.enumerated.TransactionType;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.PointTransactionRepository;
import com.hanghae.coffeeshop.repositories.UserRepository;
import com.hanghae.coffeeshop.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TempConverter tempConverter;
    @Autowired
    private PointTransactionRepository pointTransactionRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long userId) {
        UserDto returnValue = null;
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            returnValue = tempConverter.userEntityToDto(userEntity);
        } else {
            throw new InstanceUndefinedException(
                    "The user has not being found with id: " + userId
            );
        }
        return returnValue;
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        getUser(userId);
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        UserEntity addUserEntity = userRepository.save(tempConverter.userDtoToEntity(userDto));
        return tempConverter.userEntityToDto(addUserEntity);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, Long userId) {
        UserDto currentUserDto = getUser(userId);
        userDto.setId(currentUserDto.getId());
        UserEntity saveUserEntity = userRepository.saveAndFlush(tempConverter.userDtoToEntity(userDto));
        return tempConverter.userEntityToDto(saveUserEntity);
    }

    @Override
    @Transactional
    public void addPoints(Long userId, Integer points) {
        UserDto userDto = getUser(userId);
        Integer currentPoints =  userDto.getPoints();
        userDto.setPoints(currentPoints + points);
        userRepository.save(tempConverter.userDtoToEntity(userDto));
        PointTransactionDto pointsTransactionDto = new PointTransactionDto();
        pointsTransactionDto.setPoints(currentPoints);
        pointsTransactionDto.setUserId(userId);
        PointTransactionEntity pointTransactionEntity = tempConverter.pointsDtoToEntity(pointsTransactionDto);
        pointTransactionEntity.setType(TransactionType.INCOME);
        pointTransactionRepository.saveAndFlush(pointTransactionEntity);
    }

    @Override
    @Transactional
    public void deletePoints(Long userId, Integer points) {
        UserDto userDto = getUser(userId);
        Integer currentPoints =  userDto.getPoints();
        userDto.setPoints(currentPoints - points);
        userRepository.save(tempConverter.userDtoToEntity(userDto));
        PointTransactionDto pointsTransactionDto = new PointTransactionDto();
        pointsTransactionDto.setPoints(currentPoints);
        pointsTransactionDto.setUserId(userId);
        PointTransactionEntity pointTransactionEntity = tempConverter.pointsDtoToEntity(pointsTransactionDto);
        pointTransactionEntity.setType(TransactionType.EXPENSE);
        pointTransactionRepository.saveAndFlush(pointTransactionEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> listAllUsers() {
        List<UserDto> returnValue = new ArrayList<>();
        List<UserEntity> userEntityList = userRepository.findAll();
        for (UserEntity userEntity : userEntityList) {
            returnValue.add(tempConverter.userEntityToDto(userEntity));
        }
        return returnValue;
    }
    @PostConstruct
    private void loadData(){
        List<UserDto> users = List.of(
                new UserDto("pass001", 120),
                new UserDto("pass002", 340),
                new UserDto("pass003", 560),
                new UserDto("pass004", 90),
                new UserDto("pass005", 780),
                new UserDto("pass006", 230),
                new UserDto("pass007", 410),
                new UserDto("pass008", 150),
                new UserDto("pass009", 620),
                new UserDto("pass010", 50),
                new UserDto("pass011", 980),
                new UserDto("pass012", 310),
                new UserDto("pass013", 470),
                new UserDto("pass014", 860),
                new UserDto("pass015", 200),
                new UserDto("pass016", 730),
                new UserDto("pass017", 640),
                new UserDto("pass018", 90),
                new UserDto("pass019", 510),
                new UserDto("pass020", 300),
                new UserDto("pass021", 880),
                new UserDto("pass022", 140),
                new UserDto("pass023", 260),
                new UserDto("pass024", 990),
                new UserDto("pass025", 430),
                new UserDto("pass026", 670),
                new UserDto("pass027", 80),
                new UserDto("pass028", 720),
                new UserDto("pass029", 360),
                new UserDto("pass030", 540),
                new UserDto("pass031", 910),
                new UserDto("pass032", 190),
                new UserDto("pass033", 450),
                new UserDto("pass034", 610),
                new UserDto("pass035", 770),
                new UserDto("pass036", 240),
                new UserDto("pass037", 880),
                new UserDto("pass038", 130),
                new UserDto("pass039", 500),
                new UserDto("pass040", 660)
        );
        List<UserEntity> userEntityList = userRepository.findAll();
        if(userEntityList.isEmpty()){

            for (Iterator<UserDto> iterator = users.iterator();iterator.hasNext();) {
                UserDto userDto = iterator.next();
            userEntityList.add(tempConverter.userDtoToEntity(userDto));
            }
            userRepository.saveAll(userEntityList);
        }
    }
}
