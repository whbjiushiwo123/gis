package boot.dao;

import boot.model.User2;
import org.springframework.stereotype.Repository;

@Repository
public interface User2Mapper {
    int insert(User2 record);
    User2 selectByPrimaryKey(Integer id);
}
