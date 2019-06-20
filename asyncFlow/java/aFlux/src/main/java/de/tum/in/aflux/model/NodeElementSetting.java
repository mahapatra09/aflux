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
