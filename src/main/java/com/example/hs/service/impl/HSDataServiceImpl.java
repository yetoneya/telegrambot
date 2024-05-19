package com.example.hs.service.impl;

import com.example.hs.service.HSDataService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Service
public class HSDataServiceImpl implements HSDataService {

  @Override
  public String readData() throws IOException {
    File file = ResourceUtils.getFile("classpath:data/hs.txt");
    return Files.lines(file.toPath()).collect(Collectors.joining("\n"));
  }
}
