package com.project.demo.controller;

import com.project.demo.entity.InstrumentsAndEquipment;
import com.project.demo.service.InstrumentsAndEquipmentService;
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
 *仪器设备：(InstrumentsAndEquipment)表控制层
 *
 */
@RestController
@RequestMapping("/instruments_and_equipment")
public class InstrumentsAndEquipmentController extends BaseController<InstrumentsAndEquipment,InstrumentsAndEquipmentService> {

    /**
     *仪器设备对象
     */
    @Autowired
    public InstrumentsAndEquipmentController(InstrumentsAndEquipmentService service) {
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
