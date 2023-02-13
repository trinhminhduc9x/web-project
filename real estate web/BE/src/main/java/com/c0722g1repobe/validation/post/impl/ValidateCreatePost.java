package com.c0722g1repobe.validation.post.impl;

import com.c0722g1repobe.dto.post.create_post.BaseResponseCreatePost;
import com.c0722g1repobe.dto.post.create_post.CreatePostDto;
import com.c0722g1repobe.repository.customer.CustomerRepository;
import com.c0722g1repobe.repository.post.*;
import com.c0722g1repobe.utils.ResponseStatusEnum;
import com.c0722g1repobe.validation.post.IValidateCreatePost;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidateCreatePost implements IValidateCreatePost {
    private final BaseResponseCreatePost baseResponseCreatePost;
    private final CustomerRepository customerRepository;
    private final IDemandTypeRepository demandTypeRepository;
    private final ILandTypeRepository landTypeRepository;
    private final IWardsRepository wardsRepository;
    private final IDirectionRepository directionRepository;
    private final IAddressRepository addressRepository;


    private static final String REGEX_VIETNAMESE = "[a-zA-Z0-9àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ/ ]*";


    public ValidateCreatePost(BaseResponseCreatePost baseResponseCreatePost, CustomerRepository customerRepository, IDemandTypeRepository demandTypeRepository, ILandTypeRepository landTypeRepository, IWardsRepository wardsRepository, IDirectionRepository directionRepository, IAddressRepository addressRepository) {
        this.baseResponseCreatePost = baseResponseCreatePost;
        this.customerRepository = customerRepository;
        this.demandTypeRepository = demandTypeRepository;
        this.landTypeRepository = landTypeRepository;
        this.wardsRepository = wardsRepository;
        this.directionRepository = directionRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * Create by: BaoDP
     * Date Create: 01/02/2023
     * Description: validate create post dto and create post dto attributes.
     * If attributes is invalid return baseResponseCreatePost with code = INVALID_CODE(422), status = FAIL and custom message base on error.
     * Else return baseResponseCreatePost with code = 200 ,status = SUCCESS and message = "thêm mới thành công"
     *
     * @param createPostDto : an object of class CreatePostDto
     * @return an object of class BaseResponseCreatePost
     */
    @Override
    public BaseResponseCreatePost validateCreatePost(CreatePostDto createPostDto) {
        final int VALID_CODE = 200;

        baseResponseCreatePost.setCreatePostDto(createPostDto);

        if (checkCreatePostDtoIsNull(createPostDto)) return baseResponseCreatePost;
        if (validateIdCustomer().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateIdDemandType().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateIdLandType().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateIdWards().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateIdDirection().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateNumberAddress().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validatePrice().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateArea().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateNote().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateAddress().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateImageListURL().getCode() != VALID_CODE) return baseResponseCreatePost;
        if (validateNamePost().getCode() != VALID_CODE) return baseResponseCreatePost;

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: check param is null or not, if null then use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @param createPostDto: an object of class CreatePostDto
     * @return true if param is null otherwise return false
     */
    private boolean checkCreatePostDtoIsNull(CreatePostDto createPostDto) {
        if (createPostDto == null) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tạo mới bài đăng (null)");
            return true;
        }
        return false;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: set baseResponseCreatePost when had invalid attribute with custom message based on error : code = INVALID_CODE(422), status = FAIL and message = param value
     *
     * @param message : custom message
     */
    private void setBaseResponseCreatePostWhenInvalidWithCustomMessage(String message) {
        final int INVALID_CODE = 422;

        baseResponseCreatePost.setCode(INVALID_CODE);
        baseResponseCreatePost.setStatus(ResponseStatusEnum.FAIL);
        baseResponseCreatePost.setMessage(message);
    }

    /**
     * Create by: BaoDP
     * Date Create: 01/02/2023
     * Description: validate idCustomer, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate id of Customer
     */
    private BaseResponseCreatePost validateIdCustomer() {
        try {
            Long idCustomer = baseResponseCreatePost.getCreatePostDto().getIdCustomer();
            boolean idCustomerIsNull = idCustomer == null;
            boolean idCustomerIsEmpty = !idCustomerIsNull && idCustomer == -1L;
            boolean idCustomerNotExist = customerRepository.findIdByIdNativeQuery(idCustomer) == null;
            boolean idCustomerInvalidMin = !idCustomerIsNull && idCustomer < 1;
            boolean idCustomerInvalidMax = !idCustomerIsNull && idCustomer > 9000000000L;

            if (idCustomerIsNull) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận mã khách hàng (null)");
                return baseResponseCreatePost;
            }

            if (idCustomerIsEmpty) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Mã khách hàng không được để trống");
                return baseResponseCreatePost;
            }

            if (idCustomerNotExist) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Mã khách hàng không tồn tại");
                return baseResponseCreatePost;
            }

            if (idCustomerInvalidMin || idCustomerInvalidMax) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Mã khách hàng không hợp lệ");
                return baseResponseCreatePost;
            }

        } catch (NumberFormatException e) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Mã khách không đúng định dạng");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate idDemand, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate idDemand
     */
    private BaseResponseCreatePost validateIdDemandType() {
        try {
            Long idDemandType = baseResponseCreatePost.getCreatePostDto().getIdDemand();

            boolean idDemandTypeIsNull = idDemandType == null;
            boolean idDemandTypeIsEmpty = !idDemandTypeIsNull && idDemandType == -1;
            boolean idDemandTypeNotExist = demandTypeRepository.findByIdNativeQuery(idDemandType) == null;
            boolean idDemandTypeInvalidMin = !idDemandTypeIsNull && idDemandType < 1;
            boolean idDemandTypeInvalidMax = !idDemandTypeIsNull && idDemandType > 9000000000L;

            if (idDemandTypeIsNull) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận loại nhu cầu (null)");
                return baseResponseCreatePost;
            }

            if (idDemandTypeIsEmpty) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Loại nhu cầu không được để trống");
                return baseResponseCreatePost;
            }

            if (idDemandTypeNotExist) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Loại nhu cầu không tồn tại");
                return baseResponseCreatePost;
            }

            if (idDemandTypeInvalidMin || idDemandTypeInvalidMax) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Loại nhu cầu không hợp lệ");
                return baseResponseCreatePost;
            }

        } catch (NumberFormatException e) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Loại nhu cầu không đúng định dạng");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate idLandType, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate idLandType
     */
    private BaseResponseCreatePost validateIdLandType() {
        try {
            Long idLandType = baseResponseCreatePost.getCreatePostDto().getIdLandType();

            boolean idLandTypeIsNull = idLandType == null;
            boolean idLandTypeIsEmpty = !idLandTypeIsNull && idLandType == -1;
            boolean idLandTypeNotExist = landTypeRepository.findByIdNativeQuery(idLandType) == null;
            boolean idLandTypeInvalidMin = !idLandTypeIsNull && idLandType < 1;
            boolean idLandTypeInvalidMax = !idLandTypeIsNull && idLandType > 9000000000L;

            if (idLandTypeIsNull) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận loại bất động sản (null)");
                return baseResponseCreatePost;
            }

            if (idLandTypeIsEmpty) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Loại bất động sản không được để trống");
                return baseResponseCreatePost;
            }

            if (idLandTypeNotExist) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Loại bất động sản không tồn tại");
                return baseResponseCreatePost;
            }

            if (idLandTypeInvalidMin || idLandTypeInvalidMax) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Loại bất động sản không hợp lệ");
                return baseResponseCreatePost;
            }

        } catch (NumberFormatException e) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Loại bất động sản không đúng định dạng");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate idWards, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate idWards
     */
    private BaseResponseCreatePost validateIdWards() {
        try {
            Long idWards = baseResponseCreatePost.getCreatePostDto().getIdWards();

            boolean idWardsIsNull = idWards == null;
            boolean idWardsIsEmpty = !idWardsIsNull && idWards == -1;
            boolean idWardsNotExist = wardsRepository.findNameByIdNativeQuery(idWards) == null;
            boolean idWardsInvalidMin = !idWardsIsNull && idWards < 1;
            boolean idWardsInvalidMax = !idWardsIsNull && idWards > 9000000000L;

            if (idWardsIsNull) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận địa chỉ (null)");
                return baseResponseCreatePost;
            }

            if (idWardsIsEmpty) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Phường/xã không được để trống");
                return baseResponseCreatePost;
            }

            if (idWardsNotExist) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Phường/xã không tồn tại");
                return baseResponseCreatePost;
            }

            if (idWardsInvalidMin || idWardsInvalidMax) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Phường/xã không hợp lệ");
                return baseResponseCreatePost;
            }

        } catch (NumberFormatException e) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Phường/xã không đúng định dạng");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate idDirection, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate idDirection
     */
    private BaseResponseCreatePost validateIdDirection() {
        try {
            Long idDirection = baseResponseCreatePost.getCreatePostDto().getIdDirection();

            boolean idDirectionIsNull = idDirection == null;
            boolean idDirectionIsEmpty = !idDirectionIsNull && idDirection == -1;
            boolean idDirectionNotExist = directionRepository.findByIdNativeQuery(idDirection) == null;
            boolean idDirectionInvalidMin = !idDirectionIsNull && idDirection < 1;
            boolean idDirectionInvalidMax = !idDirectionIsNull && idDirection > 9000000000L;

            if (idDirectionIsNull) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận hướng bất động sản (null)");
                return baseResponseCreatePost;
            }

            if (idDirectionIsEmpty) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Hướng bất động sản không được để trống");
                return baseResponseCreatePost;
            }

            if (idDirectionNotExist) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Hướng bất động sản không tồn tại");
                return baseResponseCreatePost;
            }

            if (idDirectionInvalidMin || idDirectionInvalidMax) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Hướng bất động sản không hợp lệ");
                return baseResponseCreatePost;
            }

        } catch (NumberFormatException e) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Hướng bất động sản không đúng định dạng");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate numberAddress, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate numberAddress
     */
    private BaseResponseCreatePost validateNumberAddress() {
        String numberAddress = baseResponseCreatePost.getCreatePostDto().getNumberAddress();

        boolean numberAddressIsNull = numberAddress == null;
        boolean numberAddressIsEmptyOrBlank = !numberAddressIsNull && (numberAddress.isEmpty() || numberAddress.trim().isEmpty());
        boolean numberAddressInvalidMin = !numberAddressIsNull && numberAddress.length() < 5;
        boolean numberAddressInvalidMax = !numberAddressIsNull && numberAddress.length() > 50;

        boolean numberAddressInvalidCharacters = !numberAddressIsNull && !numberAddress.matches(REGEX_VIETNAMESE);

        if (numberAddressIsNull) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận địa chỉ - địa chỉ cụ thể (null)");
            return baseResponseCreatePost;
        }

        if (numberAddressIsEmptyOrBlank) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Địa chỉ cụ thể không được để trống");
            return baseResponseCreatePost;
        }

        if (numberAddressInvalidMax || numberAddressInvalidMin) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Địa chỉ cụ thể không hợp lệ (Tối đa 50 kí tự và tối thiểu 5 kí tự)");
            return baseResponseCreatePost;
        }

        if (numberAddressInvalidCharacters) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Địa chỉ cụ thể chứa các kí tự đặc biệt (chỉ cho phép chữ cái, chữ số , dấu cách và dấu /)");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate price, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate price
     */
    private BaseResponseCreatePost validatePrice() {
        try {
            Double price = baseResponseCreatePost.getCreatePostDto().getPrice();

            boolean priceIsNull = price == null;
            boolean invalidPriceMinMax = !priceIsNull && (price < 1000000 || price > 100000000000d);
            boolean priceIsEmpty = !priceIsNull && price == 0;


            if (priceIsNull) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận giá tiền (null)");
                return baseResponseCreatePost;
            }

            if (priceIsEmpty) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Giá tiền không được để trống");
                return baseResponseCreatePost;
            }

            if (invalidPriceMinMax) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Giá tiền không được hỗ trợ (phải bé hơn 100 tỷ và lớn hơn 1 triệu)");
            }

        } catch (NumberFormatException e) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Giá tiền sai định dạng (chứa kí tự)");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate area, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate area
     */
    private BaseResponseCreatePost validateArea() {
        try {
            Double area = baseResponseCreatePost.getCreatePostDto().getArea();

            boolean areaIsNull = area == null;
            boolean invalidAreaMinMax = !areaIsNull && (area < 10 || area > 10000);
            boolean areaIsEmpty = !areaIsNull && area == 0;


            if (areaIsNull) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận diện tích (null)");
                return baseResponseCreatePost;
            }

            if (areaIsEmpty) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Diện tích không được để trống");
                return baseResponseCreatePost;
            }

            if (invalidAreaMinMax) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Diện tích không được hỗ trợ (phải bé hơn 10000m2 và lớn hơn 1m2)");
            }

        } catch (NumberFormatException e) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Diện tích không hợp lệ (chứa kí tự)");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate note, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate note
     */
    private BaseResponseCreatePost validateNote() {
        String note = baseResponseCreatePost.getCreatePostDto().getNote();

        boolean noteIsNull = note == null;
        boolean noteInvalidMax = !noteIsNull && note.length() > 255;

        boolean noteInvalidCharacters = !noteIsNull && !note.matches(REGEX_VIETNAMESE);

        if (noteIsNull) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận mô tả chi tiết (null)");
            return baseResponseCreatePost;
        }

        if (noteInvalidMax) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Mô tả chi tiết không hợp lệ (Tối đa 255 kí tự)");
            return baseResponseCreatePost;
        }

        if (noteInvalidCharacters) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Mô tả chi tiết chứa các kí tự đặc biệt (chỉ cho phép chữ cái, chữ số , dấu cách và dấu /)");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate Address, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate Address
     */
    private BaseResponseCreatePost validateAddress() {
        Long idAddress = addressRepository.findIdByNumberAddressAndIdWardsNativeQuery(baseResponseCreatePost.getCreatePostDto().getNumberAddress(), baseResponseCreatePost.getCreatePostDto().getIdWards());

        boolean addressExist = idAddress != null;
        if (addressExist) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Địa chỉ này đã tồn tại bài đăng. Vui lòng kiểm tra lại hoặc liên hệ số điện thoại hỗ trợ !");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate imageListURL, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate imageListURL
     */
    private BaseResponseCreatePost validateImageListURL() {
        String[] imageListURL = baseResponseCreatePost.getCreatePostDto().getImageListURL();

        boolean imageListURLIsNull = imageListURL == null;
        boolean imageListURLIsEmpty = !imageListURLIsNull && imageListURL.length == 0;


        if (imageListURLIsNull) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận hình ảnh của bài đăng (null URL)");
            return baseResponseCreatePost;
        }

        if (imageListURLIsEmpty) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Vui lòng đính kèm hình ảnh");
            return baseResponseCreatePost;
        }

        for (String imageURL : imageListURL) {
            boolean imageURLIsNull = imageURL == null;
            boolean imageURLIsEmpty = !imageURLIsNull && imageURL.length() == 0;
            boolean imageURLInvalidMin = !imageURLIsNull && imageURL.length() < 5;
            boolean imageURLInvalidMax = !imageURLIsNull && imageURL.length() > 255;

            if (imageURLIsNull) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận hình ảnh của bài đăng (null URL)");
                return baseResponseCreatePost;
            }

            if (imageURLIsEmpty) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận hình ảnh của bài đăng (empty URL)");
                return baseResponseCreatePost;
            }

            if (imageURLInvalidMin || imageURLInvalidMax) {
                setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận hình ảnh của bài đăng (Size of URL)");
                return baseResponseCreatePost;
            }
        }

        return baseResponseCreatePost;
    }

    /**
     * Create by: BaoDP
     * Date create: 01/02/2023
     * Description: validate namePost, if it is invalid use method setBaseResponseCreatePostWhenInvalidWithCustomMessage before return
     *
     * @return baseResponseCreatePost after validate namePost
     */
    private BaseResponseCreatePost validateNamePost() {
        String namePost = baseResponseCreatePost.getCreatePostDto().getNamePost();

        boolean namePostIsNull = namePost == null;
        boolean namePostIsEmptyOrBlank = !namePostIsNull && (namePost.isEmpty() || namePost.trim().isEmpty());
        boolean namePostInvalidMin = !namePostIsNull && namePost.length() < 10;
        boolean namePostInvalidMax = !namePostIsNull && namePost.length() > 50;

        boolean namePostInvalidCharacters = !namePostIsNull && !namePost.matches(REGEX_VIETNAMESE);

        if (namePostIsNull) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Lỗi tiếp nhận tên bài đăng (null)");
            return baseResponseCreatePost;
        }

        if (namePostIsEmptyOrBlank) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Tên bài đăng không được để trống");
            return baseResponseCreatePost;
        }

        if (namePostInvalidMax || namePostInvalidMin) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Tên bài đăng không hợp lệ (Tối đa 50 kí tự và tối thiểu 10 kí tự)");
            return baseResponseCreatePost;
        }

        if (namePostInvalidCharacters) {
            setBaseResponseCreatePostWhenInvalidWithCustomMessage("Tên bài viết chứa các kí tự đặc biệt (chỉ cho phép chữ cái, chữ số , dấu cách và dấu /)");
            return baseResponseCreatePost;
        }

        return baseResponseCreatePost;
    }
}
