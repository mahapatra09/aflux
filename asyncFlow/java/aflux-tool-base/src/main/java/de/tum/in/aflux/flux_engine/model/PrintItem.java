/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */

package de.tum.in.aflux.flux_engine.model;

import java.util.Date;


/**
 * Unit of information to be shown in output in app
 * @author Tanmaya Mahapatra
 *
 */
public class PrintItem {
	private String fluxId;
	private Date timeStamp;
	private String message;
	
	
	public PrintItem(String fluxId,String message) {
		this.fluxId=fluxId;
		this.message=message;
		this.timeStamp=new Date();
	}
	

	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



	public String getFluxId() {
		return fluxId;
	}


	public void setFluxId(String fluxId) {
		this.fluxId = fluxId;
	}


	@Override
	public String toString() {
		return "OutputElement [fluxId=" + fluxId + ", timeStamp=" + timeStamp
				+ ", message=" + message + "]\n";
	}
	
	

}
