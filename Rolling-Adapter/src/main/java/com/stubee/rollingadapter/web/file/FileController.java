package com.stubee.rollingadapter.web.file;

import com.stubee.rollingapplication.domain.file.port.api.FileUploadUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "File", description = "File API")
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadUseCase fileUploadUseCase;

    @Operation(description = "file 업로드")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(@RequestPart("file") MultipartFile file) {
        return fileUploadUseCase.uploadFile(file);
    }

}