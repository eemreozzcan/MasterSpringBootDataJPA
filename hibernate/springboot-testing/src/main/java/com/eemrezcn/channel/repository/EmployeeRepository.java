package com.eemrezcn.channel.repository;


import com.eemrezcn.channel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Belirtilen e-posta adresine sahip bir Employee varlığını bulur.
     *
     * @param email E-posta adresi.
     * @return Eğer varsa, belirtilen e-posta adresine sahip Employee varlığı; aksi takdirde boş bir Optional.
     */
    Optional<Employee> findByEmail(String email);

    /**
     * İsim ve soyisim kullanarak JPQL sorgusu ile Employee varlığını bulur.
     *
     * @param firstName İsim.
     * @param lastName  Soyisim.
     * @return Eğer varsa, belirtilen isim ve soyisime sahip Employee varlığı; aksi takdirde null.
     */
    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    /**
     * İsim ve soyisim kullanarak adlandırılmış parametrelerle JPQL sorgusu ile Employee varlığını bulur.
     *
     * @param firstName İsim.
     * @param lastName  Soyisim.
     * @return Eğer varsa, belirtilen isim ve soyisime sahip Employee varlığı; aksi takdirde null.
     */
    @Query("select e from Employee e where e.firstName =:firstName and e.lastName =:lastName")
    Employee findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    /**
     * İsim ve soyisim kullanarak Native SQL sorgusu ile Employee varlığını bulur.
     *
     * @param firstName İsim.
     * @param lastName  Soyisim.
     * @return Eğer varsa, belirtilen isim ve soyisime sahip Employee varlığı; aksi takdirde null.
     */
    @Query(value = "select * from employees e where e.first_name =?1 and e.last_name =?2", nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);

    /**
     * İsim ve soyisim kullanarak adlandırılmış parametrelerle Native SQL sorgusu ile Employee varlığını bulur.
     *
     * @param firstName İsim.
     * @param lastName  Soyisim.
     * @return Eğer varsa, belirtilen isim ve soyisime sahip Employee varlığı; aksi takdirde null.
     */
    @Query(value = "select * from employees e where e.first_name =:firstName and e.last_name =:lastName",
            nativeQuery = true)
    Employee findByNativeSQLNamed(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
