package sopt.org.ThirdSeminar.controller.dto.request;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class BoardImageListRequestDto {
    private List<MultipartFile> boardImages;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isPublic;
}
