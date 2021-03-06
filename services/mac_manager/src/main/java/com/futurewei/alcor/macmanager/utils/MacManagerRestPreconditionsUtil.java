/*
Copyright 2019 The Alcor Authors.

Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/

package com.futurewei.alcor.macmanager.utils;

import com.futurewei.alcor.common.exception.ParameterNullOrEmptyException;
import com.futurewei.alcor.macmanager.exception.MacAddressInvalidException;
import com.futurewei.alcor.macmanager.exception.MacRangeInvalidException;
import com.futurewei.alcor.macmanager.exception.MacStateInvalidException;
import com.futurewei.alcor.web.entity.mac.MacAddress;
import com.futurewei.alcor.web.entity.mac.MacRange;
import com.futurewei.alcor.web.entity.mac.MacState;
import org.thymeleaf.util.StringUtils;

import javax.crypto.Mac;

public class MacManagerRestPreconditionsUtil {
    public static void verifyParameterNotNullorEmpty(String resourceId) throws ParameterNullOrEmptyException {
        if (StringUtils.isEmpty(resourceId)) {
            throw new ParameterNullOrEmptyException("Empty parameter");
        }
    }

    public static void verifyParameterNotNullorEmpty(MacState resource) throws ParameterNullOrEmptyException {
        if (resource == null) {
            throw new ParameterNullOrEmptyException("mac params is null");
        }
    }

    public static void verifyParameterNotNullorEmpty(MacRange resource) throws ParameterNullOrEmptyException {
        if (resource == null) {
            throw new ParameterNullOrEmptyException("null parameter");
        }
    }

    public static void verifyMacAddressFormat(String strMacAddress) throws MacAddressInvalidException {
        MacAddress macAddress = new MacAddress();
        if (!macAddress.validateMac(strMacAddress)) {
            throw new MacAddressInvalidException(MacManagerConstant.MAC_EXCEPTION_MACADDRESS_INVALID_FORMAT);
        }
    }

    public static void verifyMacStateData(MacState macState) throws MacStateInvalidException {
        String projectId = macState.getProjectId();
        String vpcId = macState.getVpcId();
        String portId = macState.getPortId();
        String state = macState.getState();

        if (projectId == null || vpcId == null || portId == null)
            throw new MacStateInvalidException(MacManagerConstant.MAC_EXCEPTION_REQUIRE_PARAMS_NULL);

        projectId = projectId.trim();
        vpcId = vpcId.trim();
        portId = portId.trim();

        if (projectId.length() == 0 || vpcId.length() == 0 || portId.length() == 0)
            throw new MacStateInvalidException(MacManagerConstant.MAC_EXCEPTION_REQUIRE_PARAMS_EMPTY);

        if (!StringUtils.isEmpty(state)) {
            state = state.trim();
            if (!state.equals(MacManagerConstant.MAC_STATE_ACTIVE) && !state.equals(MacManagerConstant.MAC_STATE_INACTIVE))
                throw new MacStateInvalidException(MacManagerConstant.MAC_EXCEPTION_STATE_INVALID);
        }
    }

    public static void verifyMacRangeData(MacRange macRange) throws MacRangeInvalidException {
        String rangeId = macRange.getRangeId();
        String from = macRange.getFrom();
        String to = macRange.getTo();
        String state = macRange.getState();

        if (StringUtils.isEmpty(rangeId) || StringUtils.isEmpty(from) || StringUtils.isEmpty(to))
            throw new MacRangeInvalidException(MacManagerConstant.MAC_EXCEPTION_RANGE_VALUE_NULL);

        rangeId = rangeId.trim();
        from = from.trim();
        to = to.trim();
        state = state.trim();

        if (rangeId.length() == 0 || from.length() == 0 || to.length() == 0)
            throw new MacRangeInvalidException(MacManagerConstant.MAC_EXCEPTION_RANGE_INVALID_EMPTY);

        if (!StringUtils.isEmpty(state)) {
            if (!state.equals(MacManagerConstant.MAC_STATE_ACTIVE) && !state.equals(MacManagerConstant.MAC_STATE_INACTIVE))
                throw new MacRangeInvalidException(MacManagerConstant.MAC_EXCEPTION_RANGE_INVALID_DATA);
        }
    }
}