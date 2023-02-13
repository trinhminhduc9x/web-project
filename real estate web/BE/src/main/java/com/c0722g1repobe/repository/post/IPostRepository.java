package com.c0722g1repobe.repository.post;


import com.c0722g1repobe.dto.post.*;
import com.c0722g1repobe.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

    /**
     * Created by: BaoDP
     * Date Created: 03/02/2023
     *
     * @param idAccount
     * @return page post customer
     */
    @Query(value = "select c.id_customer as idCustomer, c.code_customer as codeCustomer  from customer c where c.flag_delete = false and c.account_id_account= :idAccount", nativeQuery = true)
    CustomerGetIdAndCodCustomer getIdCustomerAndCodeCustomer(@Param("idAccount") Long idAccount);

    /**
     * Created by: UyDD
     * Date Created: 31/01/2023
     *
     * @param pageable
     * @return list post customer from database
     */

    @Query(value = "select `post`.* from `post`" +
            " join `direction` on post.direction_id_direction = `direction`.id_direction" +
            " join status_post on post.status_post_id_status_post = status_post.id_status_post" +
            " join `address` on post.address_id_address = `address`.id_address" +
            " join demand_type on post.demand_type_id_demand_type = demand_type.id_demand_type" +
            " join land_type on post.land_type_id_land_type = land_type.id_land_type" +
            " join `customer` on post.customer_id_customer = `customer`.id_customer" +
            " join `account` on customer.account_id_account = `account`.id_account" +
            " where demand_type.name_demand_type like %:nameDemandTypeSearch% and `account`.id_account = :idAccount", nativeQuery = true)
    Page<Post> getAllAndSearchWithRoleCustomer(@Param("nameDemandTypeSearch") String nameDemandTypeSearch,
                                               @Param("idAccount") String idAccount, Pageable pageable);

    /**
     * Created by: UyDD
     * Date Created: 31/01/2023
     *
     * @param pageable
     * @return list post customer from database
     */
    @Query(value = "select `post`.* from `post`" +
            " join direction on post.direction_id_direction = direction.id_direction" +
            " join status_post on post.status_post_id_status_post = status_post.id_status_post" +
            " join address on post.address_id_address = address.id_address" +
            " join demand_type on post.demand_type_id_demand_type = demand_type.id_demand_type" +
            " join land_type on post.land_type_id_land_type = land_type.id_land_type" +
            " join customer on post.customer_id_customer = customer.id_customer" +
            " where demand_type.name_demand_type like %:nameDemandTypeSearch% and customer.id_customer = :idCustomer", nativeQuery = true)
    Page<Post> getAllAndSearchWithRoleAdmin(@Param("nameDemandTypeSearch") String nameDemandTypeSearch, @Param("idCustomer") String idCustomer, Pageable pageable);
    /* Method use: getAll()
     * Get List data of required attributes from the database of related tables(Post,Address,Wards,District,StatusPost)
     * Use interface PostDto
     * Parameter: NO
     * Author: DatTQ
     * */
    @Query(value = "select p.id_post idPost,p.price price,p.date_creation dateCreation, sp.id_status_post statusPost,a.number_address numberAddress,w.name_wards nameWards,d.name_district nameDistrict,c.name_city nameCity,year(p.date_creation) yearPost,month(p.date_creation) monthPost from post p join address a on a.id_address = p.address_id_address join wards w on a.wards_id_wards = w.id_wards join district d on w.district_id_district = d.id_district join city c on d.city_id_city = c.id_city join status_post sp on sp.id_status_post = p.status_post_id_status_post where p.flag_deleted = false order by p.date_creation DESC", nativeQuery = true)
    List<PostDtoViewList> getAll();

    /*Method uses: searchYear(@Param("year") String year)
     * Get List data of required attributes from the database of related tables(Post,Address,Wards,District,StatusPost) together when searching by post year
     * Use interface PostDto
     * Parameter: String year
     * Author: DatTQ*/
    @Query(value = "select p.id_post idPost,p.price price,p.date_creation dateCreation, sp.id_status_post statusPost,a.number_address numberAddress,w.name_wards nameWards,d.name_district nameDistrict,c.name_city nameCity,year(p.date_creation) yearPost,month(p.date_creation) monthPost from post p join address a on a.id_address = p.address_id_address join wards w on a.wards_id_wards = w.id_wards join district d on w.district_id_district = d.id_district join city c on d.city_id_city = c.id_city join status_post sp on sp.id_status_post = p.status_post_id_status_post where p.flag_deleted = false and year(p.date_creation)= :year order by p.date_creation DESC", nativeQuery = true)
    List<PostDtoViewList> searchYear(@Param("year") String year);

    /*Method uses: searchYear(@Param("year") String year,@Param("month") String month )
     * Get List data of required attributes from the database of related tables(Post,Address,Wards,District,StatusPost) together when searching by post year and month
     * Use interface PostDto
     * Parameter: String year,String month
     * Author: DatTQ*/
    @Query(value = "select p.id_post idPost,p.price price,p.date_creation dateCreation, sp.id_status_post statusPost,a.number_address numberAddress,w.name_wards nameWards,d.name_district nameDistrict,c.name_city nameCity,year(p.date_creation) yearPost,month(p.date_creation) monthPost from post p join address a on a.id_address = p.address_id_address join wards w on a.wards_id_wards = w.id_wards join district d on w.district_id_district = d.id_district join city c on d.city_id_city = c.id_city join status_post sp on sp.id_status_post = p.status_post_id_status_post where p.flag_deleted = false and year(p.date_creation)= :year and month(p.date_creation)=:month order by p.date_creation DESC", nativeQuery = true)
    List<PostDtoViewList> searchYearAndMonth(@Param("year") String year, @Param("month") String month);

    /**
     * Method uses:
     * find in database a Post that has and id equal to parameter id, if Post is null or is deleted, return not found http status
     * if Post is found, return Post and OK http status
     * Created by: HuyDN
     * Created date: 31/01/2023
     * Catching NullPointerException
     *
     * @param id: a Post' id
     * @return a Post object that can be showed on Post detail screen
     */
    @Query(value = "select post.name_post               namePost,\n" +
            "       post.id_post,\n" +
            "       post.flag_deleted flagDeleted,\n" +
            "       post.area,\n" +
            "       post.note,\n" +
            "       post.price,\n" +
            "       post.approval,\n" +
            "       post.date_creation           dateCreation,\n" +
            "       direction.name_direction     nameDirection,\n" +
            "       status_post.name_status_post nameStatusPost,\n" +
            "       address.number_address       numberAddress,\n" +
            "       demand_type.id_demand_type idDemandType,\n" +
            "       demand_type.name_demand_type nameDemandType,\n" +
            "       land_type.name_land_type     nameLandType,\n" +
            "       wards.name_wards             nameWards,\n" +
            "       district.name_district       nameDistrict,\n" +
            "       city.name_city               nameCity,\n" +
            "       customer.id_customer       idCustomer,\n" +
            "       customer.name_customer       nameCustomer,\n" +
            "       customer.email_customer      emailCustomer,\n" +
            "       customer.gender_customer     genderCustomer,\n" +
            "       customer.phone_customer1     phoneCustomer1\n" +
            "from post\n" +
            "         join direction on post.direction_id_direction = direction.id_direction\n" +
            "         join status_post on post.status_post_id_status_post = status_post.id_status_post\n" +
            "         join address on post.address_id_address = address.id_address\n" +
            "         join demand_type on post.demand_type_id_demand_type = demand_type.id_demand_type\n" +
            "         join customer on post.customer_id_customer = customer.id_customer\n" +
            "         join wards on address.wards_id_wards = wards.id_wards\n" +
            "         join district on wards.district_id_district = district.id_district\n" +
            "         join city on district.city_id_city = city.id_city\n" +
            "         join land_type on post.land_type_id_land_type = land_type.id_land_type\n" +
            "where post.id_post = :id\n" +
            "  and post.flag_deleted = false\n"
            , nativeQuery = true,
            countQuery = "select post.name_post               namePost,\n" +
                    "       post.id_post,\n" +
                    "       post.flag_deleted flagDeleted,\n" +
                    "       post.area,\n" +
                    "       post.note,\n" +
                    "       post.price,\n" +
                    "       post.approval,\n" +
                    "       post.date_creation           dateCreation,\n" +
                    "       direction.name_direction     nameDirection,\n" +
                    "       status_post.name_status_post nameStatusPost,\n" +
                    "       address.number_address       numberAddress,\n" +
                    "       demand_type.id_demand_type idDemandType,\n" +
                    "       demand_type.name_demand_type nameDemandType,\n" +
                    "       land_type.name_land_type           nameLandType,\n" +
                    "       wards.name_wards             nameWards,\n" +
                    "       district.name_district       nameDistrict,\n" +
                    "       city.name_city               nameCity,\n" +
                    "       customer.id_customer       idCustomer,\n" +
                    "       customer.name_customer       nameCustomer,\n" +
                    "       customer.email_customer      emailCustomer,\n" +
                    "       customer.gender_customer     genderCustomer,\n" +
                    "       customer.phone_customer1     phoneCustomer1\n" +
                    "from post\n" +
                    "         join direction on post.direction_id_direction = direction.id_direction\n" +
                    "         join status_post on post.status_post_id_status_post = status_post.id_status_post\n" +
                    "         join address on post.address_id_address = address.id_address\n" +
                    "         join demand_type on post.demand_type_id_demand_type = demand_type.id_demand_type\n" +
                    "         join customer on post.customer_id_customer = customer.id_customer\n" +
                    "         join wards on address.wards_id_wards = wards.id_wards\n" +
                    "         join district on wards.district_id_district = district.id_district\n" +
                    "         join city on district.city_id_city = city.id_city\n" +
                    "         join land_type on post.land_type_id_land_type = land_type.id_land_type\n" +
                    "where post.id_post = :id\n" +
                    "  and post.flag_deleted = false\n")
    PostDetailDto findPostById(@Param("id") Long id);

    /**
     * Create by : BaoDP
     * Date create: 01/02/2023
     * Description: insert post object into mysql database
     *
     * @param post
     */
    @Modifying
    @Query(value = "insert into post " +
            "(approval, " +
            "area, " +
            "date_creation, " +
            "flag_deleted," +
            "name_post," +
            "note," +
            "price," +
            "address_id_address," +
            "demand_type_id_demand_type," +
            "direction_id_direction," +
            "land_type_id_land_type," +
            "customer_id_customer," +
            "status_post_id_status_post) " +
            "VALUES (" +
            ":#{#post.approval}," +
            ":#{#post.area}," +
            ":#{#post.dateCreation}," +
            ":#{#post.flagDeleted}," +
            ":#{#post.namePost}," +
            ":#{#post.note}," +
            ":#{#post.price}," +
            ":#{#post.address.idAddress}," +
            ":#{#post.demandType.idDemandType}," +
            ":#{#post.direction.idDirection}," +
            ":#{#post.landType.idLandType}," +
            ":#{#post.customer.idCustomer}," +
            ":#{#post.statusPost.idStatusPost})",
            nativeQuery = true)
    @Transactional
    void savePost(@Param("post") Post post);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage
     *
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND sp.id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%') ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND sp.id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%') ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCity(@Param("landType") String landType,
                                                             @Param("direction") String direction,
                                                             @Param("city") String city, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage
     *
     * @param landType
     * @param direction
     * @param city
     * @param minArea
     * @param maxArea
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND p.status_post_id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.area BETWEEN :minArea AND :maxArea ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND p.status_post_id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.area BETWEEN :minArea AND :maxArea ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityArea(@Param("landType") String landType,
                                                                 @Param("direction") String direction,
                                                                 @Param("city") String city,
                                                                 @Param("minArea") Double minArea,
                                                                 @Param("maxArea") Double maxArea, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage
     *
     * @param landType
     * @param direction
     * @param city
     * @param priceMin
     * @param priceMax
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND p.status_post_id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND p.status_post_id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityPrice(@Param("landType") String landType,
                                                                  @Param("direction") String direction,
                                                                  @Param("city") String city,
                                                                  @Param("priceMin") Double priceMin,
                                                                  @Param("priceMax") Double priceMax, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage
     * @param landType
     * @param direction
     * @param city
     * @param minArea
     * @param maxArea
     * @param priceMin
     * @param priceMax
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND sp.id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.area BETWEEN :minArea AND :maxArea\n" +
            "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND sp.id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.area BETWEEN :minArea AND :maxArea\n" +
                    "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityAreaPrice(@Param("landType") String landType,
                                                                      @Param("direction") String direction,
                                                                      @Param("city") String city,
                                                                      @Param("minArea") Double minArea,
                                                                      @Param("maxArea") Double maxArea,
                                                                      @Param("priceMin") Double priceMin,
                                                                      @Param("priceMax") Double priceMax, Pageable pageable);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: delete post
     *
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE post" +
            " SET flag_deleted = true" +
            " WHERE id_post = :id",
            nativeQuery = true)
    @Transactional
    void deletePost(@Param("id") Long id);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: find Post by id
     *
     * @param id
     * @return object Post
     */
    @Query(value = "SELECT * " +
            "FROM post " +
            "WHERE flag_deleted = false and id_post = :id",
            nativeQuery = true)
    Post findPost(@Param("id") Long id);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: search all Post
     *
     * @param demandTypeSearch
     * @param lendTypeSearch
     * @param pageable
     * @return json list Posts searched
     */

    @Query(value = "select p.id_post as idPost, c.code_customer as codeCustomer, d.name_demand_type as demandType," +
            " l.name_land_type as landType, ct.name_city as city , ds.name_district as district, w.name_wards as wards," +
            " p.area as area, p.note as note, p.approval as approval," +
            " p.price as price from post p " +
            "left join customer as c on p.customer_id_customer = c.id_customer " +
            "left join demand_type as d on p.demand_type_id_demand_type = d.id_demand_type " +
            "left join land_type as l on l.id_land_type = p.land_type_id_land_type " +
            "left join address as a on a.id_address = p.address_id_address " +
            "left join wards as w on a.wards_id_wards = w.id_wards " +
            "left join district as ds on w.district_id_district = ds.id_district " +
            "left join city as ct on ds.city_id_city = ct.id_city  where p.flag_deleted = false " +
            "and (d.name_demand_type like concat('%', :demandTypeSearch, '%') " +
            "and  l.name_land_type like concat('%', :lendTypeSearch, '%') " +
            "and (p.price between :minPriceSearch and :maxPriceSearch) " +
            "and (p.area between :minAreaSearch and :maxAreaSearch) " +
            "and ct.id_city like concat('%', :citySearch, '%')  " +
            "and ds.id_district like concat('%', :districtSearch, '%') " +
            "and w.id_wards like concat('%', :wardsSearch, '%')) order by p.approval asc, p.date_creation desc",
            countQuery = "select p.id_post as idPost, c.code_customer as codeCustomer," +
                    " d.name_demand_type as demandType, l.name_land_type as landType," +
                    " ct.name_city as city , ds.name_district as district, w.name_wards as wards, p.area as area, p.note as note," +
                    " p.approval as approval, p.price as price from post p " +
                    "left join customer as c on p.customer_id_customer = c.id_customer " +
                    "left join demand_type as d on p.demand_type_id_demand_type = d.id_demand_type " +
                    "left join land_type as l on l.id_land_type = p.land_type_id_land_type " +
                    "left join address as a on a.id_address = p.address_id_address " +
                    "left join wards as w on a.wards_id_wards = w.id_wards " +
                    "left join district as ds on w.district_id_district = ds.id_district " +
                    "left join city as ct on ds.city_id_city = ct.id_city  " +
                    "where p.flag_deleted = false " +
                    "and (d.name_demand_type like concat('%', :demandTypeSearch, '%') " +
                    "and  l.name_land_type like concat('%', :lendTypeSearch, '%') " +
                    "and (p.price between :minPriceSearch and :maxPriceSearch) " +
                    "and (p.area between :minAreaSearch and :maxAreaSearch) " +
                    "and ct.id_city like concat('%', :citySearch, '%')  " +
                    "and ds.id_district like concat('%', :districtSearch, '%') " +
                    "and w.id_wards like concat('%', :wardsSearch, '%')) order by p.approval asc, p.date_creation desc",
            nativeQuery = true)
    Page<PostDto> searchAllPost(@Param("demandTypeSearch") String demandTypeSearch, @Param("lendTypeSearch") String lendTypeSearch, @Param("minPriceSearch") Double minPriceSearch, @Param("maxPriceSearch") Double maxPriceSearch, @Param("citySearch") String citySearch,
                                @Param("districtSearch") String districtSearch,@Param("wardsSearch") String wardsSearch, @Param("minAreaSearch") Double minAreaSearch,@Param("maxAreaSearch") Double maxAreaSearch, Pageable pageable);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: find all Post
     *
     * @param pageable
     * @return json list Posts searched
     */

    @Query(value = "select p.id_post as idPost, c.code_customer as codeCustomer, " +
            "d.name_demand_type as demandType, l.name_land_type as landType, " +
            "ct.name_city as city , ds.name_district as district, w.name_wards as wards, p.area as area, p.note as note, " +
            "p.approval as approval, p.price as price " +
            "from post p " +
            "left join customer as c on p.customer_id_customer = c.id_customer " +
            "left join demand_type as d on p.demand_type_id_demand_type = d.id_demand_type " +
            "left join land_type as l on l.id_land_type = p.land_type_id_land_type " +
            "left join address as a on a.id_address = p.address_id_address " +
            "left join wards as w on a.wards_id_wards = w.id_wards " +
            "left join district as ds on w.district_id_district = ds.id_district " +
            "left join city as ct on ds.city_id_city = ct.id_city  where p.flag_deleted = false order by p.approval asc, p.date_creation desc",
            countQuery = "select p.id_post as idPost, c.code_customer as codeCustomer, " +
                    "d.name_demand_type as demandType, l.name_land_type as landType, " +
                    "ct.name_city as city , ds.name_district as district, w.name_wards as wards, p.area as area, p.note as note, " +
                    "p.approval as approval, p.price as price from post p " +
                    "left join customer as c on p.customer_id_customer = c.id_customer " +
                    "left join demand_type as d on p.demand_type_id_demand_type = d.id_demand_type " +
                    "left join land_type as l on l.id_land_type = p.land_type_id_land_type " +
                    "left join address as a on a.id_address = p.address_id_address " +
                    "left join wards as w on a.wards_id_wards = w.id_wards " +
                    "left join district as ds on w.district_id_district = ds.id_district " +
                    "left join city as ct on ds.city_id_city = ct.id_city  where p.flag_deleted = false order by p.approval asc, p.date_creation desc",
            nativeQuery = true)
    Page<PostDto> findAllPost(Pageable pageable);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: Approval post
     *
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE post" +
            " SET approval = true" +
            " WHERE id_post = :id",
            nativeQuery = true)
    @Transactional
    void approvalPost(@Param("id") Long id);

    /**
     * Method uses:
     * Set post's status to succeed when post's owner click on transaction succeed confirmation button
     * Created by: HuyDN
     * Created date: 04/02/2023
     * Catching NullPointerException
     *
     * @param id: a Post' id
     * @return HttpStatus
     */
    @Modifying
    @Query(value = "update post set post.status_post_id_status_post = 1 where post.id_post = :id", nativeQuery = true)
    @Transactional
    void succeedConfirm(@Param("id") Long id);

    /**
     * Create by: BaoDP
     * Date created: 07/02/2023
     * Description: get last id of post created
     *
     * @return last id of post created
     */
    @Query(value = "SELECT LAST_INSERT_ID()",nativeQuery = true)
    Long getLastInsertId();

     /** Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for sell
     *
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 2\n" +
            "  AND sp.id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%') ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 2\n" +
                    "  AND sp.id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%') ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCitySell(@Param("landType") String landType,
                                                             @Param("direction") String direction,
                                                             @Param("city") String city, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for sell
     *
     * @param landType
     * @param direction
     * @param city
     * @param minArea
     * @param maxArea
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 2\n" +
            "  AND p.status_post_id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.area BETWEEN :minArea AND :maxArea ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 2\n" +
                    "  AND p.status_post_id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.area BETWEEN :minArea AND :maxArea ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityAreaSell(@Param("landType") String landType,
                                                                 @Param("direction") String direction,
                                                                 @Param("city") String city,
                                                                 @Param("minArea") Double minArea,
                                                                 @Param("maxArea") Double maxArea, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for sell
     *
     * @param landType
     * @param direction
     * @param city
     * @param priceMin
     * @param priceMax
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 2\n" +
            "  AND p.status_post_id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 2\n" +
                    "  AND p.status_post_id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityPriceSell(@Param("landType") String landType,
                                                                  @Param("direction") String direction,
                                                                  @Param("city") String city,
                                                                  @Param("priceMin") Double priceMin,
                                                                  @Param("priceMax") Double priceMax, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for sell
     * @param landType
     * @param direction
     * @param city
     * @param minArea
     * @param maxArea
     * @param priceMin
     * @param priceMax
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 2\n" +
            "  AND sp.id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.area BETWEEN :minArea AND :maxArea\n" +
            "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 2\n" +
                    "  AND sp.id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.area BETWEEN :minArea AND :maxArea\n" +
                    "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityAreaPriceSell(@Param("landType") String landType,
                                                                      @Param("direction") String direction,
                                                                      @Param("city") String city,
                                                                      @Param("minArea") Double minArea,
                                                                      @Param("maxArea") Double maxArea,
                                                                      @Param("priceMin") Double priceMin,
                                                                      @Param("priceMax") Double priceMax, Pageable pageable);


    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for Buy
     *
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 1\n" +
            "  AND sp.id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%') ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 1\n" +
                    "  AND sp.id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%') ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityBuy(@Param("landType") String landType,
                                                                 @Param("direction") String direction,
                                                                 @Param("city") String city, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for Buy
     *
     * @param landType
     * @param direction
     * @param city
     * @param minArea
     * @param maxArea
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 1\n" +
            "  AND p.status_post_id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.area BETWEEN :minArea AND :maxArea ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 1\n" +
                    "  AND p.status_post_id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.area BETWEEN :minArea AND :maxArea ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityAreaBuy(@Param("landType") String landType,
                                                                     @Param("direction") String direction,
                                                                     @Param("city") String city,
                                                                     @Param("minArea") Double minArea,
                                                                     @Param("maxArea") Double maxArea, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for Buy
     *
     * @param landType
     * @param direction
     * @param city
     * @param priceMin
     * @param priceMax
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 1\n" +
            "  AND p.status_post_id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 1\n" +
                    "  AND p.status_post_id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityPriceBuy(@Param("landType") String landType,
                                                                      @Param("direction") String direction,
                                                                      @Param("city") String city,
                                                                      @Param("priceMin") Double priceMin,
                                                                      @Param("priceMax") Double priceMax, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for Buy
     * @param landType
     * @param direction
     * @param city
     * @param minArea
     * @param maxArea
     * @param priceMin
     * @param priceMax
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 1\n" +
            "  AND sp.id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.area BETWEEN :minArea AND :maxArea\n" +
            "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 1\n" +
                    "  AND sp.id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.area BETWEEN :minArea AND :maxArea\n" +
                    "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityAreaPriceBuy(@Param("landType") String landType,
                                                                          @Param("direction") String direction,
                                                                          @Param("city") String city,
                                                                          @Param("minArea") Double minArea,
                                                                          @Param("maxArea") Double maxArea,
                                                                          @Param("priceMin") Double priceMin,
                                                                          @Param("priceMax") Double priceMax, Pageable pageable);
    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for Rent
     *
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 3\n" +
            "  AND sp.id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%') ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 3\n" +
                    "  AND sp.id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%') ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityRent(@Param("landType") String landType,
                                                                @Param("direction") String direction,
                                                                @Param("city") String city, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for Rent
     *
     * @param landType
     * @param direction
     * @param city
     * @param minArea
     * @param maxArea
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 3\n" +
            "  AND p.status_post_id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.area BETWEEN :minArea AND :maxArea ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 3\n" +
                    "  AND p.status_post_id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.area BETWEEN :minArea AND :maxArea ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityAreaRent(@Param("landType") String landType,
                                                                    @Param("direction") String direction,
                                                                    @Param("city") String city,
                                                                    @Param("minArea") Double minArea,
                                                                    @Param("maxArea") Double maxArea, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for Rent
     *
     * @param landType
     * @param direction
     * @param city
     * @param priceMin
     * @param priceMax
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 3\n" +
            "  AND p.status_post_id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 3\n" +
                    "  AND p.status_post_id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityPriceRent(@Param("landType") String landType,
                                                                     @Param("direction") String direction,
                                                                     @Param("city") String city,
                                                                     @Param("priceMin") Double priceMin,
                                                                     @Param("priceMax") Double priceMax, Pageable pageable);

    /**
     * Create by : SangNP
     * Date create: 01/02/2023
     * Description: take post list homepage for Rent
     * @param landType
     * @param direction
     * @param city
     * @param minArea
     * @param maxArea
     * @param priceMin
     * @param priceMax
     * @param pageable
     * @return Page<PostListViewDto>
     */
    @Query(value = "SELECT p.id_post as idPost,\n" +
            "       p.name_post as namePost,\n" +
            "       p.price,\n" +
            "       p.area,\n" +
            "       p.demand_type_id_demand_type as idDemandType,\n" +
            "       lt.name_land_type as nameLandType,\n" +
            "       d.name_direction as nameDirection,\n" +
            "       d2.name_district as district,\n" +
            "       c2.name_city     as city,\n" +
            "       p.date_creation as dateCreation\n" +
            "FROM post p\n" +
            "         JOIN address a on a.id_address = p.address_id_address\n" +
            "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
            "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
            "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
            "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
            "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
            "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
            "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
            "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
            "WHERE p.flag_deleted = false\n" +
            "  AND p.approval = true\n" +
            "  AND dt.id_demand_type = 3\n" +
            "  AND sp.id_status_post = 2\n" +
            "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
            "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
            "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
            "  AND p.area BETWEEN :minArea AND :maxArea\n" +
            "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc",
            nativeQuery = true,
            countQuery = "SELECT p.id_post as idPost,\n" +
                    "       p.name_post as namePost,\n" +
                    "       p.price,\n" +
                    "       p.area,\n" +
                    "       p.demand_type_id_demand_type as idDemandType,\n" +
                    "       lt.name_land_type as nameLandType,\n" +
                    "       d.name_direction as nameDirection,\n" +
                    "       d2.name_district as district,\n" +
                    "       c2.name_city     as city,\n" +
                    "       p.date_creation as dateCreation\n" +
                    "FROM post p\n" +
                    "         JOIN address a on a.id_address = p.address_id_address\n" +
                    "         JOIN customer c on c.id_customer = p.customer_id_customer\n" +
                    "         JOIN demand_type dt on dt.id_demand_type = p.demand_type_id_demand_type\n" +
                    "         JOIN direction d on d.id_direction = p.direction_id_direction\n" +
                    "         JOIN land_type lt on lt.id_land_type = p.land_type_id_land_type\n" +
                    "         JOIN status_post sp on sp.id_status_post = p.status_post_id_status_post\n" +
                    "         JOIN wards w on w.id_wards = a.wards_id_wards\n" +
                    "         JOIN district d2 on d2.id_district = w.district_id_district\n" +
                    "         JOIN city c2 on c2.id_city = d2.city_id_city\n" +
                    "WHERE p.flag_deleted = false\n" +
                    "  AND p.approval = true\n" +
                    "  AND dt.id_demand_type = 3\n" +
                    "  AND sp.id_status_post = 2\n" +
                    "  AND lt.name_land_type LIKE CONCAT('%', :landType, '%')\n" +
                    "  AND d.name_direction LIKE CONCAT('%', :direction, '%')\n" +
                    "  AND c2.name_city LIKE CONCAT('%', :city, '%')\n" +
                    "  AND p.area BETWEEN :minArea AND :maxArea\n" +
                    "  AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.id_post desc")
    Page<PostListViewDto> findAllWithDemandTypeDirectionCityAreaPriceRent(@Param("landType") String landType,
                                                                         @Param("direction") String direction,
                                                                         @Param("city") String city,
                                                                         @Param("minArea") Double minArea,
                                                                         @Param("maxArea") Double maxArea,
                                                                         @Param("priceMin") Double priceMin,
                                                                         @Param("priceMax") Double priceMax, Pageable pageable);

    /**
     * createdBy: HuyDN
     *
     * @param id: number
     * @return: account
     */

    @Query(value = "select customer.account_id_account from customer where customer.id_customer = :id",
            nativeQuery = true,
            countQuery = "select customer.account_id_account from customer where customer.id_customer = :id")
    Long getIdAccountByIdCustomer(@Param("id") Long id);
}
