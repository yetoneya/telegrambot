package com.example.hs.service.impl;


import com.example.hs.service.HSDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
public class HSServiceImplTest {

    @Test
    public void shouldReadData() throws IOException {
        HSDataService service = new HSDataServiceImpl();
        String data = service.readData();
        System.out.println(data);
    }

}
