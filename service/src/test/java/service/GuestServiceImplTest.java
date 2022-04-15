package service;

import com.zavadski.dao.RoleDaoImpl;
import com.zavadski.model.Role;
import com.zavadski.service.RoleServiceImpl;
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
    private RoleDaoImpl roleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    private Role role = new Role();
    private List<Role> roles = new ArrayList<>();

    @BeforeEach
    void setUp() {
        role.setName("TEST");
    }

    @Test
    void testFindAllRoles() {

        roles.add(role);

        when(roleDao.findAll()).thenReturn(roles);

        List<Role> expectedRoles = roleService.findAll();

        assertNotNull(expectedRoles);
        assertEquals(1, expectedRoles.size());
        assertSame(expectedRoles, roles);

        verify(roleDao, Mockito.times(1)).findAll();
    }

}
