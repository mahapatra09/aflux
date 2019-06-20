

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright 2019 Tanmaya Mahapatra
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

package de.tum.in.aflux.model;


/**
 * Setting related to elements
 * @author Tanmaya Mahapatra
 *
 */
public class NodeElementSetting {

	/**
	 * initial width of elements
	 */
	private int width;
	private int height;
	private int leftConnectorMargin;
	private int connectorDistance;
	private int rightConnectorMargin;
	private int topConnectorMargin;
	private int bottomConnectorMargin;
	private int textHeight;

	
    
    
    private NodeElementSetting() {}
	
	public NodeElementSetting(int width, int height,
			int topConnectorMargin, int rightConnectorMargin,int bottomConnectorMargin,int leftConnectorMargin, 
			int connectorDistance,  
			int textHeight) {
		this.width=width;
		this.height=height;
		this.topConnectorMargin=topConnectorMargin;
		this.rightConnectorMargin= rightConnectorMargin;
		this.bottomConnectorMargin=bottomConnectorMargin;
		this.leftConnectorMargin=leftConnectorMargin;
		this.connectorDistance=connectorDistance;
		this.textHeight= textHeight;
	
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLeftConnectorMargin() {
		return leftConnectorMargin;
	}

	public void setLeftConnectorMargin(int leftConnectorMargin) {
		this.leftConnectorMargin = leftConnectorMargin;
	}

	public int getConnectorDistance() {
		return connectorDistance;
	}

	public void setConnectorDistance(int connectorDistance) {
		this.connectorDistance = connectorDistance;
	}

	public int getRightConnectorMargin() {
		return rightConnectorMargin;
	}

	public void setRightConnectorMargin(int rightConnectorMargin) {
		this.rightConnectorMargin = rightConnectorMargin;
	}

	public int getTextHeight() {
		return textHeight;
	}

	public void setTextHeight(int textHeight) {
		this.textHeight = textHeight;
	}

	public int getTopConnectorMargin() {
		return topConnectorMargin;
	}

	public void setTopConnectorMargin(int topConnectorMargin) {
		this.topConnectorMargin = topConnectorMargin;
	}

	public int getBottomConnectorMargin() {
		return bottomConnectorMargin;
	}

	public void setBottomConnectorMargin(int bottomConnectorMargin) {
		this.bottomConnectorMargin = bottomConnectorMargin;
	}

	
	
	
}
