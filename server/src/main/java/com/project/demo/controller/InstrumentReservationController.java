package com.project.demo.controller;

import com.project.demo.entity.InstrumentReservation;
import com.project.demo.service.InstrumentReservationService;
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
 *仪器预约：(InstrumentReservation)表控制层
 *
 */
@RestController
@RequestMapping("/instrument_reservation")
public class InstrumentReservationController extends BaseController<InstrumentReservation,InstrumentReservationService> {

    /**
     *仪器预约对象
     */
    @Autowired
    public InstrumentReservationController(InstrumentReservationService service) {
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
