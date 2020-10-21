package com.njoy.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ouyanglingzhi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulatedFault {
    private Long delayInMilliseconds;
}
