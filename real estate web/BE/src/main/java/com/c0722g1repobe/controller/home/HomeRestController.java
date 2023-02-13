package com.c0722g1repobe.controller.home;

import com.c0722g1repobe.dto.post.*;
import com.c0722g1repobe.dto.post.create_post.BaseResponseCreatePost;
import com.c0722g1repobe.dto.post.create_post.CreatePostDto;
import com.c0722g1repobe.entity.post.*;
import com.c0722g1repobe.service.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/public/home")
public class HomeRestController {
    @Autowired
    private IPostService postService;
    @Autowired
    private ILandTypeService landTypeService;
    @Autowired
    private IDirectionService directionService;
    @Autowired
    ICityService cityService;
    @Autowired
    private IImageService imageService;

    /**
     * Create by: SangNP
     * Date created: 31/01/2023
     * Function: show list post
     *
     * @param area       It's okay not to have
     * @param price      It's okay not to have
     * @param landType It's okay not to have
     * @param direction  It's okay not to have
     * @param city       It's okay not to have
     * @param pageable   It's okay not to have
     * @return if have content it will return Page<Post> with HttpStatus.OK else it will return status HttpStatus.NO_CONTENT
     */
    @GetMapping("/list")
    public ResponseEntity<Page<PostListViewDto>> getAllPost(@RequestParam(defaultValue = "") String area,
                                                            @RequestParam(defaultValue = "") String price,
                                                            @RequestParam(defaultValue = "") String landType,
                                                            @RequestParam(defaultValue = "") String direction,
                                                            @RequestParam(defaultValue = "") String city,
                                                            @PageableDefault(size = 8) Pageable pageable) {
        if (area != null && price != null && landType != null && direction != null && city != null) {
            Page<PostListViewDto> postList = postService.findAll(area, price, landType, direction, city, pageable);
            if (postList != null && postList.hasContent()) {
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Create by: SangNP
     * Date created: 07/02/2023
     * Function: show list post for sell
     *
     * @param area       It's okay not to have
     * @param price      It's okay not to have
     * @param landType It's okay not to have
     * @param direction  It's okay not to have
     * @param city       It's okay not to have
     * @param pageable   It's okay not to have
     * @return if have content it will return Page<Post> for sell with HttpStatus.OK else it will return status HttpStatus.NO_CONTENT
     */
    @GetMapping("/list/sell")
    public ResponseEntity<Page<PostListViewDto>> getAllPostSell(@RequestParam(defaultValue = "") String area,
                                                            @RequestParam(defaultValue = "") String price,
                                                            @RequestParam(defaultValue = "") String landType,
                                                            @RequestParam(defaultValue = "") String direction,
                                                            @RequestParam(defaultValue = "") String city,
                                                            @PageableDefault(size = 8) Pageable pageable) {
        if (area != null && price != null && landType != null && direction != null && city != null) {
            Page<PostListViewDto> postList = postService.findAllSell(area, price, landType, direction, city, pageable);
            if (postList != null && postList.hasContent()) {
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Create by: SangNP
     * Date created: 07/02/2023
     * Function: show list post to buy
     *
     * @param area       It's okay not to have
     * @param price      It's okay not to have
     * @param landType It's okay not to have
     * @param direction  It's okay not to have
     * @param city       It's okay not to have
     * @param pageable   It's okay not to have
     * @return if have content it will return Page<Post> for Buy with HttpStatus.OK else it will return status HttpStatus.NO_CONTENT
     */
    @GetMapping("/list/buy")
    public ResponseEntity<Page<PostListViewDto>> getAllPostBuy(@RequestParam(defaultValue = "") String area,
                                                                @RequestParam(defaultValue = "") String price,
                                                                @RequestParam(defaultValue = "") String landType,
                                                                @RequestParam(defaultValue = "") String direction,
                                                                @RequestParam(defaultValue = "") String city,
                                                                @PageableDefault(size = 8) Pageable pageable) {
        if (area != null && price != null && landType != null && direction != null && city != null) {
            Page<PostListViewDto> postList = postService.findAllBuy(area, price, landType, direction, city, pageable);
            if (postList != null && postList.hasContent()) {
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Create by: SangNP
     * Date created: 07/02/2023
     * Function: show list post for rent
     *
     * @param area       It's okay not to have
     * @param price      It's okay not to have
     * @param landType It's okay not to have
     * @param direction  It's okay not to have
     * @param city       It's okay not to have
     * @param pageable   It's okay not to have
     * @return if have content it will return Page<Post> for Rent with HttpStatus.OK else it will return status HttpStatus.NO_CONTENT
     */
    @GetMapping("/list/rent")
    public ResponseEntity<Page<PostListViewDto>> getAllPostRent(@RequestParam(defaultValue = "") String area,
                                                               @RequestParam(defaultValue = "") String price,
                                                               @RequestParam(defaultValue = "") String landType,
                                                               @RequestParam(defaultValue = "") String direction,
                                                               @RequestParam(defaultValue = "") String city,
                                                               @PageableDefault(size = 8) Pageable pageable) {
        if (area != null && price != null && landType != null && direction != null && city != null) {
            Page<PostListViewDto> postList = postService.findAllRent(area, price, landType, direction, city, pageable);
            if (postList != null && postList.hasContent()) {
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Create by: SangNP
     * Date created: 31/01/2023
     * Function: take list Land Type
     * @return List<LandType>
     */
    @GetMapping("/landType")
    public ResponseEntity<List<LandType>> findAllLandType(){
        List<LandType> landTypeList = landTypeService.findAll();
        return new ResponseEntity<>(landTypeList, HttpStatus.OK);
    }
    /**
     * Create by: SangNP
     * Date created: 31/01/2023
     * Function: take list direction
     * @return List<Direction>
     */
    @GetMapping("/direction")
    public ResponseEntity<List<Direction>> findAll() {
        List<Direction> directionList = directionService.findAll();
        return new ResponseEntity<>(directionList, HttpStatus.OK);
    }
    /**
     * Create by: SangNP
     * Date created: 02/02/2023
     * Function: list city
     *
     * @return HttpStatus.OK if json list City
     */
    @GetMapping("/city")
    public ResponseEntity<List<City>> listCity(){
        List<City> listCity = cityService.listCity();
        if (listCity.isEmpty()) {
            return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<City>>(listCity, HttpStatus.OK);
    }
    /**
     * Create by: SangNP
     * Date created: 02/02/2023
     * Function: list city
     *
     * @return HttpStatus.OK if json list Image
     */
    @GetMapping("/image")
    public ResponseEntity<List<ImageDto>> findImageByIdPost(@RequestParam Long id) {
        List<ImageDto> imageList = imageService.findImageByIdPost(id);
        if (imageList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(imageList, HttpStatus.OK);
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postDetailDto, HttpStatus.OK);
    }

    /**
     * created by HuyDN
     * HomeRestController
     *
     * @param id: number
     * @return: new ResponseEntity
     */
    @GetMapping("/detail/account-id")
    public ResponseEntity<Long> findAccountByCustomerId(@RequestParam Long id) {
        Long idAccount = postService.getIdAccountByIdCustomer(id);
        return new ResponseEntity<>(idAccount, HttpStatus.OK);
    }
}
