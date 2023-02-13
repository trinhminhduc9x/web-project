package com.c0722g1repobe.service.post;

import com.c0722g1repobe.dto.post.*;

import java.util.List;

import com.c0722g1repobe.dto.post.create_post.BaseResponseCreatePost;
import com.c0722g1repobe.dto.post.create_post.CreatePostDto;
import org.springframework.data.repository.query.Param;
import com.c0722g1repobe.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService {

    /**
     * Created by: BaoDP
     * Date Created: 03/022023
     *
     * @param idAccount
     * @return page post customer
     */
    CustomerGetIdAndCodCustomer getIdCustomerAndCodeCustomer(@Param("idAccount") Long idAccount);

    /**
     * Created by: UyDD
     * Date Created: 31/01/2023
     *
     * @param pageable
     * @return page post customer
     */

    Page<Post> getAllAndSearchWithRoleAdmin(String nameDemandTypeSearch, String idCustomer, Pageable pageable);
    Page<Post> getAllAndSearchWithRoleCustomer(String nameDemandTypeSearch, String idAccount, Pageable pageable);

    /*Method use: getAll()
     * Get List data of required attributes
     * Use interface PostDto
     * Parameter: NO
     * Author: DatTQ
     * */
    List<PostDtoViewList> getAll();

    /*Method uses: searchYear(String year)
     * Get List data of required attributes
     * Use interface PostDto
     * Parameter: String year
     * Author: DatTQ*/
    List<PostDtoViewList> searchYear(String year);

    /*Method uses: searchYear(@Param("year") String year,@Param("month") String month )
     * Get List data of required attributes
     * Use interface PostDto
     * Parameter: String year,String month
     * Author: DatTQ*/
    List<PostDtoViewList> searchYearAndMonth(String year, String month);

    /**
     * Create by: BaoDP
     * Date Create: 01/02/2023
     * Description: if createPostDto is valid then save Post before send BaseResponseCreatePost to Front-end project for handle http status code .
     *
     * @param createPostDto : an object of class CreatePostDto
     * @return an object of class BaseResponseCreatePost
     */
    BaseResponseCreatePost getResponseCreatePost(CreatePostDto createPostDto);

    /**
     * Method uses:
     * find all list posts for homepage
     * Created by: SangNP
     * Created date: 31/01/2023
     *
     * @param area
     * @param price
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return Page<PostListViewDto> and null if not found
     */
    Page<PostListViewDto> findAll(String area, String price, String landType, String direction, String city, Pageable pageable);

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
    PostDetailDto findPostById(@Param("id") Long id);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: delete post
     *
     * @param idPost
     */
    void deletePost(Long idPost);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: search post
     *
     * @param id
     * @return json  post
     */
    Post findPost(Long id);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: search list posts
     *
     * @param demandTypeSearch
     * @param lendTypeSearch
     * @param pageable
     * @param minPriceSearch
     * @param maxPriceSearch
     * @param citySearch
     * @param districtSearch
     * @param wardsSearch
     * @return json list posts
     */
    Page<PostDto> searchAllPost(String demandTypeSearch, String lendTypeSearch, Double minPriceSearch, Double maxPriceSearch,
                                String citySearch, String districtSearch, String wardsSearch, Double minAreSearch, Double maxAreSearch, Pageable pageable);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: show list posts
     *
     * @param pageable
     * @return json list posts
     */
    Page<PostDto> findAllPost(Pageable pageable);

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: approval post
     *
     * @param id
     */
    void approvalPost(Long id);

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
    void succeedConfirm(@Param("id") Long id);

    /**
     * Method uses:
     * find all list posts for sell of homepage
     * Created by: SangNP
     * Created date: 31/01/2023
     *
     * @param area
     * @param price
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return Page<PostListViewDto> and null if not found
     */
    Page<PostListViewDto> findAllSell(String area, String price, String landType, String direction, String city, Pageable pageable);
    /**
     * Method uses:
     * find all list posts for buy of homepage
     * Created by: SangNP
     * Created date: 31/01/2023
     *
     * @param area
     * @param price
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return Page<PostListViewDto> and null if not found
     */
    Page<PostListViewDto> findAllBuy(String area, String price, String landType, String direction, String city, Pageable pageable);
    /**
     * Method uses:
     * find all list posts for rent of homepage
     * Created by: SangNP
     * Created date: 31/01/2023
     *
     * @param area
     * @param price
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return Page<PostListViewDto> and null if not found
     */
    Page<PostListViewDto> findAllRent(String area, String price, String landType, String direction, String city, Pageable pageable);

    /**
     * created by HuyDN
     *
     * @param id: Long
     * @return
     */
    Long getIdAccountByIdCustomer(@Param("id") Long id);
}
