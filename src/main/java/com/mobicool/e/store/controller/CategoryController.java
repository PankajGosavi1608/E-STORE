package com.mobicool.e.store.controller;

import com.mobicool.e.store.dto.ApiResponseMessage;
import com.mobicool.e.store.dto.CategoryDto;
import com.mobicool.e.store.dto.PageableResponse;
import com.mobicool.e.store.helper.ApiConstants;
import com.mobicool.e.store.helper.ApiResponse;
import com.mobicool.e.store.service.CategoryService;
import com.mobicool.e.store.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;
    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    @Autowired
    private FileService fileService;
    private Logger logger= LoggerFactory.getLogger(CategoryController.class);

    // create
    /**
     * @author Pankaj Gosavi
     * @param categoryDto
     * @return
     * @apiNote This API is used to create category
     */
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
     // call service to create object
        CategoryDto categoryDto1 = categoryService.create(categoryDto);

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    //update
    /**
     * @author Pankaj Gosavi
     * @param categoryDto
     * @param categoryId
     * @return
     * @apiNote This API is used to update category
     */
@PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable String categoryId,
            @RequestBody CategoryDto categoryDto)
    {
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

    //delete
    /**
     * @author Pankaj Gosavi
     * @param categoryId
     * @return
     * @apiNote This API is used to delete category
     */

    @DeleteMapping
    public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable String categoryId)
    {
        categoryService.delete(categoryId);

        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message(ApiConstants.CATEGORY_DELETED).status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }

    //Get All Category

    /**
     * @author Pankaj Gosavi
     * @apiNote this api is to get all category
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */

    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<CategoryDto> pageableResponse = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }

    //get Single Category

    /**
     * @author Pankaj Gosavi
     * @apiNote this api is to get single category
     * @param categoryId
     * @return
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable String categoryId){

        CategoryDto categoryDto = categoryService.get(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
        //OR
       // return new ResponseEntity.ok(categoryDto);

    }

    /**
     * @author Pankaj Gosavi
     * @param image
     * @param categoryId
     * @return
     * @throws IOException
     */
    /*@PostMapping("/image/{categoryId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("catImage") MultipartFile image, @PathVariable String categoryId) throws IOException {
        {
            logger.info("Request Starting for fileservice layer to upload image with categoryId {}", categoryId);
            String imageName = fileService.uploadFile(image, imageUploadPath);
            CategoryDto category = categoryService.getcategory(categoryId);
            category.setCoverImage(imageName);
            CategoryDto categoryDto = categoryService.updatecategory(category, categoryId);
            ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).message("File Uploaded").success(true).status(HttpStatus.CREATED).build();
            logger.info("Request Completed for fileservice layer to upload image with categoryId: {}", categoryId);
            return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
        }
    }

    /**
     * @author Pankaj Gosavi
     * @param categoryId
     * @param response
     * @throws IOException
     */
    //Serve User Image
    /*@GetMapping("/image/{categoryId}")
    public void serveUserImage(@PathVariable String categoryId, HttpServletResponse response) throws IOException {
        CategoryDto category = categoryService.getcategory(categoryId);
        logger.info("User Image Name: {}",category.getCoverImage());
        InputStream resource = fileService.getResource(imageUploadPath,category.getCoverImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }*/

}
