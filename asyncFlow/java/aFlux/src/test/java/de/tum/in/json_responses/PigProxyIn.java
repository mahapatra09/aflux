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

package de.tum.in.json_responses;

/**
 * Created by Stanislav on 05.01.2017.
 */
public class PigProxyIn {
    private String queryCommand;
    private String qryGraphHost;
    private String qryGraphPort;
    private String qryGraphUsername;
    private String qryGraphPassword;

    public String getQueryCommand() {
        return queryCommand;
    }

    public void setQueryCommand(String queryCommand) {
        this.queryCommand = queryCommand;
    }

    public String getQryGraphHost() {
        return qryGraphHost;
    }

    public void setQryGraphHost(String qryGraphHost) {
        this.qryGraphHost = qryGraphHost;
    }

    public String getQryGraphPort() {
        return qryGraphPort;
    }

    public void setQryGraphPort(String qryGraphPort) {
        this.qryGraphPort = qryGraphPort;
    }

    public String getQryGraphUsername() {
        return qryGraphUsername;
    }

    public void setQryGraphUsername(String qryGraphUsername) {
        this.qryGraphUsername = qryGraphUsername;
    }

    public String getQryGraphPassword() {
        return qryGraphPassword;
    }

    public void setQryGraphPassword(String qryGraphPassword) {
        this.qryGraphPassword = qryGraphPassword;
    }
}
