

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
