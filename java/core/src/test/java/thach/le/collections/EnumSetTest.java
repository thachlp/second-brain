package thach.le.collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

class EnumSetTest {
    @Test
    void testAllOf() {
        final EnumSet<EmployeeType> employeeTypes = EnumSet.allOf(EmployeeType.class);
        Assertions.assertThat(employeeTypes)
                .hasSize(3)
                .contains(EmployeeType.CONTRACTOR)
                .contains(EmployeeType.FREELANCE)
                .contains(EmployeeType.PERMANENT);
    }

    @Test
    void testNoneOf() {
        final EnumSet<EmployeeType> employeeTypes = EnumSet.noneOf(EmployeeType.class);
        Assertions.assertThat(employeeTypes)
                .isEmpty();
        employeeTypes.add(EmployeeType.valueOf("CONTRACTOR"));
        Assertions.assertThat(employeeTypes).contains(EmployeeType.valueOf("CONTRACTOR"));
    }
}
