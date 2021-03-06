package com.futurewei.alcor.route.entity;

import com.futurewei.alcor.common.enumClass.RouteTableType;

public interface RouteConstant {
    final String DEFAULT_TARGET = "Local";

    final Integer DEFAULT_PRIORITY = 0;

    final RouteTableType DEFAULT_ROUTE_TABLE_TYPE = RouteTableType.VPC;

}
