/*
 *
 *  * IoT BigData Middleware : Interfacing IoT Mashups and Big Data
 *  * Copyright (C) 2016  Software & Systems Engineering , TUM
 *  *
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version 2
 *  * of the License, or (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
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
