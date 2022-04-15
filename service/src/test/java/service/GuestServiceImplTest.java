package service;

import com.zavadski.dao.UserDaoImpl;
import com.zavadski.model.User;
import com.zavadski.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {

    @Mock
    private UserDaoImpl userDao;

    @InjectMocks
    private UserServiceImpl userService;

    private User user = new User();
    private List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        user.setLogin("TEST");
    }

    @Test
    void testFindAllUsers() {

        users.add(user);

        when(userDao.findAll()).thenReturn(users);

        List<User> expectedRoles = userService.findAll();

        assertNotNull(expectedRoles);
        assertEquals(1, expectedRoles.size());
        assertSame(expectedRoles, users);

        verify(userDao, Mockito.times(1)).findAll();
    }

}
