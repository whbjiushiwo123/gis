package boot.dao;

import boot.model.User1;
import org.springframework.stereotype.Repository;

@Repository
public interface User1Mapper {
    int insert(User1 record);
    User1 selectByPrimaryKey(Integer id);
}
