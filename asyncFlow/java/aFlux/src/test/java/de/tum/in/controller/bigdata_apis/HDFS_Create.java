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

package de.tum.in.controller.bigdata_apis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahapatr on 28/09/16.
 */
public class HDFS_Create {
    private List<String> message = new ArrayList<String>();
    private List<String> response = new ArrayList<String>();

    public List<String> getFilename() {
        return message;
    }

    public void setFilename(List<String> filename) {
        this.message = filename;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public void addMessage(String filename) {
        this.message.add(filename);
    }

    public void addResponseCode(String response) {
        this.response.add(response);
    }
}
