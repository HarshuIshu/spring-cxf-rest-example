package com.idodevjobs.sample.service;

import com.idodevjobs.sample.exception.mapper.CustomException;
import com.idodevjobs.sample.model.ExampleModel;
import org.springframework.stereotype.Service;

@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {

    @Override
    public ExampleModel get(String modelId) {
        if ("1".equals(modelId)) {
            throw new CustomException();
        }
        return new ExampleModel("example", 1001);
    }

    @Override
    public void post(ExampleModel exampleModel) {

        System.out.println("Posted " + exampleModel);
    }
}