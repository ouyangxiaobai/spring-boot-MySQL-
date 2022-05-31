package com.project.demo.controller;

import com.project.demo.entity.DataClassification;
import com.project.demo.service.DataClassificationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *资料分类：(DataClassification)表控制层
 *
 */
@RestController
@RequestMapping("/data_classification")
public class DataClassificationController extends BaseController<DataClassification,DataClassificationService> {

    /**
     *资料分类对象
     */
    @Autowired
    public DataClassificationController(DataClassificationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
