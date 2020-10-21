package com.njoy.springcloud.service.impl;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.SimulatedFault;
import com.njoy.springcloud.service.FaultInjectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ouyanglingzhi
 */
@Service
public class FaultInjectionServiceImpl implements FaultInjectionService {

    @Resource
    private SimulatedFault simulatedFault;

    @Override
    public ApiResponse injectDelay(Long delay) {
        simulatedFault.setDelayInMilliseconds(delay);
        return new ApiResponse(200, "successfully set delay as " + delay, simulatedFault);
    }
}
