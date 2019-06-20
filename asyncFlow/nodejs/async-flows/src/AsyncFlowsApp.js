import React, { Component } from 'react';
import Workspace from './containers/Workspace';
import logo from './logo.svg';
import './AsyncFlowsApp.css';
var client = require('./functions/client');





class AsyncFlowsApp extends Component {
	constructor(props) {
		super(props);
		this.state= {
			flowElements:[],
			flowConnectors:[],
			settings:{
				nodeElement:{
					width:120,
					height:28,
					topConnectorMargin:10,
					bottomConnectorMargin:10,
					leftConnectorMargin:10,
					connectorDistance:15,
					rightConnectorMargin:0,
					textHeight:12
				}
			}
		}
	}
	componentDidMount() {
		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/settings'}).
				done(response => {this.setSettings(response.entity.nodeElement)});


	  client({method: 'GET',
					mode:'no-cors',
					path: this.props.host+'/elementBaseProperties'}).
						done(response => {this.setElementBaseProperties(response.entity)});


	}
	setElementBaseProperties(basePropertiesArray) {
		this.setState({
			elementBaseProperties:basePropertiesArray
		});
	}

	setSettings(nodeSettings) {
		this.setState({
			settings: {
				nodeElement:nodeSettings
			}
		});
	}



  render() {
    return (
      <div className="AsyncFlowApp">
		<Workspace
			sideBarWidth="148"
			elementBaseProperties={this.state.elementBaseProperties}
			activityBaseProperties={this.state.activityBaseProperties}
			settings={this.state.settings}
			host={this.props.host}/>
      </div>
    );
  }
}

export default AsyncFlowsApp;
