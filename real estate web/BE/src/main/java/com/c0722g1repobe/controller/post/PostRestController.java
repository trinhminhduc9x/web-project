package com.c0722g1repobe.controller.post;

import com.c0722g1repobe.dto.post.*;
import com.c0722g1repobe.entity.post.Post;
import com.c0722g1repobe.dto.post.PostDto;
import com.c0722g1repobe.dto.post.create_post.BaseResponseCreatePost;
import com.c0722g1repobe.dto.post.create_post.CreatePostDto;
import com.c0722g1repobe.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/post")
public class PostRestController {
    @Autowired
    private IPostService postService;

    /**
     * Created by: UyDD
     * Date Created: 31/01/2023
     *
     * @param pageable
     * @return HttpStatus.NO_CONTENT if list post is empty or HttpStatus.OK if result have content
     */

    @GetMapping("search-page-admin")
    public ResponseEntity<?> getAllAndSearchWithRoleAdmin( @RequestParam String nameDemandTypeSearch, @RequestParam String idCustomer, @PageableDefault(value = 4) Pageable pageable) {
        Page<Post> postList = postService.getAllAndSearchWithRoleAdmin(nameDemandTypeSearch, idCustomer, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("search-page-customer")
    public ResponseEntity<?> getAllAndSearchWithRoleCustomer(@PageableDefault(value = 4) Pageable pageable, @RequestParam String nameDemandTypeSearch, @RequestParam String idAccount) {
        Page<Post> postList = postService.getAllAndSearchWithRoleCustomer(nameDemandTypeSearch, idAccount, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    /**
     * Method use: displayList(), call getAll() of IPostService to get list data from database
     * Use ResponseEntity to handling response, datatype: List<PostDtoViewList>
     * Parameter: NO
     * If the list returned is an empty list, return http status code : HttpStatus.NO_CONTENT
     * If the list returned is a list with data, then return http status code: HttpStatus.OK and List<PostDtoViewList>
     * Author: DatTQ ; Date create: 31/01/2022
     */
    @GetMapping("/charts")
    public ResponseEntity<List<PostDtoViewList>> displayList() {
        List<PostDtoViewList> postDtoViewListList = postService.getAll();
        if (postDtoViewListList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postDtoViewListList, HttpStatus.OK);
    }

    /**
     * Method use: search(), call searchYear() and searchYearAndMonth() of IPostService to get list data from database
     * Use ResponseEntity to handling response, datatype: List<PostDtoViewList>
     * Parameter: Integer year (defaultValue = "-1"), Integer month (defaultValue = "-1")
     * If parameter month is == -1, List<PostDtoViewList> = method searchYear of IPostService
     * If parameter year is != -1 and month != -1 => List<PostDtoViewList> = method searchYearAndMonth of IPostService
     * If parameter year is == -1 and month != -1 => assign 2 parameters year and month = current year and current month
     * => List<PostDtoViewList> = method searchYearAndMonth of IPostService
     * If the list returned is an empty list, return http status code : HttpStatus.NO_CONTENT
     * If the list returned is a list with data, then return http status code: HttpStatus.OK and List<PostDtoViewList>
     * Author: DatTQ ; Date create: 31/01/2022
     */
    @GetMapping("/charts-search")
    public ResponseEntity<List<PostDtoViewList>> search(@RequestParam(defaultValue = "-1") Integer year, @RequestParam(defaultValue = "-1") Integer month) {
        List<PostDtoViewList> postDtoViewListList = postService.searchYearAndMonth(String.valueOf(year), String.valueOf(month));
        if (month == -1 && year == -1) {
            postDtoViewListList = postService.getAll();
        }
        if (month == -1 && year != -1) {
            postDtoViewListList = postService.searchYear(String.valueOf(year));
        }
        if (month != -1 && year == -1) {
            LocalDate date = LocalDate.now();
            month = date.getMonthValue();
            year = LocalDate.now().getYear();
            postDtoViewListList = postService.searchYearAndMonth(String.valueOf(year), String.valueOf(month));
        }
        if (month != -1 && year != -1) {
            postDtoViewListList = postService.searchYearAndMonth(String.valueOf(year), String.valueOf(month));
        }
        if (postDtoViewListList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postDtoViewListList, HttpStatus.OK);
    }

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
    @GetMapping("/detail")
    public ResponseEntity<PostDetailDto> findPostById(@RequestParam Long id) {

        PostDetailDto postDetailDto = postService.findPostById(id);
        if (postDetailDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postDetailDto, HttpStatus.OK);
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: send BaseResponseCreatePost object to Frontend project for handle response form server
     *
     * @param createPostDto: an object of class CreatePostDto
     * @return ResponseEntity with BaseResponseCreatePost and HttpStatus is code of BaseResponseCreatePost
     */
    @PostMapping("/create")
    public ResponseEntity<BaseResponseCreatePost> create(@RequestBody CreatePostDto createPostDto) {
        BaseResponseCreatePost baseResponseCreatePost = postService.getResponseCreatePost(createPostDto);
        return new ResponseEntity<>(baseResponseCreatePost, HttpStatus.OK);
    }

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: show list or search  Post
     *
     * @param demandTypeSearch
     * @param landTypeSearch
     * @param pageable
     * @return HttpStatus.OK if json list Post
     */
    @GetMapping("")
    public ResponseEntity<Page<PostDto>> listAllPosts(@RequestParam() Optional<String> demandTypeSearch,
                                                      @RequestParam() Optional<String> landTypeSearch,
                                                      @RequestParam() Optional<Double> minPriceSearch,
                                                      @RequestParam() Optional<Double> maxPriceSearch,
                                                      @RequestParam() Optional<String> citySearch,
                                                      @RequestParam() Optional<String> districtSearch,
                                                      @RequestParam() Optional<String> wardsSearch,
                                                      @RequestParam() Optional<Double> minAreaSearch,
                                                      @RequestParam() Optional<Double> maxAreaSearch,
                                                      @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<PostDto> listPostDtos;
        String demandTypeSearchValue = demandTypeSearch.orElse("");
        String landTypeSearchValue = landTypeSearch.orElse("");
        Double minPriceSearchValue = minPriceSearch.orElse(0.0);
        Double maxPriceSearchValue = maxPriceSearch.orElse(99999999999999999.0);
        String citySearchValue = citySearch.orElse("");
        String districtSearchValue = districtSearch.orElse("");
        String wardsSearchValue = wardsSearch.orElse("");
        Double minAreaSearchValue = minAreaSearch.orElse(0.0);
        Double maxAreaSearchValue = maxAreaSearch.orElse(99999999999999999.0);

        if (demandTypeSearchValue.equals("") || landTypeSearchValue.equals("") || minPriceSearchValue != 0.0 || maxPriceSearchValue != 99999999999999999.0 || citySearchValue.equals("") || districtSearchValue.equals("") || wardsSearchValue.equals("") || minAreaSearchValue != 0.0 || maxAreaSearchValue != 99999999999999999.0) {
            listPostDtos = postService.searchAllPost(demandTypeSearchValue, landTypeSearchValue, minPriceSearchValue, maxPriceSearchValue, citySearchValue, districtSearchValue, wardsSearchValue, minAreaSearchValue, maxAreaSearchValue, pageable);
        } else {
            listPostDtos = postService.findAllPost(pageable);
        }
        if (listPostDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listPostDtos, HttpStatus.OK);
    }

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: delete post
     *
     * @param id
     * @return HttpStatus.OK if have id in database, delete success or HttpStatus.NOT_FOUND if id not found in database
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id) {
        Post currentPost = postService.findPost(id);
        if (currentPost == null) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        postService.deletePost(id);
        return new ResponseEntity<Post>(currentPost, HttpStatus.OK);
    }

    /**
     * Create by: NgocLV
     * Date created: 31/01/2023
     * Function: approval post
     *
     * @param id
     * @return HttpStatus.OK if have id in database, approval success or HttpStatus.NOT_FOUND if id not found in database
     */
    @PatchMapping("/approval/{id}")
    public ResponseEntity<Post> approvalPost(@PathVariable("id") Long id) {
        Post currentPost = postService.findPost(id);
        if (currentPost == null) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        postService.approvalPost(id);
        return new ResponseEntity<Post>(currentPost, HttpStatus.OK);
    }

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
    @PatchMapping("/confirm")
    public ResponseEntity<HttpStatus> succeedConfirm(@RequestParam Long id) {
        PostDetailDto postDetailDto = postService.findPostById(id);
        if (postDetailDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.succeedConfirm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Created by: BaoDP
     * Date Created: 03/022023
     *
     * @param idAccount
     * @return page post customer
     */
    @PostMapping("/customer/login")
    public ResponseEntity<CustomerGetIdAndCodCustomer> getIdAndCodCustomer(@RequestBody Long idAccount) {
        CustomerGetIdAndCodCustomer customerGetIdAndCodCustomer = postService.getIdCustomerAndCodeCustomer(idAccount);
        return new ResponseEntity<>(customerGetIdAndCodCustomer,HttpStatus.OK);
    }
}
