

package com.c0722g1repobe.service.post.impl;


import com.c0722g1repobe.dto.post.*;
import com.c0722g1repobe.dto.post.create_post.BaseResponseCreatePost;
import com.c0722g1repobe.dto.post.create_post.CreatePostDto;
import com.c0722g1repobe.entity.customer.Customer;
import com.c0722g1repobe.entity.post.*;
import com.c0722g1repobe.repository.post.IAddressRepository;
import com.c0722g1repobe.repository.post.IImageRepository;
import com.c0722g1repobe.repository.post.IPostRepository;
import com.c0722g1repobe.service.post.IImageService;
import com.c0722g1repobe.service.post.IPostService;
import com.c0722g1repobe.validation.post.IValidateCreatePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

import com.c0722g1repobe.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class PostService implements IPostService {
    @Autowired
    private IValidateCreatePost validateCreatePost;
    @Autowired
    private IAddressRepository addressRepository;
    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IImageRepository imageRepository;

    /**
     * Call method getAll() of IPostRepository
     * Author: DatTQ
     */
    @Override
    public List<PostDtoViewList> getAll() {
        return postRepository.getAll();
    }

    /**
     * Call method searchYear(String year) of IPostRepository
     * Parameter: String year
     * Author: DatTQ
     */
    @Override
    public List<PostDtoViewList> searchYear(String year) {
        return postRepository.searchYear(year);
    }

    /**
     * Call method searchYear(String year, String month) of IPostRepository
     * Parameter: String year, String month
     * Author: DatTQ
     */
    @Override
    public List<PostDtoViewList> searchYearAndMonth(String year, String month) {
        return postRepository.searchYearAndMonth(year, month);
    }

    /**
     * Create by: BaoDP
     * Date Create: 01/02/2023
     * Description: transfer attributes form createPostDto to an object of class Post and add another default value to it.
     *
     * @param createPostDto : an object of class CreatePostDto
     * @return an object of class PostDto
     */
    private Post addDefaultValue(CreatePostDto createPostDto) {

        Long defaultIdStatus = 2L;

        addressRepository.saveAddress(createPostDto.getNumberAddress(), createPostDto.getIdWards());
        Long idAddress = addressRepository.findIdByNumberAddressAndIdWardsNativeQuery(createPostDto.getNumberAddress(), createPostDto.getIdWards());

        return Post.builder()
                .approval(false)
                .area(createPostDto.getArea())
                .dateCreation(LocalDate.now())
                .flagDeleted(false)
                .namePost(createPostDto.getNamePost())
                .note(createPostDto.getNote())
                .price(createPostDto.getPrice())
                .address(Address.builder().idAddress(idAddress).build())
                .demandType(DemandType.builder().idDemandType(createPostDto.getIdDemand()).build())
                .direction(Direction.builder().idDirection(createPostDto.getIdDirection()).build())
                .landType(LandType.builder().idLandType(createPostDto.getIdLandType()).build())
                .statusPost(StatusPost.builder().idStatusPost(defaultIdStatus).build())
                .customer(Customer.builder().idCustomer(createPostDto.getIdCustomer()).build())
                .build();
    }

    /**
     * Create by: BaoDP
     * Date Create: 01/02/2023
     * Description: call method savePost from PostRepository to save Post.
     *
     * @param post : an object of class PostDto
     */
    private void savePost(Post post) {
         postRepository.savePost(post);
    }

    /**
     * Create by: BaoDP
     * Date Create: 01/02/2023
     * Description: call method validateCreatePost from class ValidateCreatePost.
     *
     * @param createPostDto : an object of class CreatePostDto
     * @return an object of class BaseResponseCreatePost
     */
    private BaseResponseCreatePost validateCreatePost(CreatePostDto createPostDto) {
        return validateCreatePost.validateCreatePost(createPostDto);
    }

    /**
     * Create by: BaoDP
     * Date Create: 01/02/2023
     * Description: if createPostDto is valid then save Post before send BaseResponseCreatePost to Front-end project for handle http status code .
     *
     * @param createPostDto : an object of class CreatePostDto
     * @return an object of class BaseResponseCreatePost
     */
    @Override
    public BaseResponseCreatePost getResponseCreatePost(CreatePostDto createPostDto) {
        BaseResponseCreatePost baseResponseCreatePost = validateCreatePost(createPostDto);

        int validCode = 200;
        boolean validCreatePostDto = baseResponseCreatePost.getCode() == validCode;
        if (validCreatePostDto) {
            Post post = addDefaultValue(createPostDto);
            savePost(post);
            Long idPost = postRepository.getLastInsertId();
            String[] imageListURL = createPostDto.getImageListURL();
            for (String image : imageListURL) {
                imageRepository.saveImage(image, idPost);
            }
        }

        return baseResponseCreatePost;
    }

    /**
     * Created by: BaoDP
     * Date Created: 03/022023
     *
     * @param idAccount
     * @return page post customer
     */
    @Override
    public CustomerGetIdAndCodCustomer getIdCustomerAndCodeCustomer(Long idAccount) {
        return postRepository.getIdCustomerAndCodeCustomer(idAccount);
    }

    /**
     * Created by: UyDD
     * Date Created: 31/01/2023
     *
     * @param pageable
     * @return page post customer from post repository with role admin
     */

    @Override
    public Page<Post> getAllAndSearchWithRoleAdmin(String nameDemandTypeSearch, String idCustomer, Pageable pageable) {
        return postRepository.getAllAndSearchWithRoleAdmin(nameDemandTypeSearch, idCustomer, pageable);
    }

    /**
     * Created by: UyDD
     * Date Created: 31/01/2023
     *
     * @param pageable
     * @return page post customer from post repository with role customer
     */
    @Override
    public Page<Post> getAllAndSearchWithRoleCustomer(String nameDemandTypeSearch, String idAccount, Pageable pageable) {
        return postRepository.getAllAndSearchWithRoleCustomer(nameDemandTypeSearch, idAccount, pageable);
    }

    /**
     * Create by: SangNP
     * Date Create: 01/02/2023
     * Description: return list for home page .
     *
     * @param area
     * @param price
     * @param landType
     * @param direction
     * @param city
     * @param pageable
     * @return an Page<PostListViewDto> or null if not found
     */
    @Override
    public Page<PostListViewDto> findAll(String area, String price, String landType, String direction, String
            city, Pageable pageable) {
        boolean areaIsEmpty = area.equals("");
        boolean priceIsEmpty = price.equals("");
        if (areaIsEmpty && priceIsEmpty) {
            return postRepository.findAllWithDemandTypeDirectionCity(landType, direction, city, pageable);
        }

        if (priceIsEmpty) {
            String[] arr = area.split("-");
            if (arr.length == 2) {
                try {
                    Double areaMin = Double.parseDouble(arr[0]);
                    Double areaMax = Double.parseDouble(arr[1]);
                    return postRepository.findAllWithDemandTypeDirectionCityArea(landType, direction, city, areaMin, areaMax, pageable);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        if (areaIsEmpty) {
            String[] arr = price.split("-");
            if (arr.length == 2) {
                try {
                    Double priceMin = Double.parseDouble(arr[0]);
                    Double priceMax = Double.parseDouble(arr[1]);
                    return postRepository.findAllWithDemandTypeDirectionCityPrice(landType, direction, city, priceMin, priceMax, pageable);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        String[] arrOfArea = area.split("-");
        String[] arrOfPrice = price.split("-");
        if (arrOfArea.length == 2 && arrOfPrice.length == 2) {
            try {
                Double areaMin = Double.parseDouble(arrOfArea[0]);
                Double areaMax = Double.parseDouble(arrOfArea[1]);
                Double priceMin = Double.parseDouble(arrOfPrice[0]);
                Double priceMax = Double.parseDouble(arrOfPrice[1]);
                return postRepository.findAllWithDemandTypeDirectionCityAreaPrice(landType, direction, city, areaMin, areaMax, priceMin, priceMax, pageable);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Create by: NgocLV
     * Date Create: 01/02/2023
     * Description: delete post .
     *
     * @param idPost
     * @return delete post or null if not found
     */
    @Override
    public void deletePost(Long idPost) {
        postRepository.deletePost(idPost);
    }

    /**
     * Create by: NgocLV
     * Date Create: 01/02/2023
     * Description: find post .
     *
     * @param id
     * @return post or null if not found
     */
    @Override
    public Post findPost(Long id) {
        return postRepository.findPost(id);
    }

    /**
     * Create by: NgocLV
     * Date Create: 01/02/2023
     * Description: find list post .
     *
     * @param pageable
     * @return list post or null if not found
     */
    @Override
    public Page<PostDto> findAllPost(Pageable pageable) {
        return postRepository.findAllPost(pageable);
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
    @Override
    public PostDetailDto findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    /**
     * Create by: NgocLV
     * Date Create: 01/02/2023
     * Description: approval post .
     *
     * @param id
     * @return approval post  or null if not found
     */
    @Override
    public void approvalPost(Long id) {
        postRepository.approvalPost(id);
    }

    /**
     * Create by: NgocLV
     * Date Create: 01/02/2023
     * Description: approval post .
     *
     * @param demandTypeSearch
     * @param lendTypeSearch
     * @param minPriceSearch
     * @param maxPriceSearch
     * @param citySearch
     * @param districtSearch
     * @param wardsSearch
     * @return list post  or null if not found
     */
    @Override
    public Page<PostDto> searchAllPost(String demandTypeSearch, String lendTypeSearch, Double minPriceSearch, Double maxPriceSearch, String citySearch, String districtSearch, String wardsSearch, Double minAreSearch, Double maxAreSearch, Pageable pageable) {
        return postRepository.searchAllPost(demandTypeSearch, lendTypeSearch, minPriceSearch, maxPriceSearch, citySearch, districtSearch, wardsSearch, minAreSearch, maxAreSearch, pageable);
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
    @Override
    public void succeedConfirm(Long id) {
        postRepository.succeedConfirm(id);
    }

    @Override
    public Page<PostListViewDto> findAllSell(String area, String price, String landType, String direction, String city, Pageable pageable) {
        boolean areaIsEmpty = area.equals("");
        boolean priceIsEmpty = price.equals("");
        if (areaIsEmpty && priceIsEmpty) {
            return postRepository.findAllWithDemandTypeDirectionCitySell(landType, direction, city, pageable);
        }

        if (priceIsEmpty) {
            String[] arr = area.split("-");
            if (arr.length == 2) {
                try {
                    Double areaMin = Double.parseDouble(arr[0]);
                    Double areaMax = Double.parseDouble(arr[1]);
                    return postRepository.findAllWithDemandTypeDirectionCityAreaSell(landType, direction, city, areaMin, areaMax, pageable);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        if (areaIsEmpty) {
            String[] arr = price.split("-");
            if (arr.length == 2) {
                try {
                    Double priceMin = Double.parseDouble(arr[0]);
                    Double priceMax = Double.parseDouble(arr[1]);
                    return postRepository.findAllWithDemandTypeDirectionCityPriceSell(landType, direction, city, priceMin, priceMax, pageable);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        String[] arrOfArea = area.split("-");
        String[] arrOfPrice = price.split("-");
        if (arrOfArea.length == 2 && arrOfPrice.length == 2) {
            try {
                Double areaMin = Double.parseDouble(arrOfArea[0]);
                Double areaMax = Double.parseDouble(arrOfArea[1]);
                Double priceMin = Double.parseDouble(arrOfPrice[0]);
                Double priceMax = Double.parseDouble(arrOfPrice[1]);
                return postRepository.findAllWithDemandTypeDirectionCityAreaPriceSell(landType, direction, city, areaMin, areaMax, priceMin, priceMax, pageable);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Page<PostListViewDto> findAllBuy(String area, String price, String landType, String direction, String city, Pageable pageable) {
        boolean areaIsEmpty = area.equals("");
        boolean priceIsEmpty = price.equals("");
        if (areaIsEmpty && priceIsEmpty) {
            return postRepository.findAllWithDemandTypeDirectionCityBuy(landType, direction, city, pageable);
        }

        if (priceIsEmpty) {
            String[] arr = area.split("-");
            if (arr.length == 2) {
                try {
                    Double areaMin = Double.parseDouble(arr[0]);
                    Double areaMax = Double.parseDouble(arr[1]);
                    return postRepository.findAllWithDemandTypeDirectionCityAreaBuy(landType, direction, city, areaMin, areaMax, pageable);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        if (areaIsEmpty) {
            String[] arr = price.split("-");
            if (arr.length == 2) {
                try {
                    Double priceMin = Double.parseDouble(arr[0]);
                    Double priceMax = Double.parseDouble(arr[1]);
                    return postRepository.findAllWithDemandTypeDirectionCityPriceBuy(landType, direction, city, priceMin, priceMax, pageable);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        String[] arrOfArea = area.split("-");
        String[] arrOfPrice = price.split("-");
        if (arrOfArea.length == 2 && arrOfPrice.length == 2) {
            try {
                Double areaMin = Double.parseDouble(arrOfArea[0]);
                Double areaMax = Double.parseDouble(arrOfArea[1]);
                Double priceMin = Double.parseDouble(arrOfPrice[0]);
                Double priceMax = Double.parseDouble(arrOfPrice[1]);
                return postRepository.findAllWithDemandTypeDirectionCityAreaPriceBuy(landType, direction, city, areaMin, areaMax, priceMin, priceMax, pageable);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Page<PostListViewDto> findAllRent(String area, String price, String landType, String direction, String city, Pageable pageable) {
        boolean areaIsEmpty = area.equals("");
        boolean priceIsEmpty = price.equals("");
        if (areaIsEmpty && priceIsEmpty) {
            return postRepository.findAllWithDemandTypeDirectionCityRent(landType, direction, city, pageable);
        }

        if (priceIsEmpty) {
            String[] arr = area.split("-");
            if (arr.length == 2) {
                try {
                    Double areaMin = Double.parseDouble(arr[0]);
                    Double areaMax = Double.parseDouble(arr[1]);
                    return postRepository.findAllWithDemandTypeDirectionCityAreaRent(landType, direction, city, areaMin, areaMax, pageable);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        if (areaIsEmpty) {
            String[] arr = price.split("-");
            if (arr.length == 2) {
                try {
                    Double priceMin = Double.parseDouble(arr[0]);
                    Double priceMax = Double.parseDouble(arr[1]);
                    return postRepository.findAllWithDemandTypeDirectionCityPriceRent(landType, direction, city, priceMin, priceMax, pageable);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        String[] arrOfArea = area.split("-");
        String[] arrOfPrice = price.split("-");
        if (arrOfArea.length == 2 && arrOfPrice.length == 2) {
            try {
                Double areaMin = Double.parseDouble(arrOfArea[0]);
                Double areaMax = Double.parseDouble(arrOfArea[1]);
                Double priceMin = Double.parseDouble(arrOfPrice[0]);
                Double priceMax = Double.parseDouble(arrOfPrice[1]);
                return postRepository.findAllWithDemandTypeDirectionCityAreaPriceRent(landType, direction, city, areaMin, areaMax, priceMin, priceMax, pageable);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Long getIdAccountByIdCustomer(Long id) {
        return postRepository.getIdAccountByIdCustomer(id);
    }

}

