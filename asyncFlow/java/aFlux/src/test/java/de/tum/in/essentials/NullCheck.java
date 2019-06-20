

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright [2019] [Tanmaya Mahapatra]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tum.in.essentials;

import org.apache.log4j.Logger;

import de.tum.in.models.bigdata.Data;
import de.tum.in.models.bigdata.DeviceData;

import java.lang.reflect.Field;

/**
 * Created by mahapatr on 24/01/17.
 */
public class NullCheck {
    static Logger log = Logger.getLogger(NullCheck.class.getName());
    public static boolean checkNull(DeviceData deviceData){

        boolean error = false;
        try{
            for(Field field : deviceData.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                if(field.get(deviceData) == null){
                    error = true;
                    break;
                }
            }
            if(deviceData.getData() != null){
                for(Data data : deviceData.getData()){
                    for(Field field : data.getClass().getDeclaredFields())
                    {
                        field.setAccessible(true);
                        if(field.get(data) == null){
                            error = true;
                            break;
                        }
                    }
                }
            }

        } catch(IllegalAccessException ie) {
            log.info(ie.getMessage());
        }
        return error;
    }
}
