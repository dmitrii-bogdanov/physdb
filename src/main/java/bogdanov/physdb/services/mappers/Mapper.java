package bogdanov.physdb.services.mappers;

import bogdanov.physdb.database.entities.UserEntity;
import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //region UserDTO <--> UserEntity
    public UserEntity convert(UserRegistrationDTO user) {
        return userMapper.convert(user);
    }

    public UserEntity convert(UserDTO user) {
        return userMapper.convert(user);
    }

    public UserDTO convert(UserEntity user){
        return userMapper.convert(user);
    }
    //endregion

}
