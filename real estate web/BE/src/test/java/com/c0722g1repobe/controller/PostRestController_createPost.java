package com.c0722g1repobe.controller;

import com.c0722g1repobe.dto.post.create_post.BaseResponseCreatePost;
import com.c0722g1repobe.dto.post.create_post.CreatePostDto;
import com.c0722g1repobe.utils.ResponseStatusEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostRestController_createPost {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    /*
    Đây là một đối tượng mẫu mà Front-End gửi cho Back-End
    */
    private CreatePostDto tempCreatePostDto = CreatePostDto.builder()
            .idCustomer(1L)
            .namePost("name post createDto")
            .idDemand(1L)
            .idLandType(1L)
            .idWards(1L)
            .idDirection(1L)
            .numberAddress("122B đường 2/4")
            .price(10000000d)
            .area(500d)
            .note("Mô tả chi tiết của bài đăng")
            .imageListURL("Đường dẫn ảnh được lưu trên firebase")
            .build();

    /*
    Đây là một đối tượng mẫu sau khi Back-end xử lý dữ liệu nhận được từ front-end và gửi trả lại cho front-end
    Thuộc tính :
        + code : Đây là http code tượng trưng cho kết quả xử lý của back-end, front-end dựa vào đây để xử lý.
        + status : Đây là trạng thái của response gồm :
            - SUCCESS : thành công, đi với code 2xx, xảy ra khi BE xử lý dữ liệu của FE mà không xảy ra lỗi.
            - FAIL : thất bại , đi với code 4xx , xảy ra khi BE xử lý dữ liệu của FE trả về các lỗi xuất phát từ Client ví dụ như các lỗi khi validate.
            - ERROR : lỗi, đi với code 5xx, xảy ra khi BE xử lý dữ liệu của FE trả về các lỗi xuất phát từ Server.
        + message : Thông báo chi tiết,lí do cho kết quả mà BE trả về cho FE.
        + createPostDto : Dữ liệu mà BE đã nhận được từ FE.
     */
    private BaseResponseCreatePost tempBaseResponseCreatePost = BaseResponseCreatePost.builder()
            .code(200)
            .status(ResponseStatusEnum.SUCCESS)
            .message("Thông báo kết quả xử lý ")
            .createPostDto(tempCreatePostDto)
            .build();

    /*
    Đây là 1 biến đại diện cho trường hợp id không tồn tại
     */
    private final Long ID_NOT_EXIST = -1L;

    /*
    Đây là 1 biến đại diện cho trường hợp một chuỗi lớn hơn độ dài tối đa cho phép
     */
    private final String STRING_INVALID_MAX_LENGTH = "chuỗi lớn hơn độ dài cho phép";

    /*
    Đây là 1 biến đại diện cho trường hợp một chuỗi nhỏ hơn độ dài tối thiểu cho phép
     */
    private final String STRING_INVALID_MIN_LENGTH = "chuỗi nhỏ hơn độ dài cho phép";

    /*
    Đây là 1 biến đại diện cho trường hợp một số kiểu dữ liệu Long nhỏ hơn 1
     */
    private final Long LONG_NUMBER_INVALID_MIN = 0L;

    /*
    Đây là 1 biến đại diện cho trường hợp một số kiểu dữ liệu Long lớn hơn 9 tỷ
     */
    private final Long LONG_NUMBER_INVALID_MAX = 9000000001L;

    /***** API SẼ LUÔN TRẢ VỀ HTTP STATUS CODE 200 . ĐỐI TƯỢNG ĐƯỢC TRẢ VỀ TỪ BE CHỨA CÁC THUỘC TÍNH GIÚP FE XỬ LÝ ĐƯỢC KẾT QUẢ TRẢ VỀ CỦA BACK-END *****/

    /*
    Case 1: Object đầu vào là null
     */
    @Test
    void createPost_createPostDto_13() throws Exception {
        CreatePostDto createPostDto = null;

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tạo mới bài đăng (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 2: idCustomer là null
     */
    @Test
    void createPost_idCustomer_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(null)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận mã khách hàng (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 3: idCustomer là rỗng
     */
    @Test
    void createPost_idCustomer_14() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(Long.parseLong(""))
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Mã khách hàng không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 4: idCustomer không đúng kiểu dữ liệu
     */
    @Test
    void createPost_idCustomer_15_NumberFormatException() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(Long.parseLong("một"))
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Mã khách không đúng định dạng"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 5: idCustomer không tồn tại
     */
    @Test
    void createPost_idCustomer_15_NotExist() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(ID_NOT_EXIST)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Mã khách hàng không tồn tại"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 6: idCustomer bé hơn 1
     */
    @Test
    void createPost_idCustomer_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(LONG_NUMBER_INVALID_MIN)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Mã khách hàng không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 7: idCustomer lớn hơn 9 tỷ
     */
    @Test
    void createPost_idCustomer_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(LONG_NUMBER_INVALID_MAX)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Mã khách hàng không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 8 : namePost là null
     */
    @Test
    void createPost_namePost_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost(null)
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận tên bài đăng (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 9 : namePost là rỗng : ""
     */
    @Test
    void createPost_namePost_14_Empty() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Tên bài đăng không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 10 : namePost là khoảng trống : " "
     */
    @Test
    void createPost_namePost_14_Blank() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost(" ")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Tên bài đăng không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 11 : namePost chứa kí tự đặc biệt.
    Các kí tự đặc biệt không phải là:
     + Các chữ cái bao gồm có dấu và không dấu, in thường và in hoa.
     + Các chữ số
     + Dấu cách và dấu /
     */
    @Test
    void createPost_namePost_15() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("!@#$%^&*^")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Tên bài viết chứa các kí tự đặc biệt (chỉ cho phép chữ cái, chữ số , dấu cách và dấu /)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 12 : namePost nhiều hơn 50 kí tự
     */
    @Test
    void createPost_namePost_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost(STRING_INVALID_MAX_LENGTH)
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Tên bài đăng không hợp lệ (Tối đa 50 kí tự và tối thiểu 10 kí tự) !"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
     Case 13 : namePost ít hơn 10 kí tự
     */
    @Test
    void createPost_namePost_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost(STRING_INVALID_MIN_LENGTH)
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Tên bài đăng không hợp lệ (Tối đa 50 kí tự và tối thiểu 10 kí tự) !"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 14: idDemandType là null
     */
    @Test
    void createPost_idDemandType_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(null)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận loại nhu cầu (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 15: idDemandType là rỗng
     */
    @Test
    void createPost_idDemandType_14() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(Long.parseLong(""))
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại nhu cầu không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 16: idDemandType không đúng định dạng
     */
    @Test
    void createPost_idDemandType_15_NumberFormatException() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(Long.parseLong("một"))
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại nhu cầu không đúng định dạng"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 17: idDemandType không tồn tại
     */
    @Test
    void createPost_idDemandType_15_NotExist() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(ID_NOT_EXIST)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại nhu cầu không tồn tại"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }


    /*
    Case 18 : idDemand nhiều hơn 9 tỷ
     */
    @Test
    void createPost_idDemandType_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(LONG_NUMBER_INVALID_MAX)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại nhu cầu không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
     Case 19 : idDemandType ít hơn 1
     */
    @Test
    void createPost_idDemandType_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(LONG_NUMBER_INVALID_MIN)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại nhu cầu không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 20: idLandType là null
     */
    @Test
    void createPost_idLandType_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(null)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận loại bất động sản (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 21: idLandType là rỗng
     */
    @Test
    void createPost_idLandType_14() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(Long.parseLong(""))
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại bất động sản không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 22: idLandType không đúng định dạng
     */
    @Test
    void createPost_idLandType_15_NumberFormatException() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(Long.parseLong("một"))
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại bất động sản không đúng định dạng"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 23: idLandType không tồn tại
     */
    @Test
    void createPost_idLandType_15_NotExist() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(ID_NOT_EXIST)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại bất động sản không tồn tại"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }


    /*
    Case 24 : idLandType nhiều hơn 9 tỷ
     */
    @Test
    void createPost_idLandType_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(LONG_NUMBER_INVALID_MAX)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại bất động sản không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
     Case 25 : idLandType ít hơn 1
     */
    @Test
    void createPost_idLandType_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(LONG_NUMBER_INVALID_MIN)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Loại bất động sản không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 26: idWards là null
     */
    @Test
    void createPost_idWards_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(null)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận địa chỉ - phường/xã (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 27: idWards là rỗng
     */
    @Test
    void createPost_idWards_14() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(Long.parseLong(""))
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Phường/xã không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 28: idWards không đúng định dạng
     */
    @Test
    void createPost_idWards_15_NumberFormatException() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(Long.parseLong("một"))
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Phường/xã không đúng định dạng"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 29: idWards không tồn tại
     */
    @Test
    void createPost_idWards_15_NotExist() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(ID_NOT_EXIST)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Phường/xã không tồn tại"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }


    /*
    Case 30 : idWards nhiều hơn 9 tỷ
     */
    @Test
    void createPost_idWards_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(LONG_NUMBER_INVALID_MAX)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Phường/xã không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
     Case 31 : idWards ít hơn 1
     */
    @Test
    void createPost_idWards_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(LONG_NUMBER_INVALID_MIN)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Phường/xã không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 32: idDirection là null
     */
    @Test
    void createPost_idDirection_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(null)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận hướng bất động sản (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 33: idDirection là rỗng
     */
    @Test
    void createPost_idDirection_14() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(Long.parseLong(""))
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Hướng bất động sản không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 34: idDirection không đúng định dạng
     */
    @Test
    void createPost_idDirection_15_NumberFormatException() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(Long.parseLong("một"))
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Hướng bất động sản không đúng định dạng"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 35: idDirection không tồn tại
     */
    @Test
    void createPost_idDirection_15_NotExist() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(ID_NOT_EXIST)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Hướng bất động sản không tồn tại"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }


    /*
    Case 36 : idDirection nhiều hơn 9 tỷ
     */
    @Test
    void createPost_idDirection_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(LONG_NUMBER_INVALID_MAX)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Hướng bất động sản không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
     Case 37 : idDirection ít hơn 1
     */
    @Test
    void createPost_idDirection_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(LONG_NUMBER_INVALID_MIN)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Hướng bất động sản không hợp lệ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 38 : numberAddress là null
     */
    @Test
    void createPost_numberAddress_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress(null)
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận địa chỉ - địa chỉ cụ thể (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 39 : numberAddress là rỗng : ""
     */
    @Test
    void createPost_numberAddress_14_Empty() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Địa chỉ cụ thể không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 40 : numberAddress là khoảng trống : " "
     */
    @Test
    void createPost_numberAddress_14_Blank() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress(" ")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Địa chỉ cụ thể không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 41 : numberAddress chứa kí tự đặc biệt.
    Các kí tự đặc biệt không phải là:
     + Các chữ cái bao gồm có dấu và không dấu, in thường và in hoa.
     + Các chữ số
     + Dấu cách và dấu /
     */
    @Test
    void createPost_numberAddress_15() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("!@#$%^&*^")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Địa chỉ cụ thể chứa các kí tự đặc biệt (chỉ cho phép chữ cái, chữ số , dấu cách và dấu /)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 42 : numberAddress nhiều hơn 50 kí tự
     */
    @Test
    void createPost_numberAddress_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress(STRING_INVALID_MAX_LENGTH)
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Địa chỉ cụ thể không hợp lệ (Tối đa 50 kí tự và tối thiểu 5 kí tự) !"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
     Case 43 : numberAddress ít hơn 5 kí tự
     */
    @Test
    void createPost_numberAddress_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress(STRING_INVALID_MIN_LENGTH)
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Địa chỉ cụ thể không hợp lệ (Tối đa 50 kí tự và tối thiểu 5 kí tự) !"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 44: price là null
     */
    @Test
    void createPost_price_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(null)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận giá tiền (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 45: price là rỗng
     */
    @Test
    void createPost_price_14() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(Double.parseDouble(""))
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Giá tiền không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 46: price không đúng kiểu dữ liệu
     */
    @Test
    void createPost_price_15_NumberFormatException() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(Double.parseDouble("một"))
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Giá tiền sai định dạng (chứa kí tự)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 47: price bé hơn 1 triệu
     */
    @Test
    void createPost_price_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(1d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Giá tiền không được hỗ trợ (phải bé hơn 100 tỷ và lớn hơn 1 triệu)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 48: price lớn hơn 100 tỷ
     */
    @Test
    void createPost_price_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(100000000001d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Giá tiền không được hỗ trợ (phải bé hơn 100 tỷ và lớn hơn 1 triệu)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 49: area là null
     */
    @Test
    void createPost_area_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(null)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận diện tích (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 50: area là rỗng
     */
    @Test
    void createPost_area_14() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(Double.parseDouble(""))
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Diện tích không được để trống"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 51: area không đúng kiểu dữ liệu
     */
    @Test
    void createPost_area_15_NumberFormatException() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(Double.parseDouble("một"))
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Diện tích không hợp lệ (chứa kí tự)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 53: area bé hơn 10m2
     */
    @Test
    void createPost_area_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(5d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Diện tích không được hỗ trợ (phải bé hơn 10000m2 và lớn hơn 1m2)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 54: area lớn hơn 10000m2
     */
    @Test
    void createPost_area_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(10000000d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Diện tích không được hỗ trợ (phải bé hơn 10000m2 và lớn hơn 1m2)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 55 : note là null
     */
    @Test
    void createPost_note_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note(null)
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận mô tả chi tiết (null)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 56 : note chứa kí tự đặc biệt.
    Các kí tự đặc biệt không phải là:
     + Các chữ cái bao gồm có dấu và không dấu, in thường và in hoa.
     + Các chữ số
     + Dấu cách và dấu /
     */
    @Test
    void createPost_note_15() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("!@#$%^&*^")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Mô tả chi tiết chứa các kí tự đặc biệt (chỉ cho phép chữ cái, chữ số , dấu cách và dấu /)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 57 : note nhiều hơn 500 kí tự
     */
    @Test
    void createPost_note_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("STRING_INVALID_MAX_LENGTH")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Mô tả chi tiết không hợp lệ (Tối đa 500 kí tự)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /* VÌ NOTE LÀ FIELD KHÔNG BẮT BUỘC NÊN KHÔNG CÓ CASE CHO TRƯỜNG HỢP RỖNG, TRỐNG VÀ ĐỘ DÀI TỐI THIỂU */


    /*
    Case 58: Kiểm tra xem địa chỉ đã tồn tại (kiểm tra 2 trường idWards và numberAddress có đồng thời trùng lặp với record bất kì trong database table address)
     */
    @Test
    void createPost_numberAddress_idWards_checkContainAddress() throws Exception {
        Long existIdWardsWithSameNumberAddress = 1L;
        CreatePostDto createPostDto = CreatePostDto.builder().numberAddress("1 địa chỉ đã có sẵn trong database").idWards(existIdWardsWithSameNumberAddress).build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Địa chỉ này đã tồn tại bài đăng. Vui lòng kiểm tra lại hoặc liên hệ số điện thoại hỗ trợ"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 59 : imageListURL là null
     */
    @Test
    void createPost_imageListURL_13() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL(null)
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận hình ảnh của bài đăng (null URL)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 60 : imageListURL là rỗng : ""
     */
    @Test
    void createPost_imageListURL_14_Empty() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Vui lòng đính kèm hình ảnh"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 61 : imageListURL là khoảng trống : " "
     */
    @Test
    void createPost_imageListURL_14_Blank() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL(" ")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Vui lòng đính kèm hình ảnh"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Case 62 : imageListURL nhiều hơn 255 kí tự
     */
    @Test
    void createPost_imageListURL_17() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL(STRING_INVALID_MAX_LENGTH)
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận hình ảnh của bài đăng (Error URL)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
     Case 63 : imageListURL ít hơn 5 kí tự
     */
    @Test
    void createPost_imageListURL_16() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL(STRING_INVALID_MIN_LENGTH)
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(422))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.FAIL))
                .andExpect(jsonPath("message").value("Lỗi tiếp nhận hình ảnh của bài đăng (Error URL)"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }

    /*
    Thiếu case check imageListURL có tồn tại hay không vì chưa định hướng được luồng xác thực
     */

    /*
     Case 64 : Thêm mới thành công
     */
    @Test
    void createPost_createPostDto_18() throws Exception {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .idCustomer(1L)
                .namePost("name post createDto")
                .idDemand(1L)
                .idLandType(1L)
                .idWards(1L)
                .idDirection(1L)
                .numberAddress("122B đường 2/4")
                .price(10000000d)
                .area(500d)
                .note("Mô tả chi tiết của bài đăng")
                .imageListURL("Đường dẫn ảnh được lưu trên firebase")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("api/post/create")
                        .content(this.objectMapper.writeValueAsString(createPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("code").value(200))
                .andExpect(jsonPath("status").value(ResponseStatusEnum.SUCCESS))
                .andExpect(jsonPath("message").value("Thêm mới thành công"))
                .andExpect(jsonPath("createPostDto").value(this.objectMapper.writeValueAsString(createPostDto)));
    }
}
