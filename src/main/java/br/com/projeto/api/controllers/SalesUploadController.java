package br.com.projeto.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import br.com.projeto.api.services.ReadXLSXFileService;

@RestController
@RequestMapping("/sales")
public class SalesUploadController {

  @Autowired
  private ReadXLSXFileService readXLSXFileService;

  @PostMapping("/upload")
  public String upload(@RequestParam MultipartFile file) throws Exception {
    return readXLSXFileService.run(file);
  }
}
