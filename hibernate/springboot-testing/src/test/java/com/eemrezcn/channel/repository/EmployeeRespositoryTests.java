package com.eemrezcn.channel.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.eemrezcn.channel.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRespositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Çalışan ekleme işlemi için JUnit testi
    //@DisplayName("Çalışan ekleme işlemi için JUnit testi")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("eo@gmail,com")
                .build();
        // when - test edilecek işlem veya davranış
        Employee savedEmployee = employeeRepository.save(employee);

        // then - çıktıyı doğrulama
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }


    // Tüm çalışanları getirme işlemi için JUnit testi
    @DisplayName("Tüm çalışanları getirme işlemi için JUnit testi")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("eo@gmail,com")
                .build();

        Employee employee1 = Employee.builder()
                .firstName("Ali")
                .lastName("Ozcan")
                .email("ali@gmail,com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - test edilecek işlem veya davranış
        List<Employee> employeeList = employeeRepository.findAll();

        // then - çıktıyı doğrulama
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    // Çalışanı ID'ye göre getirme işlemi için JUnit testi
    @DisplayName("Çalışanı ID'ye göre getirme işlemi için JUnit testi")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("eo@gmail,com")
                .build();
        employeeRepository.save(employee);

        // when - test edilecek işlem veya davranış
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - çıktıyı doğrulama
        assertThat(employeeDB).isNotNull();
    }

    // Çalışanı e-postaya göre getirme işlemi için JUnit testi
    @DisplayName("Çalışanı e-postaya göre getirme işlemi için JUnit testi")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("eo@gmail,com")
                .build();
        employeeRepository.save(employee);

        // when - test edilecek işlem veya davranış
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - çıktıyı doğrulama
        assertThat(employeeDB).isNotNull();
    }

    // Çalışanı güncelleme işlemi için JUnit testi
    @DisplayName("Çalışanı güncelleme işlemi için JUnit testi")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("eo@gmail,com")
                .build();
        employeeRepository.save(employee);

        // when - test edilecek işlem veya davranış
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("semih@gmail.com");
        savedEmployee.setFirstName("Semih");
        Employee updatedEmployee =  employeeRepository.save(savedEmployee);

        // then - çıktıyı doğrulama
        assertThat(updatedEmployee.getEmail()).isEqualTo("semih@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Semih");
    }

    // Çalışanı silme işlemi için JUnit testi
    @DisplayName("Çalışanı silme işlemi için JUnit testi")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Osman")
                .lastName("Ozcan")
                .email("osman@gmail,com")
                .build();
        employeeRepository.save(employee);

        // when - test edilecek işlem veya davranış
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - çıktıyı doğrulama
        assertThat(employeeOptional).isEmpty();
    }

    // JPQL ile index kullanarak özel sorgu için JUnit testi
    @DisplayName("JPQL ile index kullanarak özel sorgu için JUnit testi")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("emre@gmail,com")
                .build();
        employeeRepository.save(employee);
        String firstName = "Emre";
        String lastName = "Ozcan";

        // when - test edilecek işlem veya davranış
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then - çıktıyı doğrulama
        assertThat(savedEmployee).isNotNull();
    }

    // JPQL ile Named parametreler kullanarak özel sorgu için JUnit testi
    @DisplayName("JPQL ile Named parametreler kullanarak özel sorgu için JUnit testi")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("emre@gmail,com")
                .build();
        employeeRepository.save(employee);
        String firstName = "Emre";
        String lastName = "Ozcan";

        // when - test edilecek işlem veya davranış
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - çıktıyı doğrulama
        assertThat(savedEmployee).isNotNull();
    }

    // Index kullanarak Native SQL ile özel sorgu için JUnit testi
    @DisplayName("Index kullanarak Native SQL ile özel sorgu için JUnit testi")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("emre@gmail,com")
                .build();
        employeeRepository.save(employee);
        // String firstName = "Emre";
        // String lastName = "Ozcan";

        // when - test edilecek işlem veya davranış
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        // then - çıktıyı doğrulama
        assertThat(savedEmployee).isNotNull();
    }

    // Named parametreler kullanarak Native SQL ile özel sorgu için JUnit testi
    @DisplayName("Named parametreler kullanarak Native SQL ile özel sorgu için JUnit testi")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){
        // given - önkoşul veya hazırlık
        Employee employee = Employee.builder()
                .firstName("Emre")
                .lastName("Ozcan")
                .email("emre@gmail,com")
                .build();
        employeeRepository.save(employee);
        // String firstName = "Emre";
        // String lastName = "Ozcan";

        // when - test edilecek işlem veya davranış
        Employee savedEmployee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());

        // then - çıktıyı doğrulama
        assertThat(savedEmployee).isNotNull();
    }

}
