package phattrienungdungvoi2ee.DoAnMonHoc.service;

import java.util.List;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.UserDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;

public interface UserService {
	List<User> getAll();
	User getByUsername(String username);
	User getById(String id);
	User create(UserDTO dto);
	User update(String id, UserDTO dto);
	void delete(String id);
}
