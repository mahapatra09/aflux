

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

package de.tum.in.aflux.tools.core;

public enum PropertyInputType {
	TEXT("text"),
	CHECKBOX("checkbox"),
	COLOR("color"),
	BUTTON("button"),
	SELECT("select"),
	LOCATION_PICKER("locationPicker"),
	/**
	 * As properties are in order in a list it can be defined divisor lines to group them 
	 */
	SEPARATOR("separator");
	
	String type;
	
	PropertyInputType(String type) {
		this.type=type;
	}
}
